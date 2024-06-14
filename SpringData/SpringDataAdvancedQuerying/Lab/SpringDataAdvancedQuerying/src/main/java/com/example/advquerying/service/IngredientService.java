package com.example.advquerying.service;


import java.util.List;

public interface IngredientService {

    List<String> getAllIngredientWithStartingName(String symbol);

    List<String> getAllIngredientsContaining(String... ingredientNames);

    int deleteIngredientByName(String name);

    int updatedIngredientPrices();

    int updatedPricesForGivenNames();
}
