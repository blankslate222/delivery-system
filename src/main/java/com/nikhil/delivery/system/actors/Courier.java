package com.nikhil.delivery.system.actors;

import com.nikhil.delivery.system.model.KitchenDetails;
import com.nikhil.delivery.system.model.Order;
import com.nikhil.delivery.system.callback.CourierCallback;

import java.util.concurrent.ThreadLocalRandom;

public interface Courier {

    String getId();

    default void collectOrder(KitchenDetails kitchenDetails, CourierCallback courierCallback)
            throws InterruptedException {
//        System.out.println("Courier : " + this.getId() + " Starting to kitchen : " + kitchen.getId());
        // can be made externally configurable
        Thread.sleep(ThreadLocalRandom.current().nextInt(3, 7 + 1));
        // courier tells the mapper service that he has arrived at the kitchen
        courierCallback.courierArrivedCallback(kitchenDetails, this);
    }

    default void deliverOrder(Customer customer, Order order, CourierCallback courierCallback) {
//        System.out.println("Delivered order " + order.getId() + " to Customer : " + ((customer != null) ? customer.getId() : ""));
        courierCallback.orderDeliveredCallback(this.getId(), order);
    }
}
