package org.jclub.product.verification.iphone;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.jclub.coreclasses.Reporter;
import org.jclub.coreclasses.iossafariintialization;
import org.junit.Assert;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class iphone_product_page_verification_pets_category {
	


	private static Logger Log = Logger.getLogger(iphone_product_page_verification_pets_category.class.getName());
	
	iossafariintialization ios = new iossafariintialization();
	
	@BeforeTest
	public void openBrowser() throws InterruptedException, IOException {
		DOMConfigurator.configure("log4j.xml");
		ios.startiphonesafari();
		ios.navigatetourl();
	}
	
	@AfterTest
	public void closeBrowser() {
		ios.closesafariinsimulator();
	}
	

	@Test
	public void startsafari() throws IOException, InterruptedException {
	
	Log.info("This is for test from iphone safari browser");
	Reporter.log("This is for test from iphone safari browser");
	
	
	if(ios.verify_video_firstpage()){
		
		Assert.assertTrue(true);
		Log.info("video display on first page when user open link in fresh tab ");
		Reporter.log("video display on first page when user open link in fresh tab ");
		
	}else{
		Assert.assertTrue(false);
		Log.info("video is not display on first page when user open link in fresh tab ");
		Reporter.log("video is not display on first page when user open link in fresh tab ");
	
	}

	ios.Close_video_popup();

	ios.dologin();
	
	if(ios.verify_signin_success()){
		Assert.assertTrue(true );
	}
	
	ios.click_on_category("Pets");
	
	if(ios.Gecurrenturl()!=null){
		
		Log.info("==========================================");
		Log.info("current page URL is :-" +ios.Gecurrenturl());
		Log.info("==========================================");
		
		Reporter.log("==========================================");
		Reporter.log("current page URL is :-" +ios.Gecurrenturl());
		Reporter.log("==========================================");
		
		Assert.assertTrue(true);

	}
	
	ios.product_attribute_verifiction("Pets");
}

}
