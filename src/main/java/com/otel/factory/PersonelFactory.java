package com.otel.factory;

import com.otel.model.Personel;
import com.otel.model.User;

public class PersonelFactory extends UserFactory{

    @Override
    public User createUser(String tcNo, String ad, String soyad, String email, String telefon, String userName, String sifre) {
        Personel personel = new Personel();
        personel.setTcNo(tcNo);
        personel.setAd(ad);
        personel.setSoyad(soyad);
        personel.setEmail(email);
        personel.setTelefon(telefon);
        personel.setUserName(userName);
        personel.setSifre(sifre);

        System.out.println("Personel oluşturuldu: " + personel.getFullname());
        return personel;
    }
}
