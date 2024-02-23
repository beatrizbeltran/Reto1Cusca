package reto.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ExtendReport.ExtentReportUtils;
import reto.test.BaseTest;

public class CalculoPag {

	@FindBy(xpath = "//h1[@class='sprk-b-TypeDisplayTwo sprk-b-PageTitle sprk-u-mbl']")
	WebElement title;

	@FindBy(id = "purchasePrice")
	WebElement purchasePrice;

	@FindBy(id = "downPayment")
	WebElement downPayment;

	@FindBy(id = "rate")
	WebElement rate;

	@FindBy(xpath = "//label[@id='manualTaxInputYLabel']")
	WebElement radio;

	@FindBy(css = "#calculateButton")
	WebElement calculateButton;

	@FindBy(xpath = "//p[@class='phfc-b-TypeDisplayFive phfc-u-FontWeight--bold']")
	WebElement cuota;

	public CalculoPag() {
		PageFactory.initElements(BaseTest.driver, this);
	}

	public void CalculatorPageTest(String Monto, String Prima, String intMensual) throws InterruptedException {

		title.click();
		((JavascriptExecutor) BaseTest.driver).executeScript("arguments[0].scrollIntoView(true);", purchasePrice);
		Thread.sleep(3000);
		purchasePrice.clear();
		purchasePrice.sendKeys(Monto);

		ExtentReportUtils.addStep("Ingresando monto de la hipoteca");
		Thread.sleep(3000);
		downPayment.clear();
		downPayment.sendKeys(Prima);
		ExtentReportUtils.addStep("Ingresando pago inicial de la hipoteca");
		Thread.sleep(3000);
		rate.clear();
		rate.sendKeys(intMensual);
		ExtentReportUtils.addStep("Ingresando tasa de interes mensual de la hipoteca");
		((JavascriptExecutor) BaseTest.driver).executeScript("arguments[0].scrollIntoView(true);", radio);
		Thread.sleep(3000);
		calculateButton.click();
		ExtentReportUtils.addStep("Calculando cuota mensual");
		((JavascriptExecutor) BaseTest.driver).executeScript("arguments[0].scrollIntoView(true);", cuota);
		Thread.sleep(3000);
		String cuotaMensualEnWeb = cuota.getText();
		ExtentReportUtils.addStep("Cuota calculada en sitio web: " + cuotaMensualEnWeb);
		double cuotaMensual = calcularCuotaMensual(Double.parseDouble(Monto), Double.parseDouble(Prima), Double.parseDouble(intMensual));
		ExtentReportUtils.addStep("Cuota calculada: " + "$"+Math.round(cuotaMensual* 100.0)/ 100.0);
		String result = ("$"+Math.round(cuotaMensual* 100.0)/ 100.0);
		Assert.assertEquals(cuotaMensualEnWeb==result, false, result);
		ExtentReportUtils.addStep("Cuota calculada y Cuota en sitio web son iguales: " + "Calculo Web: " + cuotaMensualEnWeb+ "Calculo de Validacións: " + result);


	}

	public double calcularCuotaMensual(double Monto, double PagoInicial, double Interes) {
		double P = PagoInicial;
		double MontoMenosPrima = Monto - P;
		double r = (Interes * 0.01) / 12;
		int n = 360;
		double C = MontoMenosPrima * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
		return C;

	}

}
