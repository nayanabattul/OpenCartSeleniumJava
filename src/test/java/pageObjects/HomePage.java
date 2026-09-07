package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//a[@title='My Account']")
	WebElement btnMyAccount;
	
	public void clickMyAccount() {
		btnMyAccount.click();
	}
	
	
	@FindBy(xpath="//a[text()='Login']")
	WebElement btnLogin;
	
	public void clickLogin() {
		btnLogin.click();
	}

	@FindBy(id="input-email")
	WebElement txtEmail;
	
	public void typeEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	@FindBy(id="input-password")
	WebElement txtPassword;
	
	public void typePassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLoginSubmit;
	
	public void clickLoginSubmit() {
		btnLoginSubmit.click();
	}
	
}
