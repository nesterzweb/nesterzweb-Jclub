package org.jclub.product.verification.android;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.jclub.coreclasses.Reporter;
import org.jclub.coreclasses.androidchromeintialization;
import org.junit.Assert;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class android_product_page_verification_bestseller_category {
	
	private static Logger Log = Logger.getLogger(android_product_page_verification_bestseller_category.class.getName());
	
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
		
		
		if(adc.verify_video_firstpage()){
			
			Assert.assertTrue(true);
			Log.info("video display on first page when user open link in fresh tab ");
			Reporter.log("video display on first page when user open link in fresh tab ");
			
		}else{
			Assert.assertTrue(false);
			Log.info("video is not display on first page when user open link in fresh tab ");
			Reporter.log("video is not display on first page when user open link in fresh tab ");
		}

		adc.Close_video_popup();

		adc.dologin();
		
		if(adc.verify_signin_success()){
			Assert.assertTrue(true );
		}
		
		adc.click_on_category("Bestseller");
		
		if(adc.Gecurrenturl()!=null){
			 
			Log.info("==========================================");
			Log.info("current page URL is :-" +adc.Gecurrenturl());
			Log.info("==========================================");
			
			Reporter.log("==========================================");
			Reporter.log("current page URL is :-" +adc.Gecurrenturl());
			Reporter.log("==========================================");
			
			Assert.assertTrue(true);
		}
		
		adc.product_attribute_verifiction("Bestseller");
	}
}
