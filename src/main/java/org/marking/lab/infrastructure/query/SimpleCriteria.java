package org.marking.lab.infrastructure.query;

import org.marking.lab.domain.Queryable;
import org.marking.lab.domain.Queryable.Criteria;
import org.marking.lab.domain.Queryable.Query;

public class SimpleCriteria implements Queryable.Criteria {

	private final SimpleQuery query;
	private Long limit;
	private Long offset;
	private boolean ignoreNull = true;

	public SimpleCriteria(SimpleQuery query) {
		this.query = query;
	}

	@Override
	public Query and() {
		return query;
	}

	@Override
	public Criteria limit(long limit) {
		return this;
	}

	@Override
	public Criteria offset(long limit) {
		return this;
	}

	@Override
	public Queryable build() {
		return new SimpleQueryable(query.getFilters(), limit, offset);
	}

	@Override
	public Criteria ignoreNulls() {
		this.ignoreNull = true;
		return this;
	}
}