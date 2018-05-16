package org.marking.lab.infrastructure.database.transaction;

public class TransactionConfigurationPool {
	
	private static final ThreadLocal<TransactionType> POOL;
	
	static {
		POOL = new ThreadLocal<>();		
	}
	
	static void push(TransactionType type) {
		TransactionType transactionType = POOL.get();
		if(transactionType == null) {
			POOL.set(transactionType);
		}
	}
	
	static TransactionType pop() {
		TransactionType transactionType = POOL.get();
		POOL.remove();
		return transactionType;
	}
}
