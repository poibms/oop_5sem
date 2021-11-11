package com.example.oop5;

import java.io.Serializable;

public class Recipes implements Serializable {
    String name, category, ingredients, recipe, date;

    public Recipes(String name, String category, String ingredients, String recipe, String date) {
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
        this.recipe = recipe;
        this.date = date;
    }

    public Recipes() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIngredients() { return ingredients; }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String toString() {
        return "name:" + " " + name + " " + "category: " + category;
    }
}
