package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import Utilities.DataProviders;

/*
 Data is valid - login success - test passed - logout
 Data is valid - login failed -test failed
 
 Data is invalid - login success -test failed -logout
 Data is invalid - login failed - test passed
 
 */
public class TC003_LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups = "Datadriven")
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		logger.info("Starting TC003_LoginDDT...");
		
		try {
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account link");
		hp.clickLogin();
		logger.info("Clicked on Login link");
		
		LoginPage lp = new LoginPage(driver);
		logger.info("Providing login credentials...");
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if (targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("Finished TC003_LoginDDT...");
	}
	
}
