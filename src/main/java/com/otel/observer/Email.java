package com.otel.observer;

import com.otel.model.Rezervasyon;
import javax.swing.JOptionPane;

public class Email implements IRezervasyonObserver{

    @Override
    public void update(Rezervasyon rezervasyon, String islem) {
        String musteriMail = rezervasyon.getMusteri() != null ? rezervasyon.getMusteri().getEmail() : "musteri@email.com";
        String musteriAd = rezervasyon.getMusteri() != null ? rezervasyon.getMusteri().getFullname() : "Sayın Müşteri";

        String konu = "Otel Rezervasyon Bildirimi: " + islem;
        String mesaj = mesajOlustur(rezervasyon, islem);


        System.out.println("Email Gönderim Başladı...");
        System.out.println("Kime: " + musteriMail);
        System.out.println("Konu: " + konu);
        System.out.println("İçerik: " + mesaj);


        String ekranMesaji = "EMAIL GÖNDERİLDİ \n\n" +
                "Kime: " + musteriMail + "\n" +
                "Konu: " + konu + "\n" +
                "Mesaj: " + mesaj;

        JOptionPane.showMessageDialog(null, ekranMesaji, "Email Servisi", JOptionPane.INFORMATION_MESSAGE);
    }

    public String mesajOlustur(Rezervasyon rezervasyon, String islem){
        String musteriAd = rezervasyon.getMusteri() != null ? rezervasyon.getMusteri().getFullname() : "Sayın Müşterimiz";


        String giris = rezervasyon.getGirisTarihi() != null ? rezervasyon.getGirisTarihi().toString() : "...";
        String cikis = rezervasyon.getCikisTarihi() != null ? rezervasyon.getCikisTarihi().toString() : "...";

        switch (islem.toUpperCase()){
            case "ONAYLANDI":
                return "Sayın " + musteriAd + ",\n" +
                        giris + " - " + cikis + " tarihli rezervasyonunuz onaylanmıştır.\n" +
                        "Bizi tercih ettiğiniz için teşekkür ederiz.";

            case "IPTAL":
                return "Sayın " + musteriAd + ",\n" +
                        "Rezervasyonunuz üzülerek iptal edilmiştir.\n" +
                        "Bir sorun olduğunu düşünüyorsanız lütfen resepsiyonla iletişime geçin.";

            case "TAMAMLANDI":
                return "Sayın " + musteriAd + ",\n" +
                        "Konaklamanız tamamlanmıştır.\n" +
                        "Umarız memnun kalmışsınızdır. İyi günler dileriz.";

            default:
                return "Rezervasyon durumunuz güncellendi: " + islem;
        }
    }
}