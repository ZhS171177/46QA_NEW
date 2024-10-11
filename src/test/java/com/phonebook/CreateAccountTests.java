package com.phonebook;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountTests extends TestBase {


    @BeforeMethod
    public void preCondition() {
        try {
            app.getUserHelper().logout();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }

    }

    @Test
    public void createAccountPositiveTests1() {

        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User ()
                .setEmail("asddffg_1239@gmail.com")
                .setPassword("QWERTqwe123!"));
        app.getUserHelper().clickRegistrationButton();
        Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent());
    }

    @Test
    public void createAccountPositiveTests2() {
        String email="asddff"+System.currentTimeMillis()+"@gmail.com";
        String password="QWERTqwe123!";

        app.getUserHelper().registration(email, password);
    }

    @Test
    public void createAccountLoginPositiveTest() {
        String email="asddff"+System.currentTimeMillis()+"@gmail.com";
        String password="QWERTqwe123!";
        app.getUserHelper().registration(email, password);
        app.getUserHelper().logout();
        app.getUserHelper().login(email, password);
    }

    @Test
    public void createAccountNegativeTest() {
        SoftAssert softAssert = new SoftAssert();
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User().setEmail("asddffg_1234@gmail.com").setPassword("QWERTqwe123!"));
        app.getUserHelper().clickRegistrationButton();
        // Assert.assertFalse(isSignOutButtonPresent());
        // Assert.assertTrue(isAlertPresent());
        // Assert.assertTrue(isError409Present());
        /*
         * В Java, SoftAssert — это класс, предоставляемый библиотекой TestNG, который используется для выполнения "мягких"
         * утверждений (soft assertions) в тестах. В отличие от "жестких" (hard assertions), которые немедленно прерывают
         * выполнение теста при ошибке, мягкие утверждении позволяют продолжить выполнение теста даже если одно из утверждений не прошло.
         * Цель: SoftAssert используется для проверки нескольких условий в рамках одного теста, не прерывая его выполнение при первой неудаче
         */
        softAssert.assertTrue(app.getUserHelper().isAlertPresent());
        softAssert.assertTrue(app.getUserHelper().isError409Present());
        softAssert.assertAll();
        /*
         * Назначение: assertAll() используется для проверки всех утверждений, сделанных с помощью SoftAssert, в конце теста.
         * Если одно или несколько утверждений не прошли, assertAll() вызовет исключение и тест будет помечен как неудавшийся
         */
    }

    @AfterMethod
    public void postCondition() {
        try {
            app.getUserHelper().logout();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }

    }

}
