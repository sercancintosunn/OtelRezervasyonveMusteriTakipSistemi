package com.otel.strategy;

public class Nakit  implements PaymentStrategy{

    @Override
    public boolean odemeYap(double tutar, String musteriAd) {

        System.out.println("Müşteri: " + musteriAd + tutar + "TL nakit olarak alındı");
        return true;
    }

    @Override
    public String getOdemeTipi() {
        return "NAKIT";
    }
}
