package pageUIs.nopComerce.user;

public class ShoppingCartPageUI {
	public static final String SHOPPING_CART_LINK = "xpath=//a[@class='ico-cart']";
	public static final String PRODUCT_IN_CART_PAGE = "xpath=//td[@class='product']/a[text()='%s']";
	public static final String PRODUCT_INFO_IN_CART_PAGE = "xpath=//a[text()='%s']/following-sibling::div[@class='attributes']";
	public static final String UNIT_PRICE_IN_CART_PAGE = "xpath=//a[text()='%s']/parent::td/following-sibling::td[@class='unit-price']/span";
	public static final String QUANTITY_IN_CART_PAGE = "xpath=//a[text()='%s']/parent::td/following-sibling::td[@class='quantity']/input";
	public static final String TOTAL_IN_CART_PAGE = "xpath=//a[text()='%s']/parent::td/following-sibling::td[@class='subtotal']/span";
	public static final String PRODUCT_IN_CART_HEADER = "xpath=//div[@id='flyout-cart']//div[@class='name']/a[text()='%s']";
	public static final String ADD_TO_CART_CHECKBOX = "xpath=//input[@name='addtocart']";
	public static final String EDIT_LINK = "xpath=//div[@class='edit-item']/a";
	public static final String NO_DATA_MESSAGE = "xpath=//div[@class='no-data']";
	public static final String QUANTITY_TEXTBOX = "xpath=//input[@class='qty-input']";
	public static final String TERM_CHECKBOX = "xpath=//input[@id='termsofservice']";

}
