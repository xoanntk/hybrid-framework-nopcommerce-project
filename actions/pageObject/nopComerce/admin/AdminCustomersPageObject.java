package pageObject.nopComerce.admin;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGenerator;
import pageUIs.nopComerce.admin.AdminCustomersPageUI;

public class AdminCustomersPageObject extends BasePage {
	private WebDriver driver;

	public AdminCustomersPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public AdminAddNewCusomerPageObject clickToAddNewButton() {
		waitForElementClickable(driver, AdminCustomersPageUI.ADD_NEW_BUTTON);
		clickToElementByJS(driver, AdminCustomersPageUI.ADD_NEW_BUTTON);
		return PageGenerator.getAddNewCustomerPage(driver);
	}

	public boolean isCustomerDisplayed(String name, String customerRoles, String companyName, String active) {
		waitForElementVisible(driver, AdminCustomersPageUI.CUSTOMER_INFOR, name, customerRoles, companyName, active);
		return isElementDisplay(driver, AdminCustomersPageUI.CUSTOMER_INFOR, name, customerRoles, companyName, active);
	}

	public AdminEditCusomerPageObject clickToEditButton() {
		waitForElementClickable(driver, AdminCustomersPageUI.EDIT_BUTTON);
		clickToElement(driver, AdminCustomersPageUI.EDIT_BUTTON);
		return PageGenerator.getEditCustomerPage(driver);
	}

	public void inputToCustomerRolesListbox(String textValue) {
		waitForElementClickable(driver, AdminCustomersPageUI.DELETE_ICON);
		clickToElement(driver, AdminCustomersPageUI.DELETE_ICON);
		waitForElementVisible(driver, AdminCustomersPageUI.CUSTOMER_ROLES_LISTBOX);
		senkeyToElement(driver, AdminCustomersPageUI.CUSTOMER_ROLES_LISTBOX, textValue);
		pressKeyToElement(driver, AdminCustomersPageUI.CUSTOMER_ROLES_LISTBOX, Keys.ENTER);
	}

	public String getSuccessMessageDisplayed() {
		waitForElementVisible(driver, AdminCustomersPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, AdminCustomersPageUI.SUCCESS_MESSAGE);
	}
}
