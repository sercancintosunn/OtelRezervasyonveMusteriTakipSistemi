package com.otel.state;

import com.otel.database.RezervasyonDB;
import com.otel.model.Rezervasyon;

public class Tamamlandi implements IRezervasyonState{

    @Override
    public void onayla(Rezervasyon rezervasyon) {
        System.out.println("Rezervasyon onaylanıyor");
        rezervasyon.setDurum("ONAYLANDI");

        RezervasyonDB rezervasyonDB = new RezervasyonDB();
        rezervasyonDB.rezervasyonDurumGuncelle(rezervasyon.getId(), "ONAYLANDI");
        System.out.println("Rezeervasyonunuz onaylandı");
    }

    @Override
    public void tamamla(Rezervasyon rezervasyon) {
        System.out.println("Rezervasyonunun tamamlanmış");
    }

    @Override
    public void iptalEt(Rezervasyon rezervasyon) {
        System.out.println("Rezervasyonunuz iptal ediliyor");
        rezervasyon.setDurum("IPTAL");

        RezervasyonDB rezervasyonDB = new RezervasyonDB();
        rezervasyonDB.rezervasyonDurumGuncelle(rezervasyon.getId(), "IPTAL");
        System.out.println("Rezervasyonunuz iptal edildi");
    }

    @Override
    public String getDurumAdi() {
        return "TAMAMLANDI";
    }
}
