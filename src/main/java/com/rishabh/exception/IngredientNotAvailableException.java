package com.rishabh.exception;

/**
 * This is the custom exception and is thrown when ingredients requested are not available with coffee machine.
 */
public class IngredientNotAvailableException extends Exception{

    public IngredientNotAvailableException(String message)
    {
        super(message);
    }
}
