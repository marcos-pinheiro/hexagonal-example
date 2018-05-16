package org.marking.lab.domain;

public interface Queryable {
	
	static final Queryable EMPTY = new Queryable() {
		@Override
		public Queryable where(Query query) {
			return this;
		}
	};
	
	Queryable where(Query query);
	
	interface Query {
		Condition field(String field);		
	}
	
	interface Condition {
		
		Criteria isNull();
		
		<T> Criteria isEqual(T value);
	}
	
	interface Criteria {
		
		Query and();
		
		Criteria limit(long limit);
		
		Criteria offset(long limit);
		
		Criteria ignoreNulls();
		
		Queryable build();		
	}
	
	static Queryable empty() {
		return EMPTY;
	}
}
