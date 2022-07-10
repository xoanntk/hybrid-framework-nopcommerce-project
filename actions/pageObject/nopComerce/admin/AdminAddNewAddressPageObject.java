package pageObject.nopComerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGenerator;
import pageUIs.nopComerce.admin.AdminAddNewAddressPageUI;

public class AdminAddNewAddressPageObject extends BasePage {
	private WebDriver driver;

	public AdminAddNewAddressPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public AdminEditAddressPageObject clickToSaveButton() {
		waitForElementClickable(driver, AdminAddNewAddressPageUI.SAVE_BUTTON);
		clickToElement(driver, AdminAddNewAddressPageUI.SAVE_BUTTON);
		return PageGenerator.getEditAddressPage(driver);
	}

}
