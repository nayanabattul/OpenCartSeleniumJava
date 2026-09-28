package testCases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testbase.BaseTest;
import utilities.ConfigReader;

public class AddToWishlistFlow extends BaseTest {
	
	@Test
	public void verifyWishlistCount() throws InterruptedException {
		HomePage hp = new HomePage(getDriver());
		hp.clickMyAccount();
		hp.clickLogin();
		hp.typeEmail(ConfigReader.getValidUsername());
		hp.typePassword(ConfigReader.getValidPassword());
		hp.clickLoginSubmit();
		MyAccountPage map = new MyAccountPage(getDriver());
		map.addProductToWishlist();
		map.clickElement(map.lnkWishlist);
		int actualWwishlistCount = map.getWishlistCount();
		int expectedWishlistCount = MyAccountPage.productCount;
		
		Assert.assertEquals(actualWwishlistCount, expectedWishlistCount, "Wishlist count does not match the expected value.");
		
		map.removeAllProductsFromWishlist();
		
		
}
}
