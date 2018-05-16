package org.marking.lab.application.product.commands;

import javax.validation.constraints.NotEmpty;

import org.marking.lab.application.Command;
import org.marking.lab.application.product.response.GetProductResponse;

import lombok.Data;

@Data
public class GetProductCommand implements Command<GetProductResponse> {
	private @NotEmpty String id;
	private @NotEmpty String requestedBy;
}
