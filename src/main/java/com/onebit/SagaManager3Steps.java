package com.onebit;

import com.onebit.exception.CreateOrderException;
import com.onebit.exception.InventoryDeductionException;
import com.onebit.exception.PaymentProcessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor
@Log
public class SagaManager3Steps {

    public static final String ERROR_DURANTE_LA_TRANSACCION = "Error durante la transacción: ";
    public static final String TRANSACCION_COMPENSADA = "Transacción compensada";
    private final SagaOrder3Steps sagaOrder;

    public void processTransaction() {

        try {
            // Paso 1: Crear Pedido
            if (!sagaOrder.createOrder()) {
                throw new CreateOrderException("Error al crear el pedido.");
            }

            // Paso 2: Deducir Inventario
            if (!sagaOrder.deductInventory()) {
                throw new InventoryDeductionException("Error al deducir inventario.");
            }

            // Paso 3: Procesar Pago
            if (!sagaOrder.processPayment()) {
                throw new PaymentProcessException("Error al procesar el pago.");
            }

            log.info("Transacción completada con éxito.");
        } catch (CreateOrderException createOrderException) {
            log.info(ERROR_DURANTE_LA_TRANSACCION + createOrderException.getMessage());
            sagaOrder.compensateCreateOrder();
            log.info(TRANSACCION_COMPENSADA);
        } catch (InventoryDeductionException inventoryDeductionException) {
            log.info(ERROR_DURANTE_LA_TRANSACCION + inventoryDeductionException);
            sagaOrder.compensateDeductInventory();
            sagaOrder.compensateCreateOrder();
            log.info(TRANSACCION_COMPENSADA);
        } catch (PaymentProcessException paymentProcessException) {
            log.info(ERROR_DURANTE_LA_TRANSACCION + paymentProcessException);
            compensateCompleteTransaction();
            log.info(TRANSACCION_COMPENSADA);
        }
        catch (Exception e) {
            log.info(ERROR_DURANTE_LA_TRANSACCION + e.getMessage());
            // Compensar acciones en orden inverso
            compensateCompleteTransaction();
            log.info("Transacción compensada.");
        }
    }

    private void compensateCompleteTransaction() {
        sagaOrder.compensateProcessPayment();
        sagaOrder.compensateDeductInventory();
        sagaOrder.compensateCreateOrder();
    }
}


