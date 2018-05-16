package org.marking.lab.application.product.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ListProductResponse {
	private long total;
	private int limit;
	private int offset;
	private List<GetProductResponse> products;
}
