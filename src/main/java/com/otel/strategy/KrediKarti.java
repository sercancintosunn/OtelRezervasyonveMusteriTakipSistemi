package com.otel.strategy;

public class KrediKarti implements PaymentStrategy {

    private String kartNumarasi;
    private String kartSahibi;
    private String sonKullanmaTarihi;
    private String cvv;


    public KrediKarti(String kartNumarasi, String kartSahibi, String sonKullanmaTarihi, String cvv) {
        this.kartNumarasi = kartNumarasi;
        this.kartSahibi = kartSahibi;
        this.sonKullanmaTarihi = sonKullanmaTarihi;
        this.cvv = cvv;
    }



    @Override
    public boolean odemeYap(double tutar, String musteriAd) {

        if(!kartDogrula()){
            System.out.println("Hatalı kart bilgileri.");
            System.out.println("Ödeme geçersiz");
            return false;
        }

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Müşteri: " + musteriAd + tutar + "TL kredi kartı olarak alındı");

        return true;
    }


    private boolean kartDogrula(){
        if(kartNumarasi == null || kartNumarasi.length() != 16){
            return false;
        }
        if(cvv == null || cvv.length() != 3){
            return false;
        }
        return true;

    }

    @Override
    public String getOdemeTipi() {
        return "KREDIKARTI";
    }
}
