package com.sia.tacos.data;

import com.sia.tacos.Ingredient;
import org.springframework.data.repository.CrudRepository;

//Интерфейс для совершения запросов по нахождению или апдейту ингредиентов
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
/*
    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);*/
}
