package org.jclub.product.verification;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.jclub.coreclasses.browserintialization;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class product_page_verifcation {
	
	
	
browserintialization bis = new browserintialization();

	
	
	@Test
	public void productdetailvarification() throws InterruptedException, IOException,InvocationTargetException{
		
		
		bis.openbrowsers();
		bis.navigatetourl();
		
		
		if(verify_video_firstpage()){
			
			Assert.assertTrue(true);
			System.out.println("video display on first page when user open link in fresh tab ");
			
		}else{
			Assert.assertTrue(false);
			System.out.println("video is not display on first page when user open link in fresh tab ");
		}
		
		Close_video_popup();
		
		dologin();
		if(verify_signin_success()){
			
			Assert.assertTrue(true );
			
		}
		
		click_on_category("Women");
		
		if(bis.Gecurrenturl()!=null){
			
			Assert.assertTrue(true);
		
			
		}
		product_attribute_verifiction();
		
		
		
		
		
		
		bis.closebrowsers();
		
		
	}

	public boolean verify_video_firstpage() throws IOException{
		
		return bis.isDisplayed(By.xpath(".//*[@id='video-modal']//div[@class='modal-body']"));
	}
	
	public void Close_video_popup(){
		
		bis.click(By.xpath(".//*[@id='video-modal']//button[@class='close']"));
	}
	
	public void dologin(){
		
		bis.click(By.xpath("//a[@title='Login']"));
		bis.sendkeys(By.xpath(".//*[@id='customer_email']"), "vivekparmar37@gmail.com");
		bis.sendkeys(By.xpath(".//*[@id='customer_password']"), "9623007654");
		bis.click(By.xpath(".//*[@id='new_customer']//input[@type='submit']"));
	
	}
	
	public boolean verify_signin_success() throws IOException{
		String s= bis.getText(By.xpath("//div[@class='alert alert-success']"));
		System.out.println(s);
		return bis.isDisplayed(By.xpath("//div[@class='alert alert-success']"));
	}
	
	public void click_on_category(String category){
		
		bis.click(By.xpath("//a[text()='"+category+"']"));
	
	}
	
	public void product_attribute_verifiction() throws IOException{
		
		List <WebElement> products = bis.getelementlist(By.xpath(".//*[@id='product-list']//a"));
		System.out.println(products.size());
		for (WebElement product:products ){
			
			String product_class_name = product.getAttribute("class");
			
			
			if(!product_class_name.contains("sold-out")){
			

			bis.click(product);
			
			String product_name = bis.getText(By.xpath(".//*[@id='product-name']"));
			System.out.println(product_name);
			
			String product_price_jclub = bis.getText(By.xpath(".//*[@id='product-price']"));
			System.out.println(product_price_jclub);
			
			String image_url = bis.getAttribute(By.id("product-image"), "src");
			System.out.println(image_url);
			
			String product_price_retail = bis.getText(By.xpath(".//*[@id='product-retail']"));
			System.out.println(product_price_retail);
			
			
			
			String product_saving_percentile = bis.getText(By.xpath(".//*[@id='product-savings']"));
			System.out.println(product_saving_percentile);
			
			bis.click(By.xpath(".//*[@id='pdp-modal']//button[@class='close']"));
			
			}
		}
	}
}
