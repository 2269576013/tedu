package tedu.tc;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import tedu.utils.Utils;

public class BaseTest {
	public WebDriver driver;

  @BeforeMethod
  @Parameters({"browser"})
  public void beforeMethod(String b) {
	  driver = Utils.openBrowser(b);	  
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }
}
