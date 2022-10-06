package testing.tests;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.util.Assert;

import testing.PageObjects.SinglePage;
import testing.PageObjects.CartPage;
import testing.PageObjects.HomePage;
import testing.PageObjects.SearchPage;
import testing.TestComponents.BaseTest;

public class SearchProducts extends BaseTest{
	
	
	@Test(dataProvider="getData")
	public void searchProduct(HashMap<String,String> input) throws IOException, InterruptedException, AWTException { 
		
		
		SearchPage searchPage = homePage.makeSearch(input.get("search")); 
		Thread.sleep(2000);
		searchPage.isInDom();
		System.out.println("Existen: "+searchPage.counterProducts(2)+" productos en la paginación");
		SinglePage singlePage =searchPage.selectFirstNoFree();	
		Thread.sleep(2000);
		singlePage.zoomOut();//Control Resoluciones de computadora pueden ser distintas pero por tiempo lo hice de esta manera prematura
		
		CartPage cartPage = singlePage.comparaPrecio(); 
		
		String finalMessage = cartPage.deleteProduct();
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println(finalMessage);
        
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\testing\\data\\Busquedas.json");
		
		
		return new Object[][] {{data.get(0)}};
	
	}
	
}
