package com.kosmos.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.kosmos.core.ExcelHelper;
import com.kosmos.core.TestListener;
import com.kosmos.page.CreateParentAccount;
import com.kosmos.page.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest extends TestListener {
	ExcelHelper exHand = new ExcelHelper(this);
	LoginPage loginObj = new LoginPage();
	CreateParentAccount createObj = CreateParentAccount.getPFInstance();

	@Test(priority = 0, groups = { "login" })
	public void applicationLaunchedSuccessfully() {
		Assert.assertTrue(loginObj.validateLoginPage(), "Application launched failed");
		test.log(LogStatus.INFO, "Application launched successfully");
	}

	@Test(priority = 1, groups = { "login" })
	public void checkNumberOfLanguageDropDownElement() {
		Assert.assertEquals(loginObj.getNumberOfOptionsFromLanguageDropdown(), 3,
				"Language dropdown doesn't have three options");
		test.log(LogStatus.INFO, "Language Dropdown has atleast three options");
	}

	@Test(priority = 2, groups = { "login" })
	public void validateLabelChangeOfContinueButton() {
		loginObj.validateContinueButtonLabel();
		test.log(LogStatus.INFO, "Language Dropdown has atleast three options");
	}

}
