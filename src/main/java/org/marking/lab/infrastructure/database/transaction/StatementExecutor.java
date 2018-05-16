package org.marking.lab.infrastructure.database.transaction;

import org.marking.lab.infrastructure.database.sql.CallableStatement;
import org.marking.lab.infrastructure.database.sql.RunnableStatement;
import org.marking.lab.infrastructure.database.sql.Statement;

public class StatementExecutor implements Statement {

	private final Statement statement;
	
	public StatementExecutor(Statement statement) {
		this.statement = statement;
	}
	
	
	@Override
	public void run(RunnableStatement command) {
		
		TransactionType transactionType = TransactionConfigurationPool.pop();
		if(transactionType == null)
		
		this.statement.run(command);
	}

	@Override
	public <R> R run(CallableStatement<R> command) {
		return this.statement.run(command);
	}

	@Override
	public void transact(RunnableStatement command) {
		this.statement.transact(command);
	}

	@Override
	public <R> R transact(CallableStatement<R> command) {
		return this.statement.transact(command);
	}

}
