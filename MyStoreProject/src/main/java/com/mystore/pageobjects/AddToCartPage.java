/**
 * 
 */
package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

/**
 * @author Durgesh
 *
 */
public class AddToCartPage extends BaseClass {
	
	Action action= new Action();
	
	@FindBy(id="quantity_wanted")
	private WebElement quantity;
	
	@FindBy(name="group_1")
	private WebElement size;
	
	@FindBy(xpath="//span[text()='Add to cart']")
	private WebElement addToCartBtn;
	
	@FindBy(xpath="//*[@id=\"layer_cart\"]//h2/i")
	private WebElement addToCartMessag;
	
	@FindBy(xpath="//span[contains(text(),'Proceed to checkout')]")
	private WebElement proceedToCheckOutBtn;
	
	public AddToCartPage() {
		PageFactory.initElements(driver, this);
	}

	public void enterQuantity(String quantity1) throws Throwable {
		action.type(quantity, quantity1);
	}
	
	public void selectSize(String size1) throws Throwable {
		action.selectByVisibleText(size1, size);
	}
	
	public void clickOnAddToCart() throws Throwable {
		action.click(driver, addToCartBtn);
	}
	
	public boolean validateAddtoCart() throws Throwable {
		action.fluentWait(driver, addToCartMessag, 10);
		return action.isDisplayed(driver, addToCartMessag);
	}
	
	public OrderPage clickOnCheckOut() throws Throwable {
		action.fluentWait(driver, proceedToCheckOutBtn, 10);
		action.JSClick(driver, proceedToCheckOutBtn);
		return new OrderPage();
	}
	
}
