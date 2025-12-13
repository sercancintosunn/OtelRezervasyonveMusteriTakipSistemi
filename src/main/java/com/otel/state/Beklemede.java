package com.otel.state;

import com.otel.database.RezervasyonDB;
import com.otel.model.Rezervasyon;

import java.sql.SQLOutput;

public class Beklemede implements IRezervasyonState{

    @Override
    public void onayla(Rezervasyon rezervasyon) {
        System.out.println("Rezervasyon onaylanıyor");
        rezervasyon.setDurum("ONAYLANDI");

        RezervasyonDB rezervasyonDB = new RezervasyonDB();
        rezervasyonDB.rezervasyonDurumGuncelle(rezervasyon.getId(), "ONAYLANDI");

        System.out.println("Rezervasyonunuz onaylandı");
    }

    @Override
    public void tamamla(Rezervasyon rezervasyon) {
        System.out.println("Rezervasyon onaylanmalı.");
    }

    @Override
    public void iptalEt(Rezervasyon rezervasyon) {
        System.out.println("Rezervasyon iptal ediliyor");
        rezervasyon.setDurum("IPTAL");

        RezervasyonDB rezervasyonDB = new RezervasyonDB();
        rezervasyonDB.rezervasyonDurumGuncelle(rezervasyon.getId(),"IPTAL");
        System.out.println("Rezervasyonunuz iptal edildi.");
    }

    @Override
    public String getDurumAdi() {
        return "BEKLEMEDE";
    }
}
