package testing.abstractscomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this); 
		
	}
	
	
	public void waitForElementToAppear(By findBy) 
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6)); 
	    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForWebElementToAppear(WebElement findBy) 
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
	    wait.until(ExpectedConditions.visibilityOf(findBy)); 
		
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException 
	{
		Thread.sleep(2000);
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
        //wait.until(ExpectedConditions.invisibilityOf(ele));
		
	}

}
