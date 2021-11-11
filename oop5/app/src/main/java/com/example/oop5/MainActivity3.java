package com.example.oop5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    TextView name, category, ingrid, recip, time;
    private List<Recipes> recipes;
    Recipes recipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //get
        Bundle arguments = getIntent().getExtras();
        recipe = (Recipes) arguments.get("itemData");

        name = findViewById(R.id.setName);
        category = findViewById(R.id.setCategory);
        ingrid = findViewById(R.id.setIngridients);
        recip = findViewById(R.id.setRecipe);
        time = findViewById(R.id.setTime);

        //set

        name.setText(recipe.getName());
        category.setText(recipe.getCategory());
        ingrid.setText(recipe.getIngredients());
        recip.setText(recipe.getRecipe());
        time.setText(recipe.getDate());

    }
}