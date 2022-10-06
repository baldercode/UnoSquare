package testing.PageObjects;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testing.abstractscomponents.AbstractComponent;

public class SearchPage  extends AbstractComponent{

	public WebDriver driver;
	public SearchPage(WebDriver driver) {
		
		 super(driver);
		 this.driver = driver;	 
		 PageFactory.initElements(driver, this);
			
	}
	

	@FindBy(css="header[role*='tab'] a[aria-label='comprar tablas dinámicas']")
	private WebElement tabComprar;
	
	
	@FindBy(xpath="//div[@class='c-channel-placement-heading']//a[@aria-label='mostrar todo juegos (2000)'][normalize-space()='Mostrar todo']")
	private WebElement btnMostrar;
	
	@FindBy(xpath="//a[@id='R1MarketRedirect-submit']")
	private WebElement popupElement;
	
	
	@FindBy(xpath="//a[@aria-label='página 1']")
	private WebElement tabUno;
	
	
	@FindBy(xpath="(//div[@class='c-channel-placement-content']//*[contains(text(),'MXN$')])[1]/parent::*[1]")
	private WebElement choosenProd;
	
	
	
	By productsBy = By.cssSelector("div h3.c-subheading-6");
	
	By tabUnoBy = By.xpath("//a[@aria-label='página 1']");
	
	By selectFirstNoFree = By.xpath("(//div[@class='c-channel-placement-content']//*[contains(text(),'MXN$')])[1]");
		
	public void isInDom() throws InterruptedException {
		
		waitForWebElementToAppear(tabComprar);
		tabComprar.click();
		 
		waitForWebElementToAppear(btnMostrar);
		btnMostrar.click();
		
		waitForWebElementToAppear(popupElement);
		boolean flag = popupElement.isDisplayed();	
	    if(flag == true) {
	    	popupElement.click();
	    	
	    }
	    waitForElementToDisappear(popupElement);

	}
	
	public int counterProducts(int iteraciones) throws InterruptedException {
		int i = 0, cont=0;
		while(i < iteraciones) {
			
			waitForElementToAppear(productsBy);
			List<WebElement> lista = driver.findElements(productsBy);
			lista.forEach(a -> System.out.println(a.getText()));
			driver.findElement(By.xpath("//span[text()='Siguiente']")).click();
			i++;	
			cont = cont+lista.size();
		}
		
		backToTabOne();
		
		return cont;
	}
	
	public void backToTabOne() throws InterruptedException {
		
		//waitForElementToAppear(tabUnoBy);
		waitForWebElementToAppear(tabUno);
		Thread.sleep(2000);
		boolean flag = tabUno.isDisplayed();	
	    if(flag == true) {
	    	tabUno.click();
	    }
		
	}
	
	public SinglePage selectFirstNoFree() throws InterruptedException, AWTException {
		
		
		waitForElementToAppear(selectFirstNoFree);
		WebElement first = driver.findElement(selectFirstNoFree);
		String precio = first.getText();
		String[] aux = precio.split("\\$");
		
		String priceComp = aux[1];	
		choosenProd.click();
		
		//Thread.sleep(3000);
		
		SinglePage singlePage = new SinglePage(driver, priceComp);
		
		return singlePage; 
		
		
	}
	
	
	
	

}
