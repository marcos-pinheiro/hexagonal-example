package org.marking.lab.domain.stock;

import java.time.LocalDateTime;

import org.marking.lab.domain.Entity;
import org.marking.lab.domain.product.Product;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor @Getter
@EqualsAndHashCode(callSuper = true)
public class Stock extends Entity<String> {
	
	private static final int INITIAL_STOCK = 5;
	
	private Product product;
	private int quantity;
	private LocalDateTime lastTransaction;
	private LocalDateTime lastEntry;
	
	public static final Stock initialStock(Product product) {
		return new Stock(product, INITIAL_STOCK, LocalDateTime.now(), LocalDateTime.now());
	}

	public String getBarcode() {
		this.checkProduct();
		
		return product.getBarcode().get();
	}
	
	public String getSKU() {
		this.checkProduct();
		
		return product.getSku().get();
	}
	
	private final void checkProduct() {
		if(product == null)
			throw new IllegalStateException("Product Cannot be null");
	}
}
