package pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@SuppressWarnings({ "javadoc", "static-method" , "hiding"})
public class BasePage {

	public WebDriver driver;
	public WebDriverWait wait;
	public Actions action;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
	}

	public void clickOnElement(By elem) {
		driver.findElement(elem).click();
	}

	public void waitForElementToBeClickable(By elem) {
		wait.until(ExpectedConditions.elementToBeClickable(elem));
	}

	public void waitForElementToBeVisable(By elem) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
	}

	public String getTextFromElement(By elem) {
		return driver.findElement(elem).getText();
	}

	public String getColorFromElement(By elem) {
		return driver.findElement(elem).getCssValue("color");
	}

	public void mouseHooverFromElement(By elem) {
		action = new Actions(driver);
		WebElement element1 = driver.findElement(elem);
		action.moveToElement(element1).build().perform();
	}

	public String getURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getAttributeFromElement(By elem, String attribute) {
		return driver.findElement(elem).getAttribute(attribute);
	}

	public String getIcon(By elem) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement icon = wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
		return (String) js.executeScript(
				"return window.getComputedStyle(arguments[0], ':before').getPropertyValue('content');",
				icon);
	}

	public WebElement returnWebElement(By elem) {
		return driver.findElement(elem);
	}

	public int countElements(By elem, int numberOfElementsToBE) {
		wait.until(ExpectedConditions.numberOfElementsToBe(elem, numberOfElementsToBE));
		List<WebElement> list = driver.findElements(elem);
		return list.size();
	}

	public List<Integer> getNumberTextFromElements(By elem) {

		List<WebElement> elementList = driver.findElements(elem);
		List<Integer> intList = new ArrayList<>();

		for (WebElement element : elementList) {
			intList.add(Integer.valueOf(element.getText()));
		}
		return intList;
	}

	public List<String> getTextFromElements(By elem) {

		List<WebElement> elementList = driver.findElements(elem);
		List<String> stringList = new ArrayList<>();

		for (WebElement element : elementList) {
			stringList.add(element.getText());
		}
		return stringList;
	}

	public List<String> getColorListFromElements(By elem) {

		List<WebElement> elementList = driver.findElements(elem);
		List<String> hexColorList = new ArrayList<>();

		for (WebElement element : elementList) {
			String colorAsHex = Color.fromString(element.getCssValue("color")).asHex();
			hexColorList.add(colorAsHex);
		}
		return hexColorList;
	}

}
