package com.otel.state;

import com.otel.model.Rezervasyon;

public class Iptal implements IRezervasyonState{

    @Override
    public void iptalEt(Rezervasyon rezervasyon) {
        System.out.println("Rezervasyonunuz iptal edilmiş");
    }

    @Override
    public void tamamla(Rezervasyon rezervasyon) {
        System.out.println("İptal edilen rezervasyon tamamlanamaz");
    }

    @Override
    public void onayla(Rezervasyon rezervasyon) {
        System.out.println("İptal edilen rezervasyon onaylanamaz");
    }

    @Override
    public String getDurumAdi() {
        return "IPTAL";
    }
}
