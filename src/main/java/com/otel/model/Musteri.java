package com.otel.model;

import java.sql.Timestamp;

public class Musteri extends User{
    private Timestamp kayitTarihi;

    public Musteri(){
    super();
    }

    public Musteri(int id,String tcNo,String ad,String soyad,String email,String telefon,String userName,String sifre,Timestamp kayitTarihi){
        super(id, tcNo, ad, soyad, email, telefon, userName, sifre);
        this.kayitTarihi = kayitTarihi;
    }

    @Override
    public String getUserType() {
        return "Musteri";
    }

    @Override
    public void displayInfo() {
        System.out.println("Id: " + id);
        System.out.println("Ad Soyad: "  + getFullname());
        System.out.println("TC Kimlik: " + tcNo);
        System.out.println("Email: " + email);
        System.out.println("Telefon: " + telefon);
        System.out.println("Kayıt Tarihi: " + kayitTarihi);
    }

    public Timestamp getKayitTarihi(){
        return kayitTarihi;
    }

    @Override
    public String toString(){
        return "Musteri{" +
                "id=" + id +
                ", ad='" + ad + '\'' +
                ", soyad='" + soyad + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
