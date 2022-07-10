package pageObjects.nopComerce.user;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.nopComerce.user.SearchPageUI;

public class UserSearchPageObject extends BasePage {
	WebDriver driver;

	public UserSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getWarningMessageDiplayed() {
		waitForElementVisible(driver, SearchPageUI.WARNING_MESSAGE);
		return getElementText(driver, SearchPageUI.WARNING_MESSAGE);
	}

	public void inputToSearchTextbox(String value) {
		waitForElementVisible(driver, SearchPageUI.SEARCH_TEXTBOX);
		senkeyToElement(driver, SearchPageUI.SEARCH_TEXTBOX, value);
	}

	public int getTotalNumberProductDisplay() {
		waitForAllElementVisible(driver, SearchPageUI.PRODUCTS_NAME);
		return getElementSize(driver, SearchPageUI.PRODUCTS_NAME);
	}

	public boolean isProductsDiplayed(String productName) {
		List<WebElement> productNames = getListWebElement(driver, SearchPageUI.PRODUCTS_NAME);
		String products = null;
		for (WebElement product : productNames) {
			products = product.getText().toLowerCase();
		}
		return products.contains(productName.toLowerCase());
	}

	public void checkToAdvanceSearchCheckbox() {
		waitForElementClickable(driver, SearchPageUI.ADVANCE_SEARCH_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, SearchPageUI.ADVANCE_SEARCH_CHECKBOX);
	}

	public void selectValueinCategoryDropDown(String value) {
		waitForElementClickable(driver, SearchPageUI.CATEGORY_DROPDOWN);
		selectItemInDefaultDropdownByVisibleText(driver, SearchPageUI.CATEGORY_DROPDOWN, value);
	}

	public void uncheckToAutomateSubCategory() {
		waitForElementClickable(driver, SearchPageUI.AUTOMATE_SUB_CATEGORY_CHECKBOX);
		unCheckToDefaultCheckbox(driver, SearchPageUI.AUTOMATE_SUB_CATEGORY_CHECKBOX);
	}

	public String getNoResultMessageDisplayed() {
		waitForElementVisible(driver, SearchPageUI.NO_RESULT_MESSAGE);
		return getElementText(driver, SearchPageUI.NO_RESULT_MESSAGE);
	}

	public void checkToAutomateSubCategory() {
		waitForElementClickable(driver, SearchPageUI.AUTOMATE_SUB_CATEGORY_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, SearchPageUI.AUTOMATE_SUB_CATEGORY_CHECKBOX);
	}

	public void selectValueinManufaturerDropDown(String value) {
		waitForElementClickable(driver, SearchPageUI.MANUFACTURER_DROPDOWN);
		selectItemInDefaultDropdownByVisibleText(driver, SearchPageUI.MANUFACTURER_DROPDOWN, value);
	}
}
