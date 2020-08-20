package com.selenium_for_sap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Json {

  private static ObjectMapper objectMapper = getDefaultObjectMapper();



  private static ObjectMapper getDefaultObjectMapper(){
    ObjectMapper defaultobjectMapper = new ObjectMapper();
    defaultobjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return defaultobjectMapper;
  }

  public static JsonNode parse(String src) throws IOException {
    return objectMapper.readTree(src);
  }

  public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {

    return objectMapper.treeToValue(node, clazz);
  }

  public static JsonNode toJson(Object a){

    return  objectMapper.valueToTree(a);
  }

  public static  String stringify(JsonNode node) throws JsonProcessingException {
    return generateString(node, false);
  }
  public static  String prettyPrint(JsonNode node) throws JsonProcessingException {
    return generateString(node, true);
  }
  public static  String generateString(JsonNode node, boolean pretty) throws JsonProcessingException {
    ObjectWriter objectWriter = objectMapper.writer();
    if (pretty)
      objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
    return objectWriter.writeValueAsString(node);

  }

  public List<Data> getJsonData() {
    InputStream mJsonfile;
    ArrayList<Data> mData = new ArrayList<Data>();
      try {
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        mJsonfile = new FileInputStream(new File("data.json"));
        TypeReference<List<Data>> typeReference = new TypeReference<List<Data>>() {
        };

        mData = (ArrayList<Data>) objectMapper.readValue(mJsonfile, typeReference);



      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (JsonParseException e) {
        e.printStackTrace();
      } catch (JsonMappingException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    return mData;
  }


  public void addData(Data data){
    InputStream mJsonfile;
    {
      try {
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        ArrayList<Data> obj  = new ArrayList<Data>();
        obj.add(data);

        objectMapper.writeValue(new File("data.json"), obj);


      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (JsonParseException e) {
        e.printStackTrace();
      } catch (JsonMappingException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  public static void main(String [] args){
    Json json = new Json();
    ArrayList<Data> mData = (ArrayList<Data>) json.getJsonData();
    for(Data p : mData){
      System.out.println(p.getDistrict());
    }

//    ArrayList<String[]> list = new ArrayList<String[]>();
//    list.add(new String []{ "40", "AGL-MAN -Manso Amenfi 2" });
//    list.add(new String []{ "76", "AGL-MAN -Manso Amenfi fifty"});
//    Data myData = new Data("KGL - Kiteko Ghana Ltd.", "31.07.2020 08:53 PM UTC", "Manso Amenfi",
//         14702, "31-7-20", "0008270", 14589, "31-7-20", "P_TD_AGL", 9,list
//         , "UNO-1452", "UNO-1452"
//  );

//    json.addData(myData);
  }
}
