package testCases;

import org.jboss.aerogear.security.otp.Totp;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LandingPage;

import pageObjects.MyAccountPage;

import testbase.BaseTest;
import utilities.ConfigReader;

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
			//hp.typeEmail(p.getProperty("email"));
			hp.typeEmail(ConfigReader.getValidUsername());
			logger.info("*********Entered Email*************");
			//hp.typePassword(p.getProperty("password"));
			hp.typePassword(ConfigReader.getValidPassword());
			logger.info("*********Entered Password*************");
			hp.clickLoginSubmit();
			logger.info("*********Clicked on Login Submit*************");
			
			MyAccountPage map = new MyAccountPage(getDriver());
			
			String actualHeading = map.getHeading();
			
			String expectedHeading = "My Account";
			
			 Assert.assertEquals(actualHeading, expectedHeading,
		                "Login was not successful");

		        logger.info("*********Login Test Passed*********");

			
			
		} catch (Exception e) {
			
			 logger.error("Login test failed: " + e.getMessage(), e);
			    Assert.fail("Login test failed: " + e.getMessage());
		}
		
		logger.info("*********Finished Test Case*************");
	}
	
	
	
}
