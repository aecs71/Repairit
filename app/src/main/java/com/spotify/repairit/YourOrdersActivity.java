package com.spotify.repairit;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.spotify.repairit.config.Singleton;
import com.spotify.repairit.models.OrderDetails;
import com.spotify.repairit.navigation.NavigationModel;
import com.spotify.repairit.navigation.RecyclerItemClickListener;
import com.spotify.repairit.utils.ApplicationConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anurag-local on 30-Mar-17.
 */

public class YourOrdersActivity extends BaseActivity {
    private static final String Your_ORDER_URL = ApplicationConstants.Your_ORDERS_URL;
    private RecyclerView mCardList;
    private YourOrderAdapter mAdapter;

    String TAG = YourOrdersActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourorder);
        setTitle("Your Orders");
         processYourOrderData();
        Log.i(TAG, "onCreate:" );


    }

    @Override
    protected NavigationModel.NavigationItemEnum getSelfItem() {
        return NavigationModel.NavigationItemEnum.YOUR_ORDER;
    }

    public class YourOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private Context context;
        private LayoutInflater inflater;
        List<OrderDetails> orders;

        public YourOrderAdapter(Context context, List<OrderDetails> orders) {
            this.context = context;
            this.orders = orders;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.act_recyclerview_yourorder, parent, false);
            ItemViewHolder holder = new ItemViewHolder(view);
            Log.i(TAG, "onCreateViewHolder: ");
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            OrderDetails currentOrder = orders.get(position);
            Log.i(TAG, "onBindViewHolder: "+currentOrder.getMobileName());
            itemViewHolder.mobileName.setText(currentOrder.getMobileName());
            itemViewHolder.orderId.setText(currentOrder.getOrderId());
            itemViewHolder.orderDate.setText(currentOrder.getOrderDate());
            Log.i(TAG, "onBindViewHolder: ");
        }

        @Override
        public int getItemCount() {
            Log.i(TAG, "getItemCount: "+orders.size());
            return orders.size();

        }
    }

    public void processYourOrderData() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, Your_ORDER_URL, new Response.Listener<String>() {

            List<OrderDetails> orderList = new ArrayList<OrderDetails>();
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject;

                    for (int i = 0; i < jsonArray.length(); i++) {
                        OrderDetails order = new OrderDetails();
                        jsonObject = jsonArray.getJSONObject(i);
                        order.setOrderId(jsonObject.getString("orderId"));
                        order.setOrderDate(jsonObject.getString("orderDate"));
                        order.setMobileName(jsonObject.getString("mobileName"));
                        orderList.add(order);
                      //  Log.i(TAG, "onResponse: "+orderList.toString());
                    }
                    mCardList = (RecyclerView) findViewById(R.id.order_card_list);
                    mAdapter = new YourOrderAdapter(YourOrdersActivity.this, orderList);
                    mCardList.setAdapter(mAdapter);
                    mCardList.setLayoutManager(new LinearLayoutManager(YourOrdersActivity.this));
                   // Log.i(TAG, "onResponse1: "+orderList.toString());
                    recyclerviewTouchListener(orderList);
                } catch (JSONException ex) {
                    Toast.makeText(YourOrdersActivity.this, "Could not fetch response", Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(YourOrdersActivity.this, "Could not fetch response", Toast.LENGTH_SHORT).show();
                error.printStackTrace();

            }
        });
        Singleton.getInstance(YourOrdersActivity.this).addToRequestQueue(stringRequest);

        //return orderList;
    }

    private void recyclerviewTouchListener(final List<OrderDetails> orders) {
        mCardList.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),mCardList, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext()," is selected!", Toast.LENGTH_SHORT).show();
                OrderDetails clickedOrder = orders.get(position);
                String orderID=clickedOrder.getOrderId();
                Log.i(TAG, "onItemClick: ."+position+clickedOrder.getOrderId());
                getOrderDetails(orderID);


            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    private void getOrderDetails(String orderId) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Your_ORDER_URL+"?orderid="+orderId, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject;

                    for (int i = 0; i < jsonArray.length(); i++) {
                        OrderDetails order = new OrderDetails();
                        jsonObject = jsonArray.getJSONObject(i);
                        order.setOrderId(jsonObject.getString("orderId"));
                        order.setOrderDate(jsonObject.getString("orderDate"));
                        order.setMobileName(jsonObject.getString("mobileName"));
                        order.setOrderStatus(jsonObject.getString("orderStatus"));
                        order.setOrderExpDelivery(jsonObject.getString("OrderExpDelivery"));
                        Log.i(TAG, "onResponse: "+order.toString()+" "+response);
                        Intent intent =new Intent(YourOrdersActivity.this,YourOrderDetailsActivity.class);
                        intent.putExtra("orderDetails",response);
                        startActivity(intent);
                    }

                } catch (JSONException ex) {
                    Toast.makeText(YourOrdersActivity.this, "Could not fetch response", Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(YourOrdersActivity.this, "Could not fetch response", Toast.LENGTH_SHORT).show();
                error.printStackTrace();

            }
        }){

        };
        Singleton.getInstance(YourOrdersActivity.this).addToRequestQueue(stringRequest);
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        final TextView mobileName;
        final TextView orderId;
        final TextView orderDate;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mobileName = (TextView) itemView.findViewById(R.id.mobile_name);
            orderId = (TextView) itemView.findViewById(R.id.order_id);
            orderDate = (TextView) itemView.findViewById(R.id.order_date);
        }
    }

}

