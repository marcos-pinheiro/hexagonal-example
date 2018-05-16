package org.marking.lab.domain;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class Entity<T> {
	
	private T id;
	
	@SuppressWarnings("unchecked")
	protected void generateRandomId() {
		this.id = (T) UUID.randomUUID().toString();
	}
}
