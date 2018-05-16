package org.marking.lab.domain.product;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.time.Month;
import java.time.MonthDay;

import javax.money.Monetary;

import org.javamoney.moneta.Money;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class ProductServiceTest {
	
	private ProductService service;
	
	@Before
	public void before() {
		service = spy(new ProductService());
	}
	
	@Test
	public void testApplyDiscountIfBlackFriday() {
		
		Product product = Product.builder()
				.amount(Money.of(55, Monetary.getCurrency("BRL")))
				.build();
		
		given(service.today())
			.willReturn(MonthDay.of(Month.NOVEMBER, 23));
		
		service.applyDiscountIfBlackFriday(product);
		
		Assert.assertTrue(product.getAmount().isEqualTo(Money.of(35.75, Monetary.getCurrency("BRL"))));
	}
	
	@Test
	public void testApplyDiscountIfSaturdayOfBlackFriday() {
		
		Product product = Product.builder()
				.amount(Money.of(55, Monetary.getCurrency("BRL")))
				.build();
		
		given(service.today())
			.willReturn(MonthDay.of(Month.NOVEMBER, 24));
		
		service.applyDiscountIfBlackFriday(product);
		
		Assert.assertTrue(product.getAmount().isEqualTo(Money.of(38.5, Monetary.getCurrency("BRL"))));
	}
	
	@Test
	public void testApplyDiscountIfSundayOfBlackFriday() {
		
		Product product = Product.builder()
				.amount(Money.of(55, Monetary.getCurrency("BRL")))
				.build();
		
		given(service.today())
			.willReturn(MonthDay.of(Month.NOVEMBER, 24));
		
		service.applyDiscountIfBlackFriday(product);
		
		Assert.assertTrue(product.getAmount().isEqualTo(Money.of(38.5, Monetary.getCurrency("BRL"))));
	}
	
	@Test
	public void testApplyDiscountIfNotBlackFriday() {
		
		Product product = Product.builder()
				.amount(Money.of(55, Monetary.getCurrency("BRL")))
				.build();
		
		given(service.today())
			.willReturn(MonthDay.of(Month.APRIL, 1));
		
		service.applyDiscountIfBlackFriday(product);
		
		Assert.assertTrue(product.getAmount().isEqualTo(Money.of(55, Monetary.getCurrency("BRL"))));
	}
	

	@Test
	public void testApplyDiscountIfRandomDay() {
		
		Product product = Product.builder()
				.amount(Money.of(99.9, Monetary.getCurrency("BRL")))
				.build();
		
		given(service.randomDate())
			.willReturn(MonthDay.now());
		
		service.applyDiscountIfRandomDay(product);
		
		Assert.assertTrue(product.getAmount().isEqualTo(Money.of(79.92, Monetary.getCurrency("BRL"))));
	}
	
	@Test
	public void testApplyDiscountIfNotRandomDay() {
		
		Product product = Product.builder()
				.amount(Money.of(99.9, Monetary.getCurrency("BRL")))
				.build();
		
		given(service.randomDate())
			.willReturn(MonthDay.of(Month.APRIL, 1));
		
		service.applyDiscountIfRandomDay(product);
		
		Assert.assertTrue(product.getAmount().isEqualTo(Money.of(99.9, Monetary.getCurrency("BRL"))));
	}
	
	@Test
	public void testIfApplySpecialDiscountApplyOneDiscount1() {
		
		Product product = Product.builder()
				.amount(Money.of(99.9, Monetary.getCurrency("BRL")))
				.build();
		
		given(service.applyDiscountIfBlackFriday(product))
			.willReturn(true);
		
		service.applySpecialDiscount(product);
		verify(service, Mockito.never()).applyDiscountIfRandomDay(product);
	}
	
	@Test
	public void testIfApplySpecialDiscountApplyOneDiscount2() {
		
		Product product = Product.builder()
				.amount(Money.of(99.9, Monetary.getCurrency("BRL")))
				.build();
		
		given(service.applyDiscountIfBlackFriday(product))
			.willReturn(false);
		
		service.applySpecialDiscount(product);
		verify(service, Mockito.times(1)).applyDiscountIfRandomDay(product);
	}
}
