package com.webstaurant.testutils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webstaurant.testbase.TestBase;

/**
 * Test Utils contails utils methods for other class
 * @author toanl
 * @version 1.0
 * @since   2020-01-21
 */

public class TestUtils extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	public static long ELEMENT_WAIT = 20;

	public static String TESTDATA_SHEET_PATH = "/Users/naveenkhunteta/Documents/workspace"
			+ "/FreeCRMTest/src/main/java/com/crm/qa/testdata/FreeCrmTestData.xlsx";
	/*
	 * Disable takesScreenshot function
	 * We don't need this function at this time
	 *
	 * public static String takeScreenshotAtEndOfTest(String screenshotName) throws
	 * IOException { File src = ((TakesScreenshot)
	 * driver).getScreenshotAs(OutputType.FILE); FileUtils.copyFile(src, new File(
	 * "C:\\Users\\toanl\\eclipse-workspace\\SampleSeleniumWebDriver\\screenshot\\"+screenshotName+"
	 * .png")); return
	 * "C:\\Users\\toanl\\eclipse-workspace\\SampleSeleniumWebDriver\\screenshot\\"+screenshotName+"
	 * .png"; }
	 */
	
	public static void waitForElement(WebElement element) {
		new WebDriverWait(driver, ELEMENT_WAIT).ignoring(StaleElementReferenceException.class)
		.until(ExpectedConditions.elementToBeClickable(element));
	}
	
}
