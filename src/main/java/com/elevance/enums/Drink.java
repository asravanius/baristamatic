package com.elevance.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;

import static com.elevance.enums.Ingredient.*;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Drink {
    Coffee(1, "Coffee", new Recipe[]{new Recipe(COFFEE, 3),
                                                new Recipe(SUGAR),
                                                new Recipe(CREAM)}),
    DecafCoffee(2, "Decaf Coffee", new Recipe[]{new Recipe(DECAF_COFFEE, 3),
                                                         new Recipe(SUGAR),
                                                         new Recipe(CREAM)}),
    CaffeLatte(3, "Caffe Latte",  new Recipe[]{new Recipe(ESPRESSO, 2),
                                                        new Recipe(STEAMED_MILK)}),
    CaffeAmericano(4, "Caffe Americano", new Recipe[]{new Recipe(ESPRESSO, 3)}),
    CaffeMocha(5, "Caffe Mocha", new Recipe[]{new Recipe(ESPRESSO),
                                                        new Recipe(COCOA),
                                                        new Recipe(STEAMED_MILK),
                                                        new Recipe(WHIPPED_CREAM)}),
    Cappuccino(6, "Cappuccino", new Recipe[]{new Recipe(ESPRESSO, 2),
                                                    new Recipe(STEAMED_MILK),
                                                    new Recipe(FOAMED_MILK)});

    public final int id;
    public final String name;
    private final Recipe[] recipes;
    Drink(int id, String name, Recipe[] recipes){
        this.id = id;
        this.name = name;
        this.recipes = recipes;
    }


    public String getCost(){
        double cost = Arrays.stream(recipes).mapToDouble(Recipe::getCost).sum();
        return String.format("$%.2f", cost);
    }

    public void dispense(){
        if(!this.isAvailable())
            throw new RuntimeException(this.name + " is not available at the moment, since one or more ingredients are missing");
          Arrays.stream(recipes).forEach(Recipe::updateInventory);
    }


    public boolean isAvailable(){
        return Arrays.stream(recipes).allMatch(Recipe::isAvailable);
    }

    public static Drink dispense(int id){
        for(Drink drink: Drink.values()){
            if(drink.id == id) {
                drink.dispense();
                return drink;
            }
        }
        throw new RuntimeException("No Drink found for id: "+ id);
    }

    private static class Recipe {
        private final Ingredient ingredient;
        private final Integer units;
        Recipe(Ingredient ingredient, Integer units){
            this.ingredient = ingredient;
            this.units      = units;
        }
        Recipe(Ingredient ingredient){
            this.ingredient = ingredient;
            this.units      = 1;
        }

        Double getCost(){
            return ingredient.getUnitCost() * units;
        }

        void updateInventory(){
            ingredient.updateInventory(units);
        }
        boolean isAvailable(){
            return ingredient.getCurrentQuantity() >= units;
        }
    }


}
