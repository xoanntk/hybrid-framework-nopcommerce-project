package pageUIs.nopComerce.admin;

public class AdminProductsPageUI {
	public static final String PRODUCTS_NAME = "xpath=//tbody/tr";
	public static final String PRODUCT_NAME = "xpath=//tbody/tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']//following-sibling::td[text()='%s']/following-sibling::td/i[@nop-value='%s']";
	public static final String SEARCH_SUBCATEGORY_CHECKBOX = "xpath=//input[@id='SearchIncludeSubCategories']";
	public static final String NO_DATA_MESSAGE = "xpath=//tr/td[text()='No data available in table']";
}
