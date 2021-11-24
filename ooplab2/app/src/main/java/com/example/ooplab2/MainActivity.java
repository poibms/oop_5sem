package com.example.ooplab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
     public TextView check;
     public RadioButton male, female;
     public EditText editWeight, editHeight, editAge;
     public int item;
     public double ratio;

    String[] activity = {"Passive lifestyle","Moderate activity(1-3 times a week )","Moderate activity(3-5 times a week)","Athletes (6-7 times a week)"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        check = findViewById(R.id.output);
        male = findViewById(R.id.maleRadio);
        female = findViewById(R.id.femaleRadio);
        editWeight =  findViewById(R.id.editWeight);
        editHeight =  findViewById(R.id.editHeight);
        editAge =  findViewById(R.id.editAge);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, activity);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                item = (int) parent.getSelectedItemId();

                switch (item) {
                    case 0 : ratio = 1.2;
                    break;
                    case 1 : ratio = 1.375;
                    break;
                    case 2 : ratio = 1.55;
                    break;
                    case 3 : ratio = 1.9;
                    break;
                }
//                if(item == 0) {
//                   ratio = 1.2;
//                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);

    }



    public void Male() {
        int age = Integer.parseInt(editAge.getText().toString());
        int weight = Integer.parseInt(editWeight.getText().toString());
        int height = Integer.parseInt(editWeight.getText().toString());
        double result = Math.ceil((655.0955 + (13.7516 * weight) + (5.0033 * height) + (6.7550 * age) * ratio));

        check.setText("" + result);
    }

    public void Female() {
        int age = Integer.parseInt(editAge.getText().toString());
        int weight = Integer.parseInt(editWeight.getText().toString());
        int height = Integer.parseInt(editWeight.getText().toString());
        double result = Math.ceil((655.0955 + (9.5634 * weight) + (1.8496 * height) + (4.6756 * age) * ratio));

        check.setText("" + result);

    }


    public void Calculate(View view) {



        if(male.isChecked()) {
            Male();
        } else if(female.isChecked()) {
            Female();
        } else {
            check.setText("Chose ur sex pls");
        }

        //check.setText(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        check.setText(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void lab3(View view) {
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}