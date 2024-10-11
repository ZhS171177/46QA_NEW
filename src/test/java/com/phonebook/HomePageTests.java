package com.phonebook;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    @BeforeMethod
    public void preCondition(){
        app.getHomeHelper().click(By.xpath("//a[contains(text(),'HOME')]"));
        //app.driver.get("https://telranedu.web.app/home");

    }


    @Test(invocationCount = 1, priority = 1)
    public void isHomeComponentPresentTest() {
        Assert.assertTrue(app.getHomeHelper().isHomeComponentPresent(), "Element not found on page");
        System.out.println("Item found on home page");

    }
}




