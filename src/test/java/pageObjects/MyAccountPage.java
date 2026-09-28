package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import testbase.BaseTest;
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
//		((JavascriptExecutor) BaseTest.getDriver())
//        .executeScript("arguments[0].scrollIntoView({block: 'center'});", ele);
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
	
	@FindBy(xpath="//a[normalize-space()='shopping cart']")
	public WebElement lnkShoppingCart;
	
	
	@FindBy(xpath="//div[contains(text(),'Products marked with *** are not available in the desired quantity or not in stock!')]")
	public WebElement txtOutOfStockMessage;
	
	@FindBy(xpath="//p[contains(text(),'$122.00')]")
	public WebElement txtProductPrice;
	
	@FindBy(xpath="(//table[@class='table table-bordered']//tbody/tr/td[last()])[last()]")
	public WebElement txtCartProductPrice;
	
	
	public String getProductPrice(WebElement priceElement) {
		String price = (String) ((JavascriptExecutor) driver).executeScript(
			    "return arguments[0].childNodes[0].textContent.trim();",
			    priceElement
			);
		return price;
	}
	
	@FindBy(xpath="//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']")
	public WebElement btnCartDropdown;
	
	@FindBy(xpath="//input[starts-with(@name,'quantity')]")
	public WebElement txtCartQuantity;
	
	
	public void setCartQuantity(WebElement ele, String quantity) {
		
		CustomWaits.waitForTheElement(ele);
		ele.clear();
		ele.sendKeys(quantity);
	}
	
	@FindBy(xpath="//button[@data-original-title='Update']")
	public WebElement btnUpdateCart;
	
	

	
	

}
