package pageUIs.nopComerce.admin;

public class AdminAddNewCustomerPageUI {
	public static final String DELETE_ICON = "xpath=//span[@aria-label='delete']";
	public static final String CUSTOMER_ROLES_LISTBOX = "xpath=//ul[@id='SelectedCustomerRoleIds_taglist']/following-sibling::input";
	public static final String ACTIVE_CHECKBOX = "xpath=//input[@id='Active']";
	public static final String ADMIN_COMMENT_TEXTAREA = "xpath=//textarea[@id='AdminComment']";
	public static final String SAVE_CONTINUTE_EDIT_BUTTON = "xpath=//button[@name='save-continue']";
	public static final String SUCCESS_MESSAGE = "xpath=//div[contains(@class,'alert-success')]";
	public static final String CUSTOMER_ROLE_SELECTED = "xpath=//span[@unselectable='on' and text()='%s']";
	public static final String BACK_TO_CUSTOMER_LIST_LINK = "xpath=//a[text()='back to customer list']";

}
