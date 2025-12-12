package com.otel.model;

public class Personel extends User{

    public Personel(){
        super();
    }

    public Personel(int id,String tcNo,String ad,String soyad,String email,String telefon,String userName,String sifre){
        super(id,tcNo,ad,soyad,email,telefon,userName,sifre);
    }


    @Override
    public String getUserType() {
        return "Personel";
    }

    @Override
    public void displayInfo() {
        System.out.println("ID: " + id);
        System.out.println("Ad Soyad: " + getFullname());
        System.out.println("TC Kimlik: "+ tcNo);
        System.out.println("Email: " + email);
        System.out.println("Telefon: " + telefon);
    }

    @Override
    public String toString(){
        return "Personel{" +
                "id=" + id +
                ", ad='" + ad + '\'' +
                ", soyad='" + soyad + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
