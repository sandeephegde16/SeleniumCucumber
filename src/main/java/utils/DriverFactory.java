package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {
	private static WebDriver driver;
	
	String browser;
	protected Properties prop = new Properties();
	
	public DriverFactory() {
		try {
			FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\properties\\globalProperties.properties");
			
			try {
				prop.load(fi);
			} catch (IOException e) {
				e.printStackTrace();
			}
			browser = prop.getProperty("browser");
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver getDriver() {
		try {
		switch(browser) {
		case "chrome":
			if(null==driver) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\drivers\\chromedriver.exe");
				String fileSeperator = System.getProperty("file.separator");
				String testDir = System.getProperty("user.dir");
														
				//Turn off Save Password dialog
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("credentials_enable_service", false);
				chromePrefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", chromePrefs);
				options.addArguments("--disable-extensions"); 
				options.addArguments("test-type");
				options.addArguments("start-maximized");
				
				LoggingPreferences logPrefs = new LoggingPreferences();
			    logPrefs.enable(LogType.BROWSER, Level.ALL);
			    logPrefs.enable(LogType.DRIVER, Level.ALL);
			    DesiredCapabilities dc = DesiredCapabilities.chrome();
				dc.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
				dc.setCapability(ChromeOptions.CAPABILITY, options);
				dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,UnexpectedAlertBehaviour.IGNORE);
				dc.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				dc.setCapability("chrome.switches", Arrays.asList("--incognito"));
				options.setCapability(CapabilityType.LOGGING_PREFS,logPrefs);
				driver = new ChromeDriver(dc);
				driver.manage().logs().get(LogType.DRIVER);
				driver.manage().logs().get(LogType.BROWSER);
				break;
			}
		case "firefox":
			if(null==driver) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\java\\drivers\\firefoxdriver.exe");
				driver = new FirefoxDriver();
				break;
			}
		case "IE":
			if(null==driver) {
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\main\\java\\drivers\\internetExplorerDriver.exe");
				driver = new InternetExplorerDriver();
				break;
			}
			
		}
		} catch(Exception e) {
			System.out.println("Cannot load driver; Check driver initiation!");
		} finally{
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return driver;
		
	}
}
