package pages;

import Base.commonApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePage extends commonApi {
    Logger LOG = LogManager.getLogger(homePage.class.getName());
    WebDriver driver;
    public homePage(WebDriver driver) {

        PageFactory.initElements(driver,this);
    }


    @FindBy(css = "*[id='nav-link-accountList-nav-line-1']")
    WebElement homePageSigninBtn;
    @FindBy(xpath = "//*[@id='icp-flyout-mkt-change']/span/div")
    WebElement changeStoreBtn;
    @FindBy(css = "*[class='a-spacing-extra-large']")
    WebElement changingStorePageHeader;
    @FindBy(css = "*[data-action='a-dropdown-select']")
    WebElement dropdownOptions;
    @FindBy(css = ".a-button-input")
    WebElement goToAnewRegionBtn;
    @FindBy(css = "#nav-logo-sprites")
    WebElement newRegionPageHeader;
    @FindBy(xpath = "//*[@id='nav-flyout-ya-newCust']/a")
    WebElement startHereBtn;
    @FindBy(css = ".a-spacing-small")
    WebElement createNewAccountPageHeader;
    @FindBy(css = "#ap_customer_name")
    WebElement firstAndLastNameField;
    @FindBy (css = "#ap_email")
    WebElement usernameField;
    @FindBy(css = "*[id='ap_password']")
    WebElement passwordField;
    @FindBy(css ="#ap_password_check")
    WebElement retypePasswordField;
    @FindBy(css = "#continue")
    WebElement verifyEmailBtn;
    @FindBy(xpath = "//*[@id='auth-email-missing-alert']/div/div")
    WebElement emailMissingAlert;
    @FindBy(xpath = "//*[@id='auth-password-missing-alert']/div/div")
    WebElement passwordMissingAlert;
    @FindBy(css = "*[id='nav-logo-sprites']")
    WebElement homePageLogo;
    @FindBy(css = "#createAccountSubmit")
    WebElement createNewAccountBtn;
    @FindBy(css = "//*[@id='cvf-page-content']/div/div/div/div[1]/span")
    WebElement solvePuzzleMsg;
    @FindBy(css = "*[class='icp-nav-flag icp-nav-flag-us']")
    WebElement flagBtn;


    public void clickOnFlagBtn(){
        clickBtn(flagBtn);
        LOG.info("flag button clicked");
    }
    public void HoverOverFlagBtn(){
        LOG.info("hovered over flag button");
        hoverOver(driver,flagBtn);
    }
    public boolean checkFlagBtn(){
        LOG.info("flag button is displayed: "+isDisplayed(flagBtn));
        return isDisplayed(flagBtn);
    }
    public String getSolvePuzzleMsg(){
        LOG.info(getelmText(solvePuzzleMsg));
        return getelmText(solvePuzzleMsg);
    }
    public void clickCreateNewAccountBtn(){
        clickBtn(createNewAccountBtn);
    }
    public boolean CheckHomePageLogo(){
        LOG.info("Amazon logo is displayed: "+isDisplayed(homePageLogo));
        return isDisplayed(homePageLogo);
    }
    public String getHomePageLogoText(){
        LOG.info("home page logo text: "+getelmText(homePageLogo));
        return getelmText(homePageLogo);
    }
    public void clickHomePageSigninBtn() {
        try {
            clickWithActions(driver, homePageSigninBtn);
        } catch (Exception e) {
            clickBtn(homePageSigninBtn);
        }
        LOG.info("sign button is clicked");
    }

    public void scrollToChangeStoreBtn() throws InterruptedException {
        scrollToViewJS(changeStoreBtn);
    }

    public void clickChangeStoreBtn() {
        clickWithActions(driver, changeStoreBtn);
        System.out.println("change store/region button clicked");
    }

    public String getChangingStorePageHeader() {
        LOG.info(getelmText(changingStorePageHeader));
        return getelmText(changingStorePageHeader);
    }

    public void chooseStore(String region) {
        selectFromDropDown(dropdownOptions, region);
        LOG.info("store/region selected");
    }

    public void clickGoToNewRegionWebPage() {
        clickWithActions(driver, goToAnewRegionBtn);
    }

    public String getNewRegionPageHeader() {
        LOG.info("new region page header: " + getelmText(newRegionPageHeader));
        return getelmText(newRegionPageHeader);
    }

    public void hoverOverHomePageSignInBtn() {

        hoverOver(driver, homePageSigninBtn);
    }

    public void clickCreateNewAcc() {
        clickWithActions(driver,createNewAccountBtn);
        LOG.info("Create new acc button clicked");
    }

    public String getCreateNewAccountPageHeader() {
        LOG.info("create a new account page header title: " + getelmText(createNewAccountPageHeader));
        return getelmText(createNewAccountPageHeader);
    }

    public void typeFirstLastNameAndTabKey(String firstAndLastName) {
        typeAndTabKey(firstAndLastNameField, firstAndLastName);
        LOG.info("customer name entered");
    }

    public void typeUserNameAndTabKey(String username) {
        typeAndTabKey(usernameField, username);
        LOG.info("user name entered");
    }
    public void typeUserPasswordAndTabKey(String password) {
        typeAndTabKey(passwordField, password);
        LOG.info("password entered");
    }
    public void retypeUserPasswordAndEnter(String password) {
        typeAndEnter(retypePasswordField, password);
        LOG.info("password reentered");
    }
    public void clickVerifyEmailBtn(){
        clickBtn(verifyEmailBtn);
        LOG.info("verify Email button clicked");
    }
    public String getMissingEmailAlert(){
        LOG.info("Alert message: "+getelmText(emailMissingAlert));
        return getelmText(emailMissingAlert);
    }
    public String getMissingPasswordAlert(){
        LOG.info("Alert message: "+getelmText(passwordMissingAlert));
        return getelmText(passwordMissingAlert);
    }



}