package com.example.advquerying;

import com.example.advquerying.service.IngredientService;
import com.example.advquerying.service.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final ShampooService shampooService;

    private final IngredientService ingredientService;

    public CommandLineRunnerImpl(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        ex1
//        this.shampooService.getAllShampoosByGivenSize(reader.readLine())
//                .forEach(System.out::println);
//        ex2
//        this.shampooService.getAllShampoosByGivenSizeOrLabel("medium", 10)
//                .forEach(System.out::println);
//        ex3
//        this.shampooService.getAllShampoosWithPriceHigherThan(BigDecimal.valueOf(5))
//                .forEach(System.out::println);
//        ex4
//        this.ingredientService.getAllIngredientWithStartingName("M")
//                .forEach(System.out::println);
//        ex5
//        this.ingredientService.getAllIngredientsContaining("Lavender", "Herbs", "Apple")
//                .forEach(System.out::println);
//        ex6
//        System.out.println(this.shampooService.countOfShampoosWithPriceLessThan(BigDecimal.valueOf(8.50)));
//        ex7
//        this.shampooService.getAllShampoosContainingIngredient(List.of(reader.readLine().split(" ")))
//                .forEach(System.out::println);
//        ex8
//        System.out.println(this.shampooService.getAllShampoosWithCountOfIngredientsBelowNumber());
//        ex9
//        System.out.println(this.ingredientService.deleteIngredientByName("Nettle"));
//        ex10
//        this.ingredientService.updatedIngredientPrices();
//        ex11
        System.out.println(this.ingredientService.updatedPricesForGivenNames());
    }

}
