package pages;

import Base.commonApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class shopPage extends commonApi {
    Logger LOG = LogManager.getLogger(loginPage.class.getName());
    public shopPage(WebDriver driver){

        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "#searchDropdownBox")
    WebElement departmentDropDownBox;
    @FindBy(css = "#twotabsearchtextbox")
    WebElement searchTextBox;
    @FindBy(css = "*[class='nav-a nav-b']")
    WebElement amazonDevicesDepPageHeader;
    @FindBy(xpath = "//*[@id='search']/div[1]/div[1]/div/span[3]/div[2]/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/h2/a/span")
    WebElement itemChosenToPurchase;
    @FindBy(css = "#add-to-cart-button")
    WebElement addToCartBtn;
    @FindBy(css = "*[class='a-button-text a-text-left']")
    WebElement addToCartButton2;
    @FindBy(xpath = "//*[@id='abb-intl-pop-cta']/span[3]/span/input")
    WebElement noCoverageBtn;
    @FindBy(xpath = "//*[@id='sw-atc-details-single-container']/div[2]/div[1]/span")
    WebElement addedToCartConfirmation;

    public void chooseFromDepartmentDropBox(String option){

        selectFromDropDown(departmentDropDownBox,option);
    }
    public void searchInTextBox(String itemYouLookingFor){

        typeAndEnter(searchTextBox,itemYouLookingFor);
    }
    public  String getAmazonDevicesDepPageHeader(){
        LOG.info("amazon devices dep Page Header: "+getelmText(amazonDevicesDepPageHeader));
        return getelmText(amazonDevicesDepPageHeader);
    }
    public void scrollToChosenItem(){
        scrollToViewJS(itemChosenToPurchase);
    }
    public void clickOnChosenItem(){
        clickBtn(itemChosenToPurchase);
        LOG.info("item chosen get clicked");
    }
    public void clickAddToCartBtn(){
        clickBtn(addToCartButton2);
        LOG.info("add to cart button clicked");
    }


    public void clickNoCoverageBtn() {
        LOG.info("no thanks button clicked");
        clickBtn(noCoverageBtn);
    }
    public String getAddConfirmationHeader(){
        LOG.info(getelmText(addedToCartConfirmation));
        return getelmText(addedToCartConfirmation);
    }
    public boolean checkSearchTextBox(){
        LOG.info("search box is displayed: "+isDisplayed(searchTextBox));
        return isDisplayed(searchTextBox);
    }

}
