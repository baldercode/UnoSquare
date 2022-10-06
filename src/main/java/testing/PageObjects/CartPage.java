package testing.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testing.abstractscomponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	public WebDriver driver; 
	public CartPage(WebDriver driver) {
		 super(driver);
		 this.driver = driver;	
		 PageFactory.initElements(driver, this); 	 
	}
	
	
	
	@FindBy(css="iframe[name*='store-cart-iframe_Dark_false']")
	WebElement frame;
	
	By deleteItem = By.xpath("//div[@class='c-group']/button[text()='Quitar']");
		
	public String deleteProduct() throws InterruptedException {
	
		String titleWindow = driver.getCurrentUrl();
		Thread.sleep(3000L);
		 if(titleWindow.contains("cart") && frame.isDisplayed()) {
			 driver.switchTo().frame(frame);
			 WebElement elemento = driver.findElement(deleteItem);
			 elemento.click();
			 Thread.sleep(5000);
			 driver.switchTo().defaultContent();
			 return "Tu carro esta vacio";
		 }else {
			 return "Hubo un error";
		 }
		
	}
	
	
	
	
	
	
	

}
