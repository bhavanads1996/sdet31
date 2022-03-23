package com.crm.parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class loginTest {
	
	@Test
	
	public void login() {
		
		String browser = System.getProperty("browser");
		String url =  System.getProperty("url");
		String username = System.getProperty("username");
		String password =  System.getProperty("password");
	
	WebDriver driver = null;
	if (browser.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("launched browser is " + browser);
	} else if (browser.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		System.out.println("launched browser is " + browser);
	} else {
		System.out.println("specify a valid browser");
	}
	
	
	driver.get(url);
	//wLib.waitForPageToLoad(driver);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	WebDriverWait wait = new WebDriverWait(driver,10);
	
	//Login to Vtiger
	driver.findElement(By.name("user_name")).sendKeys(username);
	driver.findElement(By.name("user_password")).sendKeys(password);
	driver.findElement(By.id("submitButton")).click();
	
	Actions actions = new Actions(driver);
	WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	actions.moveToElement(logout).perform();
	driver.findElement(By.linkText("Sign Out")).click();
	driver.close();
			
}
}