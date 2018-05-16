package org.marking.lab.infrastructure.query;

import java.util.Arrays;
import java.util.Deque;

import org.marking.lab.domain.Queryable;
import org.marking.lab.domain.Queryable.Criteria;

public class SimpleCondition implements Queryable.Condition {

	private final Deque<FieldValueOperator<?>> filters;
	private final String field;
	private final Criteria criteria;

	public SimpleCondition(String field, Deque<FieldValueOperator<?>> filters, Criteria criteria) {
		this.field = field;
		this.filters = filters;
		this.criteria = criteria;
	}

	@Override
	public Criteria isNull() {
		filters.offer(new FieldValueOperator<>(Operator.EQ, field, null));
		return criteria;
	}

	@Override
	public <T> Criteria isEqual(T value) {
		filters.offer(new FieldValueOperator<>(Operator.EQ, field, Arrays.asList(value)));
		return criteria;
	}
}