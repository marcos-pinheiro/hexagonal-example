package org.marking.lab.infrastructure.repository.sql;

import java.util.Optional;

import org.marking.lab.domain.stock.Stock;
import org.marking.lab.domain.stock.StockRepository;

public class MySQLStockRepository implements StockRepository {

	@Override
	public void add(Stock stock) {
		
	}

	@Override
	public Optional<Stock> get(String id) {
		return null;
	}
}
