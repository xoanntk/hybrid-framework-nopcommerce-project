package pageUIs.nopComerce.user;

public class OrdersPageUI {
	public static final String ORDER_NUMBER = "xpath=//div[contains(@class,'order-item')]//strong[text()='Order Number: %s']";
	public static final String ORDER_NUMBER_DETAIL = "xpath=//div[@class='order-number']/strong";
	public static final String ORDER_STATUS = "xpath=//li[@class='order-status']";
	public static final String ORDER_TOTAL_OVERVIEW = "xpath=//li[@class='order-total']";
	public static final String BILLING_INFO_BY_CLASS = "xpath=//div[@class='billing-info']//li[@class='%s']";
	public static final String SHIPPING_INFO_BY_CLASS = "xpath=//div[@class='shipping-info']/ul/li[@class='%s']";
	public static final String PAYMENT_INFO = "xpath=//li[@class='payment-method']";
	public static final String PAYMENT_STATUS = "xpath=//li[@class='payment-method-status']";
	public static final String SHIPPING_METHOD = "xpath=//li[@class='shipping-method']";
	public static final String SHIPPING_STATUS = "xpath=//li[@class='shipping-status']";
	public static final String DYNAMIC_PRODUCT_NAME = "xpath=//td[@class='product']//a[text()='%s']";
	public static final String UNIT_PRICE = "xpath=//a[text()='%s']/ancestor::td[@class='product']/following-sibling::td[@class='unit-price']/span";
	public static final String QUANTITY = "xpath=//a[text()='%s']/ancestor::td/following-sibling::td[@class='quantity']/span";
	public static final String TOTAL = "xpath=//a[text()='%s']/ancestor::td/following-sibling::td[@class='total']/span";
	public static final String GIFT_WRAPPING = "xpath=//div[@class='selected-checkout-attributes']";
	public static final String SUB_TOTAL = "xpath=//label[text()='Sub-Total:']/parent::td/following-sibling::td[@class='cart-total-right']/span";
	public static final String SHIPPING = "xpath=//label[text()='Shipping:']/parent::td/following-sibling::td[@class='cart-total-right']/span";
	public static final String TAX = "xpath=//label[text()='Tax:']/parent::td/following-sibling::td[@class='cart-total-right']/span";
	public static final String ORDER_TOTAL = "xpath=//label[text()='Order Total:']/parent::td/following-sibling::td[@class='cart-total-right']/span";
	public static final String DETAIL_BUTTON = "xpath=//strong[text()='Order Number: %s']/parent::div/following-sibling::div/button";

}
