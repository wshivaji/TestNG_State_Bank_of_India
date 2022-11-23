package SBIN_Sanity_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SBIN_Sanity_Test_Cases {

	WebDriver driver;
	ExtentReports report;
	ExtentSparkReporter spark;
	int TestCaseNumber = 0;
	@BeforeClass
	public void beforeSanity() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		report = new ExtentReports();
		spark = new ExtentSparkReporter("terget/SBIN_Sanity_Test_Report.html");
		spark.config().setDocumentTitle("SBIN Sanity Test Report");
		spark.config().setReportName("SBIN Sanity Report");
		spark.config().setTheme(Theme.DARK);
		spark.config().setEncoding("utf-8");
		report.attachReporter(spark);
		
		
	}
	
	@Test
	public void testSanity() {
		driver.get("https://sbi.co.in/");
		String title = driver.getTitle();
		System.out.println("SBIN WEb Portal Title: "+title);
		ExtentTest Test_1 = report.createTest("Check logo Test-Case: 0"+(TestCaseNumber+1));
		Assert.assertEquals(title, "SBI - Loans, Accounts, Cards, Investment, Deposits, Net Banking - Personal Banking");
		boolean checkLogo = driver.findElement(By.id("logo")).isDisplayed();
		Assert.assertTrue(checkLogo);
		if(checkLogo) {
			Test_1.log(Status.INFO, "Logo displayes successfully: pass");
			report.flush();
		}
		else {
			Test_1.log(Status.INFO, "Logo not displayed: fail");
			report.flush();
		}
		
	}
	
	@AfterClass
	public void afterSanity() {
		driver.close();
	}
}
