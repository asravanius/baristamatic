package com.elevance;

import com.elevance.controllers.MainController;
import com.elevance.enums.Drink;
import com.elevance.enums.Ingredient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.NestedServletException;

import java.io.IOException;

import static com.elevance.enums.Ingredient.COFFEE;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {
   @Autowired
   private MainController controller;

   @Autowired
   private MockMvc mockMvc;

   @BeforeEach
   public void setUp(){
      Ingredient.restock();
   }


   private String mapToJson(Object obj) throws JsonProcessingException {
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.writeValueAsString(obj);
   }

   <T> T mapFromJson(String json, Class<T> clazz) throws  IOException {

      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(json, clazz);
   }

   @Test
   public void contextLoads() throws Exception {
      Assertions.assertNotNull(controller);
   }

   @Test
   public void shouldReturnDefaultMessage() throws Exception {
      this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
              .andExpect(content().string(containsString("Welcome To Barista-matic")));
   }

   @Test
   public void shouldReturnInventory() throws Exception {
      MvcResult mvcResult = this.mockMvc.perform(get("/inventory").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();

      Assertions.assertEquals(content, mapToJson(Ingredient.values()));

   }

   @Test
   public void shouldReturnMenu() throws Exception {
      MvcResult mvcResult = this.mockMvc.perform(get("/menu").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();

      Assertions.assertEquals(content, mapToJson(Drink.values()));

   }

   @Test
   public void testCoffeeDispenser() throws Exception {
      this.mockMvc.perform(get("/dispense/1")).andDo(print()).andExpect(status().isOk())
              .andExpect(content().string(containsString("Dispensing Coffee...")));

      Assertions.assertEquals(COFFEE.getCurrentQuantity(), 7);

   }

   @Test
   public void testCoffeeDispenserAndRestock() throws Exception {
      testCoffeeDispenser();

      this.mockMvc.perform(get("/restock")).andDo(print()).andExpect(status().isOk())
              .andExpect(content().string(containsString("Restock Complete")));

      Assertions.assertEquals(COFFEE.getCurrentQuantity(), 10);

   }

   @Test
   public void testOutOfStockException() throws Exception {


      this.mockMvc.perform(get("/dispense/1"));
      this.mockMvc.perform(get("/dispense/1"));
      this.mockMvc.perform(get("/dispense/1"));

      Assertions.assertEquals(COFFEE.getCurrentQuantity(), 1);
      assertThrows(NestedServletException.class,
              () -> this.mockMvc.perform(get("/dispense/1")));

   }

   @Test
   public void testNoDrinkFoundException() {
      assertThrows(NestedServletException.class,
              () -> this.mockMvc.perform(get("/dispense/10")));

   }
}