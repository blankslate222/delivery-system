package com.nikhil.delivery.system.service.impl;

import com.nikhil.delivery.system.actors.Kitchen;
import com.nikhil.delivery.system.actors.factory.KitchenFactory;
import com.nikhil.delivery.system.callback.impl.KitchenCallbackImpl;
import com.nikhil.delivery.system.model.Order;
import com.nikhil.delivery.system.service.CourierHandlerService;
import com.nikhil.delivery.system.service.OrderDispatchService;
import com.nikhil.delivery.system.strategy.FifoStrategy;
import com.nikhil.delivery.system.strategy.MatchedStrategy;
import com.nikhil.delivery.system.strategy.impl.FifoStrategyImpl;
import com.nikhil.delivery.system.strategy.impl.MatchedStrategyImpl;
import com.nikhil.delivery.system.strategy.Strategy;
import com.nikhil.delivery.system.strategy.StrategyEnum;

import java.util.concurrent.ExecutorService;

public class OrderDispatchServiceImpl implements OrderDispatchService {

    private final ExecutorService kitchenThreadPool;
    private final ExecutorService courierHandlerThreadPool;
    private final Strategy strategy;
    private final CourierHandlerService courierSvc;

    public OrderDispatchServiceImpl(StrategyEnum strategyEnum,
            ExecutorService kitchenThreadPool,
            ExecutorService courierHandlerThreadPool) {
        this.kitchenThreadPool = kitchenThreadPool;
        this.courierHandlerThreadPool = courierHandlerThreadPool;
        if (StrategyEnum.FIFO == strategyEnum) {
            FifoStrategy fifoStrategy = new FifoStrategyImpl();
            this.strategy = fifoStrategy;
            this.courierSvc = new FifoCourierHandlerServiceImpl(fifoStrategy);
        } else {
            MatchedStrategy matchedStrategy = new MatchedStrategyImpl();
            this.strategy = matchedStrategy;
            this.courierSvc = new MatchedCourierHandlerServiceImpl(matchedStrategy);
        }
    }

    @Override
    public void placeOrder(String kitchenId, Order order) {
        final Kitchen kitchen = getKitchen(kitchenId);
        kitchenThreadPool.submit(() -> kitchen.prepOrder(order, new KitchenCallbackImpl(this.strategy)));
        courierHandlerThreadPool.submit(() -> this.courierSvc.handleCourier(order, kitchen.getKitchenDetails()));
    }

    private Kitchen getKitchen(String kitchenId) {
        Kitchen kitchen = KitchenFactory.getInstance().getKitchen(kitchenId);
        // for the sake of simulation
        if (kitchen == null){
            kitchen = KitchenFactory.getInstance().getNextAvailableKitchen();
        }
        return kitchen;
    }
}
