package com.otel.state;

import com.otel.database.RezervasyonDB;
import com.otel.model.Rezervasyon;

public class Onaylandi implements IRezervasyonState{

    @Override
    public void iptalEt(Rezervasyon rezervasyon) {
        System.out.println("REzervasyon iptal ediliyor.");
        rezervasyon.setDurum("IPTAL");

        RezervasyonDB rezervasyonDB = new RezervasyonDB();
        rezervasyonDB.rezervasyonDurumGuncelle(rezervasyon.getId(),"IPTAL");
        System.out.println("Rezervasyon iptal edildi");
    }

    @Override
    public void tamamla(Rezervasyon rezervasyon) {
        System.out.println("Rezervasyon tamamlanıyor");
        rezervasyon.setDurum("TAMAMLANDI");

        RezervasyonDB rezervasyonDB = new RezervasyonDB();
        rezervasyonDB.rezervasyonDurumGuncelle(rezervasyon.getId(),"TAMAMLANDI");
        System.out.println("Rezervasyon tamamlandı");
    }

    @Override
    public void onayla(Rezervasyon rezervasyon) {
        System.out.println("Rezervasyon zaten onaylı");
    }

    @Override
    public String getDurumAdi() {
        return "ONAYLANDI";
    }
}
