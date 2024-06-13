package com.onebit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderSagaTest {

    private OrderSaga orderSaga;

    @BeforeEach
    void setUp() {
        orderSaga = new OrderSaga();
    }

    @Test
    void testCreateOrder() {
        orderSaga.createOrder();
        assertTrue(orderSaga.isOrderCreated(), "Order should be marked as created");
    }

    @Test
    void testCompensateCreateOrder() {
        orderSaga.createOrder();
        orderSaga.compensateCreateOrder();
        assertFalse(orderSaga.isOrderCreated(), "Order should be marked as not created after compensation");
    }

    @Test
    void testCompleteOrderWhenOrderCreated() {
        orderSaga.createOrder();
        orderSaga.completeOrder();
        assertTrue(orderSaga.isOrderCompleted(), "Order should be marked as completed");
    }

    @Test
    void testCompleteOrderWhenOrderNotCreated() {
        orderSaga.completeOrder();
        assertFalse(orderSaga.isOrderCompleted(), "Order should not be marked as completed if it was not created");
    }
}

