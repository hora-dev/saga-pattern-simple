package com.onebit;

import lombok.extern.java.Log;

@Log
public class SagaOrder3Steps {

    public boolean createOrder() {
        log.info("Pedido creado.");
        return true;
    }

    public boolean deductInventory() {
        log.info("Inventario deducido.");
        return true;
    }

    public boolean processPayment() {
        log.info("Pago procesado.");
        return true;
    }

    public void compensateCreateOrder() {
        log.info("Compensación del pedido creada.");
    }

    public void compensateDeductInventory() {
        log.info("Compensación del inventario deducido.");
    }

    public void compensateProcessPayment() {
        log.info("Compensación del pago procesado.");
    }
}
