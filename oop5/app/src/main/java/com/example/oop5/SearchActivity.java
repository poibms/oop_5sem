package com.example.oop5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    EditText nameEdit;
    ListView listView;
    TextView txtview;
    private List<Recipes> recipesList;
    Recipes recip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        nameEdit = findViewById(R.id.nameInputEditText);
        listView = findViewById(R.id.listView);
        txtview = findViewById(R.id.textView3);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString(); // получаем текст нажатого элемента

                recip = recipesList.get(position);
                OpenItem();
            }
        });
    }
    public void OpenItem() {
        Intent intent1 = new Intent(this, MainActivity3.class);
        intent1.putExtra("itemData", recip);
        startActivity(intent1);
    }

    public List<Recipes> openFromInt() {
        List<Recipes> personList = new ArrayList<Recipes>();
        try {
            personList = JSONHelper.importFromJSON(this);

            Toast.makeText(this, "opened", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Файл отсутствует", Toast.LENGTH_LONG).show();
        }
        return personList;
    }

    public void SearchBtn(View view) {
        String name = nameEdit.getText().toString();
        recipesList = new ArrayList<Recipes>();
        List<Recipes> rcp = openFromInt();
        String output = "";
        for (Recipes rcp1 : rcp) {
            if (name.equals(rcp1.getName())){
                recipesList.add(rcp1);
                ArrayAdapter<Recipes> adapter = new ArrayAdapter<Recipes>(this,
                        android.R.layout.simple_list_item_1, recipesList);
                listView.setAdapter(adapter);
            }
        }

    }


}