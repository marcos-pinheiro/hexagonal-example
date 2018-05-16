package org.marking.lab.infrastructure.database.sql;

@FunctionalInterface
public interface CallableStatement<R> {
	
	R execute();
	
}
