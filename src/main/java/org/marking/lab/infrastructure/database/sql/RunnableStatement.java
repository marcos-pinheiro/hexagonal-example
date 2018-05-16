package org.marking.lab.infrastructure.database.sql;

@FunctionalInterface
public interface RunnableStatement {
	
	void execute();
	
}
