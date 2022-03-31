package com.sia.tacos.web;

import com.sia.tacos.Ingredient;

import com.sia.tacos.data.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepo;

    //Конструктор
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    //Конвертация из ID в объект Ingredient путём нахождения объекта в БД
    @Override
    public Ingredient convert(String id) {
        return ingredientRepo.findById(id).orElse(null);
    }
}
