package org.jclub.product.verification.iphone;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.jclub.coreclasses.iossafariintialization;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class iphone_product_page_verification_men_category {
	

	private static Logger Log = Logger.getLogger(iphone_product_page_verification_men_category.class.getName());

	SoftAssert softAsserttion = new SoftAssert();
	
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
	
	click_on_category("Men");
	
	if(ios.Gecurrenturl()!=null){
		
		Log.info("==========================================");
		Log.info("current page URL is :-" +ios.Gecurrenturl());
		Log.info("==========================================");
		
		Reporter.log("==========================================");
		Reporter.log("current page URL is :-" +ios.Gecurrenturl());
		Reporter.log("==========================================");
		
		Assert.assertTrue(true);

	}
	
	product_attribute_verifiction();
}


public boolean verify_video_firstpage() throws IOException, InterruptedException{
	return ios.isDisplayed(By.xpath(".//*[@id='video-modal']//div[@class='modal-body']"));
}

public void Close_video_popup() throws IOException{
	ios.click(By.xpath(".//div[@class='modal fade in']//a[@class='close-bottom']//i[@class='icon-cross']"));
}

public void dologin() throws IOException{
	ios.click(By.xpath(".//li//a[@class='bold' and @title='Login']//i[@class='icon-user']"));
	ios.sendkeys(By.xpath(".//*[@id='customer_email']"), "vivekparmar37@gmail.com");
	ios.sendkeys(By.xpath(".//*[@id='customer_password']"), "9623007654");
	ios.click(By.xpath(".//*[@id='new_customer']//input[@type='submit']"));
}

public boolean verify_signin_success() throws IOException, InterruptedException{
	String s= ios.getText(By.xpath("//div[@class='alert alert-success']"));
	Log.info(s);
	return ios.isDisplayed(By.xpath("//div[@class='alert alert-success']"));
}

public void click_on_category(String category) throws IOException{
	
	ios.click(By.xpath(".//span[text()='Shop by Category']"));
	ios.click(By.xpath(".//li//a[text()='"+category+"']"));
}

public void product_attribute_verifiction() throws IOException, InterruptedException{
	
	int i = 0;
	List <WebElement> products = ios.getelementlist(By.xpath(".//*[@id='product-list']//a"));
	
	Log.info("=====================================================================");
	Log.info("Numbers of Products in men category="+products.size());
	Log.info("=====================================================================");
	
	Reporter.log("=====================================================================");
	Reporter.log("Numbers of Products in men category : ="+products.size());
	Reporter.log("=====================================================================");
	
	for (WebElement product:products ){
	
				i++;
				
			Log.info("=====================================================================");
			Log.info("======================== Product :="+i+"==========================");
			Log.info("=====================================================================");
			
			Reporter.log("=====================================================================");
			Reporter.log("======================== Product :="+i+"==========================");
			Reporter.log("=====================================================================");
		
		String product_name = ios.getTextRuntime(By.xpath(".//*[@id='product-list']//a["+i+"]//h5"));
		Log.info("Product Name := "+product_name);
		Reporter.log("Product Name := "+product_name);
		
		String product_price_jclub = ios.getTextRuntime(By.xpath(".//*[@id='product-list']//a["+i+"]//strong"));
		Log.info("Product Sell Price : = "+product_price_jclub);
		Reporter.log("Product Sell Price : = "+product_price_jclub);
		
		String product_price_retail = ios.getTextRuntime(By.xpath(".//*[@id='product-list']//a["+i+"]//del"));
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
		
		String product_saving_percentile = ios.getTextRuntime(By.xpath(".//*[@id='product-list']//a["+i+"]//em"));
		Log.info("Product discount percentage := "+product_saving_percentile);
		Reporter.log("Product discount percentage := "+product_saving_percentile);
		
		}
		
	softAsserttion.assertAll();
			
	}
	
}
