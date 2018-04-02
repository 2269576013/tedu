package tedu;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewTestChrome {
	WebDriver driver;
  @Test
  public void f() {
	  System.out.println("fffffffffffffffffffffff");

	  assertTrue(true);
  }
  @BeforeMethod
  public void beforeMethod() {
	  String chromePath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"; 
	  String pro_Path = "C:\\"; 
	  String ChromeDriver_Path = pro_Path + "\\chromedriver.exe"; 
	  System.setProperty("webdriver.chrome.bin", chromePath); 
	  System.setProperty("webdriver.chrome.driver", ChromeDriver_Path); 
	  driver = new ChromeDriver();


  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
