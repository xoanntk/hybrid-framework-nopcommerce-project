package pageObjects.nopComerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGenerator;
import pageUIs.nopComerce.user.MyProductReviewPageUI;

public class UserMyProductReviewPageObject extends BasePage {
	WebDriver driver;

	public UserMyProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getReviewTitle() {
		waitForElementVisible(driver, MyProductReviewPageUI.REVIEWTITLE);
		return getElementText(driver, MyProductReviewPageUI.REVIEWTITLE);
	}

	public String getReviewText() {
		waitForElementVisible(driver, MyProductReviewPageUI.REVIEWTEXT);
		return getElementText(driver, MyProductReviewPageUI.REVIEWTEXT);
	}

	public String getSuccessMessage() {
		waitForElementVisible(driver, MyProductReviewPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, MyProductReviewPageUI.SUCCESS_MESSAGE);
	}

	public UserHomePageObject clickToProductLink() {
		waitForElementClickable(driver, MyProductReviewPageUI.PRODUCT_LINK);
		clickToElement(driver, MyProductReviewPageUI.PRODUCT_LINK);
		return PageGenerator.getUserHomePage(driver);
	}
}
