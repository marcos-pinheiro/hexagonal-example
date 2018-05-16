package org.marking.lab.domain.product;

import javax.money.MonetaryAmount;

import org.marking.lab.domain.Entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder @Getter
@EqualsAndHashCode(callSuper = true)
public class Product extends Entity<String> {
	
	private final SKU sku;
	private final Barcode barcode;
	private final String description;
	private MonetaryAmount amount;
	
	public void discounting(double percentual) {
		this.amount = getAmount().subtract(getAmount().multiply(percentual));
	}
	
	public static class ProductBuilder {
		public Product newProduct() {
			Product product = new Product(this.sku, this.barcode, this.description, this.amount);
			product.generateRandomId();
			
			return product;
		}
	}
}
