package com.amazon;

import Base.commonApi;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.shopPage;

public class shoppingTest extends commonApi {

    @Test
    public void addItemsToCart(){
        shopPage shop = new shopPage(driver);

        shop.chooseFromDepartmentDropBox("Amazon Devices");
        shop.searchInTextBox("Amazon Fire TV 65\" Omni Series 4K UHD smart TV with Dolby Vision, hands-free with Alexa");
        Assert.assertEquals(shop.getAmazonDevicesDepPageHeader(),"Amazon Devices");

        shop.clickOnChosenItem();
        shop.clickAddToCartBtn();
        shop.clickNoCoverageBtn();
        Assert.assertEquals(shop.getAddConfirmationHeader(),"Added to Cart");



    }
}
