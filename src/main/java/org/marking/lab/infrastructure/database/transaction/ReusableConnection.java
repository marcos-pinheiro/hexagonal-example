package org.marking.lab.infrastructure.database.transaction;

import java.sql.Connection;

public class ReusableConnection implements AutoCloseable {

	private Connection connection;
	private volatile int counter = 0;
	
	void increaseCounter() {
		counter = counter + 1;
	}
	
	void decreaseCounter() {
		counter = counter - 1;
	}
	
	boolean canBeClosed() {
		return counter <= 1;
	}
	
	public Connection connection() {
		this.increaseCounter();
		return connection;
	}
	
	@Override
	public void close() throws Exception {
		if(canBeClosed())
			connection.close();
		else
			decreaseCounter();
	}
}
