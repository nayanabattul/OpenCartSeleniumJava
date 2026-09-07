package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.CustomWaits;

public class MyAccountPage extends BasePage{
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement txtHeadingMyAccount;
	
	public String getHeading() {
		
		CustomWaits.waitForTheElement(txtHeadingMyAccount);
		return txtHeadingMyAccount.getText();
	}

}
