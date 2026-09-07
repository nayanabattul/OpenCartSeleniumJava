package testbase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
	
	//public WebDriver driver;
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public Logger logger;
	public Properties p;
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String br) throws InterruptedException, IOException {
		
		//loading config.properties file
		
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		switch(br.toLowerCase()) {
		case "chrome": 
			driver.set(new ChromeDriver()); 
			break;
			
		case "firefox": 
			driver.set(new FirefoxDriver()); 
			break;
			
		case "edge": 
			driver.set(new EdgeDriver()); 
			break;
			
		default : 
			throw new IllegalArgumentException(
                    "Invalid browser name: " + br
            );
		}
		
		
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(600));
		Thread.sleep(10000);
		getDriver().get(p.getProperty("appURL2"));
		getDriver().manage().window().maximize();
		Thread.sleep(10000);
	}
	
	
	@AfterClass
	public void tearDown() {
		if (getDriver() != null) {

            getDriver().quit();

            // Remove ThreadLocal reference
            driver.remove();
        }
	}

}
