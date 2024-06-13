package com.onebit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SagaManagerTest {

    private SagaManager sagaManager;
    private OrderSaga orderSaga;

    @BeforeEach
    void setUp() {
        sagaManager = new SagaManager();
        orderSaga = Mockito.mock(OrderSaga.class);
    }

    @Test
    void testStartSaga() {
        sagaManager.startSaga(orderSaga);
        verify(orderSaga, times(1)).createOrder();
        assertTrue(sagaManager.isSagaActive());
    }

    @Test
    void testCompleteSaga() {
        sagaManager.startSaga(orderSaga);
        sagaManager.completeSaga();
        verify(orderSaga, times(1)).completeOrder();
        assertFalse(sagaManager.isSagaActive());
    }

    @Test
    void testCompensateSagaOnFailure() {
        sagaManager.startSaga(orderSaga);
        sagaManager.failSaga();
        verify(orderSaga, times(1)).compensateCreateOrder();
        assertFalse(sagaManager.isSagaActive());
    }
}
