package org.jclub.coreclasses;



import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.ios.IOSDriver;




public class browserintialization {

	
				private WebDriver driver;
				
				String APPLICATION_URL;
				
				int Seconds = 10; 
		
				Actions build ;
				
				IOSDriver iosdriver;

			public void openbrowsers(){
				
		
			
			if (("ChromeDriver").equals("ChromeDriver"))
			{
				
				System.setProperty("webdriver.chrome.driver", "//Users//jay//Desktop//Automation Project Stuff//chromedriver");

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
			
				prefs.put("safebrowsing.enabled", "true");

				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("disable -extensions");
				//options.addArguments("--kiosk");
				options.addArguments("disable-infobars");
				options.setExperimentalOption("prefs", prefs);

				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("chrome.binary", "//Users//jay//Desktop//Automation Project Stuff//chromedriver");
				capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driver = 	new ChromeDriver(capabilities);
				build = new Actions(driver);
				
		
			}
			}
			
			public void closebrowsers(){

				driver.quit();
			}

			public void navigatetourl() throws InterruptedException, IOException{
				
				//Thread.sleep(5000);	
			APPLICATION_URL = JclubGUIProperties.getInstance().getProperty("qa_DEV_URL").trim();
			driver.navigate().to(APPLICATION_URL);
				
			}


			
			public void click(By by) throws IOException
			{
				
				try
				{					
					WebElement element = driver.findElement(by);
					getHighlightElement(element);
					timeinterval(1);
					build.moveToElement(element).click().perform();
				
					
				}
				catch (NoSuchElementException e)
				{
					driver.findElement(by).click();
				}
				catch (Exception e)
				{
					Assert.assertTrue(false, "Fail to click on link : " + by + " on page : " + e.getMessage());
					//rootLogger.warn("Fail to click on link : " + by + " on page : " + selenium.getWrappedDriver().getCurrentUrl());
				}
			}
			
			
				public void click(WebElement  element){
				
				getHighlightElement(element);
				build.moveToElement(element).click().perform();
			}
			public void sendkeys(By by,String keys){
				getHighlightElement(driver.findElement(by));
				driver.findElement(by).sendKeys(keys);
			}
			
			public void timeinterval(int i) throws InterruptedException{
				
				Thread.sleep(1000 * i );
				
			}
			
			public void getHighlightElement(final WebElement element)
			{
				try
				{
					Wait<WebDriver> wait = new WebDriverWait(driver, 10);
					// Wait for search to complete
					wait.until(new ExpectedCondition<Boolean>()
					{
						
						public Boolean apply(WebDriver webDriver)
						{
							return element != null;
						}
					});
					((JavascriptExecutor) driver)
					.executeScript("arguments[0].style.border='2px solid red'", element);
				}
				catch (Exception e)
				{
					//logger.info("Fail to highlight the Element");
				}
			}
			
			public boolean isDisplayed(By by) throws InterruptedException{
				
				timeinterval(1);
				getHighlightElement(driver.findElement(by));
				return driver.findElement(by).isDisplayed();
				
			}
			
			public String getText(By by) throws IOException, InterruptedException
			{
				waitForParticularElement(by, Seconds);
				try
				{
					getHighlightElement(driver.findElement(by));
					return driver.findElement(by).getText().trim();
				}
				catch (NoSuchElementException e)
				{
					// Assert.assertTrue(false, "Fail to get text value from : " + by + " on page : " + e.getMessage());

				}
				catch (Exception e)
				{
					// logger.info(e.getMessage());

				}
				return null;
			}
			
			public String Gecurrenturl(){
				
				return driver.getCurrentUrl();
			}
			
			public List getelementlist(By by){
			
				List <WebElement> product = driver.findElements(by);
				return product;		
			}
			
			public String getAttribute(By by, String attribute) throws IOException
			{
				try
				{
					
					getHighlightElement(driver.findElement(by));
					return driver.findElement(by).getAttribute(attribute).trim();
				}
				catch (NoSuchElementException e)
				{
					
				}
				return null;
			}
			
			public void waitForParticularElement(final By element, int waitForSeconds) throws IOException, InterruptedException
			{
				int i = 1;
				do
				{
					try
					{
						driver.findElement(element).isDisplayed();
						timeinterval(1);
						break;
					}
					catch (Exception e)
					{
						timeinterval(1);
						i++;
						System.out.println("waiting for element : " + element.toString() + " :  Waiting Time [ " + i + " ] out of " + waitForSeconds);
					}
				}
				while (i <= waitForSeconds);
			}
			
			public String getTextRuntime(By by) throws IOException, InterruptedException
			{
				timeinterval(1);
				try
				{
					moveToElement(by);
					return driver.findElement(by).getText().trim();
				}
				catch (NoSuchElementException e)
				{
				//	rootLogger.warn("Fail to get text value from : " + by + " on page : " + e.getMessage());
				}
				catch (Exception e)
				{
				//	rootLogger.warn("Fail to get text value from : " + by + " on page : " + e.getMessage());
				}
				return "";
			}
			
			public void moveToElement(WebElement element) throws IOException
			{

				try
				{
					getHighlightElement(element);
					build.moveToElement(element).build().perform();
				}
				catch (NoSuchElementException e)
				{
					//Assert.assertTrue(false, "Fail to find Element: " + element + " on page : " + detectPage());
				}
				catch (Exception e)
				{
					//rootLogger.warn("Fail to find Element: " + element + " on page : " + detectPage());
				}
			}
			
			public void moveToElement(By by) throws IOException
			{
				WebElement element;

				try
				{
					element = driver.findElement(by);
					getHighlightElement(driver.findElement(by));
					build.moveToElement(element).build().perform();
				}
				catch (NoSuchElementException e)
				{
					//rootLogger.warn("Fail to find Element: " + by + " on page : " + detectPage());
				}
				catch (Exception e)
				{
					//rootLogger.warn("Fail to find Element: " + by + " on page : " + detectPage());
				}
			}
				
	}
	
	

