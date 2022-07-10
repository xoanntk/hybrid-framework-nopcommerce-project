package pageObjects.nopComerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class UserChangePasswordPageObject extends BasePage {
	WebDriver driver;

	public UserChangePasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
