package pages;

import Base.commonApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage extends commonApi {
    Logger LOG = LogManager.getLogger(loginPage.class.getName());
    public loginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#signInSubmit")
    WebElement logInPageSigninBtn;
    @FindBy(css = "#ap_email")
    WebElement usernameField;
    @FindBy(css = "*[id='ap_password']")
    WebElement passwordField;
    @FindBy(xpath = "//*[@id='header']/div/div/a/i")
    WebElement logInAcountPageHeader;


    public String getLogInPageHeader(){
        LOG.info("account page header: "+getelmText(logInAcountPageHeader));
        return getelmText(logInAcountPageHeader);
    }
    public boolean checkUsernameField() {
        LOG.info("user name field is displayed:" +isDisplayed(usernameField));
        return isDisplayed(usernameField);
    }

    public boolean checkPasswordField() {
        LOG.info("password field is displayed: "+isDisplayed(passwordField));
        return isDisplayed(passwordField);
    }

    public boolean checkLogInPageSignInBtn() {
        LOG.info("sign in btn is displayed: "+isDisplayed(logInPageSigninBtn));
        return isDisplayed(logInPageSigninBtn);
    }

    public void clickLogInPageSigninBtn() {
        clickBtn(logInPageSigninBtn);
        LOG.info("sign button is clicked");
    }

    public void typeUserNameAndEnter(String username) {
        typeAndEnter(usernameField, username);
    }

    public void typePasswordAndEnter(String password) {
        typeAndEnter(passwordField, password);
    }

}




