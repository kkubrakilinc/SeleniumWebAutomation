import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserTest {

	WebDriver driver;
	
	String firstPrice;
	String secondPrice;
	
	public void launchBrowser()
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Kübra\\Documents\\JavaProjects\\SeleniumFramework\\drivers\\geckodriver\\geckodriver.exe");
	    driver = new FirefoxDriver();
		driver.get("https://www.n11.com");
	}
	
	public void login()
	{
		driver.findElement(By.className("btnSignIn")).click();
		driver.findElement(By.id("email")).sendKeys("kkubra.kilinc96@gmail.com");
		driver.findElement(By.id("password")).sendKeys("kubra123");
		driver.findElement(By.id("loginButton")).click();
	}
	
	public void search() 
	{
		driver.findElement(By.id("searchData")).sendKeys("bilgisayar");
		driver.findElement(By.className("searchBtn")).click();;
	}
	
	public void secondPage()
	{
		driver.findElement(By.xpath("//a[@href='https://www.n11.com/arama?q=bilgisayar&pg=2']")).click();
	}
	
	public void randomSelect()
	{
		driver.findElements(By.xpath("//*[@id=\"view\"]/ul"));
		List<WebElement> links = driver.findElements(By.tagName("h3"));
		
		System.out.println(links.size());
		
		Random rnd = new Random();
	    int randomValue = rnd.nextInt(links.size()-1);
		links.get(randomValue).click();	   
	}
	
	public void addBasketAndGoToBasket()
	{
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);      
		driver.findElement(By.xpath("//*[@id=\"contentProDetail\"]/div/div[3]/div[2]/div[3]/div[3]/a[2]")).click();
		
		firstPrice = ((WebElement) driver.findElement(By.xpath("//*[@id=\"contentProDetail\"]/div/div[3]/div[2]/div[3]/div[2]/div/div[1]/div/ins"))).getText();
		 
		driver.findElement(By.className("myBasket")).click();
		secondPrice = ((WebElement) driver.findElement(By.xpath("//*[@id=\"newCheckout\"]/div/div[1]/div[2]/div[1]/section[2]/table[2]/tbody/tr/td[3]/div[2]/div/span"))).getText();
		
		comparePrice();
	}

	public void comparePrice()
	{
		if(firstPrice.equals(secondPrice))
		{
			System.out.println("Prices are equal");
		}
		else
		{
			System.out.println("Prices are not equal");
		}
	}
	
	public void goToBasket()
	{
		driver.findElement(By.className("myBasket")).click();
		secondPrice = ((WebElement) driver.findElement(By.xpath("//*[@id=\"newCheckout\"]/div/div[1]/div[2]/div[1]/section[2]/table[2]/tbody/tr/td[3]/div[2]/div/span"))).getText();
		comparePrice();
	}
	
	public static void main(String[] args) 
	{
		BrowserTest browser = new BrowserTest();
		browser.launchBrowser();
		browser.login();
		browser.search();
		browser.secondPage();
		browser.randomSelect();
		browser.addBasketAndGoToBasket();
	}
}
