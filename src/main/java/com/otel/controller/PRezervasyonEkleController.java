package com.otel.controller;

import com.otel.database.MusteriDB;
import com.otel.database.RezervasyonDB;
import com.otel.model.Rezervasyon;
import com.otel.view.PRezervasyonEkleFrame;

import java.sql.Timestamp;

public class PRezervasyonEkleController {
    private PRezervasyonEkleFrame view;

    public PRezervasyonEkleController(PRezervasyonEkleFrame view) {
        this.view = view;
        initController();
    }

    private void initController(){
        view.getBtnRezervasyon().addActionListener(e -> {
            view.showMessage("Rezervasyonunuz Başarıyla Oluşturuldu");

            // Müşteri ID'sini TC'den bul
            int musteriID = new MusteriDB().getMusteri(view.getMusteriTC()).getId();
            int odaID = view.getOda().getId();

            // --- HATA DÜZELTMESİ BURADA YAPILDI ---
            // Eksik olan 'odemeYontemi' parametresi (String) eklendi.
            Rezervasyon rezervasyon = new Rezervasyon(
                    0,
                    musteriID,
                    odaID,
                    view.getGirisTarihi(),
                    view.getCikisTarihi(),
                    view.getKisiSayisi(),
                    view.getToplamFiyat(),
                    "BEKLEMEDE",
                    "NAKIT", // <--- EKLENEN KISIM: Ödeme yöntemi stringi
                    new Timestamp(System.currentTimeMillis())
            );

            new RezervasyonDB().rezervasyonEkle(rezervasyon);
            view.dispose();
        });
    }
}