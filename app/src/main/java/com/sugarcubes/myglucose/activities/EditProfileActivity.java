package com.sugarcubes.myglucose.activities;

import android.annotation.SuppressLint;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.app.Activity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.app.LoaderManager.LoaderCallbacks;
import android.widget.TextView;

import com.sugarcubes.myglucose.R;
import com.sugarcubes.myglucose.contentproviders.MyGlucoseContentProvider;
import com.sugarcubes.myglucose.db.DB;
import com.sugarcubes.myglucose.enums.UserType;
import com.sugarcubes.myglucose.repositories.DbApplicationUserRepository;
import com.sugarcubes.myglucose.repositories.DbPatientRepository;
import com.sugarcubes.myglucose.singletons.PatientSingleton;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity  {
    /* Spinner spinner = findViewById(R.id.spinner1);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
            this,R.array.states, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);*/

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_edit);



        Button button = findViewById(R.id.submitButton);

        button.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_UP) {

                    DbPatientRepository dbPatientRepository = new DbPatientRepository(getApplicationContext());

                    PatientSingleton patientSingleton = PatientSingleton.getInstance();

                    EditText firstNameInput = findViewById(R.id.firstNameInput);
                    EditText lastNameInput = findViewById(R.id.lastNameInput);
                    EditText phoneNumInput = findViewById(R.id.phoneNumInput);
                    EditText cityInput = findViewById(R.id.cityInput);
                    EditText stateInput = findViewById(R.id.stateInput);
                    EditText addressInput = findViewById(R.id.addressInput);
                    EditText weightInput = findViewById(R.id.weightInput);
                    EditText heightInput = findViewById(R.id.heightInput);

                    patientSingleton.setFirstName(firstNameInput.getText().toString());
                    patientSingleton.setLastName(lastNameInput.getText().toString());
                    patientSingleton.setPhoneNumber(phoneNumInput.getText().toString());
                    patientSingleton.setCity(cityInput.getText().toString());
                    patientSingleton.setState(stateInput.getText().toString());
                    patientSingleton.setAddress1(addressInput.getText().toString());
                    patientSingleton.setWeight(weightInput.getText().toString());
                    patientSingleton.setHeight(heightInput.getText().toString());

                    dbPatientRepository.update( patientSingleton.getUserName(), patientSingleton );
                    finish();
                    return true;
                }
                return false;
            }
        });
    }
}
