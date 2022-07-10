package pageObjects.nopComerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopComerce.user.CustomerInForPageUI;

public class UserCustomerInfoPageObject extends BasePage {
	private WebDriver driver;

	public UserCustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isCustomerInfoPageIsDisplayed() {
		waitForElementVisible(driver, CustomerInForPageUI.CUSTOMER_INFOR_HEADER);
		return isElementDisplay(driver, CustomerInForPageUI.CUSTOMER_INFOR_HEADER);
	}

	public void clickToRadioFemaleGender() {
		waitForElementClickable(driver, CustomerInForPageUI.GENDER_FEMALE_RADIO);
		clickToElement(driver, CustomerInForPageUI.GENDER_FEMALE_RADIO);
	}

	public boolean isFemaleRadioSelected() {
		waitForElementClickable(driver, CustomerInForPageUI.GENDER_FEMALE_RADIO);
		return isElementSelected(driver, CustomerInForPageUI.GENDER_FEMALE_RADIO);
	}
}
