package com.nikhil.delivery.system.strategy;

import com.nikhil.delivery.system.actors.Courier;
import com.nikhil.delivery.system.model.Order;

public interface MatchedStrategy extends Strategy {
    void addOrderAssignmentToPool(Order order, Courier courier);
}
