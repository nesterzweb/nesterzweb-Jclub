package org.jclub.coreclasses;

import java.io.IOException;

import org.openqa.selenium.By;

public class product_core_page {
	
	browserintialization bis = new browserintialization();
	
	public boolean verify_video_firstpage() throws IOException{
	
		
		return bis.isDisplayed(By.xpath(".//*[@id='video-modal']//div[@class='modal-body']"));
	}
	
	public void Close_video_popup(){
		
		bis.click(By.xpath(".//*[@id='video-modal']//button[@class='close']"));
		
	}
	
	
}
