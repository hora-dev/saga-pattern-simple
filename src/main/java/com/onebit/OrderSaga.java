package com.onebit;

import lombok.extern.java.Log;

@Log
public class OrderSaga {

    private boolean orderCreated;
    private boolean orderCompleted;

    public OrderSaga() {
        this.orderCreated = false;
        this.orderCompleted = false;
    }

    public void createOrder() {
        // Lógica para crear la orden
        log.info("Order created");
        this.orderCreated = true;
    }

    public void compensateCreateOrder() {
        // Lógica para compensar la creación de la orden en caso de fallo
        log.info("Order creation compensated");
        this.orderCreated = false;
    }

    public void completeOrder() {
        // Lógica para completar la orden
        if (this.orderCreated) {
            log.info("Order completed");
            this.orderCompleted = true;
        } else {
            log.info("Cannot complete order: Order not created");
            this.orderCompleted = false;
        }
    }

    public boolean isOrderCreated() {
        return orderCreated;
    }

    public boolean isOrderCompleted() {
        return orderCompleted;
    }
}
