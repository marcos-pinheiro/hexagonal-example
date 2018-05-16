package org.marking.lab.infrastructure.config;

import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;

import org.marking.lab.resources.ProductResource;
import org.marking.lab.resources.Resource;

import spark.servlet.SparkApplication;

public class SparkConfiguration extends ConfigurationInitializer implements SparkApplication {

	@Override
	public void init() {
		port(8080);
		
		get("/ping", (req, resp) -> "pong");
		
		path("/products", () -> {
			get("",  resolve(ProductResource.class)::list);
			post("", resolve(ProductResource.class)::create);
			get("/:id", resolve(ProductResource.class)::get);
			put("/:id", null);
			put("/:id/stock", null);
		});
	}
	
	<T extends Resource> T resolve(Class<T> clazz) {
		return null;
	}
}
