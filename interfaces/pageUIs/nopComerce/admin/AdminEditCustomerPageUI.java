package pageUIs.nopComerce.admin;

public class AdminEditCustomerPageUI {
	public static final String ADMIN_COMMENT_TEXTAREA = "xpath=//textarea[@id='AdminComment']";
	public static final String SAVE_BUTTON = "xpath=//button[@name='save']";
	public static final String ADDRESSES_CARD = "xpath=//i[contains(@class,'fa-address-book')]";
	public static final String ADD_NEW_ADDRESS_BUTTON = "xpath=//button[contains(text(),'Add new address')]";
	public static final String EDIT_BUTTON = "xpath=//i[contains(@class,'fa-pencil-alt')]";
	public static final String DELETE_BUTTON = "xpath=//td//i[contains(@class,'fa-trash-alt')]";
	public static final String ADDRESS_PLUS = "xpath=//i[contains(@class,'fa-address-book')]/parent::div/following-sibling::div//i[contains(@class,'fa-plus')]";
	public static final String ADDRESS = "xpath=//tbody/tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td/div[contains(.,'%s')and contains(.,'%s')and contains(.,'%s')and contains(.,'%s')and contains(.,'%s')]";
	public static final String NO_DATA_MESSAGE = "xpath=//div[@id='customer-address']//tr/td[text()='No data available in table']";

}
