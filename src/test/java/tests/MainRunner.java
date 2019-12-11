package tests;

import org.testng.annotations.Test;

public class MainRunner extends BaseTest {
	
	String siteURL = "https://maps.b144.co.il/";
	
	@Test (priority = 1 ,groups= "OpenWebSite-test", description= "Open the web site test")
	public void upFooterOpenWebSite() {
		MapsPage.getWebSite(siteURL);
	}
	
	@Test (priority = 2 ,groups= "UpFooter-test" , description= "Test if Hamburger menu have the X option")
	public void upFooterXButton() {
		MapsPage.changeHamburgerMenuUponOpeningToX();
	}
	
	@Test (priority = 3 ,groups= "UpFooter-test" , description= "Test that the Hamburger menu can be closed")
	public void upFooterHamburgerButton() {
		MapsPage.changeHamburgerMenuBackToHamburgerMenu();
	}
	
	@Test (priority = 4 ,groups= "UpFooter-test" , description= "Test that the user land at the main 144 page")
	public void upFooterClickOnB144Link() {
		MapsPage.clickOnB144Link();
	}
	
	@Test (priority = 5 ,groups= "UpFooter-test" , description= "Test that the text at Drop down menu is correct")
	public void compareTextAtDropDownMenu() {
		MainPage.compareOptionsOnDropDownMenu();
	}
	
	@Test (priority = 6 ,groups= "UpFooter-test" , description= "Test that the text color at Drop down menu is correct")
	public void compareTextColorAtDropDownMenu() {
		MainPage.testColorOfButtons();
	}

	@Test (priority = 7 ,groups= "UpFooter-test" , description= "Test login proccess of private and business user")
	public void testLoginPagesforPrivateAndBusiness() {
		MainPage.testLoginPages();
	}
	
	@Test (priority = 8 ,groups= "UpFooter-test" , description= "Test the accessability menu")
	public void testAccessabilityMenu() {
		MainPage.testAccessabilityMenu();
	}
	
	@Test (priority = 9 ,groups = "Business at the area - business tab" , description= "Test that business tab is default choosen")
	public void testBusinssTabIsDefaultSelected() {
		MapsPage.businessTabIsDefaultChoose();
	}
	
	@Test (priority = 10 ,groups = "Business at the area - business tab" , description= "Test that each card have an eye icon on it")
	public void testBusinssTabIcon() {
		MapsPage.testTheIconAtBusinessTab();
	}
	
	@Test (priority = 11 ,groups = "Business at the area - business tab" , description= "Test that the text at the card is white")
	public void testBusinssTabTextColor() {
		MapsPage.testTextColorAtBusinessAtTheArea();
	}
	
	@Test (priority = 12 ,groups = "Business at the area - business tab" , description= "Test that unselected tab is white colored")
	public void testBusinssTabColorWhenUnselected() {
		MapsPage.testColorOfUnselectedTab();
	}
	
	@Test (priority = 13 ,groups = "Business at the area - business tab" , description= "Test that the header at the page is the same as the card")
	public void testMapsHeaderTextTopRight() {
		MapsPage.testMapsHeaderTextTopRight();
	}
	
	@Test (priority = 14 ,groups = "Business at the area - business card" , description= "Test the number of cards at the page")
	public void countCardsAtMapPage() {
		MapsPage.testCardsAreNumberd();
	}
	
	@Test (priority = 15 ,groups = "Business at the area - business card" , description= "Test that cards have number")
	public void testThatCardsHaveNumbers() {
		MapsPage.testThatCardsHaveNumbers();
	}
	
	@Test (priority = 16 ,groups = "Business at the area - business card" , description= "Test that the color of the ticket number is white")
	public void testColorOfTicketNumber() {
		MapsPage.testColorOfTicketNumber();
	}
	
	@Test (priority = 17 ,groups = "Business at the area - business card" , description= "Test that the background color of number at the ticket is not the same as other backgound")
	public void testColorOfNumberAtTicketThatNotTheSameAsBackground() {
		MapsPage.testColorOfNumberAtTicketThatNotTheSameAsBackground();
	}
	
	@Test (priority = 18 ,groups = "Business at the area - business card" , description= "Test that choosing number on card lead to business page")
	public void testClickOnNumberLeadToBusinessPage() {
		MapsPage.testClickOnNumberLeadToBusinessPage();
	}
	
	@Test (priority = 19 ,groups = "Business at the area - business card" , description= "Test that each card has title")
	public void testThatEachCardHasTitle() {
		MapsPage.testThatEachCardHasTitle();
	}
	
	@Test (priority = 20 ,groups = "Business at the area - business card" , description= "Test that each card text is white colored")
	public void testThatEachCardTitleIsWhite() {
		MapsPage.testThatEachCardTitleIsWhite();
	}
	
}
