package com.example.practice2.ioc;

import org.springframework.stereotype.Component;

@Component
public class Chef {

    private IngredientFactory ingredientFactory;
    //셰프가 식재료 공장과 협업 DI
    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public String cook(String menu){
        Ingredient ingredient = ingredientFactory.get(menu);
        return ingredient.getName() + "으로 만든 " + menu;
    }
}
