package tedu.tc;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import tedu.utils.Log;
import tedu.utils.Utils;

public class NewTestFirefox {
	WebDriver driver;
  @Test(groups={"g1"})
  public void f1() {
	  driver.get("file:///E:/example/select.html");
	  WebElement brand = driver.findElement(By.id("brand"));
	  Utils.selectDropDown(brand, "byvalue", "9");
	  Utils.sleep(3000);
	  System.out.println("fffffffffffffffffffffff");
	  assertTrue(true);
  }
  @Test
  public void f2() {
	  driver.get("file:///E:/example/link.html");
	  WebElement element = driver.findElement(By.linkText("链接到id.html"));
	  Utils.clickAndWait(element);
	  Utils.sleep(1000);
	  System.out.println("fffffffffffffffffffffff");
	  assertTrue(true);
	  Log.info("Page is loaded ");
	  Log.warn("Page is loading……");
	  Log.error("Unable to Open Browser.");
	  Log.info("网页加载完毕！");
  }
  

@Test
public void f3() {
  driver.get("file:///E:/example/id.html");
  WebElement element = driver.findElement(By.id("username"));
  Utils.inputValue(element, "jack");
}
@Test
public void f4Frame() {
  driver.get("file:///E:/example/main1.html");
  WebElement element = driver.findElement(By.name("f1"));
  System.out.println(Utils.getElementStatus(element));

}  

  @BeforeMethod
  public void beforeMethod() {
	  driver=Utils.openBrowser("firefox");
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
