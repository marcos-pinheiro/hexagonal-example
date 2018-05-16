package org.marking.lab.resources;

import java.util.Collection;

import javax.inject.Inject;

import org.marking.lab.application.CommandBus;
import org.marking.lab.application.product.commands.CreateProductCommand;
import org.marking.lab.application.product.commands.GetProductCommand;
import org.marking.lab.application.product.commands.ListProductCommand;
import org.marking.lab.application.product.response.GetProductResponse;
import org.marking.lab.application.product.response.ListProductResponse;

import spark.Request;
import spark.Response;

public class ProductResource extends Resource {
	
	private final CommandBus commandBus;
	
	@Inject
	public ProductResource(CommandBus commandBus) {
		this.commandBus = commandBus;
	}
	
	public String create(Request request, Response response) {
		String id = commandBus.execute(new CreateProductCommand());
		return id;
	}
	
	public Collection<GetProductResponse> list(Request request, Response response) {
		ListProductResponse resp = commandBus.execute(new ListProductCommand());
		
		super.paginateResponse(response, resp.getTotal(), resp.getLimit(), resp.getOffset());
		return resp.getProducts();
	}
	
	public GetProductResponse get(Request request, Response response) {
		GetProductResponse product = commandBus.execute(new GetProductCommand());
		return product;
	}
}
