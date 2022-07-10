package pageObject.nopComerce.admin;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGenerator;
import pageUIs.nopComerce.admin.AdminAddNewCustomerPageUI;

public class AdminAddNewCusomerPageObject extends BasePage {
	private WebDriver driver;

	public AdminAddNewCusomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToCustomerRolesListbox(String textValue) {
		waitForElementClickable(driver, AdminAddNewCustomerPageUI.DELETE_ICON);
		clickToElement(driver, AdminAddNewCustomerPageUI.DELETE_ICON);
		waitForElementVisible(driver, AdminAddNewCustomerPageUI.CUSTOMER_ROLES_LISTBOX);
		senkeyToElement(driver, AdminAddNewCustomerPageUI.CUSTOMER_ROLES_LISTBOX, textValue);
		pressKeyToElement(driver, AdminAddNewCustomerPageUI.CUSTOMER_ROLES_LISTBOX, Keys.ENTER);
	}

	public void checkToActiveCheckbox() {
		waitForElementClickable(driver, AdminAddNewCustomerPageUI.ACTIVE_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, AdminAddNewCustomerPageUI.ACTIVE_CHECKBOX);
	}

	public void inputToAdminCommentTextArea(String textValue) {
		waitForElementVisible(driver, AdminAddNewCustomerPageUI.ADMIN_COMMENT_TEXTAREA);
		senkeyToElement(driver, AdminAddNewCustomerPageUI.ADMIN_COMMENT_TEXTAREA, textValue);
	}

	public void clickToSaveAndContinuteEditButton() {
		waitForElementClickable(driver, AdminAddNewCustomerPageUI.SAVE_CONTINUTE_EDIT_BUTTON);
		clickToElement(driver, AdminAddNewCustomerPageUI.SAVE_CONTINUTE_EDIT_BUTTON);
	}

	public String getSuccessMessageDisplayed() {
		waitForElementVisible(driver, AdminAddNewCustomerPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, AdminAddNewCustomerPageUI.SUCCESS_MESSAGE);
	}

	public boolean isCustomerRolesSelected(String customerRoles) {
		waitForElementVisible(driver, AdminAddNewCustomerPageUI.CUSTOMER_ROLE_SELECTED, customerRoles);
		return isElementDisplay(driver, AdminAddNewCustomerPageUI.CUSTOMER_ROLE_SELECTED, customerRoles);
	}

	public String getTextAtAdminCommentTextarea() {
		waitForElementVisible(driver, AdminAddNewCustomerPageUI.ADMIN_COMMENT_TEXTAREA);
		return getElementText(driver, AdminAddNewCustomerPageUI.ADMIN_COMMENT_TEXTAREA);
	}

	public AdminCustomersPageObject clickToBackToCustomerList() {
		waitForElementClickable(driver, AdminAddNewCustomerPageUI.BACK_TO_CUSTOMER_LIST_LINK);
		clickToElement(driver, AdminAddNewCustomerPageUI.BACK_TO_CUSTOMER_LIST_LINK);
		return PageGenerator.getCustomersPage(driver);
	}

}
