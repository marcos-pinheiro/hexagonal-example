package org.marking.lab.resources;

import spark.Response;

public abstract class Resource {
	
	
	protected void paginateResponse(Response response, long total, long limit, long offset) {
		response.header("Total-Count", String.valueOf(total));
		response.header("Response-Limit", String.valueOf(limit));
		response.header("Response-Offset", String.valueOf(offset));
	}
}
