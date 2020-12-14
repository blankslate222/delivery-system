package com.nikhil.delivery.system.callback.impl;

import com.nikhil.delivery.system.actors.Courier;
import com.nikhil.delivery.system.model.KitchenDetails;
import com.nikhil.delivery.system.model.Order;
import com.nikhil.delivery.system.callback.CourierCallback;
import com.nikhil.delivery.system.strategy.Strategy;

public class CourierCallbackImpl implements CourierCallback {

    private Strategy strategy;

    public CourierCallbackImpl(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void courierArrivedCallback(KitchenDetails kitchenDetails, Courier courier) {
        try {
            System.out.println(Thread.currentThread().getName() + ":Courier arrived : " + courier.getId());
            courier.deliverOrder(null, strategy.getReadyOrder(courier.getId()), this);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void orderDeliveredCallback(String courierId, Order order) {
        System.out.println(Thread.currentThread().getName() + ":Courier : " + courierId + " Delivered order : " + order.getId());
    }
}
