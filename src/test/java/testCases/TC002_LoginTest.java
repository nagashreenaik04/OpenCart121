package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups = {"Sanity","Master"})
	public void verify_login()
	{
		logger.info("***** Starting TC002_LoginTest *****");
		
		try {
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account link");
		hp.clickLogin();
		logger.info("Clicked on Login link");
		
		LoginPage lp = new LoginPage(driver);
		logger.info("Providing login credentials...");
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
//		Assert.assertTrue(targetPage);//
		Assert.assertEquals(targetPage, true, "Login Failed");
		}
		catch(Exception e)
		{
			logger.info("Login Failed...");
			Assert.fail();
		}
		logger.info("***** Finished TC002_LoginTest *****");
	}

}
