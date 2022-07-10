package pageUIs.nopComerce.admin;

public class AdminCustomersPageUI {
	public static final String ADD_NEW_BUTTON = "xpath=//i[contains(@class,'fa-plus-square')]";
	public static final String CUSTOMER_INFOR = "xpath=//tbody/tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td/i[@nop-value='%s']";
	public static final String EDIT_BUTTON = "xpath=//i[contains(@class,'fa-pencil-alt')]";
	public static final String SAVE_BUTTON_AT_ADD_NEW_ADDRESS = "xpath=//button[contains(text(),'Add new address')]";
	public static final String CUSTOMER_ROLE_SELECTED = "xpath=//span[@unselectable='on' and text()='%s']";
	public static final String DELETE_ICON = "xpath=//span[@aria-label='delete']";
	public static final String CUSTOMER_ROLES_LISTBOX = "xpath=//ul[@id='SelectedCustomerRoleIds_taglist']/following-sibling::input";
	public static final String SUCCESS_MESSAGE = "xpath=//div[contains(@class,'alert-success')]";

}
