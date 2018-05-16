package org.marking.lab.application.product;

import javax.inject.Inject;
import javax.money.Monetary;

import org.javamoney.moneta.Money;
import org.marking.lab.application.CommandHandler;
import org.marking.lab.application.Validator;
import org.marking.lab.application.product.commands.CreateProductCommand;
import org.marking.lab.domain.DomainEvents;
import org.marking.lab.domain.product.Barcode;
import org.marking.lab.domain.product.Product;
import org.marking.lab.domain.product.ProductRepository;
import org.marking.lab.domain.product.SKU;
import org.marking.lab.domain.product.events.ProductCreated;
import org.marking.lab.infrastructure.database.transaction.Transactional;

public class CreateProductHandler implements CommandHandler<String, CreateProductCommand> {

	private final ProductRepository repository;
	private final Validator validator;
	
	@Inject
	public CreateProductHandler(ProductRepository repository, Validator validator) {
		this.repository = repository;
		this.validator  = validator;
	}
	
	@Override @Transactional
	public String handle(CreateProductCommand command) {
		validator.validate(command);
		
		Product product = Product.builder()
			.description(command.getDescription())
			.sku(SKU.generate())
			.barcode(Barcode.of(command.getBarcode()))
			.amount(Money.of(command.getAmount(), Monetary.getCurrency("BRL")))
			.newProduct();
				
		checkIfExists(product);
		repository.add(product);
		
		DomainEvents
			.publish(new ProductCreated(product));
		
		return product.getId();
	}
	
	void checkIfExists(Product product) {
		if(repository.contains(product)) {
			throw new RuntimeException(""); //change exception
		}
	}
}
