package com.rishabh.jobs;

import com.rishabh.Beverage;
import com.rishabh.CoffeMachine;
import com.rishabh.exception.IngredientNotAvailableException;


public class PrepareBeverage implements Runnable {

    private CoffeMachine coffeMachine;
    private Beverage beverage;
    public PrepareBeverage(CoffeMachine coffeMachine, Beverage beverage) {
        this.coffeMachine=coffeMachine;
        this.beverage=beverage;
    }

    /**
     * This represents the current thread job procedure. It calls allocateIngredients method on coffee machine instance
     * to get the required ingredients.
     *
     * Prints the message that the "**beverage-name** is prepared" if coffee machine successfully allocates the ingredients,
     * otherwise prints the ingredient which is not available in custom exception message.
     *
     * This method can be extended tomorrow to include other details of preparing a beverage.
     * Right now it is just printing one message.
     */
    @Override
    public void run() {
        try {
            coffeMachine.allocateIngredients(beverage);
            System.out.println(beverage.getName()+" is prepared");
        } catch (IngredientNotAvailableException e) {
            System.out.println(e.getMessage());
        }


    }
}
