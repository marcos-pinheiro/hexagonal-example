package org.marking.lab.infrastructure.query;

import java.util.Collection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor @Getter
public class FieldValueOperator<T> {
	
	private final Operator operator;
	private final String field;
	private final Collection<T> values;
	
}
