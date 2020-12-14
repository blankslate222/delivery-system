package com.nikhil.delivery.system.actors.impl;

import com.nikhil.delivery.system.actors.Courier;
import com.nikhil.delivery.system.actors.Customer;
import com.nikhil.delivery.system.model.KitchenDetails;
import com.nikhil.delivery.system.model.Order;
import com.nikhil.delivery.system.callback.CourierCallback;

import java.util.UUID;

import lombok.Data;

@Data
public class BasicCourierImpl implements Courier {

    private String id;

    public BasicCourierImpl() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public void collectOrder(KitchenDetails kitchenDetails, CourierCallback courierCallback)
            throws InterruptedException {
        Courier.super.collectOrder(kitchenDetails, courierCallback);
    }

    @Override
    public void deliverOrder(Customer customer, Order order, CourierCallback courierCallback) {
        Courier.super.deliverOrder(customer, order, courierCallback);
    }
}
