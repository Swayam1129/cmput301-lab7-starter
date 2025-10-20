package com.example.androiduitesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    // Declare the variables so that you will be able to reference it later.
    ListView cityList;
    EditText newName;
    LinearLayout nameField;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.field_nameEntry);
        newName  = findViewById(R.id.editText_name);

        cityList = findViewById(R.id.city_list);

//        String []cities ={"Edmonton", "Vancouver", "Moscow", "Sydney","New Delhi","Mumbai"};

        dataList = new ArrayList<>();

//        dataList.addAll(Arrays.asList(cities));




        cityList.setDividerHeight(2);
        cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,dataList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = view.findViewById(android.R.id.text1);
                text.setTextColor(Color.WHITE);
                text.setTextSize(20);
                int padding = (int) (24 * getResources().getDisplayMetrics().density);
                text.setPadding(padding, padding, padding, padding);
                view.setBackgroundColor(Color.parseColor("#223C65"));
                return view;
            }
        };

        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCity = dataList.get(position);
            Intent intent = new Intent(MainActivity.this, ShowActivity.class);
            intent.putExtra("CITY_NAME", selectedCity);
            startActivity(intent);
        });

        final Button addButton = findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nameField.setVisibility(View.VISIBLE);
            }
        });

        final Button confirmButton = findViewById(R.id.button_confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String cityName = newName.getText().toString();
                cityAdapter.add(cityName);
                newName.getText().clear();
                nameField.setVisibility(View.INVISIBLE);
            }
        });

        final Button deleteButton = findViewById(R.id.button_clear);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cityAdapter.clear();
            }
        });
    }
}