package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIs.nopComerce.user.BasePageUI;

public class BasePage {

	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);

	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refeshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(3);
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	public void switchToTabByID(WebDriver driver, String expectedID) {
		Set<String> allTabIds = driver.getWindowHandles();
		for (String id : allTabIds) {
			if (!id.equals(expectedID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void switchToTabByTitle(WebDriver driver, String expectecTitle) {
		Set<String> allTabIds = driver.getWindowHandles();
		for (String id : allTabIds) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectecTitle)) {
				break;
			}
		}
	}

	public void closeAllTabWithoutParent(WebDriver driver, String parentID) {
		Set<String> allTabIds = driver.getWindowHandles();
		for (String id : allTabIds) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
	}

	public By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=")) {
			by = By.cssSelector(locatorType.substring(6));
		} else if (locatorType.startsWith("name=")) {
			by = By.cssSelector(locatorType.substring(5));
		} else if (locatorType.startsWith("css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("xpath=")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator type is not supported!");
		}

		return by;
	}

	public String getDynamicXpath(String locatorType, String... values) {
		if (locatorType.startsWith("xpath=")) {
			locatorType = String.format(locatorType, (Object[]) values);

		}
		return locatorType;
	}

	public WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	public WebElement getWebElement(WebDriver driver, String locatorType, String... dynamicvalues) {
		return driver.findElement(getByLocator(getDynamicXpath(locatorType, dynamicvalues)));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locatorType, String... dynamicvalues) {
		return driver.findElements(getByLocator(getDynamicXpath(locatorType, dynamicvalues)));
	}

	public void clickToElement(WebDriver driver, String locatorType) {
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, locatorType);
			sleepInSecond(2);
		} else {
			getWebElement(driver, locatorType).click();
		}
	}

	public void clickToElement(WebDriver driver, String locatorType, String... dynamicvalues) {
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, getDynamicXpath(locatorType, dynamicvalues));
			sleepInSecond(2);
		} else {
			getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues)).click();
		}
	}

	public void senkeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	public void senkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicvalues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues));
		element.clear();
		element.sendKeys(textValue);
	}

	public void selectItemInDefaultDropdownByVisibleText(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}

	public void selectItemInDefaultDropdownByVisibleText(WebDriver driver, String locatorType, String textItem, String... dynamicvalues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues)));
		select.selectByVisibleText(textItem);
	}

	public String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}

	public String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType, String... dynamicvalues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues)));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}

	public void selecItemCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedItemtext) {
		getWebElement(driver, parentXpath).click();
		sleepInSecond(2);

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		List<WebElement> childItem = getListWebElement(driver, childXpath);

		for (WebElement tempElement : childItem) {
			if (tempElement.getText().trim().equals(expectedItemtext)) {
				if (tempElement.isDisplayed()) {
					tempElement.click();
					sleepInSecond(2);
				} else {
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", tempElement);
					sleepInSecond(2);
					jsExecutor.executeScript("arguments[0].click();", tempElement);
					sleepInSecond(2);
				}
				break;
			}
		}
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicvalues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues)).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText().trim();
	}

	public String getElementText(WebDriver driver, String locatorType, String... dynamicvalues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues)).getText().trim();
	}

	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}

	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName, String... dynamicvalues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues)).getCssValue(propertyName);
	}

	public String getHexaColorFromRgba(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}

	public int getElementSize(WebDriver driver, String locatorType, String... dynamicvalues) {
		return getListWebElement(driver, getDynamicXpath(locatorType, dynamicvalues)).size();
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... dynamicvalues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues));
		if (!element.isSelected()) {
			if (driver.toString().contains("internet explorer")) {
				clickToElementByJS(driver, getDynamicXpath(locatorType, dynamicvalues));
				sleepInSecond(2);
			} else {
				element.click();
			}
		}
	}

	public void unCheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void unCheckToDefaultCheckbox(WebDriver driver, String locatorType, String... dynamicvalues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues));
		if (element.isSelected()) {
			if (driver.toString().contains("internet explorer")) {
				clickToElementByJS(driver, getDynamicXpath(locatorType, dynamicvalues));
				sleepInSecond(2);
			} else {
				element.click();
			}
		}
	}

	public boolean isElementDisplay(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}

	public boolean isElementDisplay(WebDriver driver, String locatorType, String... dynamicvalues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues)).isDisplayed();
	}

	public boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		overrideGlobalTimeout(driver, shortTimeOut);
		List<WebElement> elements = getListWebElement(driver, locatorType);
		overrideGlobalTimeout(driver, longTimeOut);

		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public boolean isElementEnable(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	public boolean isElementEnable(WebDriver driver, String locatorType, String... dynamicvalues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues)).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	public boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicvalues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues)).isSelected();
	}

	public void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}

	public void switchToFrameIframe(WebDriver driver, String locatorType, String... dynamicvalues) {
		driver.switchTo().frame(getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues)));
	}

	public void switchToDefaultContent(WebDriver driver, String locatorType) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String locatorType, String... dynamicvalues) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues))).perform();
	}

	public void rightClickToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.contextClick(getWebElement(driver, locatorType)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.doubleClick(getWebElement(driver, locatorType)).perform();
	}

	public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		Actions action = new Actions(driver);
		action.dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicvalues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues)), key).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void hightlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void hightlightElement(WebDriver driver, String locatorType, String... dynamicvalues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	public void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicvalues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues)));
	}

	public void scrollToElementOnTop(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	public void scrollToElementOnTop(WebDriver driver, String locatorType, String... dynamicvalues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues)));
	}

	public void scrollToElementOnDown(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locatorType));
	}

	public void scrollToElementOnDown(WebDriver driver, String locatorType, String... dynamicvalues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues)));
	}

	public String getElementValueByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		xpathLocator = xpathLocator.replace("xpath=", "");
		return (String) jsExecutor.executeScript("return $(document.evalute(\"" + xpathLocator + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE,null).singleNodeValue).val()");
	}

	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}

	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove, String... dynamicvalues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues)));
	}

	public boolean isJQueryAjaxLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null)&& (jQuery.active===0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}

	public String getElementValidationMessage(WebDriver driver, String locatorType, String... dynamicvalues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues)));
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(driver, locatorType));
		if (status) {
			return true;
		}
		return false;
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicvalues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(driver, getDynamicXpath(locatorType, dynamicvalues)));
		return status;
	}

	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicvalues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicvalues))));
	}

	public void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	public void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicvalues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicvalues))));
	}

	public void waitForElementInVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	/*
	 * wait for element undisplayed in DOM or not in DOM and override implicit timeout
	 */
	public void waitForElementUndisplayed(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeOut);
		overrideGlobalTimeout(driver, shortTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideGlobalTimeout(driver, longTimeOut);

	}

	public void waitForElementUndisplayed(WebDriver driver, String locatorType, String... dynamicvalues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeOut);
		overrideGlobalTimeout(driver, shortTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicvalues))));
		overrideGlobalTimeout(driver, longTimeOut);

	}

	public void waitForElementInVisible(WebDriver driver, String locatorType, String... dynamicvalues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicvalues))));
	}

	public void waitForAllElementINVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}

	public void waitForAllElementINVisible(WebDriver driver, String locatorType, String... dynamicvalues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(locatorType, dynamicvalues))));
	}

	public void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicvalues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicvalues))));
	}

	public void inputToTextboxByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxID);
		senkeyToElement(driver, BasePageUI.TEXTBOX_BY_ID, value, textboxID);
	}

	public String getErrorMessageTextboxByID(WebDriver driver, String textboxID) {
		waitForElementVisible(driver, BasePageUI.ERROR_MESSAGE_TEXTBOX_BY_ID, textboxID);
		return getElementText(driver, BasePageUI.ERROR_MESSAGE_TEXTBOX_BY_ID, textboxID);
	}

	public void openPageAtMyAccountByPageName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_PAGE_MY_ACCOUNT_AREA, pageName);

	}

	public void openPageAtHeader(WebDriver driver, String className) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_HEADER_BY_CLASS, className);
		clickToElementByJS(driver, BasePageUI.DYNAMIC_PAGE_AT_HEADER_BY_CLASS, className);
	}

	public void openPageAtFooter(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_FOOTER, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_FOOTER, pageName);

	}

	public void clickToButtonByID(WebDriver driver, String buttonID) {
		waitForElementClickable(driver, BasePageUI.BUTTON_BY_ID, buttonID);
		clickToElement(driver, BasePageUI.BUTTON_BY_ID, buttonID);
	}

	public void clickToButtonByClass(WebDriver driver, String buttonClass) {
		waitForElementClickable(driver, BasePageUI.BUTTON_BY_CLASS, buttonClass);
		clickToElement(driver, BasePageUI.BUTTON_BY_CLASS, buttonClass);
	}

	public void selectItemInDropDownByName(WebDriver driver, String dropdownAttributeName, String itemValue) {
		waitForElementClickable(driver, BasePageUI.DROPDOWN_BY_NAME, dropdownAttributeName);
		selectItemInDefaultDropdownByVisibleText(driver, BasePageUI.DROPDOWN_BY_NAME, itemValue, dropdownAttributeName);
	}

	public void clickToRadioButtonByLabel(WebDriver driver, String radioLabelName) {
		waitForElementClickable(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, radioLabelName);
		checkToDefaultCheckboxRadio(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, radioLabelName);
	}

	public void clickToRadioButtonByID(WebDriver driver, String radioID) {
		waitForElementClickable(driver, BasePageUI.RADIO_CHECKBOX_BY_ID, radioID);
		checkToDefaultCheckboxRadio(driver, BasePageUI.RADIO_CHECKBOX_BY_ID, radioID);
	}

	public String getTextboxValueByID(WebDriver driver, String textboxID) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(driver, BasePageUI.TEXTBOX_BY_ID, "value", textboxID);
	}

	public String getTextAtTextboxByClass(WebDriver driver, String textboxClass) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_CLASS, textboxClass);
		return getElementText(driver, BasePageUI.TEXTBOX_BY_CLASS, textboxClass);
	}

	public String getMessageInBarNotification(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.BAR_NOTIFICATION);
		return getElementText(driver, BasePageUI.BAR_NOTIFICATION);
	}

	public void closeBarNotification(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CLOSE_NOTIFICATION);
		clickToElement(driver, BasePageUI.CLOSE_NOTIFICATION);
	}

	public void openSubmenuPage(WebDriver driver, String menuName, String submenuName) {
		hoverMouseToElement(driver, BasePageUI.MENU_PAGE, menuName);
		waitForElementClickable(driver, BasePageUI.SUBMENU_PAGE, menuName, submenuName);
		clickToElement(driver, BasePageUI.SUBMENU_PAGE, menuName, submenuName);
	}

	public String getSelectedValueInDropDownByName(WebDriver driver, String dropdownName) {
		waitForElementVisible(driver, BasePageUI.DROPDOWN_BY_NAME, dropdownName);
		return getSelectedItemDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_NAME, dropdownName);
	}

	public void openPageAtSideBarByClass(WebDriver driver, String className, String itemName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_SIDEBAR_BY_CLASS, className);
		clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_SIDEBAR_BY_CLASS, className);
		waitForElementClickable(driver, BasePageUI.DYNAMIC_ITEM_AT_SIDEBAR_BY_ITEM_NAME, className, itemName);
		clickToElement(driver, BasePageUI.DYNAMIC_ITEM_AT_SIDEBAR_BY_ITEM_NAME, className, itemName);
	}

	public boolean isRadioCheckboxSelectedByID(WebDriver driver, String id) {
		waitForElementVisible(driver, BasePageUI.RADIO_CHECKBOX_BY_ID, id);
		return isElementSelected(driver, BasePageUI.RADIO_CHECKBOX_BY_ID, id);
	}

	private long longTimeOut = GlobalConstants.getGlobalConstants().getLongTimeout();
	private long shortTimeOut = GlobalConstants.getGlobalConstants().getShortTimeout();
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;

}
