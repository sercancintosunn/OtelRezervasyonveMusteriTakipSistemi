package com.otel.controller;

import com.otel.database.MusteriDB;
import com.otel.database.RezervasyonDB;
import com.otel.helper.SessionManager;
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
            int musteriID = new MusteriDB().getMusteri(view.getMusteriTC()).getId();
            int odaID = view.getOda().getId();
            Rezervasyon rezervasyon = new Rezervasyon(0,musteriID,odaID,view.getGirisTarihi(),view.getCikisTarihi(),view.getKisiSayisi(),view.getToplamFiyat(),"BEKLEMEDE",new Timestamp(System.currentTimeMillis()));
            new RezervasyonDB().rezervasyonEkle(rezervasyon);
            view.dispose();
        });
    }
}
