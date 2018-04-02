package tedu;

import static org.testng.Assert.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewTestIE {
	WebDriver driver;
  @Test
  public void f() {
	  System.out.println("fffffffffffffffffffffff");
	  assertEquals(1,1);
	  assertTrue(true);
  }
  @BeforeMethod
  public void beforeMethod() {
	  String IEPath = "C:\\Program Files\\Internet Explorer\\iexplore.exe"; 
	  //String IEPath_64 = "C:\Program Files (x86)\Internet Explorer\iexplore.exe"; 
	  String pro_Path = "C:\\"; 
	  System.out.println(pro_Path);
	  String IEDriver_Path = pro_Path + "\\IEDriverServer.exe"; 
	  System.setProperty("webdriver.ie.bin", IEPath); 
	  System.setProperty("webdriver.ie.driver", IEDriver_Path); 
	  DesiredCapabilities ieCapabilities = DesiredCapabilities .internetExplorer(); 
	  ieCapabilities .setCapability( InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); 
	  driver = new InternetExplorerDriver(ieCapabilities);


  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
