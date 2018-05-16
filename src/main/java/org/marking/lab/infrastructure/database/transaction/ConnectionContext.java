package org.marking.lab.infrastructure.database.transaction;

public class ConnectionContext {
	
	private TransactionContextStatus status;
	private ReusableConnection connection;
	//private List<IsolatedContext> isolatedContexts;

	public ConnectionContext() {
		this.status = TransactionContextStatus.PENDING;
	}

	public boolean isPending() {
		return this.status == TransactionContextStatus.PENDING;
	}

	public void initialize(ReusableConnection connection) {
		this.connection = connection;
		this.status = TransactionContextStatus.RUNNING;
	}
	
	public ReusableConnection get() {
		return connection;
	}
}
