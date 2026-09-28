package pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import utilities.CustomWaits;

public class MyAccountPage extends BasePage{
	public static int productCount;
	
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
	
	
	@FindBy(xpath="//ul[@class='nav navbar-nav']/li/a")
	public List<WebElement> menuItems;
	
	By menuItemsLocator = By.xpath("//ul[@class='nav navbar-nav']/li/a");
	
	@FindBy(xpath="//a[text()='Components']/following-sibling::div/div/ul[@class='list-unstyled']/li")
	public List<WebElement> desktopsMenuItems;
	
	
	public List<WebElement> getListOfSubProducts(String productName) {
	String xpath = "//a[text()='" + productName + "']/following-sibling::div/div/ul[@class='list-unstyled']/li/a";
	return driver.findElements(By.xpath(xpath));
	}
	
	@FindBy(xpath="//button[@data-original-title='Add to Wish List']")
	public List<WebElement> btnAddToWishlist;
	
	//@FindBy(xpath="//a[@data-original-title='Remove']")
	By btnRemoveFromWishlistLocator = By.xpath("//a[@data-original-title='Remove']");
	
	@FindBy(xpath="//a[@id='wishlist-total']")
	public WebElement lnkWishlist;
			
	
	public void addProductToWishlist() throws InterruptedException{
		productCount = 0;
		List<String> menuNames = new ArrayList<>();

		for (WebElement menuItem : driver.findElements(menuItemsLocator)) {
		    menuNames.add(menuItem.getText());
		}
		
		for(String menu : menuNames) {
		List<WebElement> menuItems = driver.findElements(menuItemsLocator);

	    WebElement menuItem = menuItems.stream()
	            .filter(item -> item.getText().equals(menu))
	            .findFirst()
	            .orElseThrow();
			actions.moveToElement(menuItem).perform();
			//String menu = menuItem.getText();
			//System.out.println(menu);
			if(getListOfSubProducts(menu).size() > 0) {
				//System.out.println(getListOfSubProducts(menu).size());
				Pattern pattern = Pattern.compile("\\((\\d+)\\)");
				 for (WebElement option : getListOfSubProducts(menu)) {
			            String text = option.getText();
			            Matcher matcher = pattern.matcher(text);

			            if (matcher.find()) {
			                int number = Integer.parseInt(matcher.group(1));
			                if (number > 0) {
			                    option.click(); // Click the matching option
			                    //System.out.println("Successfully selected: " + text);
			                    for(WebElement wishlistButton : btnAddToWishlist) {
			                    	CustomWaits.waitForTheElement(wishlistButton);
			                    	wishlistButton.click();
			                    //	System.out.println("Product added to wishlist successfully");
			                    	productCount++;
			                    	Thread.sleep(1000); // Wait for 2 seconds to allow the UI to update
			                    }
			                    break;
			                }
			            }
			        }
			}else {
				menuItem.click();
				 for(WebElement wishlistButton : btnAddToWishlist) {
                 	CustomWaits.waitForTheElement(wishlistButton);
                 	wishlistButton.click();
                 	//System.out.println("Product added to wishlist successfully");
                 	productCount++;
                 	Thread.sleep(1000); // Wait for 2 seconds to allow the UI to update
                 }
			}
		}

	}
	
	public int getWishlistCount() {
	    String wishlistText = lnkWishlist.getText(); // e.g., "Wish List (3)"
	    Pattern pattern = Pattern.compile("\\((\\d+)\\)");
	    Matcher matcher = pattern.matcher(wishlistText);
	    if (matcher.find()) {
	        return Integer.parseInt(matcher.group(1));
	    }
	    return 0; // Return 0 if no count is found
	}
	
	public void removeAllProductsFromWishlist() {
	    List<WebElement> removeButtons = driver.findElements(btnRemoveFromWishlistLocator);
	    
	    while (!removeButtons.isEmpty()) {
	        // Always target the first element in the freshly located list
	        WebElement removeButton = removeButtons.get(0);
	        
	        CustomWaits.waitForTheElement(removeButton);
	        removeButton.click();
	        //System.out.println("Product removed from wishlist successfully");


	        // Fetch the updated list from the DOM
	        removeButtons = driver.findElements(btnRemoveFromWishlistLocator);
	    }
	}

}
