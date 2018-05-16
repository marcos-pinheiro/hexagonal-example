package org.marking.lab.infrastructure.query;

import java.util.Deque;

import org.marking.lab.domain.Queryable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor @Getter
public class SimpleQueryable implements Queryable {
	
	private final Deque<FieldValueOperator<?>> filters;
	private final Long offset;
	private final Long limit;
	
	@Override
	public Queryable where(Query query) {
		return this;
	}
}
