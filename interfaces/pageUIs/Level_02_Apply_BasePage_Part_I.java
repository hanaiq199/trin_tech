package com.trin.account;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Level_02_Apply_BasePage_Part_I {
    WebDriver driver;
    BasePage basePage;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://www.joyrise.io/");
        driver.quit();

        basePage = new BasePage();

    }

    @Test
    public void Register_Empty_Data(){

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

}
