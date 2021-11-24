package com.example.ooplab2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {


    private static final int GALLERY_REQUEST = 1;
    private ImageButton btnArrowBack;
    Bitmap bitmap;
    Car car;
    private ArrayAdapter<Car> adapter;
    private List<Car> cars;

        TextView name, model, bodyType, trans, engine, date, mileage, number, mail;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Bundle arguments = getIntent().getExtras();
        car = (Car) arguments.get("thirdAct");

        name = findViewById(R.id.setName);
        model = findViewById(R.id.setModel);
        bodyType = findViewById(R.id.setBodyType);
        trans = findViewById(R.id.setTrans);
        engine = findViewById(R.id.setEng);
        date = findViewById(R.id.setDate);
        mileage = findViewById(R.id.setMilg);
        number = findViewById(R.id.setNumber);
        mail = findViewById(R.id.setMail);
        img = findViewById(R.id.imageView);

        btnArrowBack = findViewById(R.id.btnArrowBack);


        //set Data
        name.setText(car.getName());
        model.setText(car.getModel());
        bodyType.setText(car.getBodyType());
        trans.setText(car.getTransmission());
        engine.setText(car.getEngineType());
        date.setText(car.getRealeseDate() + "");
        mileage.setText(car.getMileage() + "");
        number.setText(car.getNumber());
        mail.setText(car.getMail());



        cars = new ArrayList<Car>();

        btnArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }


    public void call(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void mail(View view) {
        String mailstr = mail.getText().toString();
        Intent intent2 = new Intent(Intent.ACTION_SEND);
        intent2.setData(Uri.parse("mailto:"));
        intent2.setType("plain/text");
        intent2.putExtra(Intent.EXTRA_EMAIL, new String[]{mailstr});
        startActivity(intent2);
    }


    public void ChoosePhoto(View view) {
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


}