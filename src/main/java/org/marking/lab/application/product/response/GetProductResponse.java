package org.marking.lab.application.product.response;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class GetProductResponse {
	private String id;
	private String sku;
	private String barcode;
	private String description;
	private String amount;
}
