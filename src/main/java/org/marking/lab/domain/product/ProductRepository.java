package org.marking.lab.domain.product;

import java.util.Optional;
import java.util.stream.Stream;

import org.marking.lab.domain.Queryable;

public interface ProductRepository {
	
	boolean contains(Product product);

	void add(Product product);

	Optional<Product> get(String id);
	
	Stream<Product> filter(Queryable queryable);
	
	long count(Queryable queryable);
}
