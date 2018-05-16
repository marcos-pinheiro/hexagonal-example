package org.marking.lab.domain.product;

import java.util.Objects;

public final class Barcode {
	
	private 	final String value;
	
	private Barcode(String value) {
		this.value = value;
	}
	
	public static final Barcode of(String value) {
		Objects.requireNonNull(value);
		
		return new Barcode(value);
	}
	
	public String get() {
		this.validate();
		
		return value;
	}
	
	public void validate() {
		if(this.value.length() != 13) {
			throw new RuntimeException();
		}
	}
	
	@Override
	public String toString() {
		return get();
	}
}
