package com.elevance.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.elevance.enums.Drink;
import com.elevance.enums.Ingredient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@GetMapping("/")
	public String greeting() {
		return "Welcome To Barista-matic";
	}

	@GetMapping("/inventory")
	public Ingredient[] ingredients() {
		return Ingredient.values();
	}

	@GetMapping("/restock")
	public String restock() {
		Ingredient.restock();
		 return "Restock Complete";
	}

	@GetMapping("/dispense/{drinkId}")
	public String dispense(@PathVariable("drinkId") int drinkId) {
		Drink drink = Drink.dispense(drinkId);
		return "Dispensing " + drink.name + "...";
	}

	@GetMapping("/menu")
	public Drink[] menu() {
		return Drink.values();
	}

}