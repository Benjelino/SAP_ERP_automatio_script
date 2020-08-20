package com.selenium_for_sap;

import com.sun.istack.internal.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Data {

  private String frightforwarder;
  private String date;
  private String district;
  private int waybill;
  private String ctordate;
  private int ctor;
  private String puritydate;
  private String grade;
  private int tonage;
  private ArrayList<String[]> stationmarknquantity;
  private String inboundid;
  private String outboundid;
  private  String secondaryid;
  private String arrivaldate;
  private String offloadingdate;


  public Data(){}

  public Data(String frightforwarder, String date, String district, int waybill,
      String ctordate, int ctor, String puritydate, String grade, int tonage,
      ArrayList<String[]> stationmarknquantity, String inboundid, String outboundid,
      String secondaryid, String arrivaldate, String offloadingdate) {
    this.frightforwarder = frightforwarder;
    this.date = date;
    this.district = district;
    this.waybill = waybill;
    this.ctordate = ctordate;
    this.ctor = ctor;
    this.puritydate = puritydate;
    this.grade = grade;
    this.tonage = tonage;
    this.stationmarknquantity = stationmarknquantity;
    this.inboundid = inboundid;
    this.outboundid = outboundid;
    this.secondaryid = secondaryid;
    this.arrivaldate = arrivaldate;
    this.offloadingdate = offloadingdate;
  }


  public String getArrivaldate() {
    return arrivaldate;
  }

  public void setArrivaldate(String arrivaldate) {
    this.arrivaldate = arrivaldate;
  }

  public String getOffloadingdate() {
    return offloadingdate;
  }

  public void setOffloadingdate(String offloadingdate) {
    this.offloadingdate = offloadingdate;
  }

  public String getFrightforwarder() {
    return frightforwarder;
  }

  public void setFrightforwarder(String frightforwarder) {
    this.frightforwarder = frightforwarder;
  }

  public String getSecondaryid() {
    return secondaryid;
  }

  public void setSecondaryid(String secondaryid) {
    this.secondaryid = secondaryid;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public int getWaybill() {
    return waybill;
  }

  public void setWaybill(int waybill) {
    this.waybill = waybill;
  }

  public String getCtordate() {
    return ctordate;
  }

  public void setCtordate(String ctordate) {
    this.ctordate = ctordate;
  }

  public int getCtor() {
    return ctor;
  }

  public void setCtor(int ctor) {
    this.ctor = ctor;
  }

  public String getPuritydate() {
    return puritydate;
  }

  public void setPuritydate(String puritydate) {
    this.puritydate = puritydate;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public int getTonage() {
    return tonage;
  }

  public void setTonage(int tonage) {
    this.tonage = tonage;
  }

  public ArrayList<String[]> getStationmarknquantity() {
    return stationmarknquantity;
  }

  public void setStationmarknquantity(ArrayList<String[]> stationmarknquantity) {
    this.stationmarknquantity = stationmarknquantity;
  }

  public String getInboundid() {
    return inboundid;
  }

  public void setInboundid(String inboundid) {
    this.inboundid = inboundid;
  }

  public String getOutboundid() {
    return outboundid;
  }

  public void setOutboundid(String outboundid) {
    this.outboundid = outboundid;
  }


}
