package com.nikhil.delivery.system.actors;

import com.nikhil.delivery.system.model.KitchenDetails;
import com.nikhil.delivery.system.model.Order;
import com.nikhil.delivery.system.callback.KitchenCallback;

public interface Kitchen {

    KitchenDetails getKitchenDetails();

    default void prepOrder(Order order, KitchenCallback callback) {
        // block for prep time
        try {
            Thread.sleep(order.getPrepTimeMs());
            callback.orderReadyCallback(order);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Could not complete order");
        }
    }
}
