package pageObject.nopComerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGenerator;
import pageUIs.nopComerce.admin.AdminEditCustomerPageUI;

public class AdminEditCusomerPageObject extends BasePage {
	private WebDriver driver;

	public AdminEditCusomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToAdminCommentTextArea(String textValue) {
		waitForElementVisible(driver, AdminEditCustomerPageUI.ADMIN_COMMENT_TEXTAREA);
		senkeyToElement(driver, AdminEditCustomerPageUI.ADMIN_COMMENT_TEXTAREA, textValue);
	}

	public AdminCustomersPageObject clickToSaveButton() {
		waitForElementClickable(driver, AdminEditCustomerPageUI.SAVE_BUTTON);
		clickToElement(driver, AdminEditCustomerPageUI.SAVE_BUTTON);
		return PageGenerator.getCustomersPage(driver);
	}

	public void clickToAddressesCard() {
		waitForElementClickable(driver, AdminEditCustomerPageUI.ADDRESSES_CARD);
		clickToElement(driver, AdminEditCustomerPageUI.ADDRESSES_CARD);
	}

	public AdminAddNewAddressPageObject clickToAddNewAddressButton() {
		waitForElementClickable(driver, AdminEditCustomerPageUI.ADD_NEW_ADDRESS_BUTTON);
		clickToElementByJS(driver, AdminEditCustomerPageUI.ADD_NEW_ADDRESS_BUTTON);
		return PageGenerator.getAddNewAddressPage(driver);
	}

	public AdminEditAddressPageObject clickToEditButton() {
		waitForElementClickable(driver, AdminEditCustomerPageUI.EDIT_BUTTON);
		clickToElementByJS(driver, AdminEditCustomerPageUI.EDIT_BUTTON);
		return PageGenerator.getEditAddressPage(driver);
	}

	public void clickToDeleteButton() {
		waitForElementClickable(driver, AdminEditCustomerPageUI.DELETE_BUTTON);
		clickToElement(driver, AdminEditCustomerPageUI.DELETE_BUTTON);
	}

	public boolean isAddressDisplayed(String firstName, String lastName, String email, String phoneNumber, String faxNumber, String companyName, String address1, String address2,
			String cityZippostalCode, String country) {
		waitForElementVisible(driver, AdminEditCustomerPageUI.ADDRESS, firstName, lastName, email, phoneNumber, faxNumber, companyName, address1, address2, cityZippostalCode, country);
		return isElementDisplay(driver, AdminEditCustomerPageUI.ADDRESS, firstName, lastName, email, phoneNumber, faxNumber, companyName, address1, address2, cityZippostalCode, country);
	}

	public boolean isNoDataMessageDisplayed() {
		waitForElementVisible(driver, AdminEditCustomerPageUI.NO_DATA_MESSAGE);
		return isElementDisplay(driver, AdminEditCustomerPageUI.NO_DATA_MESSAGE);
	}

	public void acceptToAlert() {
		waitForAlertPresence(driver);
		acceptAlert(driver);
	}

}
