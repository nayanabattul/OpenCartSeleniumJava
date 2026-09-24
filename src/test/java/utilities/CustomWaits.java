package utilities;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testbase.BaseTest;

public class CustomWaits {
	
	
	public static WebElement waitForTheElement(WebElement ele) {
	
	WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(30));
	
	return wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public static WebElement waitForTheElementToBeClickable(WebElement ele) {
		
		WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(30));
		
		return wait.until(ExpectedConditions.elementToBeClickable(ele));
		}	
	
	

}
