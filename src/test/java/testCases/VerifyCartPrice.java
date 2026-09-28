package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testbase.BaseTest;
import utilities.ConfigReader;

public class VerifyCartPrice extends BaseTest {
	
	@Test
	public void verifyCartPrice() throws InterruptedException {
		HomePage hp = new HomePage(getDriver());
		hp.clickMyAccount();
		hp.clickLogin();
		hp.typeEmail(ConfigReader.getValidUsername());
		hp.typePassword(ConfigReader.getValidPassword());
		hp.clickLoginSubmit();
		MyAccountPage map = new MyAccountPage(getDriver());
		map.hoverElement(map.lnkDesktops);
		map.clickElement(map.lnkMacDesktops);
		map.clickElement(map.btnAddToCart);
		String productPrice = map.getProductPrice(map.txtProductPrice);
		
		map.clickElement(map.lnkShoppingCart);
		
		
		
		map.setCartQuantity(map.txtCartQuantity, "1");
		map.clickElement(map.btnUpdateCart);
		Thread.sleep(5000);
		String cartPrice = map.getMessage(map.txtCartProductPrice);
		Assert.assertEquals(cartPrice, productPrice, "Cart price does not match the product price");
	}

}
