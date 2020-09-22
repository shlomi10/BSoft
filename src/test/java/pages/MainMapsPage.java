package pages;

import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;

@SuppressWarnings({ "javadoc" })
public class MainMapsPage extends BasePage {

	public MainMapsPage(WebDriver driver) {
		super(driver);
	}

	By hamburgerMenu = By.xpath("//div[@class='hamburger-box']");
	By xHamburgerMenuClose = By.xpath("//div[@class='hamburger hamburger--3dx']");
	By xHamburgerMenuOpen = By.xpath("//div[@class='hamburger hamburger--3dx is-active']");
	By b144LogoButton = By.id("logoB144");
	String currentURL;

	String expectedIcon = "url(\"https://maps.b144.co.il/images/icons/f80af126.white_eye_icon.png\")";
	By businessIconTab = By.cssSelector("span.business-in-area");

	By businessTabID = By.xpath("//a[@id='busineesInArea']");
	WebElement we;
	String colorToCompare;
	String we2;
	String selectedTabState;

	String whiteExpectedTextColorAtBusinessTab = "#ffffff";
	String hex, color;

	By categoriesTab = By.id("mapCategories");
	String whiteExpectedTextColor = "#ffffff";

	By mapsHeaderTextTopRight = By.cssSelector(".maps-header-text-h1");
	By childMapCategoryHeader = By.cssSelector("#currentMapCategory");
	String childMapCategoryText;
	String expectedMapsHeaderText = "בעלי מקצוע בתחום";
	String actualMapsHeaderText;

	By cardNumberBusinessAtTheArea = By.cssSelector(".card-number--blue");
	int actualCards, expectedCards = 15;

	String whiteExpectedNumberColorAtBusinessTab = "#fff";

	By cardBackgroundColor = By.cssSelector(".card.crdom .card-hdr");
	String expecteCardBackgroundColor = "#16254f";
	String expectedCardNumberBackgroundColor = "#1f2667";

	By arrowElement = By.cssSelector(".card-arrow");
	String whiteExpectedArrowColor = "#ffffff";

	By cardTitleElement = By.cssSelector(".medium-hdl");
	By PageTitle = By.cssSelector(".title-row");
	String expectedTextOfCard, actualTextOfpage;

	String whiteExpectedTitleColor = "#ffffff";

	public void getWebSite(String siteURL) {
		navigateToURL(siteURL);
	}

	public void changeHamburgerMenuUponOpeningToX() {
		waitForElementToBeClickable(hamburgerMenu);
		clickOnElement(hamburgerMenu);
		boolean isDisp = driver.findElement(xHamburgerMenuOpen).isDisplayed();
		Assert.assertTrue(isDisp, "X button is not dispayed");
	}

	public void changeHamburgerMenuBackToHamburgerMenu() {
		waitForElementToBeClickable(xHamburgerMenuOpen);
		clickOnElement(xHamburgerMenuOpen);
		boolean isDisp = driver.findElement(xHamburgerMenuClose).isDisplayed();
		Assert.assertTrue(isDisp, "Hamburger Menu button is not dispayed");
	}

	public void clickOnB144Link() {
		clickOnElement(b144LogoButton);
		currentURL = driver.getCurrentUrl();
		Assert.assertEquals(currentURL, "https://www.b144.co.il/", "Not the expected URL");
	}

	public void businessTabIsDefaultChoose() {
		waitForElementToBeVisible(businessTabID);
		selectedTabState = getAttributeFromElement(businessTabID, "aria-selected");
		Assert.assertEquals(selectedTabState, "true",
				"The Business tab is not selected by default");
	}

	public void testTheIconAtBusinessTab() {
		Assert.assertEquals(getIcon(businessIconTab), expectedIcon,
				"The icon is not the eye at the business tab");
	}

	public void testTextColorAtBusinessAtTheArea() {
		we = returnWebElement(businessTabID);
		colorToCompare = we.getCssValue("color");
		hex = Color.fromString(colorToCompare).asHex();
		Assert.assertEquals(hex, whiteExpectedTextColorAtBusinessTab,
				"The text at the business tab is not white");
	}

	public void testIcon() {
		we = returnWebElement(businessTabID);
		colorToCompare = we.getCssValue("color");
		hex = Color.fromString(colorToCompare).asHex();
		Assert.assertEquals(hex, whiteExpectedTextColorAtBusinessTab,
				"The text at the business tab is not white");
	}

	public void testColorOfUnselectedTab() {
		clickOnElement(categoriesTab);
		we = returnWebElement(categoriesTab);
		colorToCompare = we.getCssValue("color");
		hex = Color.fromString(colorToCompare).asHex();
		Assert.assertEquals(hex, whiteExpectedTextColor,
				"The color at the Business tab when not sselected, is not white");
	}

	public void testMapsHeaderTextTopRight() {
		actualMapsHeaderText = getTextFromElement(mapsHeaderTextTopRight);
		childMapCategoryText = getTextFromElement(childMapCategoryHeader);
		actualMapsHeaderText = actualMapsHeaderText.replace(childMapCategoryText, "").trim();
		Assert.assertEquals(actualMapsHeaderText, expectedMapsHeaderText,
				"the text at the maps Header is not right one");
	}

	public void testCardsAreNumberd() {
		clickOnElement(businessTabID);
		actualCards = countElements(cardNumberBusinessAtTheArea, expectedCards);
		Assert.assertEquals(actualCards, expectedCards, "There is no 15 cards at the page");
	}

	// to test
	public void testThatCardsHaveNumbers() {
		getNumberTextFromElements(cardNumberBusinessAtTheArea);
		for (Integer number : (getNumberTextFromElements(cardNumberBusinessAtTheArea))) {
			Assert.assertTrue((number > 0 && number < 16), "the card is without number");
		}
	}

	public void testColorOfTicketNumber() {
		we = returnWebElement(cardNumberBusinessAtTheArea);
		colorToCompare = we.getCssValue("color");
		hex = Color.fromString(colorToCompare).asHex();
		Assert.assertEquals(hex, whiteExpectedTextColorAtBusinessTab,
				"The number at the card at business tab is not white");
	}

	public void testColorOfNumberAtTicketThatNotTheSameAsBackground() {
		we = returnWebElement(cardBackgroundColor);
		colorToCompare = we.getCssValue("background-color");
		hex = Color.fromString(expecteCardBackgroundColor).asHex();
		Assert.assertNotEquals(hex, expectedCardNumberBackgroundColor,
				"The background color of the number at the card is the same as the rest of the background");
	}

	public void testClickOnNumberLeadToBusinessPage() {
		expectedTextOfCard = getTextFromElement(cardTitleElement);
		clickOnElement(cardTitleElement);
		actualTextOfpage = getTextFromElement(PageTitle);
		Assert.assertEquals(actualTextOfpage, expectedTextOfCard,
				"The text of the first card is not the same as the text of the page");
	}

	public void testThatEachCardHasTitle() {
		driver.navigate().back();
		Assert.assertFalse(getTextFromElements(cardTitleElement).isEmpty(),
				"There is cards without title on it");
	}

	// to test why the print is failed
	public void testThatEachCardTitleIsWhite() {
		Iterator<String> colorIterator = getColorListFromElements(cardTitleElement).iterator();
		while (colorIterator.hasNext()) {
			// System.out.println(colorIterator.next());
			Assert.assertEquals(whiteExpectedTitleColor, colorIterator.next());
		}
	}

}
