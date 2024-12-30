    package com.qa.SampleTest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.testComponent.BaseTest;
import com.qa.testComponent.Retry;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogPage;

public class ErrorValidation extends BaseTest {
	
	@Test(groups= {"Error Handling"},retryAnalyzer = Retry.class)
	public  void loginErrorValidation() throws IOException, InterruptedException {
		
		String productName = "ZARA COAT 3";
		ProductCatalogPage prductcatlgpge = page.loginApplication("tom1431@gmail.com", "Tom@12346");
		Assert.assertEquals("Incorrect email or password.", page.getErrorMessage());
		
	}
	
	@Test
	public  void productErrorValidation() throws IOException, InterruptedException {
		
		String productName = "ZARA COAT 3";
		ProductCatalogPage prductcatlgpge = page.loginApplication("tom1453@gmail.com", "Tom@1234");
		List<WebElement> products = prductcatlgpge.getProductList();
		prductcatlgpge.addProductToCart(productName);
		CartPage cartpage = prductcatlgpge.goToCartPage();
		Boolean match = cartpage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match); 
		
		
	}
	

}
