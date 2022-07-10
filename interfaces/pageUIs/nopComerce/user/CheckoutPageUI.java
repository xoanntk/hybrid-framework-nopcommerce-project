package pageUIs.nopComerce.user;

public class CheckoutPageUI {
	public static final String COUNTRY_DROPDOWN = "xpath=//select[@id='BillingNewAddress_CountryId']";
	public static final String BILLING_INFO_BY_CLASS = "xpath=//div[@class='billing-info']//li[@class='%s']";
	public static final String SHIPPING_INFO_BY_CLASS = "xpath=//div[@class='shipping-info']/ul/li[@class='%s']";
	public static final String PAYMENT_INFO = "xpath=//li[@class='payment-method']";
	public static final String SHIPPING_METHOD = "xpath=//li[@class='shipping-method']";
	public static final String OLDER_NUMBER = "xpath=//div[@class='order-number']/strong";
	public static final String SUCCESS_MESSAGE = "xpath=//div[contains(@class,'order-completed')]/div[@class='title']/strong";
	public static final String PRODUCT_NAME = "xpath=//td[@class='product']/a[text()='%s']";
	public static final String UNIT_PRICE = "xpath=//a[text()='%s']/parent::td/following-sibling::td[@class='unit-price']/span";
	public static final String QUANTITY = "xpath=//a[text()='%s']/parent::td/following-sibling::td[@class='quantity']/span";
	public static final String TOTAL = "xpath=//a[text()='%s']/parent::td/following-sibling::td[@class='subtotal']/span";
}
