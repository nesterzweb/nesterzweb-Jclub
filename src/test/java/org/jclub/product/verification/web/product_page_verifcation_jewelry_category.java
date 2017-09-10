package org.jclub.product.verification.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.jclub.coreclasses.browserintialization;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class product_page_verifcation_jewelry_category {
	
	
	private static Logger Log = Logger.getLogger(product_page_verifcation_jewelry_category.class.getName());

	SoftAssert softAsserttion = new SoftAssert();
	
	browserintialization bis = new browserintialization();

	@BeforeTest
	public void openBrowser() throws InterruptedException, IOException {
		DOMConfigurator.configure("log4j.xml");
		
		
		bis.openbrowsers();
		bis.navigatetourl();
		
	}
	
	@AfterTest
	public void closeBrowser() {
		bis.closebrowsers();
	}
	
	@Test
	public void productdetailvarification() throws InterruptedException, IOException,InvocationTargetException{
		
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
		
		click_on_category("Jewelry");
		
		if(bis.Gecurrenturl()!=null){
			
			Log.info("==========================================");
			Log.info("current page URL is :-" +bis.Gecurrenturl());
			Log.info("==========================================");
			
			Reporter.log("==========================================");
			Reporter.log("current page URL is :-" +bis.Gecurrenturl());
			Reporter.log("==========================================");
			
			
			
			Assert.assertTrue(true);
		}
		product_attribute_verifiction();
	}

	public boolean verify_video_firstpage() throws IOException, InterruptedException{
		return bis.isDisplayed(By.xpath(".//*[@id='video-modal']//div[@class='modal-body']"));
	}
	
	public void Close_video_popup() throws IOException{
		bis.click(By.xpath(".//*[@id='video-modal']//button[@class='close']"));
	}
	
	public void dologin() throws IOException{
		bis.click(By.xpath(".//li//a[@class='bold' and @title='Login']"));
		bis.sendkeys(By.xpath(".//*[@id='customer_email']"), "vivekparmar37@gmail.com");
		bis.sendkeys(By.xpath(".//*[@id='customer_password']"), "9623007654");
		bis.click(By.xpath(".//*[@id='new_customer']//input[@type='submit']"));
	}
	
	public boolean verify_signin_success() throws IOException, InterruptedException{
		String s= bis.getText(By.xpath("//div[@class='alert alert-success']"));
		Log.info(s);
		return bis.isDisplayed(By.xpath("//div[@class='alert alert-success']"));
	}
	
	public void click_on_category(String category) throws IOException{
		
		bis.click(By.xpath("//a[text()='"+category+"']"));
	}
	
	public void product_attribute_verifiction() throws IOException, InterruptedException{
		
		int i = 0;
		List <WebElement> products = bis.getelementlist(By.xpath(".//*[@id='product-list']//a"));
		
		Log.info("=====================================================================");
		Log.info("Numbers of Products in jewelry category="+products.size());
		Log.info("=====================================================================");
		
		Reporter.log("=====================================================================");
		Reporter.log("Numbers of Products in jewelar category : ="+products.size());
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
			
			bis.timeinterval(1);	
			bis.click(product);
			
			if(bis.isDisplayed(By.id("product-modal-body"))) {
			
			String product_name = bis.getTextRuntime(By.id("product-name"));
			Log.info("Product Name := "+product_name);
			Reporter.log("Product Name := "+product_name);
			
			String product_price_jclub = bis.getTextRuntime(By.xpath(".//*[@id='product-price']"));
			Log.info("Product Sell Price : = "+product_price_jclub);
			Reporter.log("Product Sell Price : = "+product_price_jclub);
			
			String product_price_retail = bis.getTextRuntime(By.xpath(".//*[@id='product-retail']"));
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
			
			String image_url = bis.getAttribute(By.id("product-image"), "src");
			Log.info("Product Image URL : = "+image_url);
			Reporter.log("Product Image URL : = "+image_url);
			
			String product_saving_percentile = bis.getTextRuntime(By.xpath(".//*[@id='product-savings']"));
			Log.info("Product discount percentage := "+product_saving_percentile);
			Reporter.log("Product discount percentage := "+product_saving_percentile);
			
			String product_description = bis.getTextRuntime(By.id("description-tab"));
			Log.info("Product description := "+product_description);
			Reporter.log("Product description := "+product_description);
			
			bis.click(By.xpath(".//*[@id='pdp-modal']//button[@class='close']"));
			
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
				Reporter.log("Sold out product  so URL not able to open");
			}
				
		}
		softAsserttion.assertAll();
	}
	
}
