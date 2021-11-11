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

public class EditActivity extends AppCompatActivity {

    String[] list = { "Soup", "Main", "Dessert", "Beverages"};
    Spinner spinner;
    EditText name, ingridients, recip, time;

    private List<Recipes> recipes;
    private int position;
    Recipes recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get
        spinner = findViewById(R.id.category);
        name = findViewById(R.id.nameInputEditText);
        ingridients = findViewById(R.id.ingrodientEditText);
        recip = findViewById(R.id.recipeEditText);
        time = findViewById(R.id.timeEditText);

        Bundle arguments = getIntent().getExtras();
        position = (int) arguments.get("changeItem");

        try {
            recipes = JSONHelper.importFromJSON(this);
        }
        catch (Exception e){
        }

//        name.setText(recipe.getName());




        recipe = new Recipes();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public void saveChangesBtn(View view) {
        getInfo();
        if (recipes == null)
            recipes = new ArrayList<Recipes>();
//        Recipes recipe1 = new Recipes(setname, setcategory, setingred, setrecip, setdate);
        Recipes recipe1 = new Recipes(recipe.getName(),recipe.getCategory(),recipe.getIngredients(),recipe.getRecipe(),recipe.getDate());
        recipes.set(position,recipe1);
        boolean result = JSONHelper.exportToJSON(this, recipes);
        if (result) {
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
        }
    }

    public void getInfo() {
        recipe.setName(name.getText().toString());
        recipe.setCategory(spinner.getSelectedItem().toString());
        recipe.setIngredients(ingridients.getText().toString());
        recipe.setRecipe(recip.getText().toString());
        recipe.setDate(time.getText().toString());
    }
}