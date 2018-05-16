package org.marking.lab.application.product.commands;

import org.marking.lab.application.Command;
import org.marking.lab.application.product.response.ListProductResponse;

import lombok.Data;

@Data
public class ListProductCommand implements Command<ListProductResponse> {
	private int limit = 20;
	private int offset = 0;
	private String sku;
	private String barcode;
}
