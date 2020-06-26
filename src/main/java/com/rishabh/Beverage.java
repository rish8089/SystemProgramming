package com.rishabh;

import java.util.HashMap;
import java.util.Map;

public class Beverage {
    private String name;
    //stores the ingredient name as key and quantity required as value
    private Map<String,Integer> ingredients;

    public Beverage()
    {
        ingredients=new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }



}
