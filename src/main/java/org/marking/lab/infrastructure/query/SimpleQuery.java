package org.marking.lab.infrastructure.query;

import java.util.ArrayDeque;
import java.util.Deque;

import org.marking.lab.domain.Queryable;
import org.marking.lab.domain.Queryable.Condition;
import org.marking.lab.domain.Queryable.Criteria;

public class SimpleQuery implements Queryable.Query {

	private final Deque<FieldValueOperator<?>> filters;
	private final Criteria criteria;

	public SimpleQuery() {
		this.filters = new ArrayDeque<>();
		this.criteria = new SimpleCriteria(this);
	}

	@Override
	public Condition field(String field) {
		return new SimpleCondition(field, this.filters, this.criteria);
	}
	
	public Deque<FieldValueOperator<?>> getFilters() {
		return filters;
	}
}