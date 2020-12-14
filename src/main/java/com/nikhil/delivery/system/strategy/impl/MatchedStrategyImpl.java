package com.nikhil.delivery.system.strategy.impl;

import com.nikhil.delivery.system.actors.Courier;
import com.nikhil.delivery.system.model.Order;
import com.nikhil.delivery.system.strategy.MatchedStrategy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class MatchedStrategyImpl implements MatchedStrategy {

    private final ConcurrentHashMap<String, Order> courierMapping = new ConcurrentHashMap<>(200, 0.8f);
    private final ConcurrentHashMap<String, BlockingQueue<Order>> readyOrders = new ConcurrentHashMap<>(200, 0.8f);

    @Override
    public void orderReady(Order order) throws Exception {
        readyOrders.get(order.getId()).put(order);
    }

    @Override
    public Order getReadyOrder(String courierId) throws Exception {
        Order order = courierMapping.get(courierId);
        // blocks until the order becomes ready
        return readyOrders.get(order.getId()).take();
    }

    @Override
    public void addOrderAssignmentToPool(Order order, Courier courier) {
        courierMapping.putIfAbsent(courier.getId(), order);
        BlockingQueue<Order> blockingQ = new ArrayBlockingQueue<>(1);
        readyOrders.put(order.getId(), blockingQ);
    }
}
