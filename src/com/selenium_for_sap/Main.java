package com.selenium_for_sap;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

    public WebDriver obj;

    public  void invokeBrowser(){
        try {
            System.out.println("Hello");
            System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
            obj = new ChromeDriver();
            obj.manage().deleteAllCookies();
            obj.manage().window().maximize();
            obj.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            obj.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

            obj.get("http://www.google.com");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void mSearch(){
        
    }

    public static void main(String[] args) {
        Main mTest = new Main();
        mTest.invokeBrowser();
    }
}
