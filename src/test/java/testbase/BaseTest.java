package testbase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
	
	//public WebDriver driver;
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public Logger logger;
	public Properties p;
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	@BeforeMethod
	@Parameters("browser")
	public void setup(String br) throws InterruptedException, IOException {
		
		//loading config.properties file
		
//		FileReader file = new FileReader("./src//test//resources//config.properties");
//		p=new Properties();
//		p.load(file);
		
		p = new Properties();
		
		System.out.println("Resource URL: " +
		        getClass().getClassLoader().getResource("config.properties"));

	    try (InputStream input = getClass()
	            .getClassLoader()
	            .getResourceAsStream("config.properties")) {

	        if (input == null) {
	            throw new FileNotFoundException(
	                "config.properties not found in src/test/resources"
	            );
	        }

	        p.load(input);
	    }
		
		logger = LogManager.getLogger(this.getClass());
		switch(br.toLowerCase()) {
		case "chrome": 
			ChromeOptions options = new ChromeOptions(); // Check whether the test is running in CI 
			String ci = System.getenv("CI"); 
			if (ci != null && ci.equalsIgnoreCase("true")) { 
				options.addArguments("--headless=new"); 
				options.addArguments("--no-sandbox"); 
				options.addArguments("--disable-dev-shm-usage"); 
				}
			driver.set(new ChromeDriver(options)); 
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
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//Thread.sleep(10000);
		getDriver().get(p.getProperty("appURL2"));
		getDriver().manage().window().maximize();
		Thread.sleep(10000);
	}
	
	
	@AfterMethod
	public void tearDown() {
		if (getDriver() != null) {

            getDriver().quit();

            // Remove ThreadLocal reference
            driver.remove();
        }
	}

}
