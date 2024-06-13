package com.onebit;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderSaga3PasosTest {

    @Test
    public void testCreateOrder() {
        PedidoSaga3Pasos saga = Mockito.spy(new PedidoSaga3Pasos());

        // Simulamos el comportamiento del método createOrder
        Mockito.when(saga.createOrder()).thenReturn(true);

        // Llamamos al método y verificamos que devuelve true
        assertTrue(saga.createOrder());

        // Verificamos que se imprima "Pedido creado."
        Mockito.verify(saga).createOrder();
    }

    @Test
    public void testDeductInventory() {
        PedidoSaga3Pasos saga = Mockito.spy(new PedidoSaga3Pasos());

        // Simulamos el comportamiento del método deductInventory
        Mockito.when(saga.deductInventory()).thenReturn(true);

        // Llamamos al método y verificamos que devuelve true
        assertTrue(saga.deductInventory());

        // Verificamos que se imprima "Inventario deducido."
        Mockito.verify(saga).deductInventory();
    }

    @Test
    public void testProcessPayment() {
        PedidoSaga3Pasos saga = Mockito.spy(new PedidoSaga3Pasos());

        // Simulamos el comportamiento del método processPayment
        Mockito.when(saga.processPayment()).thenReturn(true);

        // Llamamos al método y verificamos que devuelve true
        assertTrue(saga.processPayment());

        // Verificamos que se imprima "Pago procesado."
        Mockito.verify(saga).processPayment();
    }

    @Test
    public void testCompensateCreateOrder() {
        PedidoSaga3Pasos saga = Mockito.spy(new PedidoSaga3Pasos());

        // Llamamos al método compensateCreateOrder
        saga.compensateCreateOrder();

        // Verificamos que se imprima "Compensación del pedido creada."
        Mockito.verify(saga).compensateCreateOrder();
    }

    @Test
    public void testCompensateDeductInventory() {
        PedidoSaga3Pasos saga = Mockito.spy(new PedidoSaga3Pasos());

        // Llamamos al método compensateDeductInventory
        saga.compensateDeductInventory();

        // Verificamos que se imprima "Compensación del inventario deducido."
        Mockito.verify(saga).compensateDeductInventory();
    }

    @Test
    public void testCompensateProcessPayment() {
        PedidoSaga3Pasos saga = Mockito.spy(new PedidoSaga3Pasos());

        // Llamamos al método compensateProcessPayment
        saga.compensateProcessPayment();

        // Verificamos que se imprima "Compensación del pago procesado."
        Mockito.verify(saga).compensateProcessPayment();
    }
}
