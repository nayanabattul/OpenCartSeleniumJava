package utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import testbase.BaseTest;

public class ScrollToElement {
	
	Actions actions = new Actions(BaseTest.getDriver());
	
	public void scrollToElement(WebElement ele) {
		actions.moveToElement(ele).perform();
		//actions.scrollToElement(ele).perform();
	}

}
