package com.spotify.repairit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.spotify.repairit.models.PickupDetails;
import com.spotify.repairit.models.RepairRequest;

/**
 * Created by aecs7 on 28-Feb-17.
 */

public class ScheduleRepairActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button schedule;
    EditText date,pickupAddrss,pickupLandmark,pickupPincode;
    String TAG= ScheduleRepairActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedulerepair);
        toolbar = (Toolbar) findViewById(R.id.deviceToolbar);
        toolbar.setTitle("RepairIt");
        toolbar.setLogo(R.drawable.ic_build_white_24dp);
        toolbar.setTitleMarginStart(50);
        schedule= (Button) findViewById(R.id.schedule);
        schedule.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                RepairRequest repairRequest=getFormData();
                Gson gson = new Gson();
                String repairRequestString = gson.toJson(repairRequest);
                Log.e(TAG, "onClick: "+repairRequestString);
            }
        });
    }

    private RepairRequest getFormData()
    {
        PickupDetails pickup;
        date= (EditText) findViewById(R.id.pickupDate);
        pickupAddrss= (EditText) findViewById(R.id.pickupAddress);
        pickupLandmark= (EditText) findViewById(R.id.landmark);
        pickupPincode= (EditText) findViewById(R.id.pickupPincode);

        if(date.getText().toString()!=""&&pickupAddrss.getText().toString()!=""&&pickupLandmark.getText().toString()!=""&& pickupPincode.getText().toString()!="")
        {
            pickup=new PickupDetails(date.getText().toString(),pickupAddrss.getText().toString(),pickupLandmark.getText().toString(),
                    pickupPincode.getText().toString());
            ContactActivity.repairRequest.setPickupDetails(pickup);
        }
        return  ContactActivity.repairRequest;
    }
}
