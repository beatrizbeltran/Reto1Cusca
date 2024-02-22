package reto.test;

import org.testng.annotations.Test;

import reto.pages.CalculoPag;

public class CalculoTesting extends BaseTest{
	
	
	@Test
	public void CalculoCuota1() throws InterruptedException {
		CalculoPag CalculoPag = new CalculoPag();
		
		CalculoPag.CalculatorPageTest("250000", "50000", "4.0");
	}
	

}
