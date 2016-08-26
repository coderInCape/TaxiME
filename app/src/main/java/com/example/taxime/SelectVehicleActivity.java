package com.example.taxime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SelectVehicleActivity extends AppCompatActivity {

    private RadioGroup vehicleTypeGroup;
    private RadioButton checkedVehicleTypeRadioButton;
    private Button nextBtn;

    private static final String TAG = "My Logs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_vehicle);

        findViewsById();




        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkedVehicleTypeRadioButton = (RadioButton) findViewById(vehicleTypeGroup.getCheckedRadioButtonId());
                User.vehicleType = checkedVehicleTypeRadioButton.getText().toString();

                Log.i(TAG, User.vehicleType);
                startActivity(new Intent(getBaseContext(), PickupAddressActivity.class));
            }
        });
    }

    private void findViewsById() {
        nextBtn = (Button) findViewById(R.id.next);
        vehicleTypeGroup = (RadioGroup) findViewById(R.id.vehicleTypeGroup);
    }
}
