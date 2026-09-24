package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import utilities.CustomWaits;

public class MyAccountPage extends BasePage{
	
	Actions actions = new Actions(driver);
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement txtHeadingMyAccount;
	
	public String getHeading() {
		
		CustomWaits.waitForTheElement(txtHeadingMyAccount);
		return txtHeadingMyAccount.getText();
	}
	
	
	@FindBy(xpath="//a[text()='Desktops']")
	public WebElement lnkDesktops;
	
	public void hoverElement(WebElement ele) {
		CustomWaits.waitForTheElement(ele);
		actions.moveToElement(ele).perform();
	}
	
	@FindBy(xpath="//a[text()='Mac (1)']")
	public WebElement lnkMacDesktops;

	public void clickElement(WebElement ele) {
		CustomWaits.waitForTheElementToBeClickable(ele);
		ele.click();
		
	}
	
	
	@FindBy(xpath="//span[normalize-space()='Add to Cart']")
	public WebElement btnAddToCart;
	
	
	@FindBy(xpath="//div[contains(text(),'Success: You have added')]")
	public WebElement txtSuccessMessage;
	
	public String getMessage(WebElement ele) {
		CustomWaits.waitForTheElement(ele);
		return ele.getText();
	}
	
	@FindBy(xpath="//a[contains(text(),'shopping cart')]")
	public WebElement lnkShoppingCart;
	
	
	@FindBy(xpath="//div[contains(text(),'Products marked with *** are not available in the desired quantity or not in stock!')]")
	public WebElement txtOutOfStockMessage;
	
	

}
