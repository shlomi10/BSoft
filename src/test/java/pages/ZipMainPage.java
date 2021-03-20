package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * @author Shlomi
 * @category Zip page
 * @apiNote These functions are to test the zip page
 */
@SuppressWarnings("javadoc")
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
	
	By BusinessTabElem = By.xpath("//*[contains(@class,'bezekDrop business')]");
	String searchBusinessElemTab = ".bezekDrop.business";
	By businessSuitCaseIconElem = By.cssSelector(".inputRow.visible [id='suitcaseImg']");
	By searchPeopleElem = By.id("people");
	String searchPeopleElemTab = "#people";
	By peopleIconElem = By.cssSelector(".inputRow.visible [id='peopleImage']");
	By searchZipElem = By.id("zipcode");
	String searchZipElemTab = "#zipcode";
	By zipIconElem = By.cssSelector(".inputRow.visible .place-icon");
	String expectedLineUnderTab = "\"\"";
	
	By searchFieldBusiness = By.id("txtCat");
	String FirstThreeLettersToSearch = "הובל";
	By suggestedOffers = By.cssSelector(".auto-complete-item");
	String numberOfExpectedSuggestions = "5";
	int actualnumberOfSuggestions;
	String cityToSearchOn = "תל אביב יפו";
	By citySearchField = By.id("txtCity");
	By searchButtonElement = By.cssSelector("div[id='b_BusinessSearch']");
	By titleResultPage = By.id("titleHdr");
	String expectedLandingUrlAfterSearch = "";
	String expectedReultInTitle = FirstThreeLettersToSearch +" "+ cityToSearchOn;
	String textThatWasSearched;
	String cityThatWasSearched;
	
	
	// navigate to the zip page
	public ZipMainPage getWebSite(String siteURL) {
		navigateToURL(siteURL);
		return this;
	}
	
	// open the hamburger menu and validate that X to close is there
	public ZipMainPage changeHamburgerMenuUponOpeningToX() {
		waitForElementToBeClickable(hamburgerMenu);
		clickOnElement(hamburgerMenu);
		isDisp = IsElementDisplayed(xHamburgerMenuOpen);
		assertTrue(isDisp, "X button is not dispayed");
		return this;
	}
	
	// validate the landing page when select B144 Link
	public ZipMainPage clickOnB144Link() {
		clickOnElement(b144LogoButton);
		assertEquals(getURLCurrentUrl(), "https://www.b144.co.il/", "Not the expected URL for zip main page");
		return this;
	}
	
	// navigate to business area 
	public ZipMainPage getToBusinessArea(String URL) {
		navigateToURL(URL);
		waitForElementToBeClickable(enterToBusinessSubscribers);
		clickOnElement(enterToBusinessSubscribers);
		waitForElementToBeClickable(accessabilityMenu);
		assertEquals(getURLCurrentUrl(), expectedURLBusinessSite, "Not the expected URL for business site");
		return this;
	}

	// validate the presence of accessibility menu
	public ZipMainPage accessabilityMenu() {
		waitForElementToBeClickable(accessabilityMenu);
		clickOnElement(accessabilityMenu);
		state = getAttributeFromElement(accessabilityMenuState, "aria-hidden");
		assertEquals(state, "false", "The Accessability menu is close instead of open state");
		waitForElementToBeClickable(closeButtonAccessabilityButton);
		clickOnElement(closeButtonAccessabilityButton);
		state1 = getAttributeFromElement(accessabilityMenuState, "aria-hidden");
		assertEquals(state1, "true", "The Accessability menu is open instead of close state");
		driver.navigate().back();
		return this;
	}
	
	// test the business registration 
	public ZipMainPage registerAsBusiness() {
		waitForElementToBeClickable(joinBusinessBotton);
		clickOnElement(joinBusinessBotton);
		assertEquals(getTextFromElement(businessRegistrationTitleElem), expectedTextAtRegistrationPage);
		driver.navigate().back();
		waitForElementToBeVisible(footerLinks);
		scrollToElement(footerLinks);
		scrollToElement(joinBusinessBotton);
		//System.out.println("location is: "+returnWebElement(joinBusinessBotton).getLocation());
		assertEquals(returnWebElement(joinBusinessBotton).getLocation().getX(), expectedXLocationOfRegisterAsBusiness);
		assertEquals(returnWebElement(joinBusinessBotton).getLocation().getY(), expectedYLocationOfRegisterAsBusiness);
		return this;
	}
	
	// test the search for business field under zip
	public ZipMainPage serachFieldLine() {
		clickOnElement(BusinessTabElem);
		assertTrue(returnWebElement(businessSuitCaseIconElem).isDisplayed(), "the suitcase in search field of business is not displayed");
		//System.out.println("business: "+getTheLineUnderTab(searchBusinessElemTab));
		assertEquals(getTheLineUnderTab(searchBusinessElemTab), expectedLineUnderTab, "no underscore under search business tab");
		clickOnElement(searchPeopleElem);
		assertTrue(returnWebElement(peopleIconElem).isDisplayed(), "the suitcase in search field of business is not displayed");
		//System.out.println("people: "+getTheLineUnderTab(searchPeopleElemTab));
		assertEquals(getTheLineUnderTab(searchPeopleElemTab), expectedLineUnderTab, "no underscore under search people tab");

		clickOnElement(searchZipElem);
		assertTrue(returnWebElement(zipIconElem).isDisplayed(), "the suitcase in search field of business is not displayed");
		//System.out.println("zip: "+getTheLineUnderTab(searchZipElemTab));
		assertEquals(getTheLineUnderTab(searchZipElemTab), expectedLineUnderTab, "no underscore under search zip tab");
		return this;
	}
	
	// test the business suggestions
	public ZipMainPage performSearchAndGetSuggestedOffers() {
		clickOnElement(BusinessTabElem);
		clearAndTypeTextToElem(searchFieldBusiness, FirstThreeLettersToSearch);
		mouseHooverFromElementToElementAndClick(searchFieldBusiness);
		mouseHooverFromElementToElementAndClick(searchFieldBusiness);
		spaceBarKeyType(searchFieldBusiness);
		waitForElementToBePresented(suggestedOffers);
		assertEquals(getNumberOfElementsFromDom(), numberOfExpectedSuggestions, "the suggested search option is not as expected");
		downArrowKeyType(searchFieldBusiness);
		clickEnterOnElem(searchFieldBusiness);
		textThatWasSearched = getAttributeFromElement(searchFieldBusiness, "value");
		System.out.println("text of what searched : "+ textThatWasSearched);
		return this;
	}
	
	// test the city suggestions and perform search
	public ZipMainPage selectCityToSearchFor() {
		clearAndTypeTextToElem(citySearchField, cityToSearchOn);
		cityThatWasSearched = getAttributeFromElement(citySearchField, "value");
		System.out.println("city of what searched : " + cityThatWasSearched);
		downArrowKeyType(citySearchField);
		clickEnterOnElem(citySearchField);
		waitForElementToBePresented(titleResultPage);
		// clickOnElement(searchButtonElement);
		String result = getTextFromElement(titleResultPage);
		System.out.println("the search matcher is : " + result);
		Pattern p = Pattern.compile("^" +textThatWasSearched+ " ב*" +cityThatWasSearched + "$");
		Matcher m = p.matcher(result);
		
		boolean isTextWasFounded = m.find();
		if (isTextWasFounded) 
			System.out.println("the landing page result is: " + m.group(0));
		
		System.out.println("the value of matcher is "+isTextWasFounded);
		assertTrue(isTextWasFounded, "you didn't landed at the results page");
		return this;
	}
}