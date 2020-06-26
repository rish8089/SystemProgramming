package com.rishabh;

import java.util.HashMap;
import java.util.Map;

public class Beverage {
    private String name;
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
