package com.onebit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SagaManagerTest {

    private SagaManager sagaManager;
    private SagaOrder sagaOrder;

    @BeforeEach
    void setUp() {
        sagaManager = new SagaManager();
        sagaOrder = Mockito.mock(SagaOrder.class);
    }

    @Test
    void testStartSaga() {
        sagaManager.startSaga(sagaOrder);
        verify(sagaOrder, times(1)).createOrder();
        assertTrue(sagaManager.isSagaActive());
    }

    @Test
    void testCompleteSaga() {
        sagaManager.startSaga(sagaOrder);
        sagaManager.completeSaga();
        verify(sagaOrder, times(1)).completeOrder();
        assertFalse(sagaManager.isSagaActive());
    }

    @Test
    void testCompensateSagaOnFailure() {
        sagaManager.startSaga(sagaOrder);
        sagaManager.failSaga();
        verify(sagaOrder, times(1)).compensateCreateOrder();
        assertFalse(sagaManager.isSagaActive());
    }
}
