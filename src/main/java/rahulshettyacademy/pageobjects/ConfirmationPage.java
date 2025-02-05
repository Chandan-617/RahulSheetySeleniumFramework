package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
	
	
	
	WebDriver driver;
	public ConfirmationPage(WebDriver driver)
	
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(css=".hero-primary")
	
	WebElement conformationMessage;
	
	public String verifyConformaionMessage()
	{
		
		return conformationMessage.getText();
		
	}

}
