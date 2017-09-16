package org.jclub.coreclasses;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
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

import org.testng.asserts.SoftAssert;




public class browserintialization {

	
	private static Logger Log = Logger.getLogger(browserintialization.class.getName());
	
	SoftAssert softAsserttion = new SoftAssert();
	
				private WebDriver driver;
				
				String APPLICATION_URL;
				
				int Seconds = 10; 
		
				Actions build ;
				Alert alert;
				

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
			
			public List<WebElement> getelementlist(By by){
			
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
			
  //------------------------------------------------------------------- Product Testing related method-------------------------------------------------------------------//
			
			public boolean verify_video_firstpage() throws IOException, InterruptedException{
				return isDisplayed(By.xpath(".//*[@id='video-modal']//div[@class='modal-body']"));
			}
			
			public void Close_video_popup() throws IOException{
				click(By.xpath(".//*[@id='video-modal']//button[@class='close']"));
			}
				
			public void dologin() throws IOException{
				click(By.xpath(".//li//a[@class='bold' and @title='Login']"));
				sendkeys(By.xpath(".//*[@id='customer_email']"), "vivekparmar37@gmail.com");
				sendkeys(By.xpath(".//*[@id='customer_password']"), "9623007654");
				click(By.xpath(".//*[@id='new_customer']//input[@type='submit']"));
			}
			
			public boolean verify_signin_success() throws IOException, InterruptedException{
				String s= getText(By.xpath("//div[@class='alert alert-success']"));
				Log.info(s);
				return isDisplayed(By.xpath("//div[@class='alert alert-success']"));
			}
			
			public void click_on_category(String category) throws IOException{
				
				click(By.xpath("//a[text()='"+category+"']"));
			}
			
			public void product_attribute_verifiction(String Category) throws IOException, InterruptedException{
				
				int i = 0;
				List <WebElement> products = getelementlist(By.xpath(".//*[@id='product-list']//a"));
				
				Log.info("=====================================================================");
				Log.info("Numbers of Products in" +Category+" category="+products.size());
				Log.info("=====================================================================");
				
				Reporter.log("=====================================================================");
				Reporter.log("Numbers of Products in "+Category+" category : ="+products.size());
				Reporter.log("=====================================================================");
				
				for (WebElement product:products ){
				
					i++;
								
					String product_class_name = product.getAttribute("class");

					if(!product_class_name.contains("sold-out")){
						
						Log.info("=====================================================================");
						Log.info("======================== Product :="+i+"==========================");
						Log.info("=====================================================================");
						
						Reporter.log("=====================================================================");
						Reporter.log("======================== Product :="+i+"==========================");
						Reporter.log("=====================================================================");
					
					timeinterval(1);	
					click(product);
					
					if(isDisplayed(By.id("product-modal-body"))) {
					
					String product_name = getTextRuntime(By.id("product-name"));
					Log.info("Product Name := "+product_name);
					Reporter.log("Product Name := "+product_name);
					
					String product_price_jclub = getTextRuntime(By.xpath(".//*[@id='product-price']"));
					Log.info("Product Sell Price : = "+product_price_jclub);
					Reporter.log("Product Sell Price : = "+product_price_jclub);
					
					String product_price_retail = getTextRuntime(By.xpath(".//*[@id='product-retail']"));
					Log.info("Product Price : = "+product_price_retail);
					Reporter.log("Product Price : = "+product_price_retail);
					
					
					StringTokenizer jp =  new StringTokenizer(product_price_jclub);
					String jclubprice = jp.nextToken("$");
					float jcp = Float.parseFloat(jclubprice);
					
					StringTokenizer rp =  new StringTokenizer(product_price_retail);
					String retailprice = rp.nextToken("$");
					float rep =  Float.parseFloat(retailprice);
					
					if(jcp<rep) {
					
						Log.info("Jclub price is less than retail price");
						Reporter.log("Jclub price is less than retail price");
					}else
					{
						String bugDescription = "jclub price is greater than retail price.";	
						Reporter.log("<b> <font color='red' size='2'>BUG DESCRIPTION :" + bugDescription + "</font></b>");
						Log.info("jclub price is greater than retail price");
						
						softAsserttion.assertTrue(false);					
					}
					
					String image_url = getAttribute(By.id("product-image"), "src");
					Log.info("Product Image URL : = "+image_url);
					Reporter.log("Product Image URL : = "+image_url);
					
					String product_saving_percentile = getTextRuntime(By.xpath(".//*[@id='product-savings']"));
					Log.info("Product discount percentage := "+product_saving_percentile);
					Reporter.log("Product discount percentage := "+product_saving_percentile);
					
					StringTokenizer psp =  new StringTokenizer(product_saving_percentile);
					String savingpercentile = psp.nextToken("%");
					int sav_pep =  Integer.parseInt(savingpercentile);
					
					float cal_saving_percentile = (jcp * 100)/rep;
					
					int cal_sav = Math.round(cal_saving_percentile);
					
					if(sav_pep!=(100-cal_sav)) {
						
						String bugDescription = "Wrong saving percentile display.";
						Reporter.log("<b> <font color='red' size='2'>BUG DESCRIPTION :" + bugDescription + "</font></b>");
						Log.info("Wrong saving percentile display");
						softAsserttion.assertTrue(false);		
					}
					
					String product_description = getTextRuntime(By.id("description-tab"));
					Log.info("Product description := "+product_description);
					Reporter.log("Product description := "+product_description);
					
					click(By.xpath(".//*[@id='pdp-modal']//button[@class='close']"));
					
					}
					
					}
					else {
						Log.info("=====================================================================");
						Log.info("======================== Product :="+i+"==========================");
						Log.info("=====================================================================");
						
						Reporter.log("=====================================================================");
						Reporter.log("======================== Product :="+i+"==========================");
						Reporter.log("=====================================================================");
						Reporter.log("");
						Reporter.log("");
						Log.info("Sold out product  so URL not able to open");
						String soldout = "Sold out product  so URL not able to open";
						Reporter.log("<b> <font color='black' size='2'>Scenario Name : " + soldout + "</font></b>");
						
					}
						
				}
				softAsserttion.assertAll();
			}
			
			public void clearCartProduct() throws IOException, InterruptedException {
				
				click(By.id("open-cart"));
				
				if(isDisplayed(By.id("cart-modal-content"))) {
			
					List <WebElement> productsincart = getelementlist(By.xpath("//button[@class='item-remover btn-clear']"));
		
					for (WebElement product:productsincart ){
					   
						click(product);
					
					 Alert alert = driver.switchTo().alert();
					 
					 alert.accept();

				
			}
			
		}
				click(By.xpath("(//button[@class='close'])[1]"));
	}
	}
	
	

