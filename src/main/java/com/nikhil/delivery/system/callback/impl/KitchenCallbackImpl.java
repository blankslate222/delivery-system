package com.nikhil.delivery.system.callback.impl;

import com.nikhil.delivery.system.model.Order;
import com.nikhil.delivery.system.callback.KitchenCallback;
import com.nikhil.delivery.system.strategy.Strategy;

public class KitchenCallbackImpl implements KitchenCallback {

    private Strategy strategy;

    public KitchenCallbackImpl(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void orderReadyCallback(Order order) {
        try {
            System.out.println("Order ready : " + order.getId());
            strategy.orderReady(order);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
