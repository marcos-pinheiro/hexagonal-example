package org.marking.lab.application.product.commands;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

import org.marking.lab.application.Command;

import lombok.Data;

@Data
public class CreateProductCommand implements Command<String> {
	private @NotEmpty String sku;
	private @NotEmpty String description;
	private @NotEmpty String barcode;
	private @NotEmpty BigDecimal amount;
}
