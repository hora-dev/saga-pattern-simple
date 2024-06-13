package com.onebit;

public class SagaManager3Pasos {

    private PedidoSaga3Pasos orderSaga;

    public SagaManager3Pasos(PedidoSaga3Pasos pedidoSaga3Pasos) {
        this.orderSaga = pedidoSaga3Pasos;
    }

    public void processTransaction() {

        boolean isSuccess = true;

        try {
            // Paso 1: Crear Pedido
            if (!orderSaga.createOrder()) {
                throw new RuntimeException("Error al crear el pedido.");
            }

            // Paso 2: Deducir Inventario
            if (!orderSaga.deductInventory()) {
                throw new RuntimeException("Error al deducir inventario.");
            }

            // Paso 3: Procesar Pago
            if (!orderSaga.processPayment()) {
                throw new RuntimeException("Error al procesar el pago.");
            }

            System.out.println("Transacción completada con éxito.");
        } catch (Exception e) {
            System.out.println("Error durante la transacción: " + e.getMessage());
            isSuccess = false;
        } finally {
            if (!isSuccess) {
                // Compensar acciones en orden inverso
                orderSaga.compensateProcessPayment();
                orderSaga.compensateDeductInventory();
                orderSaga.compensateCreateOrder();
                System.out.println("Transacción compensada.");
            }
        }
    }
}


