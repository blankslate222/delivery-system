package com.nikhil.delivery.system.callback;

import com.nikhil.delivery.system.actors.Courier;
import com.nikhil.delivery.system.model.KitchenDetails;
import com.nikhil.delivery.system.model.Order;

public interface CourierCallback {
    void courierArrivedCallback(KitchenDetails kitchenDetails, Courier courier);
    void orderDeliveredCallback(String courierId, Order order);
}
