package org.jclub.product.verification.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.jclub.coreclasses.Reporter;
import org.jclub.coreclasses.browserintialization;

import org.junit.Assert;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class product_page_verifcation_lifestyle_category {
	
	
	private static Logger Log = Logger.getLogger(product_page_verifcation_lifestyle_category.class.getName());
	
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
		
		if(bis.verify_video_firstpage()){
			
			Assert.assertTrue(true);
			Log.info("video display on first page when user open link in fresh tab ");
			Reporter.log("video display on first page when user open link in fresh tab ");
			
		}else{
			Assert.assertTrue(false);
			Log.info("video is not display on first page when user open link in fresh tab ");
			Reporter.log("video is not display on first page when user open link in fresh tab ");
		
		}
		
		bis.Close_video_popup();
		
		bis.dologin();
		
		if(bis.verify_signin_success()){
			Assert.assertTrue(true );
		}
		
		bis.click_on_category("Lifestyle");
		
		if(bis.Gecurrenturl()!=null){
			
			Log.info("==========================================");
			Log.info("current page URL is :-" +bis.Gecurrenturl());
			Log.info("==========================================");
			
			Reporter.log("==========================================");
			Reporter.log("current page URL is :-" +bis.Gecurrenturl());
			Reporter.log("==========================================");
			
			
			
			Assert.assertTrue(true);
		}
		bis.product_attribute_verifiction("Lifestyle");
	}
}
