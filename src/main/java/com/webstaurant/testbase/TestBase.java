package com.webstaurant.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.webstaurant.testutils.WebEventListener;

/**
 * Abstract class Test Base
 * @author toanl
 * @version 1.0
 * @since   2020-01-21
 */

public abstract class TestBase extends PageInit{
	private static Logger log = Logger.getLogger(TestBase.class);
	protected static Properties properties;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase() {
		FileInputStream file = null;
		properties= new Properties();
		
		try {
			file = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/webstaurant/config/config.properties");
		} catch (FileNotFoundException e) {
			System.out.println("File is not availalbe");
		}
		try {
			properties.load(file);
		} catch (IOException e) {
			System.out.println("Cannot load file");
		}
	}
	
	public static void setUpWebPage() {
		if(properties.get("browser").equals("Chrome")){
			System.setProperty("webdriver.chrome.driver", properties.getProperty("ChromeDriverLocation"));
			driver = new ChromeDriver();
		} else if(properties.get("browser").equals("FireFox")) {
			System.setProperty("webdriver.gecko.driver", properties.getProperty("FireFoxDriverLocation"));
			driver = new FirefoxDriver();
		}
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.SECONDS);
		driver.get(properties.getProperty("url"));
		initPages();
	}
	
	

}
