package reto.test;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ExtendReport.ExtentReportUtils;

public class BaseTest {

	public static WebDriver driver;

	@BeforeSuite
	public void initDriver() {
		ExtentReportUtils.setUpReport();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.rocketmortgage.com/calculators/mortgage-calculator?qlsource=RMTextLink");
	}

	@AfterSuite
	public void quitDriver() {
		driver.quit();
	}

}
