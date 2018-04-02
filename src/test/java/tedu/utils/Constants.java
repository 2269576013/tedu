package tedu.utils;

public class Constants {
	//截图文件路径
	public static final String SCREENSHOT = System.getProperty("user.dir")+ "\\screenshots";
	//IE驱动文件
	public static final String IE_DRIVER = "C:\\IEDriverServer.exe";
	//Chrome驱动文件
	public static final String CHROME_DRIVER = "C:\\chromedriver.exe";
	//隐式等待默认超时时间
	public static final long IMPLICITLY_WAIT=60;
	//后台登录页网址
	public static final String ECSHOP_BG_URL = "http://localhost/ws/ecshop/upload/admin/index.php";
	public static final String ECSHOP_BG_MAIN_TITLE = "ECShop管理中心";

	public static final String DATA_PATH = System.getProperty("user.dir")+ "\\data\\";

}
