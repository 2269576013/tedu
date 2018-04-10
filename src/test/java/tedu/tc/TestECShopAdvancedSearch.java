package tedu.tc;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import tedu.page.AdvancedSearchPage;
import tedu.page.SearchResultPage;
import tedu.utils.ReadFile;

public class TestECShopAdvancedSearch extends BaseTest{
	AdvancedSearchPage asp;
	SearchResultPage srp;
	
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
	  asp = new AdvancedSearchPage(driver);
	  asp.get();
	  srp = asp.advancedSearch(kw, cg, bd, minp, maxp, ext, d, cl);
	  String actCount = srp.getCount();
		assertEquals(actCount,expCount);
  }

  @DataProvider(parallel=true)
  public Object[][] dp() {
    return ReadFile.getTestDataFromExcel("C:\\", "数据_ECShop_高级搜索.xlsx", "高级搜索");
  }

}
