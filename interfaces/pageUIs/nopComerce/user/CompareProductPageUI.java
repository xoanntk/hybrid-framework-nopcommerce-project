package pageUIs.nopComerce.user;

public class CompareProductPageUI {
	public static final String DYNAMIC_REMOVE_PRODUCT_LINK = "xpath=//td/a[text()='%s']/parent::td/parent::tr/preceding-sibling::tr[@class='remove-product']";
	public static final String DYNAMIC_PRODUCT_NAME = "xpath=//tr[@class='product-name']/td/a[text()='%s']";
	public static final String PRODUCT_PRICE1 = "xpath=//tr[@class='product-price']/td[3]";
	public static final String PRODUCT_PRICE2 = "xpath=//tr[@class='product-price']/td[2]";
	public static final String CLEAR_LIST_BUTTON = "xpath=//a[@class='clear-list']";
	public static final String NO_DATA_MESSAGE = "xpath=//div[@class='no-data']";

}
