package com.kosmos.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.kosmos.core.ExcelHelper;
import com.kosmos.core.TestListener;
import com.kosmos.page.CreateParentAccount;
import com.kosmos.page.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

public class CreateParentAccountTest extends TestListener {
	
	ExcelHelper exHand = new ExcelHelper(this);
	LoginPage loginObj = new LoginPage();
	CreateParentAccount createObj = CreateParentAccount.getPFInstance();
	
	@Test(priority = 3, groups = { "createParentAccount" })
	public void createParentAccount() {
		String tcName = createObj.getTCName();
		String firstName = exHand.getTestDataValue(tcName, "firstName");
		String lastName = exHand.getTestDataValue(tcName, "lastName");
		String emailAddress = exHand.getTestDataValue(tcName, "emailAddress");
		String parentUserName = exHand.getTestDataValue(tcName, "parentUserName");
		String parentPassword = exHand.getTestDataValue(tcName, "parentPassword");
		String confirmPassword = exHand.getTestDataValue(tcName, "confirmPassword");
		loginObj.clickParentSupportLink();
		loginObj.clickCreateButton();
		Assert.assertTrue(createObj.validateCreateParentPageLoaded(), "Create Parent Account page not loaded");
		Assert.assertTrue(
				createObj.enterFirstName(firstName).enterLastName(lastName).enterEmail(emailAddress)
						.enterparentUserName(parentUserName).enterParentPassword(parentPassword)
						.enterConfirmedPassword(confirmPassword).createButtonIsDisabled(),
				"Create button is not disabled");
		test.log(LogStatus.INFO, "Validated that create button is disabled when all the fields are not filled");
		
	}

}
