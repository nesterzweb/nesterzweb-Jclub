package org.jclub.coreclasses;



import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.jclub.product.verification.iphone.iphone_product_page_verification_bestseller_category;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.asserts.SoftAssert;

import io.appium.java_client.ios.IOSDriver;

public class iossafariintialization {

				
				String APPLICATION_URL;
				
				int Seconds = 10; 
		
				Actions build ;
				
				IOSDriver iosdriver;

				private static Logger Log = Logger.getLogger(iphone_product_page_verification_bestseller_category.class.getName());

				SoftAssert softAsserttion = new SoftAssert();
		
			
			public void closebrowsers(){

				iosdriver.quit();
			}

			public void navigatetourl() throws InterruptedException, IOException{
				
				//Thread.sleep(5000);	
			APPLICATION_URL = JclubGUIProperties.getInstance().getProperty("qa_DEV_URL").trim();
			iosdriver.navigate().to(APPLICATION_URL);
				
			}


			
			public void click(By by) throws IOException
			{
				
				try
				{
					timeinterval(2);
					iosdriver.findElement(by).click();
					timeinterval(5);
				}
				catch (NoSuchElementException e)
				{
					iosdriver.findElement(by).click();
				}
				catch (Exception e)
				{
					Assert.assertTrue(false, "Fail to click on link : " + by + " on page : " + e.getMessage());
					//rootLogger.warn("Fail to click on link : " + by + " on page : " + selenium.getWrappediosdriver().getCurrentUrl());
				}
			}
			
			
				public void click(WebElement  element){
				
				getHighlightElement(element);
				build.moveToElement(element).click().perform();
			}
			public void sendkeys(By by,String keys){
				getHighlightElement(iosdriver.findElement(by));
				iosdriver.findElement(by).sendKeys(keys);
			}
			
			public void timeinterval(int i) throws InterruptedException{
				
				Thread.sleep(1000 * i );
				
			}
			
			public void getHighlightElement(final WebElement element)
			{
				try
				{
					Wait<WebDriver> wait = new WebDriverWait(iosdriver, 10);
					// Wait for search to complete
					wait.until(new ExpectedCondition<Boolean>()
					{
						
						public Boolean apply(WebDriver webiosdriver)
						{
							return element != null;
						}
					});
					((JavascriptExecutor) iosdriver)
					.executeScript("arguments[0].style.border='2px solid red'", element);
				}
				catch (Exception e)
				{
					//logger.info("Fail to highlight the Element");
				}
			}
			
			public boolean isDisplayed(By by) throws InterruptedException{
				
				timeinterval(1);
				getHighlightElement(iosdriver.findElement(by));
				return iosdriver.findElement(by).isDisplayed();
				
			}
			
