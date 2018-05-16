package org.marking.lab.domain.product.events;

import java.time.Instant;

import org.marking.lab.domain.Event;
import org.marking.lab.domain.product.Product;

public class ProductCreated implements Event<Product> {

	private final Product product;
	private final Instant createdAt;
	
	public ProductCreated(Product product) {
		this.product = product;
		this.createdAt = Instant.now();
	}

	@Override
	public Product get() {
		return product;
	}

	public Instant createdAt() {
		return createdAt;
	}
}
