package org.marking.lab.infrastructure.database.transaction;

public class SimpleTransactionManager implements TransactionManager<ConnectionContext> {
	
	private final ThreadLocal<ConnectionContext> pool;
	
	public SimpleTransactionManager() {
		this.pool = new ThreadLocal<>();
	}
	
	ConnectionContext registerNew() {
		ConnectionContext connectionContext = create();
		pool.set(connectionContext);
		
		return connectionContext;
	}
	
	@Override
	public ConnectionContext create() {
		return new ConnectionContext();
	}
	
	@Override
	public ConnectionContext current() {
		if(registered())
			return pool.get();
		
		return registerNew();
	}
	
	@Override
	public void release() {
		pool.remove();
	}
	
	@Override
	public boolean registered() {
		return pool.get() == null;
	}

	@Override
	public ConnectionContext get(String id) {
		return current();
	}
}
