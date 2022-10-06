package testing.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException, AWTException {

		
		WebDriverManager.chromedriver().setup(); 
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Robot robot = new Robot();
		
		driver.get("https://www.microsoft.com/es-mx/");
		
		for(int i=0; i<2;i++) {
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
		}
		
		
		Thread.sleep(3000);
	
		
		//homepage
		driver.findElement(By.xpath("//li[@class='single-link js-nav-menu uhf-menu-item']/a[text()='Windows']")).click();
		
		Thread.sleep(3000);//en estos dar explicits
		driver.findElement(By.xpath("//span[normalize-space()='Buscar']")).click();
		
		Thread.sleep(4000);//en estos dar explicits
		WebElement busqueda = driver.findElement(By.xpath("//input[@id='cli_shellHeaderSearchInput']"));
		
		Thread.sleep(3000);//en estos dar explicits 
		busqueda.sendKeys("Games");
		
		
		Thread.sleep(2000);//en estos dar explicits
		driver.findElement(By.cssSelector("button[id='search']")).click();
		
		
		
		
		
		
		
		Thread.sleep(2000);//en estos dar explicits boton de comprar pagina busqueda
		driver.findElement(By.cssSelector("header[role*='tab'] a[aria-label='comprar tablas dinámicas']")).click();
		

		Thread.sleep(2000);//en estos dar explicits  pagina busqueda
		driver.findElement(By.xpath("//div[@class='c-channel-placement-heading']//a[@aria-label='mostrar todo juegos (2000)'][normalize-space()='Mostrar todo']")).click();
		
		
		
		
		Thread.sleep(2000);//en estos dar explicits  popup pagina busqueda
		boolean flag = driver.findElement(By.xpath("//a[@id='R1MarketRedirect-submit']")).isDisplayed();
		if(flag==true) {
			driver.findElement(By.xpath("//a[@id='R1MarketRedirect-submit']")).click();	
		}
		
		
		
		int it = 0, cont = 0;
		while(it < 2) {
			Thread.sleep(1000);//en estos dar explicits
			
			List<WebElement> lista = driver.findElements(By.cssSelector("div h3.c-subheading-6"));
			lista.forEach(a -> System.out.println(a.getText()));
			driver.findElement(By.xpath("//span[text()='Siguiente']")).click();			
			
			cont = cont+lista.size();
			it++;
		}
		
		System.out.println("Hay "+cont+" de productos");
	
		Thread.sleep(500);
		if(driver.findElement(By.xpath("//a[@aria-label='página 1']")).isDisplayed()) {
			
			driver.findElement(By.xpath("//a[@aria-label='página 1']")).click();
		
		}
		
		
		
		
		
		Thread.sleep(1000);
		
		String precio = driver.findElement(By.xpath("(//div[@class='c-channel-placement-content']//*[contains(text(),'MXN$')])[1]")).getText();
		String[] aux = precio.split("\\$");
		
		
		String precioCompara = aux[1];	
		driver.findElement(By.xpath("(//div[@class='c-channel-placement-content']//*[contains(text(),'MXN$')])[1]/parent::*[1]")).click();
		
		
		//Pagina single de xbox
		System.out.println("Es el segundo precio: "+driver.findElement(By.cssSelector("span.Price-module__boldText___34T2w.Price-module__moreText___1FNlT.AcquisitionButtons-module__listedPrice___BhPfG")).getText());
		
		String precioCompraraDos = driver.findElement(By.cssSelector("span.Price-module__boldText___34T2w.Price-module__moreText___1FNlT.AcquisitionButtons-module__listedPrice___BhPfG")).getText();
		
		for(int i=0; i<3;i++) {
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
		}
	 
		
		Thread.sleep(4000);
		if(precioCompraraDos.contains(precioCompara)) {
			System.out.println("Lo contiene");
		    driver.findElement(By.xpath("//button[contains(@title,'Agregar')]")).click();
			 Thread.sleep(6000);

		}
		 driver.findElement(By.id("uhf-shopping-cart")).click();
	     
		 
		 //pagina carrrito
		 String titleWindow = driver.getCurrentUrl();
		 System.out.println("Nombre de la ventana: "+titleWindow);
		 	
		 
		 
		 
		 
		 
		 driver.switchTo().defaultContent();
		 
		 
		 if(titleWindow.contains("cart")) {
			 System.out.println("Si estas en el carro de compras");
			 WebElement marco = driver.findElement(By.cssSelector("iframe[name*='store-cart-iframe_Dark_false']"));
			 driver.switchTo().frame(marco);
			
			 driver.findElement(By.xpath("//div[@class='c-group']/button[text()='Quitar']")).click();
			 Thread.sleep(5000);
		
		 }		 
		 
		 
		//driver.navigate().back();

		Thread.sleep(1000);
        //driver.quit();
	}

}
