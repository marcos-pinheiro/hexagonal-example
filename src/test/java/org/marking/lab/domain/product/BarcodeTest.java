package org.marking.lab.domain.product;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BarcodeTest {
	
	@Test
	public void testGetAValidBarcode() {
		Barcode barcode = Barcode
				.of("1234567890ABC");
		
		Assert.assertTrue("1234567890ABC".equals(barcode.get()));
	}
	
	@Test(expected = RuntimeException.class)
	public void testGetAInvalidBarcode() {
		Barcode.of("1234567890").get();
	}
}
