package pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Shlomi
 * @category Main methods
 * @apiNote These functions are for all pages
 */
@SuppressWarnings({ "javadoc"})
public class BasePage {

	public WebDriver driver;
	public WebDriverWait wait;
	public Actions action;
	
	// constructor
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
	}

	// function to navigate to URL
	public void navigateToURL(String URL) {
		driver.navigate().to(URL);
	}
	
	// function to get the current URL
	public String getURLCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	// function to get back webElement
	public WebElement returnWebElement(By elem) {
		return driver.findElement(elem);
	}
	
	// function to click on element
	public void clickOnElement(By elem) {
		driver.findElement(elem).click();
	}
	
	// function to clear field and than type text
	public void clearAndTypeTextToElem(By elem, String text) {
		WebElement textField = driver.findElement(elem);
		textField.clear();
		textField.sendKeys(text);
	}
	
	// function to click enter
	public void clickEnterOnElem(By elem) {
		driver.findElement(elem).sendKeys(Keys.ENTER);
	}
	
	// function to click on right arrow
	public void rightArrowKeyType(By elem) {
		driver.findElement(elem).sendKeys(Keys.ARROW_RIGHT);
	}
	
	// function to click on down arrow
	public void downArrowKeyType(By elem) {
		driver.findElement(elem).sendKeys(Keys.ARROW_DOWN);
	}

	// function to wait for element to be clickable
	public void waitForElementToBeClickable(By elem) {
		wait.until(ExpectedConditions.elementToBeClickable(elem));
	}

	// function to wait for element to be visible
	public void waitForElementToBeVisable(By elem) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
	}
	
	// function to wait for element to be presented
	public void waitForElementToBePresence(By elem) {
		wait.until(ExpectedConditions.presenceOfElementLocated(elem));
	}
	
	// function to validate if element is displayed
	public boolean IsElementDisplayed(By elem) {
		return driver.findElement(elem).isDisplayed();
	}
	
	// function to get the number of elements under the DOM
	public String getNumberOfElementsFromDom() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.getElementsByClassName('auto-complete-item').length;").toString(); 
	}

	// function to get text from element
	public String getTextFromElement(By elem) {
		return driver.findElement(elem).getText();
	}

	// function to get color from element
	public String getColorFromElement(By elem) {
		return driver.findElement(elem).getCssValue("color");
	}

	// function to move courser to element
	public void mouseHooverFromElement(By elem) {
		action = new Actions(driver);
		WebElement element1 = driver.findElement(elem);
		action.moveToElement(element1).build().perform();
	}
	
	// function to move courser to element and click on him
	public void mouseHooverFromElementToElementAndClick(By elem) {
		action = new Actions(driver);
		WebElement element1 = driver.findElement(elem);
		action.moveToElement(element1).click().build().perform();
	}

	// function to get any attribute from element
	public String getAttributeFromElement(By elem, String attribute) {
		return driver.findElement(elem).getAttribute(attribute);
	}

	// function to get icon from element
	public String getIcon(By elem) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement icon = wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
		return (String) js.executeScript("return window.getComputedStyle(arguments[0], ':before').getPropertyValue('content');",icon);
	}

	// function to get number of elements
	public int countElements(By elem, int numberOfElementsToBE) {
		wait.until(ExpectedConditions.numberOfElementsToBe(elem, numberOfElementsToBE));
		List<WebElement> list = driver.findElements(elem);
		return list.size();
	}

	// function to get number text of elements
	public List<Integer> getNumberTextFromElements(By elem) {
		List<WebElement> elementList = driver.findElements(elem);
		List<Integer> intList = new ArrayList<>();
		for (WebElement element : elementList) {
			intList.add(Integer.valueOf(element.getText()));
		}
		return intList;
	}

	// function to get text of elements
	public List<String> getTextFromElements(By elem) {
		List<WebElement> elementList = driver.findElements(elem);
		List<String> stringList = new ArrayList<>();
		for (WebElement element : elementList) {
			stringList.add(element.getText());
		}
		return stringList;
	}

	// function to get color list of elements in Hex
	public List<String> getColorListFromElements(By elem) {
		List<WebElement> elementList = driver.findElements(elem);
		List<String> hexColorList = new ArrayList<>();
		for (WebElement element : elementList) {
			String colorAsHex = Color.fromString(element.getCssValue("color")).asHex();
			hexColorList.add(colorAsHex);
		}
		return hexColorList;
	}

	// function to scroll to element
	public void scrollToElement(By elemToScroll) {
		WebElement element1 = driver.findElement(elemToScroll);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
	}
	
	// function to get line element under tab
	public String getTheLineUnderTab(String selector) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String script = "return window.getComputedStyle(document.querySelector('"+selector+"'),':after').getPropertyValue('content')";
		String content = (String)js.executeScript(script);
		return content;
	}
}
