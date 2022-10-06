package testing.TestComponents;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import testing.PageObjects.HomePage;


public class BaseTest {
	
	
	public WebDriver driver;
	public HomePage homePage;
	
	
	public WebDriver iniciarDriver() throws IOException
	{ 
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\testing\\resources\\GlobalData.propierties");
		prop.load(fis);
		String browserName = System.getProperty("browser") !=null ? System.getProperty("browser") :prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		 
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\chromedriver.exe");
		    if(browserName.contains("headless")) {
		    	
		    	options.addArguments("headless");		    	
		    }
			
			driver = new ChromeDriver(options);
			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
		//Firefox
		    System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\geckodriver.exe");
			driver = new FirefoxDriver();
			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;	
	}
	
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException 
	{
		 //read json to string	
		 String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
			
		 //convert string to hasmap Jackson databind  nos ayudara a convertirlo
		 ObjectMapper mapper = new ObjectMapper();
		 List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		 
		 return data;
	}
	
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot  ts =(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File fileDestiny = new File(System.getProperty("user.dir")+"\\reports\\" + testCaseName + ".png");
		FileUtils.copyFile(source, fileDestiny);
		
		return System.getProperty("user.dir")+"\\reports\\" + testCaseName + ".png";
	}
	

    
	@BeforeMethod(alwaysRun=true)
	public HomePage launchAplication() throws IOException, InterruptedException, AWTException 
	{
	    driver = iniciarDriver();
	    homePage = new HomePage(driver);
	    WebElement html = driver.findElement(By.tagName("html"));
	    html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
	    Thread.sleep(5000L);
	    homePage.goTo();
	    Robot robot = new Robot();
	    for(int i=0; i<2;i++) {
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
		}
	    
		
		return homePage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() throws InterruptedException 
	{
		Thread.sleep(2000);
	    driver.close();
		
	}
	

}
