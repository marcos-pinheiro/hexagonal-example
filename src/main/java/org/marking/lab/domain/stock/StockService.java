package org.marking.lab.domain.stock;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.marking.lab.domain.product.Product;
import org.marking.lab.domain.product.events.ProductCreated;

public class StockService {
	
	private final StockRepository stockRepository;
	
	@Inject
	public StockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}
	
	
	public void registerInitialQuantityToProduct(Product product) {
		stockRepository.add(Stock.initialStock(product));
	}
	
	void onProductCreated(@Observes ProductCreated event) {
		this.registerInitialQuantityToProduct(event.get());
	}
}
