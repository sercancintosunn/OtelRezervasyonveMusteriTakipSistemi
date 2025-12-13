package com.otel.model;

import java.sql.Timestamp;
import java.util.Date;

public class Rezervasyon {

    private int id;
    private int musteriId;
    private int odaId;
    private Date girisTarihi;
    private Date cikisTarihi;
    private int kisiSayisi;
    private double toplamFiyat;
    private String durum;
    private Timestamp oluşturmaTarihi;

    private Musteri musteri;
    private Oda oda;

    public Rezervasyon(){

    }

    public Rezervasyon(int id,int musteriId,int odaId,Date girisTarihi,Date cikisTarihi,int kisiSayisi,
                       double toplamFiyat,String durum,Timestamp oluşturmaTarihi){
        this.id = id;
        this.musteriId = musteriId;
        this.odaId = odaId;
        this.girisTarihi = girisTarihi;
        this.cikisTarihi = cikisTarihi;
        this.kisiSayisi = kisiSayisi;
        this.toplamFiyat = toplamFiyat;
        this.durum = durum;
        this.oluşturmaTarihi = oluşturmaTarihi;
    }

    public int getId(){
        return  id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getOdaId() {
        return odaId;
    }

    public void setOdaId(int odaId) {
        this.odaId = odaId;
    }

    public int getMusteriId() {
        return musteriId;
    }

    public void setMusteriId(int musteriId) {
        this.musteriId = musteriId;
    }

    public Date getGirisTarihi() {
        return girisTarihi;
    }

    public void setGirisTarihi(Date girisTarihi) {
        this.girisTarihi = girisTarihi;
    }

    public Date getCikisTarihi() {
        return cikisTarihi;
    }

    public void setCikisTarihi(Date cikisTarihi) {
        this.cikisTarihi = cikisTarihi;
    }

    public int getKisiSayisi() {
        return kisiSayisi;
    }

    public void setKisiSayisi(int kisiSayisi) {
        this.kisiSayisi = kisiSayisi;
    }

    public double getToplamFiyat() {
        return toplamFiyat;
    }

    public void setToplamFiyat(double toplamFiyat) {
        this.toplamFiyat = toplamFiyat;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public Timestamp getOluşturmaTarihi() {
        return oluşturmaTarihi;
    }

    public void setOluşturmaTarihi(Timestamp oluşturmaTarihi) {
        this.oluşturmaTarihi = oluşturmaTarihi;
    }

    public Musteri getMusteri() {
        return musteri;
    }


    public Oda getOda() {
        return oda;
    }

    public int getKonaklamaSüresi(){
        long gunFarki = cikisTarihi.getTime() - girisTarihi.getTime();
        return (int) (gunFarki / (1000*60*60*24));
    }

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
    }

    public void setOda(Oda oda) {
        this.oda = oda;
    }

    @Override
    public String toString() {
        return "Rezervasyon{" +
                "id=" + id +
                ", musteriId=" + musteriId +
                ", odaId=" + odaId +
                ", girisTarihi=" + girisTarihi +
                ", cikisTarihi=" + cikisTarihi +
                ", kisiSayisi=" + kisiSayisi +
                ", toplamFiyat=" + toplamFiyat +
                ", durum='" + durum + '\'' +
                '}';
    }



}
