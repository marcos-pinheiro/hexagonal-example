package org.marking.lab.domain.product;

import java.time.Month;
import java.time.MonthDay;

public class ProductService {
	
	public void applySpecialDiscount(Product product) {
		boolean discountAlreadyApplied = applyDiscountIfBlackFriday(product);
		if(!discountAlreadyApplied) {
			applyDiscountIfRandomDay(product);
		}
	}
	
	boolean applyDiscountIfBlackFriday(Product product) {
		MonthDay today = today();
		
		if(today.equals(MonthDay.of(Month.NOVEMBER, 23))) {
			product.discounting(0.35);
			return true;
		}
		else if(today.equals(MonthDay.of(Month.NOVEMBER, 24))) {
			product.discounting(0.30);
			return true;
		}
		
		return false;
	}
	
	boolean applyDiscountIfRandomDay(Product product) {
		if(today().equals(randomDate())) {
			product.discounting(0.20);
			return true;
		}
		
		return false;
	}
	
	MonthDay randomDate() {
		return MonthDay.of(5, 15);
	}
	
	MonthDay today() {
		return MonthDay.now();
	}
}
