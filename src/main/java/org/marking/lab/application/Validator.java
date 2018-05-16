package org.marking.lab.application;

public interface Validator {
	
	<T> void validate(T object);
	
}
