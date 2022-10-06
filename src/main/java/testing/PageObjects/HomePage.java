package testing.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testing.abstractscomponents.AbstractComponent;


public class HomePage extends AbstractComponent{

	public WebDriver driver;
	public HomePage  homePage;
	
	public HomePage(WebDriver driver) {
		 super(driver);
		 this.driver = driver;	
		 PageFactory.initElements(driver, this);  
			
	}
	

	@FindBy(xpath="//li[@class='single-link js-nav-menu uhf-menu-item']/a[text()='Windows']")
	private WebElement btnWindows;
	

	@FindBy(xpath="//span[normalize-space()='Buscar']")
	private WebElement searchInput;
	

	@FindBy(xpath="//input[@id='cli_shellHeaderSearchInput']")
	private WebElement searchBtn;
	
	
	@FindBy(css="button[id='search']")
	private WebElement clickBtn;
	
	
	public SearchPage makeSearch(String s) {
		
		btnWindows.click();
		waitForWebElementToAppear(searchInput); 
		searchInput.click();
		waitForWebElementToAppear(searchBtn);
		searchBtn.sendKeys(s);
		waitForWebElementToAppear(clickBtn);
		clickBtn.click();
		
		SearchPage searchPage = new SearchPage(driver);
		return searchPage;
		
		
		
	}
	
	public void goTo()
	{
		driver.get("https://www.microsoft.com/es-mx/");
	
	}

}
