package com.nikhil.delivery.system.service;

import com.nikhil.delivery.system.model.KitchenDetails;
import com.nikhil.delivery.system.model.Order;
import com.nikhil.delivery.system.strategy.Strategy;

public interface CourierHandlerService {
    void handleCourier(Order order, KitchenDetails kitchenDetails, Strategy strategy);
}
