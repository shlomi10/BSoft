package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.B144MainPage;
import pages.MainMapsPage;
import pages.ZipMainPage;
import utilities.ExtentListener;

@SuppressWarnings("javadoc")
public class BaseTest {

	WebDriver driver;
	MainMapsPage mapsPage;
	B144MainPage mainPage;
	ExtentListener extentListener;
	ZipMainPage zipMainPage;
	
	@Parameters({ "browser" })
	@BeforeTest(alwaysRun = true)
	public void setup(String browser) {
		WebDriverManager.chromedriver().setup();
		try {
			WebDriverManager.chromedriver().setup();
			if (browser.equalsIgnoreCase("Edge")) {
				driver = new EdgeDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("IE")) {
				driver = new InternetExplorerDriver();
			}
		}catch (Exception e) {
			System.out.println("You enter wrong browser");
		}
		
		//System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
		// maximize the browser window
		driver.manage().window().maximize();

		mapsPage = new MainMapsPage(driver);
		mainPage = new B144MainPage(driver);
		zipMainPage = new ZipMainPage(driver);
	}

	@AfterTest(alwaysRun = true)
	public void close() {
		driver.quit();
	}
}
