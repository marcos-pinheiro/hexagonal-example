package org.marking.lab.application.product;

import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.marking.lab.application.Validator;
import org.marking.lab.domain.product.Product;
import org.marking.lab.domain.product.ProductRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CreateProductHandlerTest {
	
	@InjectMocks  
	@Spy CreateProductHandler handler;
	@Mock ProductRepository productRepository;
	@Mock Validator validator;
	
	@Test
	public void testCheckIfNotExistsProductForCreate() {
		Product product = Product.builder().newProduct();
		given(productRepository.contains(product))
			.willReturn(false);
		
		handler.checkIfExists(product);
	}
	
	@Test(expected = RuntimeException.class)
	public void testCheckIfExistsProductForCreate() {
		Product product = Product.builder().newProduct();
		given(productRepository.contains(product))
			.willReturn(true);
		
		handler.checkIfExists(product);
	}
}
