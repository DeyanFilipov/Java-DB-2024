package com.example.advquerying.service.impl;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import com.example.advquerying.service.IngredientService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<String> getAllIngredientWithStartingName(String symbol) {
        return this.ingredientRepository.findAllByNameStartsWith(symbol)
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllIngredientsContaining(String... ingredientNames) {
        return ingredientRepository.findAllByNameInOrderByPriceAsc(List.of(ingredientNames))
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
    }

    @Override
    public int deleteIngredientByName(String name) {
        return this.ingredientRepository.deleteIngredientByName(name);
    }

    @Override
    public int updatedIngredientPrices() {
        return this.ingredientRepository.updateAllByPrice(BigDecimal.valueOf(1.10));
    }

    @Override
    public int updatedPricesForGivenNames() {
        return this.ingredientRepository.
                updateAllByPriceForGivenNames(
                        BigDecimal.valueOf(1.10),
                        List.of("Apple", "Macadamia Oil"));
    }
}
