package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends BasePage{

	public LandingPage(WebDriver driver) {
		super(driver);
	}
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	@FindBy(xpath="//div[text()='Reviewer Productivity by Procedure Category ']")
	WebElement txtHeadingLandingPage;
	
	public String getHeading() {
		wait.until(ExpectedConditions.visibilityOf(txtHeadingLandingPage));
		return txtHeadingLandingPage.getText();
		
	}
	

}
