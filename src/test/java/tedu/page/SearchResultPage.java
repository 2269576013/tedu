package tedu.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SearchResultPage extends BasePage{
	
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
	
	public SearchResultPage(WebDriver driver){
		super(driver);
	}
	
	public SearchResultPage(WebDriver driver,String title){
		super(driver,title);
	}
	
	public String getCount(){
		return count.getText();
	}

}
