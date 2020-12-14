package com.nikhil.delivery.system.service.impl;

import com.nikhil.delivery.system.actors.Courier;
import com.nikhil.delivery.system.actors.factory.CourierFactory;
import com.nikhil.delivery.system.callback.CourierCallback;
import com.nikhil.delivery.system.callback.impl.CourierCallbackImpl;
import com.nikhil.delivery.system.model.KitchenDetails;
import com.nikhil.delivery.system.model.Order;
import com.nikhil.delivery.system.service.CourierHandlerService;
import com.nikhil.delivery.system.strategy.FifoStrategy;

public class FifoCourierHandlerServiceImpl implements CourierHandlerService {
    private FifoStrategy strategy;

    public FifoCourierHandlerServiceImpl(FifoStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void handleCourier(Order order, KitchenDetails kitchen) {
        Courier nextAvailableCourier = CourierFactory.getInstance().getNextAvailableCourier();
        CourierCallback courierCallBack = new CourierCallbackImpl(this.strategy);
        try {
            nextAvailableCourier.collectOrder(kitchen, courierCallBack);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
