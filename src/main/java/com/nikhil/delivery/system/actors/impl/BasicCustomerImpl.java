package com.nikhil.delivery.system.actors.impl;

import com.nikhil.delivery.system.actors.Customer;
import com.nikhil.delivery.system.model.Order;
import com.nikhil.delivery.system.service.OrderDispatchService;

public class BasicCustomerImpl implements Customer {

    private String id;
    private String name;
    private String address;
    private OrderDispatchService dispatcher;

    public BasicCustomerImpl(String id, String name, String address,
            OrderDispatchService dispatcher) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dispatcher = dispatcher;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    /**
     * simulates the act of order placement by a customer
     * @param kitchenId
     * @param order
     */
    @Override
    public void placeOrder(String kitchenId, Order order) {
        dispatcher.placeOrder(kitchenId, order);
    }
}
