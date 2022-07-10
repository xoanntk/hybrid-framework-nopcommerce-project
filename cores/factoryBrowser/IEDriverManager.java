package factoryBrowser;

import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IEDriverManager implements BrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		if (!IS_OS_WINDOWS) {
			throw new BrowserNotSupporteddException("IE is not supported on " + System.getProperty("os.name"));
		}

		WebDriverManager.iedriver().arch32().driverVersion("3.141.59").setup();
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, "true");
		options.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, "true");
		options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, "false");
		options.setCapability("ignoreProtectedModeSettings", "true");
		options.setCapability("ignoreZoomSettings", "true");
		options.setCapability("requireWindowFocus", "true");
		options.setCapability("enableElementCacheCleanup", "true");

		return new InternetExplorerDriver(options);
	}

}
