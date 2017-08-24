package com.spotify.repairit;
import com.google.gson.Gson;
import com.spotify.repairit.models.RepairRequest;
import com.spotify.repairit.models.UserDetails;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by aecs7 on 27-Feb-17.
 */

public class ContactActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button deviceInfoBtn;
    EditText contactName,contactEmail,contactPhone,contactCity;
    public static RepairRequest repairRequest;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        toolbar= (Toolbar) findViewById(R.id.contactToolbar);
        toolbar.setTitle("RepairIt");
       // toolbar.setLogo(R.drawable.ic_build_white_24dp);
        toolbar.setTitleMarginStart(50);
        deviceInfoBtn=(Button) findViewById(R.id.deviceButton);
        deviceInfoBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getFormData();
               //Gson gson = new Gson();
                //String userString = gson.toJson(user);
                Intent intent=new Intent(ContactActivity.this,DeviceActivity.class);
                //intent.putExtra("userString", userString);
                startActivity(intent);
            }
        });

    }

    public void getFormData() {
        UserDetails user;
        contactName= (EditText) findViewById(R.id.contactName);
        contactEmail= (EditText) findViewById(R.id.contactEmail);
        contactPhone= (EditText) findViewById(R.id.contactMobile);
        contactCity= (EditText) findViewById(R.id.contactCity);
        if(contactName.getText().toString()!=""&& contactEmail.getText().toString()!="" &&contactPhone.getText().toString()!=""
                &&contactCity.getText().toString()!="");
        {user=new UserDetails(contactName.getText().toString(),contactEmail.getText().toString(),
                contactPhone.getText().toString(),contactCity.getText().toString());
            repairRequest=new RepairRequest();
            repairRequest.setUserDetails(user);
        }

    }


}
