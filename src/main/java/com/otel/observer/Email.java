package com.otel.observer;

import com.otel.model.Rezervasyon;

public class Email implements IRezervasyonObserver{

    @Override
    public void update(Rezervasyon rezervasyon, String islem) {
        String musteriMail = rezervasyon.getMusteri() != null ? rezervasyon.getMusteri().getEmail() : "musteri@email.com";

        String mesaj = mesajOlustur(rezervasyon,islem);

        System.out.println("Email Gönderildi:");
        System.out.println("Alıcı: " + musteriMail);
        System.out.println("Konu: Rezervasyon " + islem);
        System.out.println("Mesaj: " + mesaj);

    }

    public String mesajOlustur(Rezervasyon rezervasyon,String islem){
        String musteriAd = rezervasyon.getMusteri() !=null ? rezervasyon.getMusteri().getFullname() : "Sayı Müşterimiz";

        switch (islem.toUpperCase()){
            case "ONAYLANDI":
                return "Sayın " + musteriAd + "rezervasyonunuz onaylanmıştır";

            case "IPTAL":
                return "Sayın " + musteriAd + "rezervasyonunuz iptal edilmiştir";

            case "TAMAMLANDI":
                return "Sayın " + musteriAd + "rezervasyonunuz  tamamlanmıştır.";

            default:
                return "Rezervasyonunuz oluşturulduı";
        }

    }
}
