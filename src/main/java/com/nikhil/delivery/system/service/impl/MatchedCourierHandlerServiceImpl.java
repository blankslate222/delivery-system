package com.nikhil.delivery.system.service.impl;

import com.nikhil.delivery.system.actors.Courier;
import com.nikhil.delivery.system.actors.factory.CourierFactory;
import com.nikhil.delivery.system.callback.impl.CourierCallbackImpl;
import com.nikhil.delivery.system.model.KitchenDetails;
import com.nikhil.delivery.system.model.Order;
import com.nikhil.delivery.system.callback.CourierCallback;
import com.nikhil.delivery.system.service.CourierHandlerService;
import com.nikhil.delivery.system.strategy.MatchedStrategy;
import com.nikhil.delivery.system.strategy.Strategy;

public class MatchedCourierHandlerServiceImpl implements CourierHandlerService {

    @Override
    public void handleCourier(Order order, KitchenDetails kitchen, Strategy strategy) {
        MatchedStrategy matchedStrategy = (MatchedStrategy) strategy;
        Courier nextAvailableCourier = CourierFactory.getInstance().getNextAvailableCourier();
        CourierCallback courierCallBack = new CourierCallbackImpl(strategy);
        try {
            matchedStrategy.addOrderAssignmentToPool(order, nextAvailableCourier);
            System.out.println("Assigned order : " + order.getId() + " to courier : " + nextAvailableCourier.getId());
            nextAvailableCourier.collectOrder(kitchen, courierCallBack);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
