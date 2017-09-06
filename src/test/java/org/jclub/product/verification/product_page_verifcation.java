package org.jclub.product.verification;

import java.io.IOException;

import org.jclub.coreclasses.browserintialization;
import org.jclub.coreclasses.product_core_page;
import org.junit.Assert;
import org.testng.annotations.Test;

public class product_page_verifcation extends product_core_page{
	
	
	
browserintialization bis = new browserintialization();
	
	@Test
	public void productdetailvarification() throws InterruptedException, IOException{
		
		
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
		
		bis.closebrowsers();
		
		
	}

}
