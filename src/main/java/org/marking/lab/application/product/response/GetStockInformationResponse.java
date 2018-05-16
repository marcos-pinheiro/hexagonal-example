package org.marking.lab.application.product.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class GetStockInformationResponse {
	
	private String stockId;
	private String productId;
	private String sku;
	private String barcode;
	private int quantity;
	private LocalDateTime lastEntry;
	
}
