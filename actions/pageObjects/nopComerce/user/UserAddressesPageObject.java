package pageObjects.nopComerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopComerce.user.AddressesPageUI;

public class UserAddressesPageObject extends BasePage {
	WebDriver driver;

	public UserAddressesPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectToCountryDropdown(String itemValue) {
		waitForElementClickable(driver, AddressesPageUI.COUNTRY_DROPDOWN);
		selectItemInDefaultDropdownByVisibleText(driver, AddressesPageUI.COUNTRY_DROPDOWN, itemValue);
	}
}
