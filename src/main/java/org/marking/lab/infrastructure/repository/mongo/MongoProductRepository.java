package org.marking.lab.infrastructure.repository.mongo;

import java.util.Optional;
import java.util.stream.Stream;

import org.marking.lab.domain.Queryable;
import org.marking.lab.domain.product.Product;
import org.marking.lab.domain.product.ProductRepository;

public class MongoProductRepository implements ProductRepository {

	@Override
	public boolean contains(Product product) {
		return false;
	}

	@Override
	public void add(Product product) {
		
	}

	@Override
	public Optional<Product> get(String id) {
		return null;
	}

	@Override
	public Stream<Product> filter(Queryable queryable) {
		return null;
	}

	@Override
	public long count(Queryable queryable) {
		// TODO Auto-generated method stub
		return 0;
	}
}
