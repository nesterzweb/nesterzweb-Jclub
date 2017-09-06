package org.jclub.coreclasses;



import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;




public class browserintialization {

	
				private WebDriver driver;
				
				String APPLICATION_URL;
		

			public void openbrowsers(){
				
		
			
			if (("ChromeDriver").equals("ChromeDriver"))
			{
				
				System.setProperty("webdriver.chrome.driver", "E://Portico_Framework//portico-qa-libs//src//main//resources//Drivers//chromedriver.exe");

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
			
				prefs.put("safebrowsing.enabled", "true");

				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable -extensions");
				options.addArguments("--start-maximized");
				options.addArguments("disable-infobars");
				options.setExperimentalOption("prefs", prefs);

				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("chrome.binary", "E://Portico_Framework//portico-qa-libs//src//main//resources//Drivers//chromedriver.exe");
				capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driver = 	new ChromeDriver(capabilities);
				
		
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

			public void click(By by){
				
				getHighlightElement(driver.findElement(by));
				driver.findElement(by).click();
			}
			
			public void sendkeys(By by,String keys){
				getHighlightElement(driver.findElement(by));
				driver.findElement(by).sendKeys(keys);
			}
			
			public void timeinterval(int i) throws InterruptedException{
				
				Thread.sleep(5000 * i );
				
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
			
			public boolean isDisplayed(By by){
				
				//getHighlightElement(driver.findElement(by));
				return driver.findElement(by).isDisplayed();
				
			}
			
			public String getText(By by) throws IOException
			{
				//waitForParticularElement(by, Seconds);
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
	}
	
	

