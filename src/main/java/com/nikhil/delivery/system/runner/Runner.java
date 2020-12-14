package com.nikhil.delivery.system.runner;

import com.nikhil.delivery.system.actors.impl.BasicCustomerImpl;
import com.nikhil.delivery.system.model.Order;
import com.nikhil.delivery.system.service.OrderDispatchService;
import com.nikhil.delivery.system.strategy.StrategyEnum;
import com.nikhil.delivery.system.service.impl.OrderDispatchServiceImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Runner {

    public void runFifoSimulation() {
        System.out.println("================== FIFO SIMULATOR ======================");
        final ExecutorService kitchenThreadPool = Executors.newFixedThreadPool(50);
        final ExecutorService courierHandlerThreadPool = Executors.newFixedThreadPool(50);
        final OrderDispatchService
                dispatcherSvc = new OrderDispatchServiceImpl(StrategyEnum.FIFO, kitchenThreadPool, courierHandlerThreadPool);

        new BasicCustomerImpl("cust1", "cust1", "address1", dispatcherSvc)
                .placeOrder("kitchenId1", Order.builder().id("id1").name("name1").prepTimeMs(200).build());
        new BasicCustomerImpl("cust1", "cust1", "address1", dispatcherSvc)
                .placeOrder("kitchenId2", Order.builder().id("id2").name("name2").prepTimeMs(150).build());

        kitchenThreadPool.shutdown();
        courierHandlerThreadPool.shutdown();
        System.out.println();
    }

    public void runMatchedSimulation() {
        System.out.println("================== MATCHED SIMULATOR ======================");
        final ExecutorService kitchenThreadPool = Executors.newFixedThreadPool(50);
        final ExecutorService courierHandlerThreadPool = Executors.newFixedThreadPool(50);
        final OrderDispatchService
                dispatcherSvc = new OrderDispatchServiceImpl(StrategyEnum.MATCHED, kitchenThreadPool, courierHandlerThreadPool);

        new BasicCustomerImpl("cust1", "cust1", "address1", dispatcherSvc)
                .placeOrder("kitchenId1", Order.builder().id("id1").name("name1").prepTimeMs(200).build());
        new BasicCustomerImpl("cust1", "cust1", "address1", dispatcherSvc)
                .placeOrder("kitchenId2", Order.builder().id("id2").name("name2").prepTimeMs(150).build());

        kitchenThreadPool.shutdown();
        courierHandlerThreadPool.shutdown();
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        Runner runner = new Runner();
//        runner.runFifoSimulation();
        runner.runMatchedSimulation();
    }
}
