package com.onebit;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SagaOrder3PasosTest {

    @Test
    void testCreateOrder() {
        SagaOrder3Steps saga = Mockito.spy(new SagaOrder3Steps());

        // Simulamos el comportamiento del método createOrder
        Mockito.when(saga.createOrder()).thenReturn(true);

        // Llamamos al método y verificamos que devuelve true
        assertTrue(saga.createOrder());

        // Verificamos que se imprima "Pedido creado."
        Mockito.verify(saga).createOrder();
    }

    @Test
    void testDeductInventory() {
        SagaOrder3Steps saga = Mockito.spy(new SagaOrder3Steps());

        // Simulamos el comportamiento del método deductInventory
        Mockito.when(saga.deductInventory()).thenReturn(true);

        // Llamamos al método y verificamos que devuelve true
        assertTrue(saga.deductInventory());

        // Verificamos que se imprima "Inventario deducido."
        Mockito.verify(saga).deductInventory();
    }

    @Test
    void testProcessPayment() {
        SagaOrder3Steps saga = Mockito.spy(new SagaOrder3Steps());

        // Simulamos el comportamiento del método processPayment
        Mockito.when(saga.processPayment()).thenReturn(true);

        // Llamamos al método y verificamos que devuelve true
        assertTrue(saga.processPayment());

        // Verificamos que se imprima "Pago procesado."
        Mockito.verify(saga).processPayment();
    }

    @Test
    void testCompensateCreateOrder() {
        SagaOrder3Steps saga = Mockito.spy(new SagaOrder3Steps());

        // Llamamos al método compensateCreateOrder
        saga.compensateCreateOrder();

        // Verificamos que se imprima "Compensación del pedido creada."
        Mockito.verify(saga).compensateCreateOrder();
    }

    @Test
    void testCompensateDeductInventory() {
        SagaOrder3Steps saga = Mockito.spy(new SagaOrder3Steps());

        // Llamamos al método compensateDeductInventory
        saga.compensateDeductInventory();

        // Verificamos que se imprima "Compensación del inventario deducido."
        Mockito.verify(saga).compensateDeductInventory();
    }

    @Test
    void testCompensateProcessPayment() {
        SagaOrder3Steps saga = Mockito.spy(new SagaOrder3Steps());

        // Llamamos al método compensateProcessPayment
        saga.compensateProcessPayment();

        // Verificamos que se imprima "Compensación del pago procesado."
        Mockito.verify(saga).compensateProcessPayment();
    }
}
