package com.selenium_for_sap;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

  public WebDriver obj;
  private static String USERNAME = "Georgea01";
  private static String PASSWORD = "Ecom12345";


  public void invokeBrowser() {
    try {
      System.out.println("Hello");
      System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
      obj = new ChromeDriver();
      obj.manage().deleteAllCookies();
      obj.manage().window().maximize();
      obj.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
      obj.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);

      obj.get(
          "https://my344821.sapbydesign.com/sap/public/ap/ui/repository/SAP_UI/HTMLOBERON5/client.html?client_type=html&app.component=/SAP_UI_CT/Main/root.uiccwoc&rootWindow=X&redirectUrl=/sap/public/ap/ui/runtimehttps://my344821.sapbydesign.com/sap/public/ap/ui/repository/SAP_UI/HTMLOBERON5/client.html?client_type=html&app.component=/SAP_UI_CT/Main/root.uiccwoc&rootWindow=X&redirectUrl=/sap/public/ap/ui/runtime");
      mLogin();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void mLogin() {
    try {
      obj.findElement(By.id("__control0-user-inner")).sendKeys(USERNAME);
      obj.findElement(By.id("__control0-pass-inner")).sendKeys(PASSWORD);
      obj.findElement(By.id("__control0-logonBtn")).click();
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Main mTest = new Main();
    mTest.invokeBrowser();
  }
}
