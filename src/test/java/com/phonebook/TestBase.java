package com.phonebook;

import com.phonebook.core.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class TestBase {
     Logger logger = LoggerFactory.getLogger(TestBase.class);

//    @BeforeSuite
//    public void test1() {
//        logger.info("hello @BeforeSuite");
//    }

//    @BeforeClass
//    public void test2() {
//        logger.info("hello  @BeforeClass");
//    }

    protected static ApplicationManager app = new ApplicationManager
            (System.getProperty("browser", "chrome"));
   // Logger logger = LoggerFactory.getLogger(TestBase.class);


    //@BeforeMethod
    @BeforeSuite
    public void setUp() {
        logger.info("*****************************Testing in progress****************************");
        app.init();
//        logger.info("Hello world.");
    }

    @BeforeMethod
    public void startTest(Method method){
        logger.info("Test is started ["+ method.getName()+"]");
    }
/*
   @AfterMethod
    public  void stopTest(Method method, ITestResult result){
        if(result.isSuccess()){
            logger.info("Test is passed ["+ method.getName()+"]");
        }else{
            logger.info("Test is failed ["+ method.getName()+"], Screenshot:["+app.getUserHelper().takeScreenshot()+"]");
        }
    }
*/



    //@AfterMethod(enabled = false)
    @AfterSuite(enabled =true)
    public void terDown() {
        logger.info("*****************************All test end****************************");
        app.stop();

    }

    @AfterMethod
    public void stopTest(Method method, ITestResult result, ITestContext context) {

        StringBuilder parameters = new StringBuilder();
        for (String key : context.getAttributeNames()) {
            Object value = context.getAttribute(key);
            parameters.append(key).append("=").append(value).append(", ");
        }

        if (parameters.length() > 0) {
            parameters.setLength(parameters.length() - 2);
        }

        logger.info("Test is started with date:["+parameters+"]");

        if (result.isSuccess()) {
            logger.info("Test is PASSED: [" + method.getName() + "]");
        } else {
            // Проверяем наличие алерта и закрываем его с помощью isAlertPresent
            if (app.getUserHelper().isAlertPresent()) {
                logger.warn("Alert was present and has been accepted.");
            } else {
                logger.info("No alert present.");
            }
            // Теперь делаем скриншот
            String screenshotPath = app.getUserHelper().takeScreenshot();
            logger.error("Test is FAILED: [" + method.getName() + "], Screenshot: [" + screenshotPath + "]");
        }
        logger.info("Test is ended: [" + method.getName() + "]");
    }

}
