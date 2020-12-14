package com.nikhil.delivery.system.actors.factory;

import com.nikhil.delivery.system.actors.Kitchen;
import com.nikhil.delivery.system.actors.impl.BasicKitchenImpl;
import com.nikhil.delivery.system.model.KitchenDetails;

import java.util.HashMap;
import java.util.Map;

public class KitchenFactory {
    private static final KitchenFactory FACTORY = new KitchenFactory();
    private final Map<String, Kitchen> kitchenInventory = new HashMap<>();

    private KitchenFactory() {

    }

    public static KitchenFactory getInstance() {
        return FACTORY;
    }

    public Kitchen getKitchen(String id) {
        Kitchen kitchen = kitchenInventory.get(id);
//        if (kitchen == null) {
//            throw new RuntimeException("Kitchen not found");
//        }
        return kitchen;
    }

    public Kitchen getNextAvailableKitchen() {
        return new BasicKitchenImpl(KitchenDetails.builder().id("kitchenId").name("kitchenName").build());
    }
}
