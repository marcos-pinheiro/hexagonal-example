package org.marking.lab.infrastructure.database.transaction;

public interface TransactionManager<T> {
	
	T create();
	
	T current();
	
	T get(String id);
	
	boolean registered();
	
	void release();
}
