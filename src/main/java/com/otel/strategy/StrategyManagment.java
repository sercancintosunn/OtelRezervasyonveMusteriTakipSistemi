package com.otel.strategy;

public class StrategyManagment {

    private PaymentStrategy strategy;

    public void setPayment(PaymentStrategy strategy){
        this.strategy = strategy;
    }

    public boolean odemeYap(double tutar, String musteriAd){

        if(strategy == null){
            System.out.println("Ödeme yöntemi seçilmedi");
            return false;
        }

        return strategy.odemeYap(tutar,musteriAd);
    }

    public String getOdemeTipi(){
        return strategy.getOdemeTipi();
    }

}
