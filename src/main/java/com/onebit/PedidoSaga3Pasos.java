package com.onebit;

public class PedidoSaga3Pasos {
    public boolean createOrder() {
        System.out.println("Pedido creado.");
        return true;
    }

    public boolean deductInventory() {
        System.out.println("Inventario deducido.");
        return true;
    }

    public boolean processPayment() {
        System.out.println("Pago procesado.");
        return true;
    }

    public void compensateCreateOrder() {
        System.out.println("Compensación del pedido creada.");
    }

    public void compensateDeductInventory() {
        System.out.println("Compensación del inventario deducido.");
    }

    public void compensateProcessPayment() {
        System.out.println("Compensación del pago procesado.");
    }
}
