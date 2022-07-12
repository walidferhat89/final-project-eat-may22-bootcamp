package com.amazon;

import Base.commonApi;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.homePage;
import pages.shopPage;
import reporting.utility.utility;
import reporting.utility.connectDB;
import reporting.utility.excelReader;



public class homePageTest extends commonApi {

String amazonDecodeFirstAndLast = utility.decode(properties.getProperty("newAmazon.firstAndLastName"));
String amazonDecodeNewUser = utility.decode(properties.getProperty("newAmazon.username"));
    connectDB connectDB = new connectDB();
    @Test
    public void checkUsersLandOnTheCorrectPage(){
        excelReader excelReader = new excelReader("C:\\Users\\walid\\IdeaProjects\\framework-selenium-practice\\data\\amazoneData.xlsx","sheet1");
        getTitle();
        Assert.assertEquals(excelReader.getDataForGivenKey("key","webSite title"),getTitle());

    }
    @Test
    public void checkPresenceOfHomePageComp(){
        excelReader excelReader = new excelReader("C:\\Users\\walid\\IdeaProjects\\framework-selenium-practice\\data\\amazoneData.xlsx","sheet1");
        homePage homePage = new homePage(getDriver());
        shopPage shop = new shopPage(getDriver());

        homePage.CheckHomePageLogo();
        Assert.assertEquals(excelReader.getDataForGivenKey("key","HomePageLogoText"),homePage.getHomePageLogoText());
        shop.checkSearchTextBox();
        homePage.checkFlagBtn();

    }
    @Test
    public void changeStore() throws InterruptedException {

        homePage homePage = new homePage(getDriver());

        /*homePage.scrollToChangeStoreBtn();*/
        homePage.HoverOverFlagBtn();
        homePage.clickChangeStoreBtn();
        homePage.getChangingStorePageHeader();

        Assert.assertEquals("Website (Country/Region)",homePage.getChangingStorePageHeader());
        homePage.chooseStore("Italy (Italia)");
        homePage.clickGoToNewRegionWebPage();
        Assert.assertEquals("Amazon.it",homePage.getNewRegionPageHeader());
    }
    @Test
    public void createNewAcc() throws Exception {
        excelReader excelReader = new excelReader("C:\\Users\\walid\\IdeaProjects\\framework-selenium-practice\\data\\amazoneData.xlsx","sheet1");
        homePage home = new homePage(getDriver());

       /* String dataBaseUsername = connectDB.readDataBase("amzncredentials","username").get(0);
        String dataBasePassword = connectDB.readDataBase("amzncredentials","password").get(0);
*/
        /*home.hoverOverHomePageSignInBtn();*/
        home.clickHomePageSigninBtn();
        home.clickCreateNewAccountBtn();
        /*home.clickCreateNewAcc();*/
        Assert.assertEquals(excelReader.getDataForGivenKey("key","CreateNewAccountPageHeader"),home.getCreateNewAccountPageHeader());

        home.typeFirstLastNameAndTabKey(utility.decode(properties.getProperty("newAmazon.firstAndLastName")));
        home.typeUserNameAndTabKey(utility.decode(properties.getProperty("newAmazon.username")));
        home.typeUserPasswordAndTabKey(utility.decode(properties.getProperty("newAmazon.password")));
        home.retypeUserPasswordAndEnter(utility.decode(properties.getProperty("newAmazon.password")));
        Assert.assertEquals(excelReader.getDataForGivenKey("key","solve puzzle msg"),home.getSolvePuzzleMsg());
        /*home.clickVerifyEmailBtn();*/

    }
    @Test
    public void createAccWithInvalidEmail(){
        excelReader excelReader = new excelReader("C:\\Users\\walid\\IdeaProjects\\framework-selenium-practice\\data\\amazoneData.xlsx","sheet1");
        homePage home = new homePage(getDriver());

        /*home.hoverOverHomePageSignInBtn();
        home.clickCreateNewAcc();*/
        home.clickHomePageSigninBtn();
        home.clickCreateNewAccountBtn();
        Assert.assertEquals("Create account",home.getCreateNewAccountPageHeader());

        home.typeFirstLastNameAndTabKey(amazonDecodeFirstAndLast);
        home.typeUserNameAndTabKey(" ");
        Assert.assertEquals(home.getMissingEmailAlert(),excelReader.getDataForGivenKey("key","missing email alert"));;

    }
    @Test
    public void createAccWithInvalidPassword(){
        excelReader excelReader = new excelReader("C:\\Users\\walid\\IdeaProjects\\framework-selenium-practice\\data\\amazoneData.xlsx","sheet1");
        homePage home = new homePage(getDriver());

       /* home.hoverOverHomePageSignInBtn();
        home.clickCreateNewAcc();*/
        home.clickHomePageSigninBtn();
        home.clickCreateNewAccountBtn();
        Assert.assertEquals("Create account",excelReader.getDataForGivenKey("key","missing password alert"));

        home.typeFirstLastNameAndTabKey(amazonDecodeFirstAndLast);
        home.typeUserNameAndTabKey(amazonDecodeNewUser);
        home.typeUserPasswordAndTabKey(" ");
        Assert.assertEquals("Minimum 6 characters required",home.getMissingPasswordAlert());

    }









}