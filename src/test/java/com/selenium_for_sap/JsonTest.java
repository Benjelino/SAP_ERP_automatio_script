package com.selenium_for_sap;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.selenium_for_sap.pojo.SimpleTestCaseJsonPojo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.junit.jupiter.api.Test;

class JsonTest {

  private String simpleTestCaseJsonSource = "{\"title\": \"Coder from scratch\", \"name\": \"Benjamin Kwame Amponsah\"}";

  @Test
  void getJsonData() {
    InputStream mJsonfile;
    {
      try {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        mJsonfile = new FileInputStream(new File("data.json"));
        TypeReference<List<SimpleTestCaseJsonPojo>> typeReference = new TypeReference<List<SimpleTestCaseJsonPojo>>() {
        };
        List<SimpleTestCaseJsonPojo> mSimplePojo = objectMapper.readValue(mJsonfile, typeReference);
        for (SimpleTestCaseJsonPojo p : mSimplePojo) {
          assertEquals(p.getTitle(), "The factors influencing food intake");
        }
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
  @Test
  void parse() throws IOException {

    JsonNode node = Json.parse(simpleTestCaseJsonSource);

    assertEquals(node.get("title").asText(), "Coder from scratch");
  }

  @Test
  void fromJson() throws IOException {

    JsonNode node = Json.parse(simpleTestCaseJsonSource);
    SimpleTestCaseJsonPojo pojo = Json.fromJson(node, SimpleTestCaseJsonPojo.class);

    assertEquals(pojo.getTitle(), "Coder from scratch");

  }

  @Test
  void toJson(){
    SimpleTestCaseJsonPojo pojo = new SimpleTestCaseJsonPojo();
    pojo.setTitle("The factors influencing food intake");

    JsonNode node = Json.toJson(pojo);

    assertEquals(node.get("title").asText(), "The factors influencing food intake");
  }

  @Test
  void stringify() throws JsonProcessingException {

    SimpleTestCaseJsonPojo pojo = new SimpleTestCaseJsonPojo();
    SimpleTestCaseJsonPojo yaw = new SimpleTestCaseJsonPojo();
    pojo.setTitle("The factors influencing food intake");
    yaw.setTitle("Figuring out the functions");

    JsonNode node = Json.toJson(pojo);
    assertEquals(Json.stringify(node), "{\"title\":\"The factors influencing food intake\"}");
  }

}