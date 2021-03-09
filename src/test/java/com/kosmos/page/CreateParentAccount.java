package com.kosmos.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kosmos.core.PageObjectInit;

public class CreateParentAccount extends PageObjectInit {
	WebDriver driver = super.getWebBrowser();

	By setUpParentHeader = By.xpath("//div[contains(text(),'Set up parent support')]");
	By firstNameTextBox = By.xpath("//div[@class='upperCamelCase']/label[contains(text(),'First name')]/../input");
	By lastNameTextBox = By.xpath("//div[@class='upperCamelCase']/label[contains(text(),'Last name')]/../input");
	By emailTextBox = By.xpath("//div/label[contains(text(),'Email address')]/../input");
	By countyDropDownButton = By.xpath("//*[@id='main']/div/div[3]/div[1]/div[3]/div[1]/div[1]/div[1]/button");
	By countryName = By.xpath("//div[contains(text(),'India')]");
	By createParentUsername = By.xpath("//div/label[contains(text(),'Create parent username')]/../input");
	By createParentPassword = By.xpath("//div/label[contains(text(),'Create parent password')]/../input");
	By confirmPasswordTextBox = By.xpath("//div/label[contains(text(),'Confirm password')]/../input");
	By createButton = By.xpath("//button/div/div/span[contains(text(),'CREATE ACCOUNT')]");

	public static CreateParentAccount createAccountPFIns = null;

	private CreateParentAccount() {
	}

	public static CreateParentAccount getPFInstance() {
		if (createAccountPFIns == null)
			createAccountPFIns = new CreateParentAccount();
		return createAccountPFIns;
	}

	WebDriverWait wait = new WebDriverWait(driver, 20);

	// method to validate create parent account page is loaded
	public boolean validateCreateParentPageLoaded() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(setUpParentHeader));
		return true;
	}

	// method to enter first name
	public CreateParentAccount enterFirstName(String firstName) {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(firstNameTextBox)));
		driver.findElement(firstNameTextBox).sendKeys(firstName);
		return this;
	}

	// method to enter first name
	public CreateParentAccount enterLastName(String lastName) {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(lastNameTextBox)));
		driver.findElement(lastNameTextBox).sendKeys(lastName);
		return this;
	}

	// method to enter first name
	public CreateParentAccount enterEmail(String email) {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(emailTextBox)));
		driver.findElement(emailTextBox).sendKeys(email);
		return this;
	}

	// method to enter first name
	public CreateParentAccount enterparentUserName(String parentUserName) {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(createParentUsername)));
		driver.findElement(createParentUsername).sendKeys(parentUserName);
		return this;
	}

	// method to enter first name
	public CreateParentAccount enterParentPassword(String parentPassword) {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(createParentPassword)));
		driver.findElement(createParentPassword).sendKeys(parentPassword);
		return this;
	}

	// method to enter first name
	public CreateParentAccount enterConfirmedPassword(String confirmPassword) {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(confirmPasswordTextBox)));
		driver.findElement(confirmPasswordTextBox).sendKeys(confirmPassword);
		return this;
	}

	// method to select country
	public CreateParentAccount selectCountry() {
		driver.findElement(countyDropDownButton).click();
		driver.findElement(countryName).click();
		return this;
	}

	// method to verify that create button is disabled
	public boolean createButtonIsDisabled() {
		String attributes = driver.findElement(createButton).getAttribute("style");
		boolean isDisabled = attributes.contains("opacity: 0.3");
		return isDisabled;
	}

}
