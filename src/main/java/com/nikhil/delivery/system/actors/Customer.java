package com.nikhil.delivery.system.actors;

import com.nikhil.delivery.system.model.Order;

public interface Customer {
    String getId();
    String getName();
    String getAddress();
    void placeOrder(String kitchenId, Order order);
}
