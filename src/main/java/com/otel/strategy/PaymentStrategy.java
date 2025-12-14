package com.otel.strategy;

public interface PaymentStrategy {

    boolean odemeYap(double tutar,String musteriAd);

    String  getOdemeTipi();
}
