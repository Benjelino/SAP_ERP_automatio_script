package com.selenium_for_sap;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.selenium_for_sap.pojo.SimpleTestCaseJsonPojo;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class JsonTest {

  private String simpleTestCaseJsonSource = "{\"title\": \"Coder from scratch\", \"name\": \"Benjamin Kwame Amponsah\"}";

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
    System.out.println(Json.stringify(node));
    System.out.println(Json.prettyPrint(node));
  }

}