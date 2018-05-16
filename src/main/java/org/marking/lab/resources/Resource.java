package org.marking.lab.resources;

import spark.Response;

public abstract class Resource {
	
	
	protected void paginateResponse(Response response, long total, long limit, long offset) {
		response.header("X-Total-Count", String.valueOf(total));
		response.header("X-Response-Limit", String.valueOf(limit));
		response.header("X-Response-Offset", String.valueOf(offset));
	}
}
