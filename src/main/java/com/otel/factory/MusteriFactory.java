package com.otel.factory;

import com.otel.model.Musteri;
import com.otel.model.User;

public class MusteriFactory extends UserFactory {

    @Override
    public User createUser(String tcNo, String ad, String soyad, String email, String telefon, String userName, String sifre) {
        Musteri musteri = new Musteri();
        musteri.setTcNo(tcNo);
        musteri.setAd(ad);
        musteri.setSoyad(soyad);
        musteri.setEmail(email);
        musteri.setTelefon(telefon);
        musteri.setUserName(userName);
        musteri.setSifre(sifre);

        System.out.println("Müşteri oluşturuldu: " + musteri.getFullname());
        return musteri;
    }
}
