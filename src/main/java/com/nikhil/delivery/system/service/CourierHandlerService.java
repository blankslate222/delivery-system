package com.nikhil.delivery.system.service;

import com.nikhil.delivery.system.model.KitchenDetails;
import com.nikhil.delivery.system.model.Order;

public interface CourierHandlerService {
    void handleCourier(Order order, KitchenDetails kitchenDetails);
}
