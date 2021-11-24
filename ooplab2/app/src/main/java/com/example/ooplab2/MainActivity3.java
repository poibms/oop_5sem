package com.example.ooplab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {


    private EditText date, millage;
    private String name, model, bodytype, path;
    private RadioButton transmission, gas, diesel, elec;
    private ImageButton btnback;
    private ListView listView;

    private List<Car> cars;
    Car car;
    Car car1;
//    Car car = new Car();
    final static String userVariableKey = "USER_VARIABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        date = findViewById(R.id.textInputEditText);
        transmission = findViewById(R.id.manualTrans);
        gas = findViewById(R.id.gasEngine);
        diesel = findViewById(R.id.dieselEngine);
        elec = findViewById(R.id.elecEngine);
        millage = findViewById(R.id.textInputMillage);
        btnback = findViewById(R.id.btnArrowBack);
        listView = findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString(); // получаем текст нажатого элемента

                car1 = cars.get(position);
                OpenItem();
            }


        });

        Bundle arguments = getIntent().getExtras();
        car = (Car) arguments.get("frst");

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//
//        outState.putSerializable(userVariableKey, cars.toString());
//        super.onSaveInstanceState(outState);
//    }
//    // получение ранее сохраненного состояния
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        // получаем объект User в переменную
//        cars = (List<Car>) savedInstanceState.getSerializable(userVariableKey);
//
////        date.setText(car.getRealeseDate() +"");
////        millage.setText(car.getMileage() + "");
//    }


    public void OpenItem() {
        Intent intent1 = new Intent(this, MainActivity4.class);
        intent1.putExtra("thirdAct", car1);
        startActivity(intent1);
    }

    public void Click(View view) {
        getInfo();

        if(cars == null)
            cars = new ArrayList<Car>();
        Car car1 = new Car(car.getName(), car.getModel(),car.getBodyType(),car.getTransmission(),car.getEngineType(),car.getRealeseDate(),
                car.getMileage(), car.getMail(), car.getNumber()    );

        cars.add(car1);
        boolean result = JSONHelper.exportToJSON(this, cars);
        if(result){
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
        }

        cars = JSONHelper.importFromJSON(this);

        ArrayAdapter<Car> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, cars);

        listView.setAdapter(adapter);

    }

    private void getInfo() {
        String trans, egn;
        if (transmission.isChecked()) {
            trans = "manual";
        } else {
            trans = "auto";
        }

        if (gas.isChecked()) {
            egn = "gass";
        } else if (diesel.isChecked()) {
            egn = "diesel";
        } else {
            egn = "electric";
        }

        int releaseDate = Integer.parseInt(date.getText().toString());
        int mlg = Integer.parseInt((millage.getText().toString()));

        car.setTransmission(trans);
        car.setEngineType(egn);
        car.setRealeseDate(releaseDate);
        car.setMileage(mlg);

    }
}

