package com.onebit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

class SagaManager3StepsTest {

    private SagaOrder3Steps sagaOrder3StepsMock;
    private SagaManager3Steps sagaManager3Steps;

    @BeforeEach
    public void setUp() {
        sagaOrder3StepsMock = Mockito.mock(SagaOrder3Steps.class);
        sagaManager3Steps = new SagaManager3Steps(sagaOrder3StepsMock);
    }

    @Test
    void testProcessTransaction_Success() {
        // Simular éxito en todos los pasos
        when(sagaOrder3StepsMock.createOrder()).thenReturn(true);
        when(sagaOrder3StepsMock.deductInventory()).thenReturn(true);
        when(sagaOrder3StepsMock.processPayment()).thenReturn(true);

        // Llamar al método bajo prueba
        sagaManager3Steps.processTransaction();

        // Verificar que se haya completado con éxito
        verify(sagaOrder3StepsMock).createOrder();
        verify(sagaOrder3StepsMock).deductInventory();
        verify(sagaOrder3StepsMock).processPayment();

        // Verificar que no se llamen los métodos de compensación
        verify(sagaOrder3StepsMock, never()).compensateCreateOrder();
        verify(sagaOrder3StepsMock, never()).compensateDeductInventory();
        verify(sagaOrder3StepsMock, never()).compensateProcessPayment();
    }

    @Test
    void testProcessTransaction_Compensation() {
        // Simular error en uno de los pasos
        when(sagaOrder3StepsMock.createOrder()).thenReturn(true);
        when(sagaOrder3StepsMock.deductInventory()).thenReturn(false); // Simulamos error aquí
        when(sagaOrder3StepsMock.processPayment()).thenReturn(true);

        // Llamar al método bajo prueba
        sagaManager3Steps.processTransaction();

        // Verificar que se hayan llamado los pasos y las compensaciones en orden inverso
        verify(sagaOrder3StepsMock).createOrder();
        verify(sagaOrder3StepsMock).deductInventory();
        verify(sagaOrder3StepsMock, never()).processPayment(); // No se debería llamar al tercer paso
        verify(sagaOrder3StepsMock).compensateCreateOrder();
        verify(sagaOrder3StepsMock).compensateDeductInventory();
        verify(sagaOrder3StepsMock, never()).compensateProcessPayment(); // No se debería llamar a la compensación del pago
    }

    @Test
    void testCreateOrderException() {
        // Configurar el mock para que falle en la creación del pedido
        when(sagaOrder3StepsMock.createOrder()).thenReturn(false);

        sagaManager3Steps.processTransaction();

        // Verificar que se llamaron las compensaciones correctas
        verify(sagaOrder3StepsMock, times(1)).compensateCreateOrder();
    }

    @Test
    void testInventoryDeductionException() {
        // Configurar el mock para que pase la creación del pedido pero falle en deducir el inventario
        when(sagaOrder3StepsMock.createOrder()).thenReturn(true);
        when(sagaOrder3StepsMock.deductInventory()).thenReturn(false);

        sagaManager3Steps.processTransaction();

        // Verificar que se llamaron las compensaciones correctas
        verify(sagaOrder3StepsMock, times(1)).compensateDeductInventory();
        verify(sagaOrder3StepsMock, times(1)).compensateCreateOrder();
    }

    @Test
    void testPaymentProcessException() {
        // Configurar el mock para que pase los pasos anteriores pero falle en procesar el pago
        when(sagaOrder3StepsMock.createOrder()).thenReturn(true);
        when(sagaOrder3StepsMock.deductInventory()).thenReturn(true);
        when(sagaOrder3StepsMock.processPayment()).thenReturn(false);

        sagaManager3Steps.processTransaction();

        // Verificar que se llamaron las compensaciones correctas
        verify(sagaOrder3StepsMock, times(1)).compensateProcessPayment();
        verify(sagaOrder3StepsMock, times(1)).compensateDeductInventory();
        verify(sagaOrder3StepsMock, times(1)).compensateCreateOrder();
    }

    @Test
    void testGeneralException() {
        // Configurar el mock para que lance una excepción inesperada
        when(sagaOrder3StepsMock.createOrder()).thenThrow(new RuntimeException("Unexpected error"));

        sagaManager3Steps.processTransaction();

        // Verificar que se llamaron todas las compensaciones
        verify(sagaOrder3StepsMock, times(1)).compensateCreateOrder();
        verify(sagaOrder3StepsMock, times(1)).compensateDeductInventory();
        verify(sagaOrder3StepsMock, times(1)).compensateProcessPayment();
    }
}

