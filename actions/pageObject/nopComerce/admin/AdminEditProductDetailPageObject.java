package pageObject.nopComerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopComerce.admin.AdminEditProductDetailPageUI;

public class AdminEditProductDetailPageObject extends BasePage {
	private WebDriver driver;

	public AdminEditProductDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getProductNameDisplayed() {
		waitForElementVisible(driver, AdminEditProductDetailPageUI.PRODUCT_NAME_TEXT);
		return getElementAttribute(driver, AdminEditProductDetailPageUI.PRODUCT_NAME_TEXT, "value");
	}

	public void clickToProductInfoCard() {
		waitForElementClickable(driver, AdminEditProductDetailPageUI.PRODUCT_INFO_CARD);
		clickToElement(driver, AdminEditProductDetailPageUI.PRODUCT_INFO_CARD);
	}

}
