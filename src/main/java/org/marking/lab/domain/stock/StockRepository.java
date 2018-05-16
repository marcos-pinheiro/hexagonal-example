package org.marking.lab.domain.stock;

import java.util.Optional;

public interface StockRepository {
	
	void add(Stock stock);
	
	Optional<Stock> get(String id);
	
}
