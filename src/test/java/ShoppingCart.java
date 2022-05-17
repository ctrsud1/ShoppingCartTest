import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCart {
	
	 private WebDriver driver;
	 private Actions actions;
	 
	 @Before
	 public void setUp() {
	    //add this chrome driver to this location to run this test in chrome browser
	    System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
	    driver  = new ChromeDriver();
	    actions = new Actions (driver);
	 }
	  

	 
	  @Test
	  public void addToCartHighestCostDress() {
		 //Open the required website to test
		String baseUrl = "http://automationpractice.com/index.php";
		driver.manage().window().maximize();
		driver.get(baseUrl);
		
	         
		
		//get List of menu items for shopping
		List<WebElement> elements = driver.findElements( By.xpath("//a[@class='sf-with-ul']"));
		try {
			   if(elements != null ) {
				
				for(int i=0;i<elements.size();i++)	{
					System.out.print(elements.get(i).getAttribute("title")+"\n");
				}
				
				//click on dresses
				elements.get(3).click();
				
				//gets all dresses
				List<WebElement> productElements  = driver.findElements( By.xpath("//div[@class='product-container']"));
				//retrieve all dresses prices
				List<WebElement> productPrices = driver.findElements(By.xpath("//span[@class='price product-price']"));
				//gets addTocart button for hover-over dress
				List<WebElement> addToCart = driver.findElements(By.xpath("//a[@class='button ajax_add_to_cart_button btn btn-default']"));
	
				
				double highestPrice=0.0;
				int highestPriceIndex = -1;
				
				for(int i=0;i<productPrices.size();i++)	{
					String productPrice = productPrices.get(i).getAttribute("innerText").trim().substring(1);
					double price = Double.parseDouble(productPrice);
				
					//find highest price product and storing the index of the product
					if(price > highestPrice)	{
						highestPrice = price;
						highestPriceIndex = i/2;
					}
			   }
				
				actions.moveToElement(productElements.get(highestPriceIndex)).perform();
				
				addToCart.get(highestPriceIndex).click();
				}
			   
			   } //Catch the Exception and print if any
		          catch(Exception e)	{
				   System.out.print(e.toString());
			   }
	  }
	  
	  
		 @After
		 public void tearDown() {
		   //Closing the browser
			// driver.quit();
		  System.out.println("Test Completed Successfully");
		 }

	
	


}

