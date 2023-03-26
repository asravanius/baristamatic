package com.elevance.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Ingredient {
    COFFEE("Coffee", 0.75),
    DECAF_COFFEE("Decaf Coffee", 0.75),
    SUGAR("Sugar", 0.25),
    CREAM("Cream", 0.25),
    STEAMED_MILK("Steamed Milk", 0.35),
    FOAMED_MILK("Foamed Milk", 0.35),
    ESPRESSO("Espresso", 1.10),
    COCOA("Cocoa", 0.90),
    WHIPPED_CREAM("Whipped Cream", 1.00);

    public final String name;
    private final Double unitCost;


    private static final Map<Ingredient, Integer> inventory = new HashMap<>();

    static {
        restock();
    }

    public static void restock() {
        for (Ingredient value : values()) {
            inventory.put(value, 10);
        }
    }

    Ingredient(String name, Double unitCost){
        this.name =  name;
        this.unitCost = unitCost;
    }

    public void updateInventory(int units){
        var newInventory = inventory.get(this) - units;
        inventory.put(this, newInventory);
    }

    @JsonIgnore
    public Double getUnitCost() {
        return unitCost;
    }

    public Integer getCurrentQuantity(){
        return inventory.get(this);
    }




}
