package com.spotify.repairit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.spotify.repairit.navigation.NavigationModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anurag-local on 19-Apr-17.
 */

public class YourOrderDetailsActivity extends BaseActivity {
    TextView orderId,orderDate,orderStatus,mobileName,expDelivery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourorderdetails);
        Bundle bundle=getIntent().getExtras();
        String orderDetails=bundle.getString("orderDetails");
        orderId= (TextView) findViewById(R.id.orderid_no);
        orderDate= (TextView) findViewById(R.id.orderid_dateval);
        orderStatus= (TextView) findViewById(R.id.order_statusval);
        mobileName= (TextView) findViewById(R.id.mobile_nameval);
        expDelivery= (TextView) findViewById(R.id.expected_delivery_date);
        try {
            JSONArray jsonArray =new JSONArray(orderDetails);
            JSONObject jsonObj= jsonArray.getJSONObject(0);
            orderId.setText(jsonObj.getString("orderId"));
            orderDate.setText(jsonObj.getString("orderDate"));
            orderStatus.setText(jsonObj.getString("orderStatus"));
            mobileName.setText(jsonObj.getString("mobileName"));
            expDelivery.setText(jsonObj.getString("OrderExpDelivery"));
        } catch (JSONException e) {
            Toast.makeText(YourOrderDetailsActivity.this, "Could not fetch response", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }
    @Override
    protected NavigationModel.NavigationItemEnum getSelfItem() {
        return NavigationModel.NavigationItemEnum.YOUR_ORDER;
    }
}
