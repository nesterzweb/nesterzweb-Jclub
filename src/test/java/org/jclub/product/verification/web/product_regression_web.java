package org.jclub.product.verification.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.jclub.coreclasses.Reporter;
import org.jclub.coreclasses.browserintialization;
import org.junit.Assert;
import org.openqa.selenium.By;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class product_regression_web {
	
	private static Logger Log = Logger.getLogger(product_page_verifcation_women_category.class.getName());

	SoftAssert softAsserttion = new SoftAssert();
	
	browserintialization bis = new browserintialization();
	
	String productColor;
	
	String productSize;
	
	String productNameCart;
	
	String productPriceCart;
	
	String SizeandColorCart;
	
	String productColorCart;
	
	String productSizeCart;
	
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
		
		bis.clearCartProduct();
		
		bis.click_on_category("Pets");
		
		if(bis.Gecurrenturl()!=null){
			
			Log.info("==========================================");
			Log.info("current page URL is :-" +bis.Gecurrenturl());
			Log.info("==========================================");
			
			Reporter.log("==========================================");
			Reporter.log("current page URL is :-" +bis.Gecurrenturl());
			Reporter.log("==========================================");
			
			
			
			Assert.assertTrue(true);
		}
		
		Reporter.log("==========================================");
		Reporter.log("Click on product:-");
		Reporter.log("==========================================");
		
		bis.click(By.xpath("(.//*[@id='product-list']//a)[3]"));
		
		if(bis.isDisplayed(By.id("product-modal-body"))) {
			
			String product_name = bis.getTextRuntime(By.id("product-name"));
			Log.info("Product Name := "+product_name);
			Reporter.log("Product Name := "+product_name);
		
			
			String split2[]= product_name.split("Assorted");
			
			String product_name_filter = split2[0];
			System.out.println(product_name_filter);
			
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
			
				Log.info("Jclub price is less than retail price");
				Reporter.log("Jclub price is less than retail price");
			}else
			{
				String bugDescription = "jclub price is greater than retail price.";	
				Reporter.log("<b> <font color='red' size='2'>BUG DESCRIPTION :" + bugDescription + "</font></b>");
				Log.info("jclub price is greater than retail price");
				
				softAsserttion.assertTrue(false);					
			}
			
			String image_url = bis.getAttribute(By.id("product-image"), "src");
			Log.info("Product Image URL : = "+image_url);
			Reporter.log("Product Image URL : = "+image_url);
			
			String product_saving_percentile = bis.getTextRuntime(By.xpath(".//*[@id='product-savings']"));
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
			
			String product_description = bis.getTextRuntime(By.id("description-tab"));
			Log.info("Product description := "+product_description);
			Reporter.log("Product description := "+product_description);
			
		
			
		
		Reporter.log("==========================================");
		Reporter.log("Click on add to cart button");
		Reporter.log("==========================================");
	
		bis.click(By.id("add-item-btn"));
		
		if(bis.isDisplayed(By.id("add-item-error"))){
			bis.click(By.xpath(".//label[@for='options-color-0']"));
			 productColor = bis.getText(By.xpath(".//label[@for='options-color-0']"));
			bis.click(By.id("add-item-btn"));
			if(bis.isDisplayed(By.id("add-item-error"))) {
				bis.click(By.xpath(".//label[@for='options-size-0']"));
				productSize = bis.getText(By.xpath(".//label[@for='options-size-0']"));
				bis.click(By.id("add-item-btn"));
			}
		}
		
		if(bis.isDisplayed(By.id("product-modal-body"))) {
			
			Reporter.log("==========================================");
			Reporter.log("Add to cart button working as expected");
			Reporter.log("==========================================");
			
			Reporter.log("==========================================");
			Reporter.log("Add to cart button redirect to cart page.");
			Reporter.log("==========================================");
			
			productNameCart = bis.getText(By.xpath("(//div[@class='details']//strong)[1]"));
			System.out.println(productNameCart);
			
			productPriceCart = bis.getText(By.xpath("(//div[@class='details']//strong)[2]"));
			
			StringTokenizer jpc =  new StringTokenizer(productPriceCart);
			String jclubpricecart = jpc.nextToken("$");
			float jcpc = Float.parseFloat(jclubpricecart);
			
 			if(bis.isDisplayed(By.xpath("//div[@class='options']"))) {
				
				
				SizeandColorCart =bis.getText(By.xpath("//div[@class='options']"));
				
				String split[]= SizeandColorCart.split("\\s+");
				
				if(SizeandColorCart.contains(",")) {
					
					String split1[]= split[1].split(",");
					productSizeCart = split1[0];
				}
			productColorCart = split[3];
				
				
			}
			
			if(productNameCart.equalsIgnoreCase(product_name_filter.trim())) {
				
				Reporter.log("==========================================");
				Reporter.log("product name on product description page and cart page display same");
				Reporter.log("==========================================");
				
			}else {
				Reporter.log("==========================================");
				Reporter.log("product name on product description page and cart page not display same");
				Reporter.log("==========================================");
				
				Assert.assertTrue(false);	
			}
			
			
			Float obj1 = new Float(jcp);
		    Float obj2 = new Float(jcpc);
			
			if(obj1.equals(obj2)) {
				
				Reporter.log("==========================================");
				Reporter.log("product price on product description page and cart page display same");
				Reporter.log("==========================================");
				
			}else {
				Reporter.log("==========================================");
				Reporter.log("product price on product description page and cart page not display same");
				Reporter.log("==========================================");
				
				Assert.assertTrue(false);	
			}
			
			
			if(productColorCart.equalsIgnoreCase(productColor)) {
				
				Reporter.log("==========================================");
				Reporter.log("Selected product color display on product cart");
				Reporter.log("==========================================");
				
			}else {
				Reporter.log("==========================================");
				Reporter.log("Selected product color not display on product cart");
				Reporter.log("==========================================");
				
				Assert.assertTrue(false);	
			}
			
			if(productSizeCart.equalsIgnoreCase(productSize)) {
				
				Reporter.log("==========================================");
				Reporter.log("Selected product size display on product cart");
				Reporter.log("==========================================");
				
			}else {
				Reporter.log("==========================================");
				Reporter.log("Selected product size not display on product cart");
				Reporter.log("==========================================");
				
				Assert.assertTrue(false);	
			}
			
			
			bis.click(By.id("checkout-btn"));
			
			bis.click(By.xpath("(//input[@value='new'])[1]"));
			
		}
		
		
	}
	
	
	}

}
