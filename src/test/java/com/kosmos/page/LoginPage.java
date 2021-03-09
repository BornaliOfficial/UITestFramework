package com.kosmos.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.kosmos.core.PageObjectInit;

public class LoginPage extends PageObjectInit {

	WebDriver driver = super.getWebBrowser();

	By loginStageWrap = By.xpath("//*[@class='Stage loginWrap']");
	By languageDropdown = By.xpath("//*[@class='accountDetailsLangDropDown']");
	By dropDownMenuItems = By.xpath("//span[@role='menuitem']");
	By languageList = By.xpath("//span[@role='menuitem']/div/div/div");
	By englishMenuItem = By.xpath("//span[@role='menuitem']/div/div/div[contains(text(),'English')]");
	By hindiMenuItem = By.xpath("//span[@role='menuitem']/div/div/div[contains(text(),'हिंदी')]");
	By espanolMenuItem = By.xpath("//span[@role='menuitem']/div/div/div[contains(text(),'Español')]");
	By englishContinueBtn = By.xpath("//*[@id=\"SI_0005\"]//div[contains(text(),'Continue')]");
	By hindiContinueBtn = By.xpath("//*[@id=\"SI_0005\"]//div[contains(text(),'अग्रसर रहें')]");
	By espanolContinueBtn = By.xpath("//*[@id=\"SI_0005\"]//div[contains(text(),'Continuar')]");
	By parentSupportLink = By.linkText("Set up parent support");
	By createAccountBtn = By.xpath(
			"//div[@class='acrCheckCodeButton']/button/div/div/span[contains(text(),'CREATE A NEW ACCOUNT')]/../../..");

	WebDriverWait wait = new WebDriverWait(driver, 20);

// method to validate that login page has successfully loaded
	public boolean validateLoginPage() {
		super.invokeImplicitWait(30);
		driver.findElements(By.tagName("iframe"));
		driver.navigate().refresh();
		super.invokeImplicitWait(30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(loginStageWrap)));
		return driver.findElement(loginStageWrap).isDisplayed();
	}

	// method to get number of options in language drop down
	public int getNumberOfOptionsFromLanguageDropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(languageDropdown)));

		// drop down item should be designed with select tag but here only div
		// tag is used, so Select class is not working for the drop down element

//		Select dropDownEl = new Select(driver.findElement(languageDropdown));
//		List<WebElement> drpDownList = dropDownEl.getOptions();

		// alternate approach
		WebElement dropDownEl = driver.findElement(languageDropdown);
		try {
			dropDownEl.click();
		} catch (NoSuchElementException nse) {
			System.out.println("Element searched to click on is not available");
		}
		List<WebElement> drpDownList = super.getElementList(dropDownMenuItems);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(languageDropdown)));
		driver.findElement(englishMenuItem).click();
		return drpDownList.size();
	}

	// method to auto click on each element of the language drop down and validate
	// label of Continue button
	public void validateContinueButtonLabel() {
		// force refreshing the page
		driver.navigate().refresh();

		// applying wait to handle stale element exception
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(languageDropdown)));
		WebElement dropDownEl = driver.findElement(languageDropdown);
		dropDownEl.click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(englishMenuItem)));
		driver.findElement(englishMenuItem).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(englishContinueBtn));
		Assert.assertTrue(driver.findElement(englishContinueBtn).isDisplayed(),
				"Continue button's label didn't get updated to English");

		// force refreshing the page
		driver.navigate().refresh();

		// applying wait to handle stale element exception
		sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(languageDropdown)));
		driver.findElement(languageDropdown).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(hindiMenuItem)));
		driver.findElement(hindiMenuItem).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(hindiContinueBtn));
		Assert.assertTrue(driver.findElement(hindiContinueBtn).isDisplayed(),
				"Continue button's label didn't get updated to Hindi");

		// force refreshing the page
		driver.navigate().refresh();

		// applying wait to handle stale element exception
		sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(languageDropdown)));
		driver.findElement(languageDropdown).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(espanolMenuItem)));
		driver.findElement(espanolMenuItem).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(espanolContinueBtn));
		Assert.assertTrue(driver.findElement(espanolContinueBtn).isDisplayed(),
				"Continue button's label didn't get updated to Espanol");
	}

	// method to click on parent support link
	public void clickParentSupportLink() {
		// force refreshing the page
		driver.navigate().refresh();

		// applying wait to handle stale element exception
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(languageDropdown)));
		driver.findElement(languageDropdown).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(englishMenuItem)));
		driver.findElement(englishMenuItem).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(englishContinueBtn));
		Assert.assertTrue(driver.findElement(englishContinueBtn).isDisplayed(),
				"Continue button's label didn't get updated to English");
		sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(parentSupportLink)));
		driver.findElement(parentSupportLink).click();
	}

	// method to click on create button
	public CreateParentAccount clickCreateButton() {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(createAccountBtn)));
		driver.findElement(createAccountBtn).click();
		return CreateParentAccount.getPFInstance();
	}

}
