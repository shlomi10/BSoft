package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


@SuppressWarnings({ "javadoc" })
public class ZipMainPage extends BasePage {

	public ZipMainPage(WebDriver driver) {
		super(driver);
	}
	
	By hamburgerMenu = By.cssSelector(".hamburger-box");
	By xHamburgerMenuOpen = By.cssSelector(".hamburger--3dx.is-active");
	boolean isDisp;
	By b144LogoButton = By.id("logoB144");
	By enterToBusinessSubscribers = By.cssSelector(".login.hidden-xs");
	String expectedURLBusinessSite = "https://members.b144.co.il/Infrastructure/B144_Login/Login_New.aspx?p=1205";
	
	By accessabilityMenu = By.id("access");
	By accessabilityMenuState = By.id("INDmenu");
	By closeButtonAccessabilityButton = By.xpath("//div[@class='INDmenuHeader']/button[1]");
	By accessabilityMenuCloseState = By.xpath("//div[@id='INDmenu' and @aria-hidden='true']");
	String state, state1;
	
	By joinBusinessBotton = By.id("joinBusiness");
	By businessRegistrationTitleElem = By.cssSelector("h1.page-title");
	String expectedTextAtRegistrationPage = "רישום עצמי למדריך העסקים B144";
	
	By footerLinks = By.cssSelector(".footer-links-list");
	int expectedXLocationOfRegisterAsBusiness = 0;
	int expectedYLocationOfRegisterAsBusiness = 93;
	
	By searchBusinessElem = By.cssSelector(".bezekDrop.business");
	String searchBusinessElemTab = ".bezekDrop.business";
	By businessSuitCaseIconElem = By.cssSelector(".inputRow.visible [id='suitcaseImg']");
	By searchPeopleElem = By.id("people");
	String searchPeopleElemTab = "#people";
	By peopleIconElem = By.cssSelector(".inputRow.visible [id='peopleImage']");
	By searchZipElem = By.id("zipcode");
	String searchZipElemTab = "#zipcode";
	By zipIconElem = By.cssSelector(".inputRow.visible .place-icon");
	String expectedLineUnderTab = "\"\"";

	public void getWebSite(String siteURL) {
		navigateToURL(siteURL);
	}
	
	public void changeHamburgerMenuUponOpeningToX() {
		waitForElementToBeClickable(hamburgerMenu);
		clickOnElement(hamburgerMenu);
		isDisp = getBooleanIfElementIsDisplayed(xHamburgerMenuOpen);
		Assert.assertTrue(isDisp, "X button is not dispayed");
	}
	
	public void clickOnB144Link() {
		clickOnElement(b144LogoButton);
		Assert.assertEquals(getURLCurrentUrl(), "https://www.b144.co.il/", "Not the expected URL for zip main page");
	}
	
	public void getToBusinessArea(String URL) {
		navigateToURL(URL);
		waitForElementToBeClickable(enterToBusinessSubscribers);
		clickOnElement(enterToBusinessSubscribers);
		assertEquals(getURLCurrentUrl(), expectedURLBusinessSite, "Not the expected URL for business site");
	}

	public void accessabilityMenu() {
		waitForElementToBeClickable(accessabilityMenu);
		clickOnElement(accessabilityMenu);
		state = getAttributeFromElement(accessabilityMenuState, "aria-hidden");
		assertEquals(state, "false", "The Accessability menu is close instead of open state");
		waitForElementToBeClickable(closeButtonAccessabilityButton);
		clickOnElement(closeButtonAccessabilityButton);
		state1 = getAttributeFromElement(accessabilityMenuState, "aria-hidden");
		assertEquals(state1, "true", "The Accessability menu is open instead of close state");
		driver.navigate().back();
	}
	
	public void registerAsBusiness() {
		waitForElementToBeClickable(joinBusinessBotton);
		clickOnElement(joinBusinessBotton);
		assertEquals(getTextFromElement(businessRegistrationTitleElem), expectedTextAtRegistrationPage);
		driver.navigate().back();
		waitForElementToBeVisable(footerLinks);
		scrollToElement(footerLinks);
		scrollToElement(joinBusinessBotton);
		System.out.println("location is: "+returnWebElement(joinBusinessBotton).getLocation());
		assertEquals(returnWebElement(joinBusinessBotton).getLocation().getX(), expectedXLocationOfRegisterAsBusiness);
		assertEquals(returnWebElement(joinBusinessBotton).getLocation().getY(), expectedYLocationOfRegisterAsBusiness);
	}
	
	public void serachFieldLine() {
		clickOnElement(searchBusinessElem);
		assertTrue(returnWebElement(businessSuitCaseIconElem).isDisplayed(), "the suitcase in search field of business is not displayed");
		System.out.println("business: "+getTheLineUnderTab(searchBusinessElemTab));
		assertEquals(getTheLineUnderTab(searchBusinessElemTab), expectedLineUnderTab, "no underscore under search business tab");
		clickOnElement(searchPeopleElem);
		assertTrue(returnWebElement(peopleIconElem).isDisplayed(), "the suitcase in search field of business is not displayed");
		System.out.println("people: "+getTheLineUnderTab(searchPeopleElemTab));
		assertEquals(getTheLineUnderTab(searchPeopleElemTab), expectedLineUnderTab, "no underscore under search people tab");

		clickOnElement(searchZipElem);
		assertTrue(returnWebElement(zipIconElem).isDisplayed(), "the suitcase in search field of business is not displayed");
		System.out.println("zip: "+getTheLineUnderTab(searchZipElemTab));
		assertEquals(getTheLineUnderTab(searchZipElemTab), expectedLineUnderTab, "no underscore under search zip tab");

	}
	
}
