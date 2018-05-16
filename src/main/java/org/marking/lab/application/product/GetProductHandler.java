package org.marking.lab.application.product;

import org.marking.lab.application.CommandHandler;
import org.marking.lab.application.Validator;
import org.marking.lab.application.product.commands.GetProductCommand;
import org.marking.lab.application.product.response.GetProductResponse;
import org.marking.lab.domain.product.Product;
import org.marking.lab.domain.product.ProductRepository;
import org.marking.lab.domain.product.ProductService;

public class GetProductHandler implements CommandHandler<GetProductResponse, GetProductCommand> {

	private final ProductRepository productRepository;
	private final ProductService productService;
	private final Validator validator;
	
	public GetProductHandler(ProductRepository productRepository, ProductService productService, Validator validator) {
		this.productService = productService;
		this.productRepository = productRepository;
		this.validator = validator;
	}

	@Override
	public GetProductResponse handle(GetProductCommand command) {
		validator.validate(command);
		
		Product product = productRepository.get(command.getId()).orElseThrow(() -> new RuntimeException("not found"));
		productService.applySpecialDiscount(product);
		
		return GetProductResponse.builder()
				.id(product.getId())
				.sku(product.getSku().get())
				.barcode(product.getBarcode().get())
				.description(product.getDescription())
				.amount(product.getAmount().getNumber().toString())
				.build();
	}
}
