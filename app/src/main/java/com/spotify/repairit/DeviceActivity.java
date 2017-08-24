package com.spotify.repairit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.spotify.repairit.models.DeviceDetails;

/**
 * Created by aecs7 on 28-Feb-17.
 */

public class DeviceActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button scheduleRepairBtn;
    EditText deviceType,deviceBrand,deviceModel,deviceIssue;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        toolbar = (Toolbar) findViewById(R.id.deviceToolbar);
        toolbar.setTitle("RepairIt");
        toolbar.setLogo(R.drawable.ic_build_white_24dp);
        toolbar.setTitleMarginStart(50);
        scheduleRepairBtn = (Button) findViewById(R.id.scheduleRepair);
        scheduleRepairBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFormData();
                Intent intent = new Intent(DeviceActivity.this, ScheduleRepairActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getFormData() {
        DeviceDetails device;
        deviceType= (EditText) findViewById(R.id.deviceType);
        deviceBrand= (EditText) findViewById(R.id.deviceBrand);
        deviceModel= (EditText) findViewById(R.id.deviceModel);
        deviceIssue= (EditText) findViewById(R.id.deviceIssue);
        if(deviceType.getText().toString()!="" && deviceBrand.getText().toString()!=""&&deviceModel.getText().toString()!=""
                &&deviceIssue.getText().toString()!="")
        {
            device=new DeviceDetails(deviceType.getText().toString(),deviceBrand.getText().toString(),deviceModel.getText().toString()
            ,deviceIssue.getText().toString());

           ContactActivity.repairRequest.setDeviceDetails(device);
        }
    }
}
