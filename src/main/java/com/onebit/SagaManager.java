package com.onebit;

import lombok.Getter;

public class SagaManager {

    @Getter
    private boolean sagaActive;
    private SagaOrder currentSaga;

    public void startSaga(SagaOrder saga) {
        this.currentSaga = saga;
        saga.createOrder();
        this.sagaActive = true;
    }

    public void completeSaga() {
        if (sagaActive) {
            currentSaga.completeOrder();
            sagaActive = false;
        }
    }

    public void failSaga() {
        if (sagaActive) {
            currentSaga.compensateCreateOrder();
            sagaActive = false;
        }
    }
}
