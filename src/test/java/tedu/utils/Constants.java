package tedu.utils;

public class Constants {
	//IE驱动文件
	public static final String IE_DRIVER = "C:\\IEDriverServer.exe";
	//Chrome驱动文件
	public static final String CHROME_DRIVER = "C:\\chromedriver.exe";
	//隐式等待默认超时时间
	public static final long IMPLICITLY_WAIT=60;
	//显式等待中的超时时间
	public static final int EXPLICIT_WAIT = 60;
	//截图文件路径
	public static final String SCREENSHOT = System.getProperty("user.dir")+ "\\screenshots";
	//测试数据存储路径
	public static final String DATA_PATH = System.getProperty("user.dir")+ "\\data\\";
	//ECSHOP主域名
	public static final String ECSHOP_BASE_URL = "http://localhost/";
	//ECSHOP高级搜索页面URL
	public static final String ECSHOP_ADVANCED_SEARCH_URL = ECSHOP_BASE_URL+"ws/ecshop/upload/search.php?encode=YToyOntzOjM6ImFjdCI7czoxNToiYWR2YW5jZWRfc2VhcmNoIjtzOjE4OiJzZWFyY2hfZW5jb2RlX3RpbWUiO2k6MTUyMjc0MDQ3ODt9";
	//ECSHOP后台页面主标题
	public static final String ECSHOP_BG_MAIN_TITLE = "ECShop管理中心";
	//ECSHOP前台页面主标题
	public static final String ECSHOP_FP_MAIN_TITLE = "ECSHOP演示站 - Powered by ECShop";
	//ECSHOP高级搜索页面标题
	public static final String ECSHOP_ADVANCED_SEARCH_TITLE = "高级搜索_"+ECSHOP_FP_MAIN_TITLE;
	//ECSHOP搜索结果页面标题
	public static final String ECSHOP_SEARCH_RESULT_TITLE = "商品搜索_"+ECSHOP_FP_MAIN_TITLE;

}
