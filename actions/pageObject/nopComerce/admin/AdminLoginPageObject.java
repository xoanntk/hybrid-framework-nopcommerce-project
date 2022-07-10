package pageObject.nopComerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGenerator;
import pageUIs.nopComerce.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {
	private WebDriver driver;

	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToUserNameTextbox(String emailAddress) {
		waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
		senkeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		senkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public AdminDashboardPageObject clickToLoginButton() {
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return PageGenerator.getAdminDashboardPage(driver);
	}

	public AdminDashboardPageObject loginAsAdmin(String emailAddress, String password) {
		inputToUserNameTextbox(emailAddress);
		inputToPasswordTextbox(password);
		return clickToLoginButton();
	}

}
