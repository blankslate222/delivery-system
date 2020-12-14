package com.nikhil.delivery.system.service;


import com.nikhil.delivery.system.model.Order;

/**
 * 1) Dispatches order to Kitchen
 * 2) Calls the mapper callback which handles mapping between couriers and orders
 */
public interface OrderDispatchService {
    void placeOrder(String kitchenId, Order order);
}