			public String getText(By by) throws IOException, InterruptedException
			{
				waitForParticularElement(by, Seconds);
				try
				{
					getHighlightElement(iosdriver.findElement(by));
					return iosdriver.findElement(by).getText().trim();
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
				
				return iosdriver.getCurrentUrl();
			}
			
			public List getelementlist(By by){
			
				List <WebElement> product = iosdriver.findElements(by);
				return product;		
			}
			
			public String getAttribute(By by, String attribute) throws IOException
			{
				try
				{
					
					getHighlightElement(iosdriver.findElement(by));
					return iosdriver.findElement(by).getAttribute(attribute).trim();
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
						iosdriver.findElement(element).isDisplayed();
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
					return iosdriver.findElement(by).getText().trim();
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
					//build.moveToElement(element).build().perform();
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
					element = iosdriver.findElement(by);
					getHighlightElement(iosdriver.findElement(by));
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
			
			
			public void startiphonesafari() throws IOException {
				
				DesiredCapabilities capabilities = new DesiredCapabilities();
				
				capabilities.setCapability("deviceName", "iPhone 6");
				
				capabilities.setCapability("platformName", "iOS");
				
				capabilities.setCapability("platformVersion", "10.3");
				
				capabilities.setCapability(CapabilityType.BROWSER_NAME, "safari"); 
				
				capabilities.setCapability("automationName" ,  "XCUITest");
				
				iosdriver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				
				build = new Actions(iosdriver);
			}
			
			public void closesafariinsimulator() {
				
				iosdriver.quit();
			}
			
			public void verticalSwipe() {
				
				Dimension dim = iosdriver.manage().window().getSize();
				
				int height = dim.getHeight();
				int width = dim.getWidth();
				
				int x = width/2;
				
				int starty = (int)(height*0.80);
				
				int endy = (int)(height*0.20);
				
				
				iosdriver.swipe(x, starty, x, endy, 1000);
					
			}
			
			public boolean verify_video_firstpage() throws IOException, InterruptedException{
				return isDisplayed(By.xpath(".//*[@id='video-modal']//div[@class='modal-body']"));
			}

			public void Close_video_popup() throws IOException{
				click(By.xpath(".//div[@class='modal fade in']//a[@class='close-bottom']//i[@class='icon-cross']"));
			}

			public void dologin() throws IOException{
				click(By.xpath(".//li//a[@class='bold' and @title='Login']//i[@class='icon-user']"));
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
				
				click(By.xpath(".//span[text()='Shop by Category']"));
				click(By.xpath(".//li//a[text()='"+category+"']"));
			}

			public void product_attribute_verifiction(String Category) throws IOException, InterruptedException{
				
				int i = 0;
				List <WebElement> products = getelementlist(By.xpath(".//*[@id='product-list']//a"));
				
				Log.info("=====================================================================");
				Log.info("Numbers of Products in "+Category+" category="+products.size());
				Log.info("=====================================================================");
				
				Reporter.log("=====================================================================");
				Reporter.log("Numbers of Products in "+Category+" category : ="+products.size());
				Reporter.log("=====================================================================");
				
				for (WebElement product:products ){
				
							i++;
							
						Log.info("=====================================================================");
						Log.info("======================== Product :="+i+"==========================");
						Log.info("=====================================================================");
						
						Reporter.log("=====================================================================");
						Reporter.log("======================== Product :="+i+"==========================");
						Reporter.log("=====================================================================");
					
					String product_name = getTextRuntime(By.xpath(".//*[@id='product-list']//a["+i+"]//h5"));
					Log.info("Product Name := "+product_name);
					Reporter.log("Product Name := "+product_name);
					
					String product_price_jclub = getTextRuntime(By.xpath(".//*[@id='product-list']//a["+i+"]//strong"));
					Log.info("Product Sell Price : = "+product_price_jclub);
					Reporter.log("Product Sell Price : = "+product_price_jclub);
					
					String product_price_retail = getTextRuntime(By.xpath(".//*[@id='product-list']//a["+i+"]//del"));
					Log.info("Product Price : = "+product_price_retail);
					Reporter.log("Product Price : = "+product_price_retail);
					
					
					StringTokenizer jp =  new StringTokenizer(product_price_jclub);
					String jclubprice = jp.nextToken("$");
					float jcp = Float.parseFloat(jclubprice);
					
					StringTokenizer rp =  new StringTokenizer(product_price_retail);
					String retailprice = rp.nextToken("$");
					float rep =  Float.parseFloat(retailprice);
					
					if(jcp<rep) {
						Log.info("Jclub price is less then retail price");
						Reporter.log("Jclub price is less then retail price");

					}else
					{
						Log.info("jclub price is greter then retail price");
						Reporter.log("jclub price is greter then retail price");
						softAsserttion.assertTrue(false);					
					}
					
					String product_saving_percentile = getTextRuntime(By.xpath(".//*[@id='product-list']//a["+i+"]//em"));
					Log.info("Product discount percentage := "+product_saving_percentile);
					Reporter.log("Product discount percentage := "+product_saving_percentile);
					
					}
					
				softAsserttion.assertAll();
						
			  }
	
}
	
	

