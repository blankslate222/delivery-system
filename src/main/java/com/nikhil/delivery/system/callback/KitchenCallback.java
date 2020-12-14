package com.nikhil.delivery.system.callback;

import com.nikhil.delivery.system.model.Order;

public interface KitchenCallback {
    void orderReadyCallback(Order order);
}
