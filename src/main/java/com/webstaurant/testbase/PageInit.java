package com.webstaurant.testbase;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.webstaurant.pages.HomePage;

/**
 * 
 * Class Page Init
 * @author toanl
 * @version 1.0
 * @since   2020-01-21 
 *
 */

public class PageInit {
	public static WebDriver driver = null;
	private static Logger log = Logger.getLogger(PageInit.class);
	protected static int PAGE_LOAD_TIMEOUT = 10;
	protected static int ELEMENT_WAIT_TIMEOUT = 50;
	public static String LOGIN_PAGE_TITLE = "Duolingo - The world's best way to learn a language";
	public static HomePage homePage = new HomePage();
	public static String HOME_PAGE_TITLE = "Duolingo - Cách học Tiếng Anh tốt nhất thế giới";
	public static String AI_PAGE_TITLE ="AI Research - Duolingo";
	
	public static void initPages() {
		PageFactory.initElements(driver, homePage);
	}
	
	public WebDriver getDriver() {
		return driver;
	}

}
