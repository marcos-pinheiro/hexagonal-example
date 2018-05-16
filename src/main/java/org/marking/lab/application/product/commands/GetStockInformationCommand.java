package org.marking.lab.application.product.commands;

import org.marking.lab.application.Command;
import org.marking.lab.application.product.response.GetStockInformationResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class GetStockInformationCommand implements Command<GetStockInformationResponse> {
	
	private String productId;
	private String requestedBy;
	
}
