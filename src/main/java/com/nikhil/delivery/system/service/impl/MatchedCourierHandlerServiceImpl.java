package com.nikhil.delivery.system.service.impl;

import com.nikhil.delivery.system.actors.Courier;
import com.nikhil.delivery.system.actors.factory.CourierFactory;
import com.nikhil.delivery.system.callback.CourierCallback;
import com.nikhil.delivery.system.callback.impl.CourierCallbackImpl;
import com.nikhil.delivery.system.model.KitchenDetails;
import com.nikhil.delivery.system.model.Order;
import com.nikhil.delivery.system.service.CourierHandlerService;
import com.nikhil.delivery.system.strategy.MatchedStrategy;

public class MatchedCourierHandlerServiceImpl implements CourierHandlerService {
    private MatchedStrategy strategy;

    public MatchedCourierHandlerServiceImpl(MatchedStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void handleCourier(Order order, KitchenDetails kitchen) {
        Courier nextAvailableCourier = CourierFactory.getInstance().getNextAvailableCourier();
        CourierCallback courierCallBack = new CourierCallbackImpl(this.strategy);
        try {
            this.strategy.addOrderAssignmentToPool(order, nextAvailableCourier);
            System.out.println("Assigned order : " + order.getId() + " to courier : " + nextAvailableCourier.getId());
            nextAvailableCourier.collectOrder(kitchen, courierCallBack);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
