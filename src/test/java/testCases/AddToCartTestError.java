package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testbase.BaseTest;
import utilities.ConfigReader;
import utilities.ScrollToElement;

public class AddToCartTestError extends BaseTest {
	
	@Test
	public void verifyErrorMessageOfOutOfStock() {
	HomePage hp = new HomePage(getDriver());
	hp.clickMyAccount();
	hp.clickLogin();
	hp.typeEmail(ConfigReader.getValidUsername());
	hp.typePassword(ConfigReader.getValidPassword());
	hp.clickLoginSubmit();
	MyAccountPage map = new MyAccountPage(getDriver());
	String actualHeading = map.getHeading();
	
	String expectedHeading = "My Account";
	
	 Assert.assertEquals(actualHeading, expectedHeading,
                "Login was not successful");
	
	 
	 map.hoverElement(map.lnkDesktops);
	 
	 map.clickElement(map.lnkMacDesktops);
	 
	 ScrollToElement scroll = new ScrollToElement();
	 scroll.scrollToElement(map.btnAddToCart);
	 map.clickElement(map.btnAddToCart);
	 String actualMessage = map.getMessage(map.txtSuccessMessage);
	 
	 String expectedMessage = "Success: You have added iMac to your shopping cart!";
	 
	 Assert.assertTrue(actualMessage.contains(expectedMessage),
             "Success message is not displayed as expected");
	 
	 map.clickElement(map.lnkShoppingCart);
	 
	 String actualErrorMessage = map.getMessage(map.txtOutOfStockMessage);
	 String expectedErrorMessage = "Products marked with *** are not available in the desired quantity or not in stock!";
	 
	 
	 Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
             "Error message is not displayed as expected");
	}
}
