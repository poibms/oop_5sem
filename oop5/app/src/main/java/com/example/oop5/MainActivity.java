package com.example.oop5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String fname = "data.json";
    ListView listView;
    private List<Recipes> recipes;
    Recipes recip;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("10 причин родиться в украине");


        listView = findViewById(R.id.listView);
        registerForContextMenu(listView);

        ExistBase(fname);

        try{
            recipes = JSONHelper.importFromJSON(this);

            // используем адаптер данных
            ArrayAdapter<Recipes> adapter1 = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, recipes);

            listView.setAdapter(adapter1);
            //TextView outAll = (TextView) findViewById(R.id.outAll);
            //outAll.setText(apps.toString());
        }catch(Exception e){
            Toast.makeText(this, "Файл отсутствует", Toast.LENGTH_LONG).show();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString(); // получаем текст нажатого элемента

                recip = recipes.get(position);
                OpenItem();
            }
        });
    }

    private boolean ExistBase(String fname) {
        boolean rc = false;
        File file = new File(super.getFilesDir(), fname);
        if(rc = file.exists()) {
            Toast.makeText(this, "File already created", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "File not founded", Toast.LENGTH_SHORT).show();
            try {
                file.createNewFile();
                Toast.makeText(this, "File was created ", Toast.LENGTH_SHORT).show();
            } catch(IOException ex) {

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return rc;
    }
    public void AddItem(MenuItem item) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.edit:
                EditItem(info.position);
                return true;
            case R.id.delete:
                DeleteItem(info.position); //метод, выполняющий действие при удалении пункта меню
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void OpenItem() {
        Intent intent1 = new Intent(this, MainActivity3.class);
        intent1.putExtra("itemData", recip);
        startActivity(intent1);
    }


    public void DeleteItem(int position) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Are you sure you want to delete this item?");
        alertDialog.setMessage("It will be impossible to restore");

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                Delete(position);
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getApplicationContext(), "Pussy", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();
    }

    public void Delete(int item) {
        try {
            List<Recipes> Applications = JSONHelper.importFromJSON(this);
            Applications.remove(item);
            JSONHelper.exportToJSON(this, Applications);
        } catch (Exception e) {
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
        }
        try {
            recipes = JSONHelper.importFromJSON(this);

            ArrayAdapter<Recipes> adapter = new ArrayAdapter<Recipes>(this,
                    android.R.layout.simple_list_item_1, recipes);
            listView.setAdapter(adapter);
        } catch (Exception e) {
        }
    }
    private void EditItem(int position) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("changeItem", position);
        startActivity(intent);
        try {
            recipes = JSONHelper.importFromJSON(this);

            ArrayAdapter<Recipes> adapter = new ArrayAdapter<Recipes>(this,
                    android.R.layout.simple_list_item_1, recipes);
            listView.setAdapter(adapter);
        } catch (Exception e) {
        }
    }


    public void Search(MenuItem item) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void Sort(MenuItem item) {

        list = new ArrayList<String>(Arrays.<String>asList(String.valueOf(recipes)));
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}