package uk.co.automationtesting;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import base.Hooks;
import pageObjects.Homepage;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;
@Listeners(resources.Listeners.class)
public class AddRemoveItemBasketTest extends Hooks{

	public AddRemoveItemBasketTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Test
	public void addRemoveItem() throws InterruptedException, IOException {
		Homepage home = new Homepage();

		//handles cookie prompt
		home.getCookie().click();

		home.getTestStoreLink().click();
		
		ShopHomepage shopHome = new ShopHomepage();
		shopHome.getProdOne().click();
		
		ShopProductPage shopProd = new ShopProductPage();
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		shopProd.getQuantityIncrease().click();
		shopProd.getAddToCartBtn().click();
		
		Thread.sleep(3000);
		
		ShopContentPanel cPanel = new ShopContentPanel();
		cPanel.getContinueShopppingBtn().click();
		shopProd.getHomepageLink().click();
		shopHome.getProdTwo().click();
		shopProd.getAddToCartBtn().click();
		cPanel.getCheckoutBtn().click();
		
		
		ShoppingCart cart = new ShoppingCart();
		cart.getDeleteItemTwo().click();
		
		waitForElementInvisible(cart.getDeleteItemTwo(),10);
		
		System.out.println(cart.getTotalAmount().getText());
		
		Assert.assertEquals(cart.getTotalAmount().getText(), "$45.24");
		
	}

}
