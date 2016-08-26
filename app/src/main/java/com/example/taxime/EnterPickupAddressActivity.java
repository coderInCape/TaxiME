package com.example.taxime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class EnterPickupAddressActivity extends AppCompatActivity {

    private EditText addressField;
    private EditText suburbField;
    private EditText postcodeField;
    private Spinner stateSpinner;

    private static final String TAG = "EnterPickupAddressActivity.java Logs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pickup_address);

        findViewsById();

        Button nextBtn = (Button) findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User.address = addressField.getText().toString();
                User.suburb = suburbField.getText().toString();
                User.postcode = postcodeField.getText().toString();
                User.state = stateSpinner.getSelectedItem().toString();

                Log.i(TAG, User.address);
                Log.i(TAG, User.suburb);
                Log.i(TAG, User.postcode);
                Log.i(TAG, User.state);

                startActivity(new Intent(getBaseContext(), EnterOtherInfoActivity.class));
            }
        });
    }

    private void findViewsById() {
        addressField = (EditText) findViewById(R.id.addressField);
        suburbField = (EditText) findViewById(R.id.suburbField);
        postcodeField = (EditText) findViewById(R.id.postcodeField);
        stateSpinner = (Spinner) findViewById(R.id.stateSpinner);
    }
}
