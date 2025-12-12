package com.otel.model;

import java.security.PublicKey;

public class Oda {
    private int id;
    private String odaNumarasi;
    private String odaTipi;
    private int kapasite;
    private double fiyat;
    private String durum;

    public Oda(){

    }

    public Oda(int id,String odaNumarasi,String odaTipi,int kapasite,double fiyat,String durum){
        this.id = id;
        this.odaNumarasi = odaNumarasi;
        this.odaTipi = odaTipi;
        this.kapasite = kapasite;
        this.fiyat = fiyat;
        this.durum = durum;
    }

    public int getId(){
        return id;
    }

    public String getOdaNumarasi(){
        return odaNumarasi;
    }

    public void setOdaNumarasi(String odaNumarasi){
        this.odaNumarasi = odaNumarasi;
    }

    public String getOdaTipi(){
        return odaTipi;
    }

    public void setOdaTipi(String odaTipi){
        this.odaTipi = odaTipi;
    }

    public int getKapasite(){
        return kapasite;
    }

    public void setKapasite(int kapasite){
        this.kapasite = kapasite;
    }

    public double getFiyat(){
        return fiyat;
    }

    public void setFiyat(double fiyat){
        this.fiyat = fiyat;
    }

    public String getDurum(){
        return durum;
    }

    public void setDurum(String durum){
        this.durum = durum;
    }

    public boolean isMusait(){
        return "Musait".equals(this.durum);
    }

    @Override
    public String toString() {
        return "Oda{" +
                "id=" + id +
                ", odaNumarasi='" + odaNumarasi + '\'' +
                ", odaTipi='" + odaTipi + '\'' +
                ", kapasite=" + kapasite +
                ", fiyat=" + fiyat +
                ", durum='" + durum + '\'' +
                '}';
    }



}
