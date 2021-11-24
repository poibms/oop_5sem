package com.example.ooplab2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {
    private static final int GALLERY_REQUEST = 1;
    public EditText carName, carModel, mail, number;
    public  String item;
    public Spinner spinner;
    ImageView img;
    String[] bodyType = {"Coupe","Sedan","Jeep","Cabriolet", "Hatchback"};
    Button chooseImage;
    Car car;
    Bitmap bitmap;
//    private List<Car> cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        carName = findViewById(R.id.nameInputEditText);
        carModel = findViewById(R.id.modelInputEditText);
        spinner = findViewById(R.id.spinner);
        chooseImage = findViewById(R.id.choseImage);
        number = findViewById(R.id.numberEditText);
        mail = findViewById(R.id.mailEditText);
        img = findViewById(R.id.imageView);
        car = new Car();


        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bodyType);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = (String)parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);



    }


    public void ChooseImage(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);




        switch (requestCode) {
            case GALLERY_REQUEST:
                if (resultCode == RESULT_OK) {
                   Uri selectedImage = imageReturnedIntent.getData();
                    try {
                      bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    img.setImageBitmap(bitmap);
                }
        }
//        Toast.makeText(this, bitmap.toString(), Toast.LENGTH_LONG).show();
    }


    public void Click(View view) {

       getInfo();

        Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
        intent.putExtra("frst",car);
        //        Bundle bundle = new Bundle();
//        bundle.putString("name", name);
//        bundle.putString("company", company);
//        bundle.putString("spinner", sp);
//        intent.putExtra("car", bundle);


        startActivity(intent);
    }

    public void getInfo() {
        car.setName(carName.getText().toString());
        car.setModel(carModel.getText().toString());
        car.setBodyType(item.toString());
        car.setMail(mail.getText().toString());
        car.setNumber(number.getText().toString());
    }




}