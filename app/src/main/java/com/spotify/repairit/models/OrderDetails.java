package com.spotify.repairit.models;

/**
 * Created by anurag-local on 02-Apr-17.
 */

public class OrderDetails {
    private String orderId;
    private String orderDate;
    private String mobileName;
    private String orderStatus;
    private String orderExpDeliveryDate;

    public OrderDetails() {
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderExpDelivery() {
        return orderExpDeliveryDate;
    }

    public void setOrderExpDelivery(String orderExpDelivery) {
        this.orderExpDeliveryDate = orderExpDelivery;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    @Override
    public String toString() {
        return "Orderid:  "+getOrderId()+"OrderDate:  "+getOrderDate()+"Mobile no:  "+getMobileName();
    }
}
