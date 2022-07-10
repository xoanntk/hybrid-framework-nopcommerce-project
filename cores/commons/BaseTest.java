package commons;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import factoryBrowser.BrowserList;
import factoryEnvironment.BrowserstackFactory;
import factoryEnvironment.EnviromentList;
import factoryEnvironment.GridFactory;
import factoryEnvironment.LocalFactory;
import factoryEnvironment.SaucelabFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import reportConfig.VerificationFailures;

public class BaseTest {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	protected final Log log;

	@BeforeSuite
	public void initBeforeSuite() {
		deleteAllAllureReport();
	}

	public BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	protected WebDriver getBrowserDriver(String envName, String serverName, String browserName, String ipAddress, String portNumber, String osName, String osVersion) {
		switch (envName) {
		case "local":
			driver.set(new LocalFactory(browserName).createDriver());
			break;
		case "grid":
			driver.set(new GridFactory(browserName, ipAddress, portNumber).createDriver());
			break;
		case "browserStack":
			driver.set(new BrowserstackFactory(browserName, osName, osVersion).createDriver());
			break;
		case "saucelab":
			driver.set(new SaucelabFactory(browserName, osName).createDriver());
			break;

		default:
			driver.set(new LocalFactory(browserName).createDriver());

			break;
		}
		driver.get().manage().timeouts().implicitlyWait(GlobalConstants.getGlobalConstants().getLongTimeout(), TimeUnit.SECONDS);
		driver.get().manage().window().maximize();
		driver.get().get(getEnviromentValue(serverName));
		return driver.get();
	}

	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		if (browserList == BrowserList.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();

			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.getGlobalConstants().getProjectPath() + File.separator + "browserLogs" + File.separator + "Firefox.log");

			FirefoxProfile profile = new FirefoxProfile();
			File extensionFile = new File(GlobalConstants.getGlobalConstants().getProjectPath() + File.separator + "browserExtensions" + File.separator + "to_google_translate-4.2.0.xpi");
			profile.addExtension(extensionFile);
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(profile);

			driver.set(new FirefoxDriver());

		} else if (browserList == BrowserList.H_FIREFOX) {
			WebDriverManager.chromedriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver.set(new FirefoxDriver(options));
		} else if (browserList == BrowserList.CHROME) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--lang=vi");
			driver.set(new ChromeDriver(options));
		} else if (browserList == BrowserList.H_CHROME) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver.set(new ChromeDriver(options));
		} else if (browserList == BrowserList.EDGE) {
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
		} else if (browserList == BrowserList.SAFARI) {
			driver.set(new SafariDriver());
		} else if (browserList == BrowserList.COCCOC) {
			WebDriverManager.chromedriver().driverVersion("99.0.4844.51").setup();
			ChromeOptions options = new ChromeOptions();
			if (GlobalConstants.getGlobalConstants().getOsName().startsWith("Windows")) {
				options.setBinary("C:\\Users\\THANH TOAN\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			} else {
				options.setBinary("");
			}
			driver.set(new ChromeDriver(options));

		} else {
			throw new RuntimeException("Browser name invalid");
		}

		driver.get().manage().timeouts().implicitlyWait(GlobalConstants.getGlobalConstants().getLongTimeout(), TimeUnit.SECONDS);
		driver.get().get(GlobalConstants.getGlobalConstants().getPortalPageUrl());

		return driver.get();
	}

	public WebDriver getDriverInstance() {
		return driver.get();
	}

	protected String getEnviromentValue(String serverName) {
		String envUrl = null;
		EnviromentList enviroment = EnviromentList.valueOf(serverName.toUpperCase());
		if (enviroment == EnviromentList.DEV) {
			envUrl = "https://demo.nopcommerce.com/";
		} else if (enviroment == EnviromentList.TESTING) {
			envUrl = "https://demo.nopcommerce.com/";

		} else if (enviroment == EnviromentList.STAGING) {
			envUrl = "https://demo.nopcommerce.com/";

		} else if (enviroment == EnviromentList.PRODUCTION) {
			envUrl = "https://demo.nopcommerce.com/";

		} else if (enviroment == EnviromentList.USER) {
			envUrl = "https://demo.nopcommerce.com/";

		} else if (enviroment == EnviromentList.ADMIN) {
			envUrl = "https://admin-demo.nopcommerce.com/login?ReturnUrl=/admin/";
		}
		System.out.println(envUrl);
		return envUrl;
	}

	protected int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");

		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	public void deleteAllAllureReport() {
		try {
			String pathFolderDownload = GlobalConstants.getGlobalConstants().getProjectPath() + "/allure-results";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	protected void closeBrowserAndDriver() {
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.get().toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}

			if (driver != null) {
				driver.get().manage().deleteAllCookies();
				driver.get().quit();

				driver.remove();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void showBrowserConsoleLogs(WebDriver driver) {
		if (driver.toString().contains("chrome")) {
			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for (LogEntry logging : logList) {
				log.info("-------------" + logging.getLevel().toString() + "-------------\n" + logging.getMessage());
			}
		}
	}
}
