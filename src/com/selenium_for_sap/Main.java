package com.selenium_for_sap;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

  public WebDriver obj;
  private static String USERNAME = "MichaelK01";
  private static String PASSWORD = "Welcome01";
  private boolean rejections = false;


  public void invokeBrowser() {
    try {
      System.out.println("Hello");
      System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
      obj = new ChromeDriver();
      obj.manage().deleteAllCookies();
      obj.manage().window().maximize();
      obj.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
      obj.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);

      obj.get(
          "https://my348520.sapbydesign.com/sap/public/ap/ui/repository/SAP_UI/HTMLOBERON5/client.html?client_type=html&app.component=/SAP_UI_CT/Main/root.uiccwoc&rootWindow=X&redirectUrl=/sap/public/ap/ui/runtime");
      mLogin();
      Thread.sleep(40000);
      checkInbound();
      Thread.sleep(30000);
      fillInbound();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void mLogin() {
    try {
      obj.findElement(By.id("__control0-user-inner")).sendKeys(USERNAME);
      obj.findElement(By.id("__control0-pass-inner")).sendKeys(PASSWORD);
      obj.findElement(By.id("__control0-logonBtn")).click();
      Thread.sleep(5000);
      try {
        obj.findElement(By.id("__control1-continueBtn")).click();
      } catch (NoSuchElementException e){
        e.printStackTrace();
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  public void checkInbound(){
    try {
      obj.findElement(By.id("__item25")).click();
      Thread.sleep(2000);
      obj.findElement(By.xpath("/descendant::div[starts-with(@id, '__item')][2]")).click();
      Thread.sleep(20000);
      obj.findElement(By.id("__pane1-searchField-I")).sendKeys("*" + "1723" + "*");
      obj.findElement(By.id("__pane1-searchField-search")).click();
      Thread.sleep(5000);
      String rowCount = obj.findElement(By.xpath("//table[@class='sapBUiListTab']")).getAttribute("aria-rowcount");
      if(Integer.parseInt(rowCount) == 1){
        obj.findElement(By.id("__button22-inner")).click();

      }

    }catch (Exception e){
      e.printStackTrace();
    }
  }

  public void  fillInbound(){
    try {
//    Fill takes date, internal waybill id, arrival date, date of offloading
      obj.findElement(By.id("__field64-inner")).clear();
      obj.findElement(By.id("__field64-inner")).sendKeys("12.08.2020 02:29 PM UTC");
      obj.findElement(By.id("__field66-inner")).sendKeys("14702");
      obj.findElement(By.id("__field70-inner")).sendKeys("13-08-20");
      obj.findElement(By.id("__field72-inner")).sendKeys("14-08-20");

      Thread.sleep(20000);
      obj.findElement(By.id("__button42")).click();
      JavascriptExecutor jse = (JavascriptExecutor) obj;
      jse.executeScript("document.querySelector('table th:last-child').scrollIntoView();");
      Thread.sleep(10000);
      List<WebElement> rows = obj.findElements(By.xpath("//table[@class='sapBUiListTab']/tbody/tr"));
      int count = rows.size();
      System.out.println(count);
      for(int i = 1; i<count; ++i){
        int rowNumber = i-1;
        String grade = "//td[starts-with(@id, '__table') and contains(@id,"+"'" + "_r" + rowNumber + "_c9"
            + "'" +")]/div/div/div/input";
        String row = "//td[starts-with(@id, '__table') and contains(@id,"+"'" + "_r" + rowNumber + "_c8"
            + "'" +")]/div/div/div/div/input";
        String quantity= obj.findElement(By.xpath(row)).getAttribute("value");
        obj.findElement(By.xpath(row)).click();
//        Thread.sleep(5000);
//        obj.findElement(By.xpath(row)).sendKeys(quantity);
        Thread.sleep(7000);
        obj.findElement(By.xpath(grade)).clear();
        obj.findElement(By.xpath(grade)).sendKeys("P_TD_AGL");
        jse.executeScript("document.querySelector('table th:last-child').scrollIntoView();");
        Thread.sleep(5000);
        String stationMark = obj.findElement(By.xpath("//input[starts-with(@value, 'AGL')]")).getAttribute("value");
        System.out.println(quantity);
        System.out.println(stationMark);
      }
    }catch(Exception e){
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    Main mTest = new Main();
    mTest.invokeBrowser();
  }
}
