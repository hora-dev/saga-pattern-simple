package com.onebit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SagaOrderTest {

    private SagaOrder sagaOrder;

    @BeforeEach
    void setUp() {
        sagaOrder = new SagaOrder();
    }

    @Test
    void testCreateOrder() {
        sagaOrder.createOrder();
        assertTrue(sagaOrder.isOrderCreated(), "Order should be marked as created");
    }

    @Test
    void testCompensateCreateOrder() {
        sagaOrder.createOrder();
        sagaOrder.compensateCreateOrder();
        assertFalse(sagaOrder.isOrderCreated(), "Order should be marked as not created after compensation");
    }

    @Test
    void testCompleteOrderWhenOrderCreated() {
        sagaOrder.createOrder();
        sagaOrder.completeOrder();
        assertTrue(sagaOrder.isOrderCompleted(), "Order should be marked as completed");
    }

    @Test
    void testCompleteOrderWhenOrderNotCreated() {
        sagaOrder.completeOrder();
        assertFalse(sagaOrder.isOrderCompleted(), "Order should not be marked as completed if it was not created");
    }
}

