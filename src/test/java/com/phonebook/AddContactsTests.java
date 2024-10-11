package com.phonebook;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactsTests extends TestBase {

    private final String CONTACT_NAME = "TestName";

    @BeforeMethod
    public void preCondition() {

        try {
            app.getUserHelper().login("asddffg_1234@gmail.com", "QWERTqwe123!");
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }

    /* @Tag("Positive")
       @Tag("Smoke")
       @Tag("Visa")
     */
    @Test(invocationCount = 1, priority = 1)
    public void addContactPositiveTest() {
        app.getContactHelper().addNewContactPositiveData(CONTACT_NAME);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
    }

    @Test(priority = 2)
    public void addContactPositiveWODescriptionTest() {
        app.getContactHelper().addNewContactPositiveDataWODescription(CONTACT_NAME);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
    }

    @AfterMethod(enabled = false)
    public void postCondition() {

        app.getContactHelper().deleteOneContact();
    }
}
