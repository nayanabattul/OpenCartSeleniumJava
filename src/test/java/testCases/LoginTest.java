package testCases;

import org.jboss.aerogear.security.otp.Totp;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LandingPage;

import pageObjects.MyAccountPage;

import testbase.BaseTest;

public class LoginTest extends BaseTest{

	@Test
	public void verify_login() throws InterruptedException {
		
		try {
			//Thread.sleep(5000);
			logger.info("*********Starting Test Case*************");
			HomePage hp = new HomePage(getDriver());
			hp.clickMyAccount();
			hp.clickLogin();
			logger.info("*********Clicked on Login*************");
			hp.typeEmail(p.getProperty("email"));
			logger.info("*********Entered Email*************");
			hp.typePassword(p.getProperty("password"));
			logger.info("*********Entered Password*************");
			hp.clickLoginSubmit();
			logger.info("*********Clicked on Login Submit*************");
			
			MyAccountPage map = new MyAccountPage(getDriver());
			String actualHeading = map.getHeading();
			
			String expectedHeading = "My Account";
			
			if(actualHeading.equals(expectedHeading)) {
				logger.info("*********Login Test Passed*************");
				Assert.assertTrue(true);
			} else {
				logger.error("*********Login Test Failed*************");
				Assert.fail();
			}
			
			
		} catch (Exception e) {
			
			Assert.fail();
		}
		
		logger.info("*********Finished Test Case*************");
	}
	
	
	
}
