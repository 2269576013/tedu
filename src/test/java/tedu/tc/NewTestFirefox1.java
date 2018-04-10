package tedu.tc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import tedu.utils.Utils;

public class NewTestFirefox1 {
	WebDriver driver;
  @Test
  public void f2() {
	  driver.get("file:///E:/example/alert.html");
	  WebElement element = driver.findElement(By.id("c1"));
	  Utils.click(element);
	  Utils.sleep(1000);
	  Utils.assertAlertContainsTextAndDismiss("www","abc");
	  Utils.sleep(1000);
  }

  @BeforeMethod
  public void beforeMethod() {
	  driver=Utils.openBrowser("chrome");
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
