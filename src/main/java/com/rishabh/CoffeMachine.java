package com.rishabh;

import com.rishabh.exception.IngredientNotAvailableException;

import java.util.HashMap;
import java.util.Map;

public class CoffeMachine {
    private int outlets;
    //stores the ingredient name as key and quantity available as value
    private Map<String, Integer> totalIngredients;

    public CoffeMachine() {
        totalIngredients = new HashMap<>();
    }

    public int getOutlets() {
        return outlets;
    }

    public void setOutlets(int outlets) {
        this.outlets = outlets;
    }

    public Map<String, Integer> getTotalIngredients() {
        return totalIngredients;
    }



    /**
     * This function first checks whether the required beverage ingredients are sufficiently available with coffee machine.
     * It throws custom exception if any of the ingredient is not sufficiently available, otherwise it supplies the required quantity.
     *
     * It is declared synchronized so that other outlet threads wouldn't be able to interfere in allocation process.
     *
     * @param beverage
     * @return
     */
    public synchronized void allocateIngredients(Beverage beverage) throws IngredientNotAvailableException {
        Map<String, Integer> beverageIngredients = beverage.getIngredients();
        for (Map.Entry<String, Integer> beverageIngredient : beverageIngredients.entrySet()) {
            String ingredient = beverageIngredient.getKey();//gets the ingredient name
            Integer quantityRequired = beverageIngredient.getValue();//gets the required ingredient value
            if (totalIngredients.containsKey(ingredient)) {
                Integer quantityAvailable = totalIngredients.get(ingredient);
                if (quantityAvailable < quantityRequired)
                    throw new IngredientNotAvailableException(beverage.getName() + " cannot be prepared because " + ingredient + " is not available");
            } else if (quantityRequired > 0)
                throw new IngredientNotAvailableException(beverage.getName() + " cannot be prepared because " + ingredient + " is not available");

        }

        for (Map.Entry<String, Integer> beverageIngredient : beverageIngredients.entrySet()) {
            String ingredient = beverageIngredient.getKey();
            Integer quantityRequired = beverageIngredient.getValue();
            if (quantityRequired > 0) {
                Integer quantityAvailable = totalIngredients.get(ingredient);
                totalIngredients.put(ingredient, quantityAvailable - quantityRequired);
            }
        }

    }

    /**
     * It refills the ingredient in the coffee machine.
     *
     * It is declared synchronized to avoid stale updates.
     * @param ingredient
     * @param quantity
     */
    public synchronized void refill(String ingredient,Integer quantity)
    {
        if(totalIngredients.containsKey(ingredient))
            totalIngredients.put(ingredient,quantity+totalIngredients.get(ingredient));
        else
            totalIngredients.put(ingredient,quantity);
    }


}
