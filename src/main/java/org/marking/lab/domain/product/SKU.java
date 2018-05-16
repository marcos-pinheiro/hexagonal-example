package org.marking.lab.domain.product;

import java.util.Objects;

public final class SKU {
	
	private 	final String value;
	
	private SKU(String value) {
		this.value = value;
	}
	
	public static final SKU of(String value) {
		Objects.requireNonNull(value);
		
		return new SKU(value);
	}
	
	public static final SKU generate() {
		return new SKU("#asdaczasd");
	}
	
	public String get() {
		return value;
	}
}
