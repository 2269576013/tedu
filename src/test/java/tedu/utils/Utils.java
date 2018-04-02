package tedu.utils;

import static org.testng.Assert.*;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Utils {
	public static WebDriver driver;
	public static boolean acceptNextAlert = true;
	
	/**
	 * 截图
	 * @param sTestCaseName
	 */
	public static void takeScreenshot(String sTestCaseName) {
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh_mm_ss");
		Date date = new Date();	
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File(Constants.SCREENSHOT+ sTestCaseName +"/"+sTestCaseName+ " # " + dateformat.format(date)+ ".png"));
		} catch (Exception e) {
			Log.error("Issue in Taking Screenshot");
		}
	}
	
	/** 
     * 执行js方法 
     * 
     * @param js
     * @return void 
     */  
    public static void excuteJS(String js) {
    	Log.info("excuteJS begin!");
        try {
        	Log.info("js:"+js);
            ((JavascriptExecutor) driver).executeScript(js);  
        } catch (Exception e) {  
            e.printStackTrace();
            Log.error(e.getMessage());
        } finally{
            Log.info("excuteJS end!");
        }
    }

    /** 
     * 执行js方法 
     * 
     * @param js
     * @param arg1
     * @return Object 
     */  
    public static Object excuteJS1(String js,Object... arg1) {
    	Log.info("excuteJS begin!");
        try {
        	Log.info("js:"+js);
        	Log.info("js arg1:"+arg1);
            return ((JavascriptExecutor) driver).executeScript(js,arg1); 
        } catch (Exception e) {  
            e.printStackTrace();
            Log.error(e.getMessage());
            return null;  
        } finally{
            Log.info("excuteJS end!");
        }
    }
    /** 
     * 执行js方法 
     * 
     * @param js
     * @param arg1
     * @return Object 
     */  
    public static List<WebElement> excuteJS2(String js,Object... arg1) {
    	Log.info("excuteJS begin!");
        try {
        	Log.info("js:"+js);
        	Log.info("js arg1:"+arg1);
			List<WebElement> lst = (List<WebElement>)
			((JavascriptExecutor)driver).executeScript(js,arg1);
			return lst;
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(e.getMessage());
            return null;  
        } finally{
            Log.info("excuteJS end!");
        }
    }
	/**
	 * 等待固定时间
	 */
	public static void sleep(long ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Log.error("The sleep thread is interrupted.");
		}
	}
    
	/**
	 * 等待网页加载完毕，一直等待，无终止条件
	 * @throws Exception
	 */
	public static void waitForPageLoad(){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		String js = "return document.readyState;";
		while (!"complete".equals((String)jse.executeScript(js))) {
			Log.warn("Page is loading……");
			sleep(1000);
		}
		Log.info("The page is loaded.");
	}
	/**
	 * 等待网页加载完毕，30秒作为最多等待时间
	 * @throws Exception
	 */
	public static void waitForPageLoad30(){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		String js4 = "return document.readyState;";
		boolean flag = false;
		//最多等待30秒
		for (int i=1;i<=30;i++){
		 if("complete".equals((String)jse.executeScript(js4))) {
			 flag=true;
			 Log.info("Page is loaded in "+i+" seconds.");
			 break;
		 }
		 sleep(1000);
		}
		if (!flag){
			Log.warn("Page is not loaded in 30 seconds.");
		}
	}
	
	/**
	 * 自定义显示等待的方式等待页面加载完毕
	 * @throws Exception
	 */
	public static void waitForPageLoad1(){	
		(new WebDriverWait(driver, 80)).until(new ExpectedCondition<Boolean>() {
		@Override 
    	public Boolean apply(WebDriver dr) {
		try {Boolean value = ((JavascriptExecutor) dr).executeScript("return document.readyState").equals("complete");
		    	return value;
		 } catch (Exception e) {return Boolean.FALSE;}
	    }
	    });
		Log.info("The page is loaded.");
	}


	/**
	 * 通过JQuery方法等待JS执行结束
	 * @throws Exception
	 */
	public static void waitForJSResult(){
		boolean b = (Boolean) ((JavascriptExecutor) driver).executeScript("return window.jQuery != undefined && jQuery.active === 0");
		if(b){
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(new ExpectedCondition<Boolean>(){
				@Override
				public Boolean apply(WebDriver d){
					return (Boolean)((JavascriptExecutor) d).executeScript("return jQuery.active == 0");
				}
			}); 
			Log.info("The JS is done.");
		} else {
			Log.error("The JS is not done.");
		}
	}

	/**
	 * 启动浏览器
	 * @param browser
	 * @return WebDriver
	 */
	public static WebDriver openBrowser(String browser) {
		try {
			if (browser.equalsIgnoreCase("firefox")) {
				FirefoxProfile profile = new FirefoxProfile();
				//设置不要禁用混合内容
				profile.setPreference("security.mixed_content.block_active_content", false);
				profile.setPreference("security.mixed_content.block_display_content", true);
				//设置自动下载
				//1.不显示下载管理器
				profile.setPreference("browser.download.manager.showWhenStarting", false);
				//2.指定自动下载的文件类型
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/octet-stream,application/vnd.ms-excel,text/csv,application/zip");
				//3.默认下载文件夹，0是桌面、1是默认系统用户的”下载“，2是自定义文件夹
				profile.setPreference("browser.download.folderList", 2);
				//4.设置自定义文件夹
				profile.setPreference("browser.download.dir", "D:\\downloads");
				//启动Firefox
				driver = new FirefoxDriver(profile);
			}else if (browser.equalsIgnoreCase("ie")){
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability("nativeEvents", true);    
				ieCapabilities.setCapability("unexpectedAlertBehaviour", "accept");
				ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
				ieCapabilities.setCapability("disable-popup-blocking", true);
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				ieCapabilities.setCapability("requireWindowFocus", false);
				ieCapabilities.setCapability("enablePersistentHover", false);
				System.setProperty("webdriver.ie.driver", Constants.IE_DRIVER);
				//启动IE
				driver = new InternetExplorerDriver(ieCapabilities);
			}else if (browser.equalsIgnoreCase("chrome")) {
				//delete warning --ignore-certificate-errors
		       DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		       capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
		       ChromeOptions options = new ChromeOptions();
		       options.addArguments("--test-type");
		       options.addArguments("enable-automation");
		       options.addArguments("--disable-infobars");
		       capabilities.setCapability("chrome.binary", Constants.CHROME_DRIVER);
		       capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		       //启动Chrome
		       driver = new ChromeDriver(capabilities);
			}else{
				Log.error("Invalid browser type:"+browser);
			}
			Log.info("Browser is started,Type is "+browser);
			//Maximize
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Constants.IMPLICITLY_WAIT, TimeUnit.SECONDS);
			return driver;
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("Unable to Open Browser.");
			return null;
		}
	}

	/**
	 * 获得页面元素的状态，是否可见，是否可用
	 * @param we
	 * @return boolean
	 */
	public static boolean getElementStatus(WebElement element){
		if(!element.isDisplayed()){
			Log.error("Can not find the element:"+element.toString());
		} else if (!element.isEnabled()){
			Log.error("The element is disabled:"+element.toString());
		}
		return element.isDisplayed()&&element.isEnabled();
	}

	/**
	* 选择下拉框的选项
	* @param data
	* @param element
	* @param flag
	*/
	public static void selectDropDown(WebElement element,String flag,String data){
		Select select = new Select(element);
		if(flag.equalsIgnoreCase("byvalue")){		
			select.selectByValue(data);
		}else if(flag.equalsIgnoreCase("byindex")){
			select.selectByIndex(Integer.parseInt(data));
		}else{
			select.selectByVisibleText(data);
		}
		Log.info(data +" is selected in dropdown list:"+element.toString());
	}
	
	/**
	 * 点击页面元素
	 * @param element
	 */
	public static void click(WebElement element){
		try {
			if(getElementStatus(element)){//1、判断该页面元素是否存在和可用
				element.click();//2、点击元素
				Log.info(element.toString()+" is clicked.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("Method [elementClick] "+e.getMessage());
		}
	}


	/**
	 * 点击页面元素,并等待网页加载完毕
	 * @param element
	 */
	public static void clickAndWait(WebElement element){
		click(element);
		waitForPageLoad();//3、等待网页加载

	}

	/**
	 * 向页面元素输入数据
	 * @param element
	 */
	public static void inputValue(WebElement element,String value){
		try {
			if(getElementStatus(element)){//1、判断该页面元素是否存在和可用
				if(value!=null){
					element.clear();	
					element.sendKeys(value);
					Log.info("element: "+element.toString());
					Log.info("test data: "+value + " is input to element:"+element.toString());
				}else{
					Log.error("Test data: "+value + " is null.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("Method [inputValue] "+e.getMessage());
		}
	}

	/**
	 * 切换到默认
	 */
	public static void switchToDefault(){
		driver.switchTo().defaultContent();
		Log.info("Switched to default content");
	}
	
	/**
	 * 使用id或name属性值来切换frame
	 */
	public static void switchFrame(String idOrName){
		try {
			switchToDefault();
			driver.switchTo().frame(idOrName);
			Log.info("Switched to frame, idOrName:"+idOrName);
		}catch(NoSuchFrameException e){
			e.printStackTrace();
			Log.error("No Frame to switch.");
		}
	}
	
	/**
	 * 使用index来切换frame
	 */
	public static void switchFrame(int index){
		try{
			switchToDefault();
			driver.switchTo().frame(index);
			Log.info("Switched to frame, index:"+index);
		}catch(NoSuchFrameException e){
			e.printStackTrace();
			Log.error("No Frame to switch.");
		}
	}
	
	/**
	 * 通过其他方式定位切换Frame
	 */
	public static void switchFrame(WebElement frameElement){
		try{
			if (getElementStatus(frameElement)){
				switchToDefault();
				driver.switchTo().frame(frameElement);
				Log.info("Switched to frame.");
			}
		}catch(NoSuchFrameException e){
			e.printStackTrace();
			Log.error("No Frame to switch.");
		}
	}	
	/**
	 * 通过网页内容定位切换Frame
	 */
	public static void switchFrameByPageSource(String pageSource){
		switchToDefault();
		List<WebElement> frames = driver.findElements(By.tagName("frame"));
		if (frames.size()>0){
			boolean flag = false;//未找到目标Frame
			for (int i=0;i<frames.size();i++){   //遍历所有Frame
				driver.switchTo().frame(i); //切换到编号为i的
				//如果网页内容包含指定信息
				if (driver.getPageSource().contains(pageSource)) 
					flag = true;//找到目标Frame
					break; //就退出循环
			}
			if(flag){
				Log.info("Switched to frame contains:"+pageSource);
			}else{
				Log.error("No frame contains:"+pageSource);
			}
		}else{
			Log.error("No frame to switch");
		}
	}
	/**
	 * 获得当前窗口句柄
	 */
	public static String getWindowHandler(){
		return driver.getWindowHandle(); //获得当前窗口句柄
	}
	
	/**
	 * 通过窗口句柄切换窗口
	 */
	public static void switchWindowByHandler(String handler){
		try{
			driver.switchTo().window(handler);
		}catch(NoSuchWindowException e){
			e.printStackTrace();
			Log.error("The window cannot be found："+handler);
		}
	}
	/**
	 * 通过名称切换到新窗口 
	 */
	public static void switchWindow(String winName){
		try{
			driver.switchTo().window(winName);
		}catch(NoSuchWindowException e){
			e.printStackTrace();
			Log.error("The window cannot be found："+winName);
		}
	}

	/**
	 * 切换到另一个窗口(适合一共只有2个窗口的情况)
	 */
	public static void switchWindow(){
		String originalWinHandle  = driver.getWindowHandle(); //获得当前窗口句柄
		Set<String> allWindows = driver.getWindowHandles();//获得所有窗口句柄
		if (allWindows.size()==2){
			Iterator<String> it = allWindows.iterator();
			while (it.hasNext()) {//遍历时不是当前窗口，就切换
			    String currentWindow = it.next();
			    if (!currentWindow.equals(originalWinHandle))
			    	driver.switchTo().window(currentWindow);
			}
			Log.info("Switched to new window.");
		}else{
			Log.error("There are not two windows.");
		}
	}
	
	/**
	 * 判断窗口标题或URL或网页源码是否满足条件，如果满足,通过窗口句柄切换到新窗口
	 * @param type
	 * 	1:ByTitle
	 *  2:ByURL
	 *  3:ByPageSource
	 * @param value
	 */
	public static void switchWindowByValue(int type,String value){
		String originalWinHandle  = driver.getWindowHandle(); //获得当前窗口句柄
		Set<String> allWindows = driver.getWindowHandles();//获得所有窗口句柄
		Iterator<String> it = allWindows.iterator();
		if (allWindows.size()>1){
			boolean flag = false;//未切换到目标窗口
			while (it.hasNext()) {//遍历时不是当前窗口，就切换
			    String currentWindow = it.next();
			    if (!currentWindow.equals(originalWinHandle))
			    	driver.switchTo().window(currentWindow);
			    String currentValue;
			    switch (type){
			    case 1:
			    	currentValue = driver.getTitle();
			    	Log.info("switch Window By Title Value.");
			    case 2:
			    	currentValue = driver.getCurrentUrl();
			    	Log.info("switch Window By URL Value.");
			    default:		    	
			    	currentValue = driver.getPageSource();
			    	Log.info("switch Window By PageSource Value.");
			    }
			    //如果标题或URL或网页源码包含指定的信息，则到达指定窗口
			    if (currentValue.contains(value)){
			    		flag = true;//找到目标窗口，停止切换
			    		break; //退出循环
			    }
			}
			if (flag){
				Log.info("Switched to new window.");
			}else{
				Log.error("Can not find new window contains:"+value);
			}
		}else{
			Log.error("There are not more then one windows.");
		}
	}
	/**
	 * 断言页面元素出现
	 * @param by
	 */
	public static void assertElementPresent(By by){
		assertTrue(isElementPresent(by));
	}
	/**
	 * 断言页面元素未出现或消失
	 * @param by
	 */
	public static void assertElementNotPresent(By by){
		assertFalse(isElementPresent(by));
	}

	/**
	 * 判断页面元素是否出现
	 * @param by
	 * @return true出现，false未出现
	 */
	public static boolean isElementPresent(By by) {
	    try {
	    	driver.findElement(by);
	    	Log.info("Matching elements are found.");
	      	return true;
	    } catch (NoSuchElementException e) {
	    	Log.error("No matching elements are found.");
	    	return false;
	    }
	}
	
	/**
	 * 断言弹出框出现
	 * @param by
	 */
	public static void assertAlertPresent(){
		assertTrue(isAlertPresent());
	}
	/**
	 * 断言弹出框未出现或消失
	 */
	public static void assertAlertNotPresent(){
		assertFalse(isAlertPresent());
	}
	/**
	 * 判断是否弹出对话框
	 * @return
	 */
	public static boolean isAlertPresent() {
	    try {
	    	driver.switchTo().alert();
	    	Log.info("The dialog can be found.");
	      	return true;
	    } catch (NoAlertPresentException e) {
	    	Log.error("The dialog cannot be found.");
	    	return false;
	    }
	}

	/**
	 * 断言弹出框内容正确，并且点击“确定”或“是”来关闭弹出框
	 */
	public static void assertAlertText(String expText){
		String actText = closeAlertAndGetItsText();
		try{
			assertEquals(actText,expText);
		}catch(AssertionError e){
			e.printStackTrace();
			Log.error(e.getMessage());
		}
	}	
	/**
	 * 断言弹出框内容正确，并且点击“取消”或“否”来关闭弹出框
	 */
	public static void assertAlertTextAndDismiss(String expText){
		acceptNextAlert = false;
		assertAlertText(expText);
	}
	/**
	 * 断言弹出框内容包含预期文本，并且点击“确定”或“是”来关闭弹出框
	 */
	public static void assertAlertContainsText(String... expTexts){
		String actText = closeAlertAndGetItsText();
		for (String expText:expTexts){
			try{
				assertTrue(actText.contains(expText));
			} catch (AssertionError e){
				e.printStackTrace();
				Log.error("Actual alert text ["+actText +"] does not contains expected text ["+expText+"].");
			}
		}
	}
	/**
	 * 断言弹出框内容包含预期文本，并且点击“取消”或“否”来关闭弹出框
	 */
	public static void assertAlertContainsTextAndDismiss(String... expTexts){
		acceptNextAlert = false;
		assertAlertContainsText(expTexts);
	}
	
	/**
	 * 获得弹出框内的文本内容，并且关闭弹出框，默认点击“确定”
	 * 如果点击“取消”，需要提前给Utils类的acceptNextAlert赋值为false，再调用该方法
	 * @return
	 */
	public static String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	    	Log.info("Accept the dialog");
	        alert.accept();
	      } else {
	    	Log.info("Dismiss the dialog");
	        alert.dismiss();
	      }
	      Log.info("The text in the dialog is:"+alertText);
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	}

	
	/**
	 * 断言页面元素内的文本等于预期值
	 * @param element
	 * @param expText
	 */
	public static void assertText(WebElement element,String expText){
		if(getElementStatus(element)){//1、判断该页面元素是否存在和可用
			if(expText!=null){
				try{assertEquals(element.getText(),expText);
				}catch(AssertionError e){
					e.printStackTrace();
					Log.error(e.getMessage());}
			}else{Log.error("Test data: "+expText + " is null.");}
		}
	}
	
	/**
	 * 断言页面元素内的文本包含预期值
	 * @param element
	 * @param expText
	 */
	public static void assertContainsText(WebElement element,String expText){
		if(getElementStatus(element)){//1、判断该页面元素是否存在和可用
			if(expText!=null){
				try{
					assertTrue(element.getText().contains(expText));
				}catch(AssertionError e){
					e.printStackTrace();
					Log.error("Text["+element.getText()+"] does not contains expected text ["+expText+"]");}
			}else{Log.error("Test data: "+expText + " is null.");}
		}
	}
	
	/**
	 * 断言文本框内容等于预期值
	 * @param element
	 * @param expText
	 */
	public static void assertValue(WebElement element,String expValue){
		if(getElementStatus(element)){//1、判断该页面元素是否存在和可用
			if(expValue!=null){
				try{
					assertEquals(element.getAttribute("value"),expValue);
				}catch(AssertionError e){
					e.printStackTrace();
					Log.error(e.getMessage());}
			}else{Log.error("Test data: "+expValue + " is null.");}
		}
	}
	
	/**
	 * 断言文本框内容包含预期值
	 * @param element
	 * @param expText
	 */
	public static void assertContainsValue(WebElement element,String expValue){
		if(getElementStatus(element)){//1、判断该页面元素是否存在和可用
			if(expValue!=null){
				try{
					assertTrue(element.getAttribute("value").contains(expValue));
				}catch(AssertionError e){
					e.printStackTrace();
					Log.error("Value["+element.getAttribute("value")+"] does not contains expected text ["+expValue+"]");}

			}else{Log.error("Test data: "+expValue + " is null.");}
		}
	}
	/**
	 * 断言复选框或单选按钮当前被选中
	 * @param element
	 */
	public static void assertChecked(WebElement element){
		if(getElementStatus(element)){//1、判断该页面元素是否存在和可用
			try{
				assertTrue(element.isSelected());
			}catch(AssertionError e){
				e.printStackTrace();
				Log.error("The checkbox or radiobutton is not checked.");
			}
		}
	}
	
	/**
	 * 断言复选框或单选按钮当前未被选中
	 * @param element
	 */
	public static void assertNotChecked(WebElement element){
		if(getElementStatus(element)){//1、判断该页面元素是否存在和可用
			try{
				assertFalse(element.isSelected());
			}catch(AssertionError e){
				e.printStackTrace();
				Log.error("The checkbox or radiobutton is checked.");
			}
		}
	}
	/**
	 * 断言下拉框或列表框的当前选项等于预期文本
	 * @param element
	 */
	public static void assertSelectedOption(WebElement element,String expOption){
		if(getElementStatus(element)){
			try{
				String actOption = new Select(element).getFirstSelectedOption().getText();
				assertEquals(actOption,expOption);
			}catch(AssertionError e){
				e.printStackTrace();
				Log.error(e.getMessage());
			}
		}
	}

	/**
	 * 断言下拉框或列表框的当前选项包含预期文本
	 * @param element
	 */
	public static void assertSelectedContains(WebElement element,String expOption){
		if(getElementStatus(element)){
			String actOption = new Select(element).getFirstSelectedOption().getText();
			try{
				assertTrue(actOption.contains(expOption));
			}catch(AssertionError e){
				e.printStackTrace();
				Log.error("Actual option ["+actOption+"] does not contains expected option ["+expOption+"]");
			}
		}
	}
	
	/**
	 * 断言下拉框或列表框可以多选
	 * @param element
	 */
	public static void assertMultiple(WebElement element){
		if(getElementStatus(element)){
			try{
				assertTrue(new Select(element).isMultiple());
			}catch(AssertionError e){
				e.printStackTrace();
				Log.error("The dropdown list is not multiple");
			}
		}
	}
	/**
	 * 断言下拉框或列表框不可以多选，只能单选
	 * @param element
	 */
	public static void assertNotMultiple(WebElement element){
		if(getElementStatus(element)){
			try{
				assertFalse(new Select(element).isMultiple());
			}catch(AssertionError e){
				e.printStackTrace();
				Log.error("The dropdown list is multiple");
			}
		}
	}

	/**
	 * 断言下拉框或列表框的多个当前选项中包含预期文本
	 * @param element
	 */
	public static void assertSelectedAllContains(WebElement element,String expOption){
		if(getElementStatus(element)){
			boolean flag = false;
			for(WebElement selectedOption:new Select(element).getAllSelectedOptions()){
				String actOption = selectedOption.getText();
				if (actOption.contains(expOption)){
					flag = true;
					break;
				}
			}
			try{assertTrue(flag);
			}catch(AssertionError e){
				e.printStackTrace();
				Log.error("All selected options does not contains expected option ["+expOption+"]");
			}
		}
	}
	
	/**
	 * 断言下拉框或列表框的当前选项个数正确
	 * @param element
	 */
	public static void assertSelectedOptionsCount(WebElement element,int expCount){
		if(getElementStatus(element)){
			int actCount = new Select(element).getAllSelectedOptions().size();
			try{
				assertEquals(actCount,expCount);
			}catch(AssertionError e){
				e.printStackTrace();
				Log.error(e.getMessage());
			}
		}
	}
	
	public void verifyError(StringBuffer verificationErrors) throws Exception {
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	}
		


}
