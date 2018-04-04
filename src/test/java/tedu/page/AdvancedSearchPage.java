package tedu.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdvancedSearchPage {
	WebDriver driver;
	String url = "http://localhost/ws/ecshop/upload/search.php?encode=YToyOntzOjM6ImFjdCI7czoxNToiYWR2YW5jZWRfc2VhcmNoIjtzOjE4OiJzZWFyY2hfZW5jb2RlX3RpbWUiO2k6MTUyMjc0MDQ3ODt9";
	
	@FindBy(how = How.ID,using="keywords")
	@CacheLookup
	WebElement keywords;
	
	@FindBy(how = How.ID,using="select")
	@CacheLookup
	WebElement select;
	
	@FindBy(how = How.ID,using="brand")
	@CacheLookup
	WebElement brand;
	
	@FindBy(how = How.ID,using="min_price")
	@CacheLookup
	WebElement min_price;
	
	@FindBy(how = How.ID,using="max_price")
	@CacheLookup
	WebElement max_price;
	
	@FindBy(how = How.NAME,using="goods_type")
	@CacheLookup
	WebElement goods_type;
	
	@FindBy(how = How.NAME,using="attr[172]")
	@CacheLookup
	WebElement attr172;
	
	@FindBy(how = How.NAME,using="attr[185]")
	@CacheLookup
	WebElement attr185;
	
	@FindBy(how = How.NAME,using="Submit")
	@CacheLookup
	WebElement Submit;
	
	@FindBy(how = How.TAG_NAME,using="b")
	@CacheLookup
	WebElement count;
	
	public AdvancedSearchPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void get(){
		driver.get(url);
	}
	
	public String getCount(){
		return count.getText();
	}
	
	public AdvancedSearchPage advancedSearch(String kw,
			  String cg,
			  String bd,
			  String minp,
			  String maxp,
			  String ext,
			  String d,
			  String cl){
		keywords.clear();
		keywords.sendKeys(kw);
		new Select(select).selectByVisibleText(cg);		
		new Select(brand).selectByVisibleText(bd);
		min_price.clear();
		min_price.sendKeys(minp);
		max_price.clear();
		max_price.sendKeys(maxp);
		new Select(goods_type).selectByVisibleText(ext);
		if (ext.equals("精品手机")){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new Select(attr172).selectByVisibleText(d);
			new Select(attr185).selectByVisibleText(cl);
		}
		Submit.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new AdvancedSearchPage(driver);
	}
}
