package pageObjects.nopComerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopComerce.user.OrdersPageUI;

public class UserOrdersPageObject extends BasePage {
	WebDriver driver;

	public UserOrdersPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isOrderNumberDisplayed(String orderNumber) {
		waitForElementVisible(driver, OrdersPageUI.ORDER_NUMBER, orderNumber);
		return isElementDisplay(driver, OrdersPageUI.ORDER_NUMBER, orderNumber);
	}

	public String getOrderNumberAtOrdersDetail() {
		waitForElementVisible(driver, OrdersPageUI.ORDER_NUMBER_DETAIL);
		return getElementText(driver, OrdersPageUI.ORDER_NUMBER_DETAIL);
	}

	public String getOrderStatus() {
		waitForElementVisible(driver, OrdersPageUI.ORDER_STATUS);
		return getElementText(driver, OrdersPageUI.ORDER_STATUS);
	}

	public String getOrderTotalOverview() {
		waitForElementVisible(driver, OrdersPageUI.ORDER_TOTAL_OVERVIEW);
		return getElementText(driver, OrdersPageUI.ORDER_TOTAL_OVERVIEW);
	}

	public String getBillingInfoByClassName(String className) {
		waitForElementVisible(driver, OrdersPageUI.BILLING_INFO_BY_CLASS, className);
		return getElementText(driver, OrdersPageUI.BILLING_INFO_BY_CLASS, className);
	}

	public String getPaymentIfo() {
		waitForElementVisible(driver, OrdersPageUI.PAYMENT_INFO);
		return getElementText(driver, OrdersPageUI.PAYMENT_INFO);
	}

	public String getPaymentStatus() {
		waitForElementVisible(driver, OrdersPageUI.PAYMENT_STATUS);
		return getElementText(driver, OrdersPageUI.PAYMENT_STATUS);
	}

	public String getShippingInfoByClassName(String className) {
		waitForElementVisible(driver, OrdersPageUI.SHIPPING_INFO_BY_CLASS, className);
		return getElementText(driver, OrdersPageUI.SHIPPING_INFO_BY_CLASS, className);
	}

	public String getShippingMethod() {
		waitForElementVisible(driver, OrdersPageUI.SHIPPING_METHOD);
		return getElementText(driver, OrdersPageUI.SHIPPING_METHOD);
	}

	public String getShippingStatus() {
		waitForElementVisible(driver, OrdersPageUI.SHIPPING_STATUS);
		return getElementText(driver, OrdersPageUI.SHIPPING_STATUS);
	}

	public boolean isProductNameDisplayed(String productName) {
		waitForElementVisible(driver, OrdersPageUI.DYNAMIC_PRODUCT_NAME, productName);
		return isElementDisplay(driver, OrdersPageUI.DYNAMIC_PRODUCT_NAME, productName);
	}

	public String getUnitPrice(String productName) {
		waitForElementVisible(driver, OrdersPageUI.UNIT_PRICE, productName);
		return getElementText(driver, OrdersPageUI.UNIT_PRICE, productName);
	}

	public String getQuantity(String productName) {
		waitForElementVisible(driver, OrdersPageUI.QUANTITY, productName);
		return getElementText(driver, OrdersPageUI.QUANTITY, productName);
	}

	public int getTotal(String productName) {
		waitForElementVisible(driver, OrdersPageUI.TOTAL, productName);
		return Integer.parseInt(getElementText(driver, OrdersPageUI.TOTAL, productName).replace("$", "").replace(",", "").replace(".", ""));
	}

	public boolean isTotalDisplayed(String productName) {
		int unitPrice = Integer.parseInt(getUnitPrice(productName).replace("$", "").replace(",", "").replace(".", ""));
		int qty = Integer.parseInt(getQuantity(productName));
		int total = Integer.parseInt(getElementText(driver, OrdersPageUI.TOTAL, productName).replace("$", "").replace(",", "").replace(".", ""));
		return total == unitPrice * qty;
	}

	public String getGiftWrapping() {
		waitForElementVisible(driver, OrdersPageUI.GIFT_WRAPPING);
		return getElementText(driver, OrdersPageUI.GIFT_WRAPPING);
	}

	public String getSubTotal() {
		waitForElementVisible(driver, OrdersPageUI.SUB_TOTAL);
		return getElementText(driver, OrdersPageUI.SUB_TOTAL);
	}

	public String getShipping() {
		waitForElementVisible(driver, OrdersPageUI.SHIPPING);
		return getElementText(driver, OrdersPageUI.SHIPPING);
	}

	public String getTax() {
		waitForElementVisible(driver, OrdersPageUI.TAX);
		return getElementText(driver, OrdersPageUI.TAX);
	}

	public String getOrderTotal() {
		waitForElementVisible(driver, OrdersPageUI.ORDER_TOTAL);
		return getElementText(driver, OrdersPageUI.ORDER_TOTAL);
	}

	public void clickToDetail(String orderNumber) {
		waitForElementClickable(driver, OrdersPageUI.DETAIL_BUTTON, orderNumber);
		clickToElement(driver, OrdersPageUI.DETAIL_BUTTON, orderNumber);
	}

}
