package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.B144MainPage;
import pages.MainMapsPage;
import utilities.ExtentListener;

public class BaseTest extends ExtentListener {
	
	 WebDriver driver;
	 MainMapsPage MapsPage;
	 B144MainPage MainPage;
	 
	
	@BeforeTest(alwaysRun = true)
	 public void setup() {
		  System.setProperty("webdriver.chrome.driver" , ".\\drivers\\chromedriver.exe");
		  driver= new ChromeDriver();
		// maximize the browser window
		  driver.manage().window().maximize();
		    
		  MapsPage = new MainMapsPage(driver);
		  MainPage = new B144MainPage(driver);
	}
	
	@AfterTest(alwaysRun = true)
	public void close() {
		driver.quit();
	}	
}