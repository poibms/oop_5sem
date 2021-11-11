package com.example.oop5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    String[] list = { "Soup", "Main", "Dessert", "Beverages"};
    Spinner spinner;
    EditText name, ingridients, recip, time;

    private List<Recipes> recipes;
    Recipes recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        try {
            recipes = JSONHelper.importFromJSON(this);
        }
        catch (Exception e){
        }

        //get
        spinner = findViewById(R.id.category);
        name = findViewById(R.id.nameInputEditText);
        ingridients = findViewById(R.id.ingrodientEditText);
        recip = findViewById(R.id.recipeEditText);
        time = findViewById(R.id.timeEditText);

        recipe = new Recipes();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void saveBtn(View view) {
        getInfo();
//        String setname, setcategory, setingred, setrecip, setdate;
//         setname= name.getText().toString();
//         setcategory = spinner.getSelectedItem().toString();
//         setingred = ingridients.getText().toString();
//         setrecip = recip.getText().toString();
//         setdate = time.getText().toString();

        if (recipes == null)
            recipes = new ArrayList<Recipes>();
//        Recipes recipe1 = new Recipes(setname, setcategory, setingred, setrecip, setdate);
        Recipes recipe1 = new Recipes(recipe.getName(),recipe.getCategory(),recipe.getIngredients(),recipe.getRecipe(),recipe.getDate());
        recipes.add(recipe1);
        boolean result = JSONHelper.exportToJSON(this, recipes);
        if (result) {
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
        }
    }

    public void getInfo () {
        recipe.setName(name.getText().toString());
        recipe.setCategory(spinner.getSelectedItem().toString());
        recipe.setIngredients(ingridients.getText().toString());
        recipe.setRecipe(recip.getText().toString());
        recipe.setDate(time.getText().toString());
    }
}