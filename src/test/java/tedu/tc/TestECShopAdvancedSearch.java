package tedu.tc;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import tedu.page.AdvancedSearchPage;
import tedu.utils.ReadFile;

public class TestECShopAdvancedSearch {
	WebDriver driver;
	AdvancedSearchPage asp;
	
  @Test(dataProvider = "dp")
  public void f(String kw,
		  String cg,
		  String bd,
		  String minp,
		  String maxp,
		  String ext,
		  String d,
		  String cl,
		  String expCount) {
	  asp.get();
	  asp = asp.advancedSearch(kw, cg, bd, minp, maxp, ext, d, cl);
	  String actCount = asp.getCount();
		assertEquals(actCount,expCount);
  }

  @BeforeMethod
  public void beforeMethod() {
	  driver = new FirefoxDriver();
	  asp = new AdvancedSearchPage(driver);
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

  @DataProvider
  public Object[][] dp() {
    return ReadFile.getTestDataFromExcel("C:\\", "数据_ECShop_高级搜索.xls", "高级搜索");
  }

}
