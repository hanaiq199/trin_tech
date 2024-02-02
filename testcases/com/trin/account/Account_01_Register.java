package com.trin.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Account_01_Register {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://www.joyrise.io/");
        driver.quit();
    }

    @Test
    public void Register_Empty_Data(){

    }

    @AfterClass
    public void afterClass(){
       driver.quit();
    }

}
