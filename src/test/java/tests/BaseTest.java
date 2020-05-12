package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.B144MainPage;
import pages.MainMapsPage;
import utilities.ExtentListener;

@SuppressWarnings("javadoc")
public class BaseTest {

	WebDriver driver;
	MainMapsPage mapsPage;
	B144MainPage mainPage;
	ExtentListener extentListener;
	
	@BeforeTest(alwaysRun = true)
	public void setup() {
		WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		// maximize the browser window
		driver.manage().window().maximize();

		mapsPage = new MainMapsPage(driver);
		mainPage = new B144MainPage(driver);
	}

	@AfterTest(alwaysRun = true)
	public void close() {
		driver.quit();
	}
}
