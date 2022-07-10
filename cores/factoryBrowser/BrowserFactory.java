package factoryBrowser;

import org.openqa.selenium.WebDriver;

public interface BrowserFactory {
	abstract WebDriver getBrowserDriver();
}
