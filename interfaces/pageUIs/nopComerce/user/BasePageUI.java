package pageUIs.nopComerce.user;

public class BasePageUI {
	public static final String ERROR_MESSAGE_TEXTBOX_BY_ID = "xpath=//span[@id='%s']";
	public static final String TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String TEXTBOX_BY_CLASS = "xpath=//li[@class='%s']";

	public static final String LOGOUT_LINK_AT_ADMIN = "xpath=//a[text()='Logout']";

	public static final String DYNAMIC_PAGE_MY_ACCOUNT_AREA = "xpath=//div[contains(@class,'block-account-navigation')]//a[text()='%s']";
	public static final String DYNAMIC_PAGE_AT_SIDEBAR_BY_CLASS = "xpath=//i[contains(@class,'%s')]";
	public static final String DYNAMIC_ITEM_AT_SIDEBAR_BY_ITEM_NAME = "xpath=//i[contains(@class,'%s')]/parent::a/following-sibling::ul//p[contains(text(),'%s')]";
	public static final String DYNAMIC_PAGE_AT_FOOTER = "xpath=//div[@class='footer']//a[text()='%s']";
	public static final String DYNAMIC_PAGE_AT_HEADER_BY_CLASS = "xpath=//div[@class='header-links']//a[@class='%s']";
	public static final String BUTTON_BY_ID = "xpath=//button[@id='%s']";
	public static final String BUTTON_BY_CLASS = "xpath=//button[contains(@class,'%s')]";
	public static final String DROPDOWN_BY_NAME = "xpath=//select[@name='%s']";
	public static final String RADIO_BUTTON_BY_LABEL = "xpath=//label[text()='%s']/preceding-sibling::input";
	public static final String RADIO_CHECKBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String MENU_PAGE = "xpath=//ul[contains(@class,'notmobile')]//a[contains(text(),'%s')]";
	public static final String SUBMENU_PAGE = "xpath=//ul[contains(@class,'notmobile')]//a[contains(text(),'%s')]/following-sibling::ul/li/a[contains(text(),'%s')]";
	public static final String BAR_NOTIFICATION = "xpath=//div[@id='bar-notification']//p[@class='content']";
	public static final String CLOSE_NOTIFICATION = "xpath=//div[@id='bar-notification']//span[@class='close']";

}
