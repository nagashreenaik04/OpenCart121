package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	
	@Test(groups = {"Regression", "Master"})
	public void verify_account_registration()
	{
		logger.info("***Starting TC001_AccountRegistrationTest ***");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account link");
		hp.clickRegister();
		logger.info("Clicked on Register link");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		logger.info("Providing customer details...");
		regpage.setFirstName("John");
		regpage.setLastName("Son");
		regpage.setEmail(randomString()+ "@gmail.com"); //randomly generated the email
		regpage.setTelephone(randomNumeric());
		String password = randomAlphaNummeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("validating expected message...");
		String confmsg = regpage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else {
			logger.info("Test Failed...");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}
		
//		Assert.assertEquals(confmsg, "Your Account Has Been Created!!!");
		} 
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("*** Finished TC001_AccountRegistrationTest ***");
	}
	


}
