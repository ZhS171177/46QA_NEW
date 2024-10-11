package com.phonebook;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase {
    private String CONTACT_NAME="TestName";


    @BeforeMethod
    public void preCondition() {
        try {
            app.getUserHelper().login("asddffg_1234@gmail.com", "QWERTqwe123!");
        } catch (Exception e) {
           // throw new RuntimeException(e);
        }
        app.getContactHelper().addNewContactPositiveData(CONTACT_NAME);
    }

    @Test
    public void createOneAndDeleteOneContactTest() {
        int sizeBefore = app.getContactHelper().actualSizeOfContacts();
        app.getContactHelper().deleteOneContact();
        app.wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.className(app.getContactHelper().CONTACT_LOCATOR), sizeBefore));
        int sizeAfterDelete = app.getContactHelper().actualSizeOfContacts();
        System.out.println(sizeBefore);
        System.out.println(sizeAfterDelete);
        Assert.assertEquals(sizeBefore - 1, sizeAfterDelete, "Count is not equal");
    }


    @Test
    public void deleteAllContactsTests() {
        app.getContactHelper().deleteAllContacts();
        Assert.assertEquals(app.getContactHelper().actualSizeOfContacts(), 0, "Count is not equal");
    }

    @AfterMethod
    public  void postCondition(){
        try {
            app.getUserHelper().logout();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }

    }
}

