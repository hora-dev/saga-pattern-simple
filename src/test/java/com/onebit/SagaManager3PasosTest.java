package com.onebit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class SagaManager3PasosTest {

    private PedidoSaga3Pasos mockPedidoSaga;
    private SagaManager3Pasos sagaManager;

    @BeforeEach
    public void setUp() {
        mockPedidoSaga = Mockito.mock(PedidoSaga3Pasos.class);
        sagaManager = new SagaManager3Pasos(mockPedidoSaga);
    }

    @Test
    public void testProcessTransaction_Success() {
        // Simular éxito en todos los pasos
        when(mockPedidoSaga.createOrder()).thenReturn(true);
        when(mockPedidoSaga.deductInventory()).thenReturn(true);
        when(mockPedidoSaga.processPayment()).thenReturn(true);

        // Llamar al método bajo prueba
        sagaManager.processTransaction();

        // Verificar que se haya completado con éxito
        verify(mockPedidoSaga).createOrder();
        verify(mockPedidoSaga).deductInventory();
        verify(mockPedidoSaga).processPayment();

        // Verificar que no se llamen los métodos de compensación
        verify(mockPedidoSaga, never()).compensateCreateOrder();
        verify(mockPedidoSaga, never()).compensateDeductInventory();
        verify(mockPedidoSaga, never()).compensateProcessPayment();
    }

    @Test
    public void testProcessTransaction_Compensation() {
        // Simular error en uno de los pasos
        when(mockPedidoSaga.createOrder()).thenReturn(true);
        when(mockPedidoSaga.deductInventory()).thenReturn(false); // Simulamos error aquí
        when(mockPedidoSaga.processPayment()).thenReturn(true);

        // Llamar al método bajo prueba
        sagaManager.processTransaction();

        // Verificar que se hayan llamado los pasos y las compensaciones en orden inverso
        verify(mockPedidoSaga).createOrder();
        verify(mockPedidoSaga).deductInventory();
        verify(mockPedidoSaga, never()).processPayment(); // No se debería llamar al tercer paso
        verify(mockPedidoSaga).compensateCreateOrder();
        verify(mockPedidoSaga).compensateDeductInventory();
        verify(mockPedidoSaga, never()).compensateProcessPayment(); // No se debería llamar a la compensación del pago
        //TODO agregar 3 excepciones para distinguir en que paso fallo
    }
}

