


package com.qa.SampleTest;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.testComponent.BaseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.ProductCatalogPage;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups={"Purchase"})
	public  void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		
		ProductCatalogPage prductcatlgpge = page.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = prductcatlgpge.getProductList();
		prductcatlgpge.addProductToCart(productName);
		CartPage cartpage = prductcatlgpge.goToCartPage();
		Boolean match = cartpage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry("india");
		Thread.sleep(5000);
		WebElement ele = driver.findElement(By.cssSelector(".action__submit"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
		Thread.sleep(5000);
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		String confirmedMessage = confirmationpage.verifyConformaionMessage();
		Assert.assertTrue(confirmedMessage.equalsIgnoreCase("Thankyou for the order."));
		
		
	}
	
//to verify zara coat 3 is  display on order page	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest()
	{
		
		ProductCatalogPage prductcatlgpge = page.loginApplication("tom143@gmail.com", "Tom@1234");

		OrdersPage orderspage=prductcatlgpge.goToOrdersPage();
		
		Assert.assertTrue(orderspage.verifyOrderDisplay(productName));
		
	}
	
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\TestData\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},   {data.get(1)}};
	}

}
/*
 * HashMap<String,String> map=new HashMap<String,String>(); map.put("email",
 * "tom143@gmail.com"); map.put("password", "Tom@1234"); map.put("productName",
 * "ZARA COAT 3");
 * 
 * HashMap<String,String> map1=new HashMap<String,String>(); map1.put("email",
 * "tom1463@gmail.com"); map1.put("password", "Tom@12134");
 * map1.put("productName", "ADIDAS ORIGINAL");
 */
