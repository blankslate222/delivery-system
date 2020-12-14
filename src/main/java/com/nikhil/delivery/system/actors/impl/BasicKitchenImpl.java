package com.nikhil.delivery.system.actors.impl;

import com.nikhil.delivery.system.actors.Kitchen;
import com.nikhil.delivery.system.model.KitchenDetails;
import com.nikhil.delivery.system.model.Order;
import com.nikhil.delivery.system.callback.KitchenCallback;

import lombok.Data;

@Data
public class BasicKitchenImpl implements Kitchen {

    private KitchenDetails kitchenDetails;

    public BasicKitchenImpl(KitchenDetails kitchenDetails) {
        this.kitchenDetails = kitchenDetails;
    }

    @Override
    public KitchenDetails getKitchenDetails() {
        return this.kitchenDetails;
    }

    @Override
    public void prepOrder(Order order, KitchenCallback kitchenCallback) {
        Kitchen.super.prepOrder(order, kitchenCallback);
    }
}
