package commons;

import java.io.File;

import lombok.Getter;

@Getter
public class GlobalConstants {
	private static GlobalConstants globalInstance;

	private GlobalConstants() {

	}

	public static synchronized GlobalConstants getGlobalConstants() {
		if (globalInstance == null) {
			globalInstance = new GlobalConstants();
		}
		return globalInstance;
	}

	private final String portalPageUrl = "https://demo.nopcommerce.com/";
	private final String adminPageUrl = "https://admin-demo.nopcommerce.com/login?ReturnUrl=/admin/";
	private final String projectPath = System.getProperty("user.dir");
	private final String javaVersion = System.getProperty("java.version");
	private final String osName = System.getProperty("os.name");
	private final String uploadFile = projectPath + File.separator + "uploadFiles" + File.separator;
	private final String downloadFile = projectPath + File.separator + "downloadFiles";
	private final String browserLog = projectPath + File.separator + "browserLogs" + File.separator;
	private final String dragDropHTML5 = projectPath + File.separator + "dragDropHTML5";
	private final String autoITScript = projectPath + File.separator + "autoIT";
	private final String reportNGScreenshot = projectPath + File.separator + "reportNGImage" + File.separator;
	private final String dbDevUrl = "32.18.252.285:9860";
	private final String dbDevUser = "automationfc";
	private final String dbDevPass = "123@123Aa";
	private final String browserStackUserName = "yiyi_wI7VrO";
	private final String browserStackAutomateKey = "MPPJ1SkZeB74xKpAbwRg";
	private final String browserStackUrl = "https://" + browserStackUserName + ":" + browserStackAutomateKey + "@hub-cloud.browserstack.com/wd/hub";
	private final String sauceUserName = "oauth-xujester-9dbc4";
	private final String sauceAutomateKey = "2d6af456-4359-48e0-ad6c-2336ded3d6f0";
	private final String sauceLabUrl = "https://" + sauceUserName + ":" + sauceAutomateKey + "@ondemand.apac-southeast-1.saucelabs.com:443/wd/hub";
	private final long shortTimeout = 5;
	private final long longTimeout = 30;
	private final long retryTestFail = 3;

}
