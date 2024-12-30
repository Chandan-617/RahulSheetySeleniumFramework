
package com.qa.SampleTest;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogPage;

public class StandAloneTest {
	
	
	public static void main(String[] args) throws InterruptedException {
		
		String productName="ZARA COAT 3";
		
		WebDriverManager.chromedriver().setup();
		WebDriver  driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		
		
		LandingPage page=new LandingPage(driver);
		page.goTo();
		
		page.loginApplication("tom143@gmail.com", "Tom@1234");
		
		ProductCatalogPage prductcatlgpge=new ProductCatalogPage(driver);
		
		
		List<WebElement> products=prductcatlgpge.getProductList();
		
		prductcatlgpge.addProductToCart(productName);
		
		
		/*
		 * driver.findElement(By.id("userEmail")).sendKeys("tom143@gmail.com");
		 * driver.findElement(By.id("userPassword")).sendKeys("Tom@1234");
		 * driver.findElement(By.id("login")).click();
		 */
		
		/*
		 * WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
		 * ".mb-3")));
		 * 
		 * 
		 * 
		 * List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		 */
		
		/*
		 * WebElement prod=products.stream().filter(product->
		 * product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).
		 * findFirst().orElse(null);
		 * 
		 * prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		 */
		
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		
		
		Boolean match=cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		
		
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		
		Actions act=new Actions(driver);
		
		act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		//
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		
		Thread.sleep(5000);
		WebElement ele=driver.findElement(By.cssSelector(".action__submit"));
		
		((JavascriptExecutor) driver).executeScript(
	            "arguments[0].scrollIntoView();", ele);
		
		Thread.sleep(5000);
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmedMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		System.out.println(confirmedMessage);
		
		Assert.assertTrue(confirmedMessage.equalsIgnoreCase("Thankyou for the order."));
		
		//driver.close();
		
		
	}
	
	
	

}
