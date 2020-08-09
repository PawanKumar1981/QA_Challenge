package com.flight.protal.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;


public class BaseClass {

	public static WebDriver driver;
    public static Properties prop;
	//public static Logger log = Logger.getLogger(BaseClass.class.getName());

	public BaseClass() {
		prop = new Properties();
		try {
			FileInputStream src = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\flight\\protal\\config\\config.properties");
			prop.load(src);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
	
		}

		String log4jConfPath = "./log4j.properties";
		//PropertyConfigurator.configure(log4jConfPath);
	}

	public static void browserinitialization() throws IOException {

		switch (prop.getProperty("Browser")) {

		case "chrome":
			//WebDriverManager.chromedriver().setup();

			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromepath"));
			
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NONE);
			options.addArguments("--disable-notifications");
			// Instantiate the chrome driver
			driver = new ChromeDriver(options);
			// driver = new ChromeDriver();
			break;

		case "safari":
			   driver= new SafariDriver();
			   
			break;
		case "FF":
			// Instantiate the firefox driver
			//WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;			

		default:
			break;
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));

	}

	
	public void tearDown() {

		driver.quit();
		//log.info("Browser was closed sucessfully");

	}

	public static void takeScreenshot(String fileName) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\Screenshots\\"+ fileName +".png"));
	}

}
