package pageObject.nopComerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGenerator;
import pageUIs.nopComerce.admin.AdminEditAddressPageUI;

public class AdminEditAddressPageObject extends BasePage {
	private WebDriver driver;

	public AdminEditAddressPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToSaveButton() {
		waitForElementClickable(driver, AdminEditAddressPageUI.SAVE_BUTTON);
		clickToElement(driver, AdminEditAddressPageUI.SAVE_BUTTON);
	}

	public String getSuccessMessageDisplayed() {
		waitForElementVisible(driver, AdminEditAddressPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, AdminEditAddressPageUI.SUCCESS_MESSAGE);
	}

	public AdminEditCusomerPageObject clickBackToCustomerDetailLink() {
		waitForElementClickable(driver, AdminEditAddressPageUI.BACK_TO_CUSTOMER_DETAIL_LINK);
		clickToElement(driver, AdminEditAddressPageUI.BACK_TO_CUSTOMER_DETAIL_LINK);
		return PageGenerator.getEditCustomerPage(driver);
	}

	public void isAddressDisplayed(String editAddressFirstName, String editAddressLastName, String editAddressEmail, String editAddressPhoneNumber, String editAddressFaxNumber,
			String editAddressCompany, String editAddressAddress1, String editAddressAddress2, String string, String editAddressCountry) {
		// TODO Auto-generated method stub

	}

}
