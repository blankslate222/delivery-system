package com.nikhil.delivery.system.strategy.impl;

import com.nikhil.delivery.system.model.Order;
import com.nikhil.delivery.system.strategy.Strategy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FifoStrategyImpl implements Strategy {

    private final BlockingQueue<Order> readyOrderQueue = new LinkedBlockingQueue<>();

    @Override
    public void orderReady(Order order) throws Exception {
        readyOrderQueue.put(order);
    }

    @Override
    public Order getReadyOrder(String courierId) throws Exception {
        return readyOrderQueue.take();
    }
}
