package com.amazon;

import Base.commonApi;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.homePage;
import pages.loginPage;
import reporting.utility.utility;

import java.util.Properties;

public class loginPageTest extends commonApi {
    Properties properties = utility.loadProperties();
    String DecodeAmazonUserName = utility.decode(properties.getProperty("amazon.username"));
    String DecodeAmazonPassword =utility.decode(properties.getProperty("amazon.password"));


@Test
    public void loginPageComponentAreDisplayed() throws InterruptedException {
        loginPage login = new loginPage(driver);
        homePage home = new homePage(driver);

        home.clickHomePageSigninBtn();
        Assert.assertTrue(login.checkUsernameField());
        waitTime(3000);
        login.typeUserNameAndEnter(utility.decode(properties.getProperty("amazon.username")));
        login.checkPasswordField();
        login.checkLogInPageSignInBtn();

    }
    @Test
    public void logInWithValidCrd(){
        loginPage login = new loginPage(driver);
        homePage home = new homePage(driver);

        home.clickHomePageSigninBtn();
        login.typeUserNameAndEnter(DecodeAmazonUserName);
        login.typePasswordAndEnter(DecodeAmazonPassword);
        Assert.assertEquals(login.getLogInPageHeader(),"");
    }




    @Test
    public void loginWithInvalidUserName(){
        loginPage login = new loginPage(driver);
        homePage home = new homePage(driver);

        home.clickHomePageSigninBtn();
        login.typeUserNameAndEnter(" ");
        Assert.assertEquals(home.getMissingEmailAlert(),"Enter your email or mobile phone number");

    }
   @Test
    public void loginWithInvalidPassword(){
        loginPage login = new loginPage(driver);
        homePage home = new homePage(driver);

        home.clickHomePageSigninBtn();
        login.typeUserNameAndEnter(DecodeAmazonUserName);
        login.typePasswordAndEnter(" ");
        /*element is not attached to the page document error occur (missing email error msg works fine on
        logiInWithInvEmail test above) )*/
        Assert.assertEquals(home.getMissingPasswordAlert(), "Enter your password");
    }

}
