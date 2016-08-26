package com.example.taxime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReviewBookingActivity extends AppCompatActivity {
    private TextView vehicleTypeText;
    private TextView pickupAddressText;
    private TextView otherInfoText;

    private Button changeTaxiTypeBtn;
    private Button editAddressBtn;
    private Button editOtherInfoBtn;
    private Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_booking);

        findViewsById();

        finalizeOnCreateView();

        addListeners();

    }

    private void addListeners() {
        changeTaxiTypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start SelectVehicleActivity.java
                startActivity(new Intent(getBaseContext(), SelectVehicleActivity.class));
            }
        });

        editAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start EnterPickupAddressActivity.java
                startActivity(new Intent(getBaseContext(), EnterPickupAddressActivity.class));
            }
        });

        editOtherInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start EnterOtherInfoActivity.java
                startActivity(new Intent(getBaseContext(), EnterOtherInfoActivity.class));
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start TaxiMe service Provider
            }
        });
    }

    private void finalizeOnCreateView() {
        vehicleTypeText.setText(User.vehicleType);
        pickupAddressText.setText(User.address + "\n" + User.suburb + "\n" + User.postcode + "\t" + User.state);
        otherInfoText.setText(User.date + "\t" + User.time + "\n" + User.contactName + "\n" + User.contactNumber + "\n" + User.notesToDriver);
    }

    private void findViewsById() {
        vehicleTypeText = (TextView) findViewById(R.id.vehicleTypeText);
        pickupAddressText = (TextView) findViewById(R.id.pickupAddressText);
        otherInfoText = (TextView) findViewById(R.id.otherInfoText);
        changeTaxiTypeBtn = (Button) findViewById(R.id.changeTypeBtn);
        editAddressBtn = (Button) findViewById(R.id.editAddressBtn);
        editOtherInfoBtn = (Button) findViewById(R.id.editOtherInfoBtn);
        nextBtn = (Button) findViewById(R.id.nextBtn);
    }
}
