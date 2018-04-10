package tedu.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import tedu.utils.Constants;
import tedu.utils.Utils;


public class BasePage {	
	public WebDriver driver;
	public String url;

	public BasePage(WebDriver driver){ 
		this.driver = driver; 
		PageFactory.initElements(//driver,this);
				new AjaxElementLocatorFactory(driver, Constants.EXPLICIT_WAIT) , this);
	}
	/**
	 * 通过initElements方法初始化的各个页面对象，
	 * AjaxElementLocatorFactory方法可以查找元素时都会在指定的TIMEOUT时间内不断重试，
	 * 如果在指定时间内定位到元素则马上继续，
	 * 如果指定时间内未找到则抛出NoSuchElementException异常。
	 * @param driver
	 * @param title
	 */
	public BasePage(WebDriver driver, String title) {
        this.driver = driver;
        //显式等待标题包含预期值
        Utils.explicitWaitTitle(title);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.EXPLICIT_WAIT) , this);
    }
	
	//打开页面
	public void get() {
		driver.get(url);
		driver.manage().window().maximize();
	}
	//获得页面源码
	public String getPageSource() {
		return driver.getPageSource();
	}
	//获得页面标题
	public String getTitle() {
		return driver.getTitle();
	}
	//获得URL
	public String getURL() {
		return driver.getCurrentUrl();
	}
}
