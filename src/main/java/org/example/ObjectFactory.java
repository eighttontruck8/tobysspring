package org.example;

public class ObjectFactory {
    public PaymentService paymentService() {
        return new PaymentService(new WebApiExRateProvider());
    }
}
