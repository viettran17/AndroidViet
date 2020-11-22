package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.common.collect.Range;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText NameText, TypeText, AddressText, PriceText, DateText, NoteText;
    private Button btnSubmit;

    Spinner Spinner_Service_Rate;
    Spinner Spinner_Food_Rate;
    Spinner Spinner_Clean_Rate;
    String[] Star;

    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        NameText = (EditText) findViewById(R.id.NameText);
        TypeText = (EditText) findViewById(R.id.TypeText);
        AddressText = (EditText) findViewById(R.id.AddressText);
        PriceText = (EditText) findViewById(R.id.PriceText);
        DateText = (EditText) findViewById(R.id.DateText);
        NoteText = (EditText) findViewById(R.id.NoteText);

        Spinner_Service_Rate = findViewById(R.id.spinner_Service_Rate);
        Spinner_Food_Rate = findViewById(R.id.spinner_Food_Rate);
        Spinner_Clean_Rate = findViewById(R.id.spinner_Clean_Rate);
        populatespinner_Service_Rate();
        populatespinner_Food_Rate();
        populatespinner_Cleane_Rate();

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        //Validate
        awesomeValidation.addValidation(this, R.id.NameText, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.TypeText, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.typeerror);
        awesomeValidation.addValidation(this, R.id.AddressText, "^\\d{1,6}\\040([A-Z]{1}[a-z]{1,}\\040[A-Z]{1}[a-z]{1,})$|^\\d{1,6}\\040([A-Z]{1}[a-z]{1,}\\040[A-Z]{1}[a-z]{1,}\\040[A-Z]{1}[a-z]{1,})$|^\\d{1,6}\\040([A-Z]{1}[a-z]{1,}\\040[A-Z]{1}[a-z]{1,}\\040[A-Z]{1}[a-z]{1,}\\040[A-Z]{1}[a-z]{1,})$", R.string.addresserror);
        awesomeValidation.addValidation(this, R.id.PriceText, Range.closed(1, 10000), R.string.priceerror);
        awesomeValidation.addValidation(this, R.id.DateText, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.dateerror);
        awesomeValidation.addValidation(this, R.id.NoteText, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.noteerror);


        btnSubmit.setOnClickListener(this);
    }

    private void submitForm() {
        if (awesomeValidation.validate()) {
            Toast.makeText(this, "Add Restaurant Successfully", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view){
        if (view == btnSubmit) {
            submitForm();
        }
    }

    private void populatespinner_Service_Rate() {
        ArrayAdapter<String> serviceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.spinner_service));
        Spinner_Service_Rate.setAdapter(serviceAdapter);
    }

    private void populatespinner_Food_Rate() {
        ArrayAdapter<String> foodAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_food));
        Spinner_Food_Rate.setAdapter(foodAdapter);
    }

    private void populatespinner_Cleane_Rate() {
        ArrayAdapter<String> cleanAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spinner_clean));
        Spinner_Clean_Rate.setAdapter(cleanAdapter);
    }

}

