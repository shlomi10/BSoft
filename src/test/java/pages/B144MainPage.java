package pages;

import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
/**
 * @author Shlomi
 * @category B144 main page
 * @apiNote These functions are to test the B144 main page
 */
@SuppressWarnings({ "javadoc" })
public class B144MainPage extends BasePage {

	// constructor
	public B144MainPage(WebDriver driver) {
		super(driver);
	}

	By enterToAccountButton = By.xpath("//span[@id='FBNameSideMenu']");
	By menuText = By.xpath("//div[@id='account-menu']");
	List<String> expectedListTextDropDown = new ArrayList<>();
	List<String> actualListTextDropDown = new ArrayList<>();
	By firstLineElement = By.id("topMenuLogin");
	By secondLineElement = By.cssSelector("div[onclick^='ShowLoader();']");

	By privateLoginPageTitleElement = By.xpath("//div[@id='loginTitle']");
	By businessLoginPageTitleElement = By.xpath("//div[@class='dv-connect-header']");
	String expectedPrivateLoginPageTitle = "מתחברים לאיזור האישי";
	String expectedPrivateURL = "https://www.b144.co.il/b144SocialUserLogin.aspx";
	String expectedBusinessLoginPageTitle = "כניסת מנויים";
	String expectedBusinessURL = "https://members.b144.co.il/Infrastructure/B144_Login/Login_New.aspx?p=1205";

	String[] tempList;
	String firstLineAtDropDown = "כניסה לאיזור האישי";
	String secondLineAtDropDown = "כניסה למנויים עסקיים";
	String menuTextFromMenu;
	String firstLineColorAfterHover, SecondLineColorAfterHover;
	String expectedBlueColorOfText = "#0279cd";
	String hexLineOne, hexLineTwo;

	By accessabilityMenu = By.id("access");
	By accessabilityMenuState = By.xpath("//div[@id='INDmenu']");
	By closeButtonAccessabilityButton = By.xpath("//div[@class='INDmenuHeader']/button[1]");
	By accessabilityMenuCloseState = By.xpath("//div[@id='INDmenu' and @aria-hidden='true']");
	String state, state1;
	
	// test the drop down menu
	public void compareOptionsOnDropDownMenu() {
		expectedListTextDropDown.add(firstLineAtDropDown);
		expectedListTextDropDown.add(secondLineAtDropDown);
		waitForElementToBeVisible(enterToAccountButton);
		clickOnElement(enterToAccountButton);
		tempList = getTextFromElement(menuText).split("[\\r\\n]+");
		for (String temp : tempList) {
			actualListTextDropDown.add(temp);
		}
		assertEquals(actualListTextDropDown, expectedListTextDropDown,
				"The text isn't as expected at the Drop down menu");
	}

	// test the color of buttons
	public void testColorOfButtons() {
		mouseHooverFromElement(firstLineElement);
		firstLineColorAfterHover = getColorFromElement(firstLineElement);
		mouseHooverFromElement(secondLineElement);
		SecondLineColorAfterHover = getColorFromElement(secondLineElement);
		hexLineOne = Color.fromString(firstLineColorAfterHover).asHex();
		Assert.assertEquals(hexLineOne, expectedBlueColorOfText, "The first line at the DropDown menu is not Blue");
		hexLineTwo = Color.fromString(SecondLineColorAfterHover).asHex();
		Assert.assertEquals(hexLineTwo, expectedBlueColorOfText,
				"The second line at the DropDown menu is not Blue");
		System.out.println("First line color in RGBA after hoover: " + firstLineColorAfterHover
				+ " in Hexa: " + hexLineOne);
		System.out.println("Second line color in RGBA after hoover: " + SecondLineColorAfterHover
				+ " in Hexa: " + hexLineTwo);
	}

	// test the login process
	public void testLoginPages() {
		waitForElementToBeClickable(firstLineElement);
		clickOnElement(firstLineElement);
		Assert.assertEquals(getTextFromElement(privateLoginPageTitleElement),
				expectedPrivateLoginPageTitle, "The title at private customer page is not right");
		Assert.assertEquals(getURLCurrentUrl(), expectedPrivateURL,
				"The URL at private customer page is not right");
		clickOnElement(enterToAccountButton);
		clickOnElement(secondLineElement);
		Assert.assertEquals(getTextFromElement(businessLoginPageTitleElement),
				expectedBusinessLoginPageTitle, "The title at business customer page is not right");
		Assert.assertEquals(getURLCurrentUrl(), expectedBusinessURL,
				"The URL at business customer page is not right");
	}

	// test the accessability menu
	public void testAccessabilityMenu() {
		waitForElementToBeClickable(accessabilityMenu);
		clickOnElement(accessabilityMenu);
		state = getAttributeFromElement(accessabilityMenuState, "aria-hidden");
		Assert.assertEquals(state, "false",
				"The Accessability menu is close instead of open state");
		waitForElementToBeClickable(closeButtonAccessabilityButton);
		clickOnElement(closeButtonAccessabilityButton);
		waitForElementToBeVisible(accessabilityMenuState);
		state1 = getAttributeFromElement(accessabilityMenuState, "aria-hidden");
		Assert.assertEquals(state1, "true",
				"The Accessability menu is open instead of close state");
	}
}
