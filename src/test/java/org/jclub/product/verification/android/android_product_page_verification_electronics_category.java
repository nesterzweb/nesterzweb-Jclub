package org.jclub.product.verification.android;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.jclub.coreclasses.androidchromeintialization;
import org.jclub.product.verification.iphone.iphone_product_page_verification_women_category;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class android_product_page_verification_electronics_category {
	
	private static Logger Log = Logger.getLogger(android_product_page_verification_electronics_category.class.getName());

	SoftAssert softAsserttion = new SoftAssert();
	
	androidchromeintialization adc = new androidchromeintialization();

	
	
	@BeforeTest
	public void openBrowser() throws InterruptedException, IOException {
		DOMConfigurator.configure("log4j.xml");
		adc.startandroidchrome();
		adc.navigatetourl();
	}
	
	@AfterTest
	public void closeBrowser() {
		adc.closebrowsers();
	}
	
	
	@Test
	public void productverificationforandroid() throws IOException, InterruptedException {
		
		Log.info("This is for test from android chrome browser");
		Reporter.log("This is for test from android chrome browser");
		
		
		if(verify_video_firstpage()){
			
			Assert.assertTrue(true);
			Log.info("video display on first page when user open link in fresh tab ");
			Reporter.log("video display on first page when user open link in fresh tab ");
			
		}else{
			Assert.assertTrue(false);
			Log.info("video is not display on first page when user open link in fresh tab ");
			Reporter.log("video is not display on first page when user open link in fresh tab ");
		
		}

		Close_video_popup();

		dologin();
		
		if(verify_signin_success()){
			Assert.assertTrue(true );
		}
		
		click_on_category("Electronics");
		
		if(adc.Gecurrenturl()!=null){
			
			Log.info("==========================================");
			Log.info("current page URL is :-" +adc.Gecurrenturl());
			Log.info("==========================================");
			
			Reporter.log("==========================================");
			Reporter.log("current page URL is :-" +adc.Gecurrenturl());
			Reporter.log("==========================================");
			
			Assert.assertTrue(true);

		}
		
		product_attribute_verifiction();
		
		
	}
	
	
	public boolean verify_video_firstpage() throws IOException, InterruptedException{
		return adc.isDisplayed(By.xpath(".//*[@id='video-modal']//div[@class='modal-body']"));
	}

	public void Close_video_popup() throws IOException{
		adc.click(By.xpath(".//div[@class='modal fade in']//a[@class='close-bottom']//i[@class='icon-cross']"));
	}

	public void dologin() throws IOException{
		adc.click(By.xpath(".//li//a[@class='bold' and @title='Login']//i[@class='icon-user']"));
		adc.sendkeys(By.xpath(".//*[@id='customer_email']"), "vivekparmar37@gmail.com");
		adc.sendkeys(By.xpath(".//*[@id='customer_password']"), "9623007654");
		adc.click(By.xpath(".//*[@id='new_customer']//input[@type='submit']"));
	}

	public boolean verify_signin_success() throws IOException, InterruptedException{
		String s= adc.getText(By.xpath("//div[@class='alert alert-success']"));
		Log.info(s);
		return adc.isDisplayed(By.xpath("//div[@class='alert alert-success']"));
	}

	public void click_on_category(String category) throws IOException{
		
		adc.click(By.xpath(".//span[text()='Shop by Category']"));
		adc.click(By.xpath(".//li//a[text()='"+category+"']"));
	}

	public void product_attribute_verifiction() throws IOException, InterruptedException{
		
		int i = 0;
		List <WebElement> products = adc.getelementlist(By.xpath(".//*[@id='product-list']//a"));
		
		Log.info("=====================================================================");
		Log.info("Numbers of Products in electronics category="+products.size());
		Log.info("=====================================================================");
		
		Reporter.log("=====================================================================");
		Reporter.log("Numbers of Products in electronics category : ="+products.size());
		Reporter.log("=====================================================================");
		
		for (WebElement product:products ){
		
					i++;
					
				Log.info("=====================================================================");
				Log.info("======================== Product :="+i+"==========================");
				Log.info("=====================================================================");
				
				Reporter.log("=====================================================================");
				Reporter.log("======================== Product :="+i+"==========================");
				Reporter.log("=====================================================================");
			
			String product_name = adc.getTextRuntime(By.xpath(".//*[@id='product-list']//a["+i+"]//h5"));
			Log.info("Product Name := "+product_name);
			Reporter.log("Product Name := "+product_name);
			
			String product_price_jclub = adc.getTextRuntime(By.xpath(".//*[@id='product-list']//a["+i+"]//strong"));
			Log.info("Product Sell Price : = "+product_price_jclub);
			Reporter.log("Product Sell Price : = "+product_price_jclub);
			
			String product_price_retail = adc.getTextRuntime(By.xpath(".//*[@id='product-list']//a["+i+"]//del"));
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
			
			String product_saving_percentile = adc.getTextRuntime(By.xpath(".//*[@id='product-list']//a["+i+"]//em"));
			Log.info("Product discount percentage := "+product_saving_percentile);
			Reporter.log("Product discount percentage := "+product_saving_percentile);
			
			}
			
		softAsserttion.assertAll();
				
		}
		
	
}
