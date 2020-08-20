package com.selenium_for_sap;

import java.util.ArrayList;
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
  private static String PASSWORD = "Welcome1";
  private int rejections = 50;
  private boolean rejected = false;


  public void invokeBrowser(ArrayList<Data> list) {
    try {
      System.out.println("Hello");
      System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
      obj = new ChromeDriver();
      obj.manage().deleteAllCookies();
      obj.manage().window().maximize();
      obj.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
      obj.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);

      obj.get(
          "https://my347340.sapbydesign.com/sap/public/ap/ui/repository/SAP_UI/HTMLOBERON5/client.html?client_type=html&app.component=/SAP_UI_CT/Main/root.uiccwoc&rootWindow=X&redirectUrl=/sap/public/ap/ui/runtime");
      mLogin();
      Thread.sleep(40000);
      for(Data p: list) {
//        checkInbound(p);
        checkOutbound(p);
        Thread.sleep(30000);
//        fillInbound(p);
        fillOutbound(p);
        Json writer = new Json();
        writer.addData(p);
      }

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

  public void checkInbound(Data data){
    try {
      obj.findElement(By.id("__item34")).click();
      Thread.sleep(2000);
      obj.findElement(By.xpath("/descendant::div[starts-with(@id, '__item')][2]")).click();
      Thread.sleep(20000);
      obj.findElement(By.xpath("//input[starts-with(@id, '__pane') and contains(@id, '-searchField-I')]")).sendKeys("*" + data.getSecondaryid() + "*");
      obj.findElement(By.xpath("//div[starts-with(@id, '__pane') and contains(@id, '-searchField-search')]")).click();
      Thread.sleep(10000);
      String rowCount = obj.findElement(By.xpath("//table[@class='sapBUiListTab']")).getAttribute("aria-rowcount");
      if(Integer.parseInt(rowCount) == 1){
        System.out.println("row count is equal to 1");
        obj.findElement(By.xpath("//span[.='Post Goods Receipt']")).click();
      }

    }catch (Exception e){
      e.printStackTrace();
    }
  }

  public void  fillInbound(Data data){
    try {
//    Fill takes date, internal waybill id, arrival date, date of offloading
      obj.findElement(By.xpath("/descendant::input[starts-with(@id, '__field') and contains(@id, '-inner')][1]")).clear();
      obj.findElement(By.xpath("/descendant::input[starts-with(@id, '__field') and contains(@id, '-inner')][1]")).sendKeys(data.getDate());
      obj.findElement(By.xpath("/descendant::input[starts-with(@id, '__field') and contains(@id, '-inner')][2]")).sendKeys(String.valueOf(data.getWaybill()));
      obj.findElement(By.xpath("/descendant::input[starts-with(@id, '__field') and contains(@id, '-inner')][4]")).sendKeys(data.getArrivaldate());
      obj.findElement(By.xpath("/descendant::input[starts-with(@id, '__field') and contains(@id, '-inner')][5]")).sendKeys(data.getOffloadingdate());
      ArrayList<String[]> smnq = new ArrayList<String[]>();
      Thread.sleep(15000);
      obj.findElement(By.xpath("//span[.='Propose Quantities']")).click();
      JavascriptExecutor jse = (JavascriptExecutor) obj;
      jse.executeScript("document.querySelector('table th:last-child').scrollIntoView();");
      Thread.sleep(10000);
      List<WebElement> rows = obj.findElements(By.xpath("//table[@class='sapBUiListTab']/tbody/tr"));
      int count = rows.size();
      System.out.println(count);
      for(int i = 1; i<count; ++i){
        int rowNumber = i-1;
        String grade = "//td[starts-with(@id, '__table') and contains(@id,"+"'" + "_r" + rowNumber + "_c7"
            + "'" +")]/div/div/div/input";
        String row = "//td[starts-with(@id, '__table') and contains(@id,"+"'" + "_r" + rowNumber + "_c6"
            + "'" +")]/div/div/div/div/input";
        String label = "//label[starts-with(@for, '__item') and contains(@for, '-0-dli-value')]";
        String currentgrade = obj.findElement(By.xpath(grade)).getAttribute("value");
        String quantity= obj.findElement(By.xpath(row)).getAttribute("value");
        obj.findElement(By.xpath(grade)).clear();
        obj.findElement(By.xpath(grade)).sendKeys(data.getGrade());
        Thread.sleep(15000);
        obj.findElement(By.xpath(label)).click();
        System.out.println("label button clicked");
        Thread.sleep(20000);
        if(rejections!= 0 && !rejected){
          if(Integer.parseInt(quantity)>rejections){
            String addedRow = "//td[starts-with(@id, '__table') and contains(@id,"+"'" + "_r" + i + "_c6"
                + "'" +")]/div/div/div/div/input";
            String addedGrade = "//td[starts-with(@id, '__table') and contains(@id,"+"'" + "_r" + i + "_c7"
                + "'" +")]/div/div/div/input";
            int qauntityLeft = Integer.parseInt(quantity) - rejections;
            obj.findElement(By.xpath(row)).clear();
            obj.findElement(By.xpath(row)).sendKeys(String.valueOf(qauntityLeft));
            Thread.sleep(20000);
            obj.findElement(By.xpath("//span[.='Add Sub Item']")).click();
            Thread.sleep(15000);
            jse.executeScript("document.querySelector('table th:last-child').scrollIntoView();");
            Thread.sleep(5000);
            obj.findElement(By.xpath(addedRow)).sendKeys(String.valueOf(rejections));
            Thread.sleep(5000);
            obj.findElement(By.xpath(addedGrade)).clear();
            obj.findElement(By.xpath(addedGrade)).sendKeys("DS_TD_AGL");
            Thread.sleep(5000);
            obj.findElement(By.xpath(label)).click();
            currentgrade = obj.findElement(By.xpath(addedGrade)).getAttribute("value");
            i++;
            count = count +2;
            rejected=true;
          }else  if(Integer.parseInt(quantity)==rejections){
            obj.findElement(By.xpath(grade)).clear();
            obj.findElement(By.xpath(grade)).sendKeys("DS_TD_AGL");
            Thread.sleep(5000);
            obj.findElement(By.xpath(label)).click();
            currentgrade = obj.findElement(By.xpath(grade)).getAttribute("value");
            rejected=true;
          }else {
            obj.findElement(By.xpath(grade)).clear();
            obj.findElement(By.xpath(grade)).sendKeys("DS_TD_AGL");
            Thread.sleep(5000);
            obj.findElement(By.xpath(label)).click();
            currentgrade = obj.findElement(By.xpath(grade)).getAttribute("value");
            rejections = rejections - Integer.parseInt(quantity);
          }
        }
        if (!currentgrade.equals("DS_TD_AGL")) {
          Thread.sleep(8000);
          String stationMark = obj.findElement(By.xpath(
              "/descendant::div[@class='sapBUiCctsMinWidth sapBUiCctsFormIdentifierIDAndDescription'][2]/div/div/input"))
              .getAttribute("value");
          String [] stationmarknquantity = new String[]{quantity, stationMark};
          smnq.add(stationmarknquantity);
          data.setStationmarknquantity(smnq);

          System.out.println(quantity);
          System.out.println(stationMark);
        }
      }
      obj.findElement(By.xpath("//span[.='Save and Close']")).click();
      Thread.sleep(170000);
      String inboundID = obj.findElement(By.xpath("/descendant::div[@class='sapMMsgStripMessage'][1]/span[1]")).getText();
      data.setInboundid(inboundID);

      System.out.println(inboundID);
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public void checkOutbound(Data data){
    try {
      ArrayList<String[]> stationnquantity = data.getStationmarknquantity();
      String station = (String) stationnquantity.get(0)[1];
      String[] splits = station.split(" ");
      System.out.println();
      obj.findElement(By.id("__item52")).click();
      Thread.sleep(2000);
      obj.findElement(By.xpath("/descendant::div[starts-with(@id, '__item')][3]")).click();
      Thread.sleep(20000);
      obj.findElement(By.xpath("//span[starts-with(@id, '__pane') and contains(@id, '-defaultSetDDLB-arrow')]")).click();
      Thread.sleep(1000);
      obj.findElement(By.xpath("/descendant::ul[starts-with(@id, '__list')]/li[11]")).click();
      Thread.sleep(3000);
      obj.findElement(By.xpath("//a[.='Click here to execute the query']")).click();
      Thread.sleep(15000);
      obj.findElement(By.xpath("//input[starts-with(@id, '__pane') and contains(@id, '-searchField-I')]")).sendKeys(splits[0]);
      obj.findElement(By.xpath("//div[starts-with(@id, '__pane') and contains(@id, '-searchField-search')]")).click();
      Thread.sleep(15000);
      String rowCount = obj.findElement(By.xpath("//table[@class='sapBUiListTab']")).getAttribute("aria-rowcount");
      if(Integer.parseInt(rowCount) == 1){
        System.out.println("row count is equal to 1");
        obj.findElement(By.xpath("//span[.='Post Goods Issue']")).click();
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  public void fillOutbound(Data data) throws InterruptedException {

    //Freight input
    obj.findElement(By.xpath("/descendant::input[starts-with(@id, '__input') and contains(@id, '-inner')][1]")).sendKeys("KGL - Kiteko Ghana Ltd.");
    //Shipment Date
    obj.findElement(By.xpath("/descendant::input[starts-with(@id, '__field') and contains(@id, '-inner')][1]")).clear();
    obj.findElement(By.xpath("/descendant::input[starts-with(@id, '__field') and contains(@id, '-inner')][1]")).sendKeys(data.getDate());
    //CTOR input
    obj.findElement(By.xpath("/descendant::input[starts-with(@id, '__field') and contains(@id, '-inner')][3]")).sendKeys(String.valueOf(data.getCtor()));
    //Actual weight
    obj.findElement(By.xpath("/descendant::input[starts-with(@id, '__field') and contains(@id, '-inner')][4]")).sendKeys(String.valueOf(data.getTonage()));
    //CTOR Date
    obj.findElement(By.xpath("/descendant::input[starts-with(@id, '__field') and contains(@id, '-inner')][5]")).sendKeys(data.getCtordate());
    //Summery Ref
    obj.findElement(By.xpath("/descendant::input[starts-with(@id, '__field') and contains(@id, '-inner')][6]")).sendKeys(data.getCropseason());
    //Purity Certification No.
    obj.findElement(By.xpath("/descendant::input[starts-with(@id, '__field') and contains(@id, '-inner')][7]")).sendKeys(String.valueOf(data.getPurity()));
    //Purity date
    obj.findElement(By.xpath("/descendant::input[starts-with(@id, '__field') and contains(@id, '-inner')][8]")).sendKeys(data.getPuritydate());
    //Waybill number
    obj.findElement(By.xpath("/descendant::input[starts-with(@id, '__input') and contains(@id, '-inner')][2]")).sendKeys(String.valueOf(data.getWaybill()));

    ArrayList<String[]> stationnquantity = data.getStationmarknquantity();
    int list = stationnquantity.size();

    for(int i=1; i<list; i++) {
      obj.findElement(By.xpath("//span[.='Add Sub Item']")).click();
      try {
        Thread.sleep(15000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    Thread.sleep(10000);
    for (String[] item: stationnquantity){
      String station = (String) item[1];
      String[] splits = station.split(" ");
      if (list !=1){
        for(int i=1; i<=list; i++) {
          String addedRow =
              "//td[starts-with(@id, '__table') and contains(@id," + "'" + "_r" + i + "_c7"
                  + "'" + ")]/div/div/div/div/input";
          String addedGrade =
              "//td[starts-with(@id, '__table') and contains(@id," + "'" + "_r" + i + "_c8"
                  + "'" + ")]/div/div/div/input";
          String stationmark =
              "//td[starts-with(@id, '__table') and contains(@id," + "'" + "_r" + i + "_c9"
                  + "'" + ")]/div/div/div/input";

          obj.findElement(By.xpath(addedRow)).clear();
          obj.findElement(By.xpath(addedRow)).sendKeys(item[0]);
          Thread.sleep(5000);
          obj.findElement(By.xpath(stationmark)).sendKeys(item[1]);
        }
      }else {
        obj.findElement(By.xpath("//td[starts-with(@id, '__table') and contains(@id,'_r0_c9')]/div/div/div/input")).sendKeys(splits[0]);
        obj.findElement(By.xpath("//span[.='Remove Sub Item']")).click();
        Thread.sleep(5000);
        obj.findElement(By.xpath("//td[starts-with(@id, '__table') and contains(@id,'_r0_c7')]/div/div/div/div/input")).sendKeys(item[0]);
      }

    }
    Thread.sleep(10000);
    obj.findElement(By.xpath("//span[.='Release']")).click();

  }

  public static void main(String[] args) {
    Main mTest = new Main();
    Json json = new Json();
    ArrayList<Data> list = new ArrayList<Data>();
    list = (ArrayList<Data>) json.getJsonData();
    mTest.invokeBrowser(list);
  }
}
