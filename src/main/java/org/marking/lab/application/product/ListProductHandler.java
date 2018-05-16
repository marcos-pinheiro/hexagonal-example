package org.marking.lab.application.product;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.marking.lab.application.CommandHandler;
import org.marking.lab.application.Validator;
import org.marking.lab.application.product.commands.ListProductCommand;
import org.marking.lab.application.product.response.GetProductResponse;
import org.marking.lab.application.product.response.ListProductResponse;
import org.marking.lab.domain.Queryable;
import org.marking.lab.domain.product.Product;
import org.marking.lab.domain.product.ProductRepository;

public class ListProductHandler implements CommandHandler<ListProductResponse, ListProductCommand> {
	
	private final ProductRepository productRepository;
	private final Validator validator;
	
	public ListProductHandler(ProductRepository productRepository, Validator validator) {
		this.productRepository = productRepository;
		this.validator =  validator;
	}
	
	@Override
	public ListProductResponse handle(ListProductCommand command) {
		validator.validate(command);
		
		Function<Queryable.Query, Queryable.Criteria> queryCriteria = query -> query
			.field("sku").isEqual(command.getSku()).and()
			.field("barcode").isEqual(command.getBarcode())
			.ignoreNulls();
		
		CompletableFuture<Stream<Product>> productsToBeFound = findProductsAsync(command, queryCriteria);
		CompletableFuture<Long> productsToBeCounted = countProductsAsync(queryCriteria);
		
		try {
			Stream<Product> products = productsToBeFound.get();
			Long total = productsToBeCounted.get();
			
			return ListProductResponse.builder()
					.limit(command.getLimit())
					.offset(command.getOffset())
					.total(total)
					.products(products.map(this::product).collect(Collectors.toList()))
					.build();
		}
		catch (InterruptedException | ExecutionException e) {
			if(e.getCause() instanceof TimeoutException)
				throw new RuntimeException("500 = Servidor demorou muito para responder", e.getCause());
			
			throw new RuntimeException("500 = Erro interno", e.getCause());
		}
	}

	CompletableFuture<Long> countProductsAsync(Function<Queryable.Query, Queryable.Criteria> queryCriteria) {
		return CompletableFuture.supplyAsync(() -> 
					productRepository.count(query -> queryCriteria.apply(query).build()))
				.completeOnTimeout(0l, 3l, TimeUnit.SECONDS);
	}

	CompletableFuture<Stream<Product>> findProductsAsync(ListProductCommand command, Function<Queryable.Query, Queryable.Criteria> queryCriteria) {
		return CompletableFuture.supplyAsync(() -> productRepository.filter(query -> queryCriteria.apply(query)
					.limit(command.getLimit())
					.offset(command.getOffset())
					.build()))
				.orTimeout(3l, TimeUnit.SECONDS);
	}

	private GetProductResponse product(Product product) {
		return GetProductResponse.builder()
				.id(product.getId())
				.sku(product.getSku().get())
				.barcode(product.getBarcode().get())
				.amount(product.getAmount().getNumber().toString())
				.description(product.getDescription())
				.build();
	}
}
