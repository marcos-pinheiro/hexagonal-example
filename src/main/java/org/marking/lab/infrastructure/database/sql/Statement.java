package org.marking.lab.infrastructure.database.sql;

public interface Statement {
	
	void run(RunnableStatement statement);
	
	<R> R run(CallableStatement<R> statement);
	
	void transact(RunnableStatement statement);
	
	<R> R transact(CallableStatement<R> statement);
}
