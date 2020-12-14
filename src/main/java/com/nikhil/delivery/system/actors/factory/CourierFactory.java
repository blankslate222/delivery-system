package com.nikhil.delivery.system.actors.factory;

import com.nikhil.delivery.system.actors.Courier;
import com.nikhil.delivery.system.actors.impl.BasicCourierImpl;

public class CourierFactory {

    private static final CourierFactory FACTORY = new CourierFactory();

    private CourierFactory() {

    }

    public static CourierFactory getInstance() {
        return FACTORY;
    }

    public synchronized Courier getNextAvailableCourier() {
        return new BasicCourierImpl();
    }
}
