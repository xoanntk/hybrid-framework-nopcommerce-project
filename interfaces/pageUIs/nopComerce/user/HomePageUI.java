package pageUIs.nopComerce.user;

public class HomePageUI {
	public static final String MY_ACCOUNT_LINK = "xpath=//a[@class='ico-account']";
	public static final String ADD_YOUR_REVIEW_LINK = "xpath=//div[@class='product-review-links']/a[text()='Add your review']";
	public static final String DYNAMIC_PRODUCT_NAME = "xpath=//h2[@class='product-title']//a[text()='%s']";
	public static final String REVIEWTEXT_TEXTAREA = "xpath=//textarea[@id='AddProductReview_ReviewText']";
	public static final String PRODUCT_REVIEW_LINK = "xpath=//div[@class='product-review-links']/span[@class='separator']/preceding-sibling::a";
	public static final String SORTBY_DROPDOWN = "xpath=//select[@id='products-orderby']";
	public static final String DISPLAY_DROPDOWN = "xpath=//select[@id='products-pagesize']";
	public static final String PRODUCTS_NAME = "xpath=//h2[@class='product-title']/a";
	public static final String PRODUCTS_PRICE = "xpath=//span[@class='price actual-price']";
	public static final String NEXT_ICON = "xpath=//li[@class='next-page']";
	public static final String PREVIOUS_ICON = "xpath=//li[@class='previous-page']";
	public static final String PRODUCT_ITEM = "xpath=//div[@class='product-item']";
	public static final String WISHLIST_LINK = "xpath=//a[text()='wishlist']";
	public static final String PRODUCT_COMPARION_LINK = "xpath=//a[text()='product comparison']";
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "xpath=//select[contains(@name,'%s')]";
	public static final String DYNAMIC_CHECKBOX_RADIO_BY_TEXT = "xpath=//label[text()='%s']/preceding-sibling::input";
	public static final String SHOPPING_CART_LINK = "xpath=//a[@class='ico-cart']";
	public static final String CART_QUANTITY_AT_SHOPPING_CART_HEADER = "xpath=//a[@class='ico-cart']/span[@class='cart-qty']";
	public static final String COUNT_ITEMS_IN_CART_HEADER = "xpath=//div[@class='count']";
	public static final String PRODUCT_NAME_IN_CART_HEADER = "xpath=//div[@id='flyout-cart']//div[@class='name']/a[text()='%s']";
	public static final String INFO_PRODUCT_IN_CART_HEADER = "xpath=//a[text()='%s']/parent::div/following-sibling::div[@class='attributes']";
	public static final String UNIT_PRICE_IN_CART_HEADER = "xpath=//a[text()='%s']/parent::div/following-sibling::div[@class='price']/span";
	public static final String QUANTITY_IN_CART_HEADER = "xpath=//a[text()='%s']/parent::div/following-sibling::div[@class='quantity']/span";
	public static final String SUB_TOTAL_IN_CART_HEADER = "xpath=//div[@class='totals']/strong";
	public static final String QUANTITY_TEXTBOX = "xpath=//input[contains(@class,'qty-input')]";
	public static final String PRODUCT_PRICE = "xpath=//span[contains(@class,'price-value')]";

}
