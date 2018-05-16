package org.marking.lab.application.product;

import javax.inject.Inject;

import org.marking.lab.application.CommandHandler;
import org.marking.lab.application.Validator;
import org.marking.lab.application.product.commands.GetStockInformationCommand;
import org.marking.lab.application.product.response.GetStockInformationResponse;
import org.marking.lab.domain.stock.Stock;
import org.marking.lab.domain.stock.StockRepository;

public class GetStockInformationHandler implements CommandHandler<GetStockInformationResponse, GetStockInformationCommand> {

	private final StockRepository stockRepository;
	private final Validator validator;

	@Inject
	public GetStockInformationHandler(StockRepository stockRepository, Validator validator) {
		this.stockRepository = stockRepository;
		this.validator = validator;
	}
	
	@Override
	public GetStockInformationResponse handle(GetStockInformationCommand command) {
		validator.validate(command);
		
		Stock stock = stockRepository.get(command.getProductId()).orElseThrow(() -> null);
		
		return GetStockInformationResponse.builder()
				.stockId(stock.getId())
				.productId(stock.getProduct().getId())
				.quantity(stock.getQuantity())
				.sku(stock.getSKU())
				.barcode(stock.getBarcode())
				.lastEntry(stock.getLastEntry())
				.build();
	}
}
