package com.nikhil.delivery.system.strategy;

import com.nikhil.delivery.system.model.Order;

public interface Strategy {
    void orderReady(Order order) throws Exception;
    Order getReadyOrder(String courierId) throws Exception;
}
