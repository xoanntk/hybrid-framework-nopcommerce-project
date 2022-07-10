package pageObjects.nopComerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopComerce.user.CheckoutPageUI;

public class UserCheckoutPageObject extends BasePage {
	WebDriver driver;

	public UserCheckoutPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectToCountryDropdown(String value) {
		waitForElementClickable(driver, CheckoutPageUI.COUNTRY_DROPDOWN);
		selectItemInDefaultDropdownByVisibleText(driver, CheckoutPageUI.COUNTRY_DROPDOWN, value);
	}

	public String getBillingInfoByClass(String className) {
		waitForElementVisible(driver, CheckoutPageUI.BILLING_INFO_BY_CLASS, className);
		return getElementText(driver, CheckoutPageUI.BILLING_INFO_BY_CLASS, className);
	}

	public String getPaymentIfo() {
		waitForElementVisible(driver, CheckoutPageUI.PAYMENT_INFO);
		return getElementText(driver, CheckoutPageUI.PAYMENT_INFO);
	}

	public String getShippingInfoByClass(String className) {
		waitForElementVisible(driver, CheckoutPageUI.SHIPPING_INFO_BY_CLASS, className);
		return getElementText(driver, CheckoutPageUI.SHIPPING_INFO_BY_CLASS, className);
	}

	public String getShippingMethod() {
		waitForElementVisible(driver, CheckoutPageUI.SHIPPING_METHOD);
		return getElementText(driver, CheckoutPageUI.SHIPPING_METHOD);
	}

	public String getSuccessMessage() {
		waitForElementVisible(driver, CheckoutPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, CheckoutPageUI.SUCCESS_MESSAGE);
	}

	public String getOlderNumberAtCheckoutPage() {
		waitForElementVisible(driver, CheckoutPageUI.OLDER_NUMBER);
		String result = getElementText(driver, CheckoutPageUI.OLDER_NUMBER);
		String results[] = result.split(" ");
		return results[2];
	}

	public boolean isProductNameDisplayed(String productName) {
		waitForElementVisible(driver, CheckoutPageUI.PRODUCT_NAME, productName);
		return isElementDisplay(driver, CheckoutPageUI.PRODUCT_NAME, productName);
	}

	public String getUnitPrice(String productName) {
		waitForElementVisible(driver, CheckoutPageUI.UNIT_PRICE, productName);
		return getElementText(driver, CheckoutPageUI.UNIT_PRICE, productName);
	}

	public String getQuantity(String productName) {
		waitForElementVisible(driver, CheckoutPageUI.QUANTITY, productName);
		return getElementText(driver, CheckoutPageUI.QUANTITY, productName);
	}

	public String getTotal(String productName) {
		sleepInSecond(3);
		waitForElementVisible(driver, CheckoutPageUI.TOTAL, productName);
		return getElementText(driver, CheckoutPageUI.TOTAL, productName);
	}

	public boolean isTotalDisplayed(String productName) {
		int unitPrice = Integer.parseInt(getUnitPrice(productName).replace("$", "").replace(",", "").replace(".", ""));
		int qty = Integer.parseInt(getQuantity(productName));
		int total = Integer.parseInt(getElementText(driver, CheckoutPageUI.TOTAL, productName).replace("$", "").replace(",", "").replace(".", ""));
		return total == unitPrice * qty;
	}
}
