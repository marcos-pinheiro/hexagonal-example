package org.marking.lab.application.product;

import static org.mockito.BDDMockito.given;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.marking.lab.application.Validator;
import org.marking.lab.domain.product.ProductRepository;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ListProductHandlerTest {
	
	@InjectMocks
	@Spy ListProductHandler handler;
	@Mock Validator validator;
	@Mock ProductRepository productRepository;
	
	@Test
	public void testCountProductsAsync() throws InterruptedException, ExecutionException {
		given(productRepository.count(ArgumentMatchers.any()))
			.willReturn(10l);
		
		CompletableFuture<Long> future = handler.countProductsAsync(p -> null);
		Assert.assertTrue(10l == future.get());
	}
	
	@Test
	public void testCountProductsAsyncTimeout() throws InterruptedException, ExecutionException {
		given(productRepository.count(ArgumentMatchers.any()))
			.willAnswer(ctx -> {
				Thread.sleep(4000);
				return 666l;
			});
		
		CompletableFuture<Long> future = handler.countProductsAsync(p -> null);
		Assert.assertTrue(0l == future.get());
	}
	
	@Test(expected = ExecutionException.class)
	public void testFindProductsAsyncTimeout() throws InterruptedException, ExecutionException {
		given(productRepository.filter(ArgumentMatchers.any()))
			.willAnswer(ctx -> {
				Thread.sleep(6000);
				return 666l;
			});
		
		handler.findProductsAsync(null, p -> null).get();
	}
	
	@Test(timeout = 3)
	public void testFindProductsAsyncTimeoutOf3s() throws InterruptedException, ExecutionException {
		given(productRepository.filter(ArgumentMatchers.any()))
			.willAnswer(ctx -> {
				Thread.sleep(6000);
				return 666l;
			});
		
		handler.findProductsAsync(null, p -> null).get();
	}
}
