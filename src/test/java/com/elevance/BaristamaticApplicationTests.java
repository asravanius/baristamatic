package com.elevance;

import com.elevance.enums.Drink;
import com.elevance.enums.Ingredient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.elevance.enums.Drink.*;
import static com.elevance.enums.Ingredient.*;

@SpringBootTest
class BaristamaticApplicationTests {

	private Drink coffee;
	@BeforeEach
	public void setUp(){
		coffee = Drink.Coffee;
		Ingredient.restock();
	}

	@Test
	void test_stock_dispense_coffee() {
		coffee.dispense();
		Assertions.assertEquals(7, COFFEE.getCurrentQuantity(), "Coffee");
		Assertions.assertEquals(9, SUGAR.getCurrentQuantity());
		Assertions.assertEquals(9, CREAM.getCurrentQuantity());
	}

	@Test
	void test_drinks_cost() {
		Assertions.assertEquals("$2.75", Coffee.getCost());
		Assertions.assertEquals("$2.55", CaffeLatte.getCost());
		Assertions.assertEquals("$2.75", DecafCoffee.getCost());
		Assertions.assertEquals("$3.30", CaffeAmericano.getCost());
		Assertions.assertEquals("$3.35", CaffeMocha.getCost());
		Assertions.assertEquals("$2.90", Cappuccino.getCost());
	}

	@Test
	void test_coffee_available() {
		coffee.dispense();
		Assertions.assertEquals(7, COFFEE.getCurrentQuantity());
		Assertions.assertTrue(coffee.isAvailable());
		coffee.dispense();
		Assertions.assertEquals(4, COFFEE.getCurrentQuantity());
		Assertions.assertTrue(coffee.isAvailable());
		coffee.dispense();
		Assertions.assertEquals(1, COFFEE.getCurrentQuantity());
		Assertions.assertFalse(coffee.isAvailable());
	}

	@Test
	void test_restock(){

		coffee.dispense();
		Assertions.assertEquals(7, COFFEE.getCurrentQuantity());
		Assertions.assertEquals(9, SUGAR.getCurrentQuantity());
		Assertions.assertEquals(9, CREAM.getCurrentQuantity());

		Ingredient.restock();
		Assertions.assertEquals(10, COFFEE.getCurrentQuantity());
		Assertions.assertEquals(10, SUGAR.getCurrentQuantity());
		Assertions.assertEquals(10, CREAM.getCurrentQuantity());
	}


}
