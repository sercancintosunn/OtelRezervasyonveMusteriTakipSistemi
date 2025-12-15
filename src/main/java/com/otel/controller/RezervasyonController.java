package com.otel.controller;

import com.otel.database.RezervasyonDB;
import com.otel.helper.SessionManager;
import com.otel.model.Rezervasyon;
import com.otel.view.RezervasyonFrame;

import java.sql.Timestamp;
import java.util.Date;

public class RezervasyonController {
    private RezervasyonFrame view;

    public RezervasyonController(RezervasyonFrame view) {
        this.view = view;
        initController();
    }

    private void initController(){
        view.getBtnRezervasyon().addActionListener(e -> {
            view.showMessage("Rezervasyonunuz Başarıyla Oluşturuldu");
            int musteriID = SessionManager.getInstance().getUserId();
            int odaID = view.getOda().getId();
            Rezervasyon rezervasyon = new Rezervasyon(0,musteriID,odaID,view.getGirisTarihi(),view.getGirisTarihi(),view.getKisiSayisi(),view.getToplamFiyat(),"BEKLEMEDE",new Timestamp(System.currentTimeMillis()));
            new RezervasyonDB().rezervasyonEkle(rezervasyon);
            view.dispose();
        });
    }
}
