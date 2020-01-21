package com.webstaurant.pages;



import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.webstaurant.testbase.TestBase;
import com.webstaurant.testutils.TestUtils;

/**
 * 
 * Class Homepage object
 * @author toanl
 * @version 1.0
 * @since   2020-01-21 
 *
 */
public class HomePage extends TestBase {

	private static Logger log = Logger.getLogger(TestBase.class);
	private static final String PRE_XPATH_PAGE_LINK = "//a[contains(@href, '/search/stainless-work-table.html?page=";
	private static final String POST_DIV_PRODUCTBOX = "')]/div[contains(@class,'add-to-cart')]/form/div[1]";
	private static final String PRE_DIV_PRODUCTBOX = "//div[contains(@id,'productBox";
	public final String SEARCH_TEXT="stainless work table";
	private String productTypeOption = "Detached Seat - $25.49/Each";

	@FindBy(id = "searchval")
	private WebElement searchBox;

	@FindBy(xpath = "//button[@value='Search']")
	private WebElement searchBoxSubmitButton;

	@FindBy(xpath = "//span[contains(text(),'Cart')]")
	private static WebElement cartButton;

	@FindBy(xpath = "//*[@id='product_listing']/div[starts-with(@id,'productBox')]")
	private List<WebElement> productList;

	@FindBy(css = ".icon-right-open")
	private WebElement navigateNextPageButton;

	@FindBy(id = "cartItemCountSpan")
	private WebElement numberOfCartItem;

	@FindBy(xpath = "//*[@id='paging']/div/ul/li")
	private List<WebElement> listPageNumber;

	@FindBy(xpath = "//input[@name='addToCartButton'])") // [60]
	private WebElement addToCartButton;

	@FindBy(xpath = "//a[contains(text(),'Empty Cart')]")
	private WebElement emptyCartButton;

	@FindBy(xpath = "//p[contains(text(),'Your cart is empty.')]")
	private WebElement emptyCartText;

	@FindBy(xpath = "//button[contains(text(),'Empty Cart')]")
	private WebElement popUpButtonConfirmEmptyCart;
	
	@FindBy(xpath = "//button[contains(text(),'Add to Cart')]")
	private WebElement popUpButtonAddToCartButton;

	@FindBy(xpath="//button[contains(.,'×')]")
	private WebElement notificationCloseButton;
	
	public HomePage() {
		super.getDriver();
	}
	
	public void performEmptyCart() {
		emptyCartButton.click();
	}
	
	public void closeNotification() {
		notificationCloseButton.click();
	}

	public void confirmOnEmptyPopUp() {
		TestUtils.waitForElement(popUpButtonConfirmEmptyCart);
		popUpButtonConfirmEmptyCart.click();
	}
	
	public void confirmAddToCartPopUp() {
		TestUtils.waitForElement(popUpButtonAddToCartButton);
		popUpButtonAddToCartButton.click();
	}

	public static void hoverOnCardElement() {
		Actions action = new Actions(driver);
		action.moveToElement(cartButton).build().perform();
	}

	public boolean cardIsEmpty() {
		hoverOnCardElement();
		TestUtils.waitForElement(emptyCartText);
		return emptyCartText.isDisplayed();
	}

	public int getNumberOfSearchPage() {
		return listPageNumber.size() + 1;
	}

	public void inputSearchText(String searchText){
		searchBox.sendKeys(searchText);
	}

	public void clickOnSeachButton() {
		searchBoxSubmitButton.click();
	}

	public void openCart(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			log.error("TimeOut waiting for cart button");;
		}
		cartButton.click();
	}

	public String getTitleOfElement(WebElement element) {
		return element.getText();
	}

	public WebElement getElement(int elementNum) {
		int k = productList.size();
		for (int i = 0; i < k; i++) {
			if (i == elementNum)
				return productList.get(i);
		}
		return null;
	}

	public int getNumberOfItemEachPage(){
		return productList.size();
	}

	public boolean nextPageSearchIsAvailable() {
		if (!navigateNextPageButton.isEnabled())
			return false;
		else
			return true;
	}

	public void moveTotheNextPageSearch() {
		TestUtils.waitForElement(navigateNextPageButton);
		if (navigateNextPageButton.isEnabled()) {
			navigateNextPageButton.click();
		}
	}

	public void openPage(int pageNumber) {
		driver.findElement(By.xpath(PRE_XPATH_PAGE_LINK+pageNumber +"')]")).click();
	}

	public void addItemToCard(int itemNumber) {
		WebElement divA = driver.findElement(By.xpath(PRE_DIV_PRODUCTBOX + itemNumber + POST_DIV_PRODUCTBOX));
//		TestUtil.waitForElement(divA.findElement(By.xpath("select")));
//		if (divA.findElement(By.xpath("select")).isDisplayed()) {
//			Select typeOption = new Select(divA.findElement(By.xpath("select")));
//			typeOption.selectByVisibleText(productTypeOption);
//		}else
		divA.findElement(By.xpath("div/input[@name='addToCartButton']")).click();
		confirmAddToCartPopUp();
	}

	public boolean itemIsAdded() {
		TestUtils.waitForElement(numberOfCartItem);
		if (numberOfCartItem.getText() != "0")
			return true;
		else
			return false;
	}
}
