package testing.PageObjects;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.util.Assert;

import testing.abstractscomponents.AbstractComponent;

public class SinglePage extends AbstractComponent{

	public WebDriver driver;
	public String precioUno;
	
	public SinglePage(WebDriver driver, String precioUno) throws InterruptedException {
		super(driver);
		this.driver = driver;	
		this.precioUno=precioUno;
		PageFactory.initElements(driver, this);
		Thread.sleep(2000);
		
	
		
	}
	
	@FindBy(id="uhf-shopping-cart")
	WebElement cartButton;
	
	By precioComp = By.cssSelector("span.Price-module__boldText___34T2w.Price-module__moreText___1FNlT.AcquisitionButtons-module__listedPrice___BhPfG");
	
	By btnCart = By.xpath("//button[contains(@title,'Agregar')]");
	
	
	
	public CartPage comparaPrecio() throws AWTException, InterruptedException {
		
		String precioCart = driver.findElement(precioComp).getText();
		if(precioCart.contains(precioUno)) {
			Thread.sleep(3000);
			waitForElementToAppear(btnCart);
			WebElement btn = driver.findElement(btnCart);
			btn.click();
			Thread.sleep(6000);
			cartButton.click();
			Thread.sleep(3000);
	        
			CartPage cartPage = new CartPage(driver);
			
			return cartPage;
		}else {
			return null;
		}
			

	}

	public void zoomOut() throws InterruptedException, AWTException {
		

		Robot robot = new Robot();
		for(int i=0; i<2;i++) {
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
		}
		
	}
	

}
