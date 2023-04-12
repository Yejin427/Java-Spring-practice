package com.example.practice2.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ChefTest {
    @Autowired
    IngredientFactory ingredientFactory;
    @Autowired
    Chef chef;
    @Test
    void cook_donggas(){
//        IngredientFactory ingredientFactory = new IngredientFactory();
//        Chef chef = new Chef(ingredientFactory);
        String menu = "돈가스";
        String food = chef.cook(menu);
        String expected = "한돈 등심으로 만든 돈가스";
        assertEquals(expected, food);
        System.out.println(food);
    }
    @Test
    void cook_steak(){
//        IngredientFactory ingredientFactory = new IngredientFactory();
//        Chef chef = new Chef(ingredientFactory);
        String menu = "스테이크";
        String food = chef.cook(menu);
        String expected = "한우 꽃등심으로 만든 스테이크";
        assertEquals(expected, food);
        System.out.println(food);
    }
    @Test
    void cook_crispychicken(){
//        IngredientFactory ingredientFactory = new IngredientFactory();
//        Chef chef = new Chef(ingredientFactory);
        String menu = "크리스피 치킨";
        String food = chef.cook(menu);
        String expected = "국내산 10호 닭으로 만든 크리스피 치킨";
        assertEquals(expected, food);
        System.out.println(food);
    }
}