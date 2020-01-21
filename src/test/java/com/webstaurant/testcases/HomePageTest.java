package com.webstaurant.testcases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.webstaurant.testbase.TestBase;
/**
 * Test Home page with search functions
 * @author toanl
 * @version 1.0
 * @since   2020-01-21
 */
public class HomePageTest  extends TestBase{

	@BeforeMethod
	public void setUp() throws Exception {
		setUpWebPage();
		homePage.inputSearchText(homePage.SEARCH_TEXT);
		homePage.clickOnSeachButton();
	}

	/*
	 * Verify that title of the search product items of all return pages should contain the word "Table"
	 */
	@Test(priority = 1)
	public void checkTitleOfAllItems() throws Exception {
		boolean searchValueIsCorrect = true;
		int pageNumber = 0;
		do {
			for (int i = 0; i < homePage.getNumberOfItemEachPage() ; i++) {
				WebElement element = homePage.getElement(i);
				String elementTitle = homePage.getTitleOfElement(element);
				if(!elementTitle.contains("Table")) {
					System.out.println(elementTitle);
					searchValueIsCorrect = false;
				}
			}
			pageNumber++;
			homePage.moveTotheNextPageSearch();
		} while (homePage.getNumberOfSearchPage() != pageNumber);
		Assert.assertTrue(searchValueIsCorrect);

	}
	/*
	 * Verify the function add to cart and empty cart work fine with below steps
	 * Search items
	 * Add the last item of the last page to cart
	 * Remove that item
	 * 
	 */
	@Test(priority = 2)
	public void addTheLastFoundItemToCard(){
		
		
		int lastPage = homePage.getNumberOfSearchPage();
		homePage.openPage(lastPage);
		homePage.addItemToCard(homePage.getNumberOfItemEachPage());
		Assert.assertTrue(homePage.itemIsAdded());
		
		homePage.closeNotification();
		homePage.openCart();
		homePage.performEmptyCart();
		homePage.confirmOnEmptyPopUp();
		Assert.assertTrue(homePage.cardIsEmpty());
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}

}
