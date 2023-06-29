package ui;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoFrameworkAutomationSeq {
	
	WebDriver driver;
	
	@Test
	public void chromeTest() {
		String username = "demouser";
		String password = "testingisfun99";
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		Assert.assertEquals(bstackdemoTestUtil(username, password), 1);
	}
	
	@Test
	public void firefoxTest() {
		String username = "demouser";
		String password = "testingisfun99";
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		Assert.assertEquals(bstackdemoTestUtil(username, password), 1);
	}
	
	@Test
	public void safariTest() {
		String username = "demouser";
		String password = "testingisfun99";
		WebDriverManager.safaridriver().setup();
		driver = new SafariDriver();
		Assert.assertEquals(bstackdemoTestUtil(username, password), 1);
	}
	
	
	public int bstackdemoTestUtil(String username, String password) {
		driver.get("https://bstackdemo.com/");
		driver.manage().window().maximize();
		driver.findElement(By.id("signin")).click();
		synchronized (driver){
			try {
				driver.wait(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		driver.findElement(By.xpath("/html/body/div/div[2]/div/form/div[2]/div[1]/div/div[1]")).click();
		driver.findElement(By.id("react-select-2-input")).sendKeys(username, Keys.ENTER);
		driver.findElement(By.xpath("/html/body/div/div[2]/div/form/div[2]/div[2]/div/div[1]")).click();
		driver.findElement(By.id("react-select-3-input")).sendKeys(password, Keys.ENTER);
		driver.findElement(By.id("login-btn")).click();
		synchronized (driver){
			try {
				driver.wait(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			String logged_in_user = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/span")).getText();
			if(!logged_in_user.equals(username)) {
				return 0;
			}
			
		} catch(Exception e) {
			return 0;
		}
		driver.findElement(By.xpath("/html/body/div/div/div/main/div[2]/div[2]/div[4]")).click();
		synchronized (driver){
			try {
				driver.wait(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div[2]/div[3]/div[3]")).click();
		synchronized (driver){
			try {
				driver.wait(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		driver.findElement(By.id("firstNameInput")).sendKeys("sample_first_name");
		driver.findElement(By.id("lastNameInput")).sendKeys("sample_last_name");
		driver.findElement(By.id("addressLine1Input")).sendKeys("sample_address");
		driver.findElement(By.id("provinceInput")).sendKeys("sample_province");
		driver.findElement(By.id("postCodeInput")).sendKeys("sample_postal_code");
		driver.findElement(By.id("checkout-shipping-continue")).click();
		synchronized (driver){
			try {
				driver.wait(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String str = driver.findElement(By.id("confirmation-message")).getText();
		int ret_val = 0;
		if(str.contains("successful")) {
			ret_val = 1;
		}
		return ret_val;
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
