package com.phonebook.core;

import com.phonebook.fw.ContactHelper;
import com.phonebook.fw.HomeHelper;
import com.phonebook.fw.UserHelper;
import com.phonebook.model.Contact;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApplicationManager {
    public WebDriver driver;
    public WebDriverWait wait;
    UserHelper userHelper;
    HomeHelper homeHelper;
    ContactHelper contactHelper;
    private static String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options=new ChromeOptions();
            //options.addArguments("window-size=1920x1080");
            // options.addArguments("headless");// запуск без графического драйвера, используем когда все тесты готовы и отлажены
           // driver = new ChromeDriver(options);
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver=new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("edge")) {
            driver=new EdgeDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver=new SafariDriver();
        }

        {
            driver.get("https://telranedu.web.app/home");
            driver.manage().window().maximize();//разворачивает браузер на весь экран
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));// неявное ожидание 10 c
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));// явное ожидание
            userHelper = new UserHelper(driver, wait);
            homeHelper = new HomeHelper(driver, wait);
            contactHelper = new ContactHelper(driver, wait);
        }
    }

        public UserHelper getUserHelper () {
            return userHelper;
        }

        public HomeHelper getHomeHelper () {
            return homeHelper;
        }

        public ContactHelper getContactHelper () {
            return contactHelper;
        }

        public void stop () {
            driver.quit();//закрывает весь драйвер
        }
    }
