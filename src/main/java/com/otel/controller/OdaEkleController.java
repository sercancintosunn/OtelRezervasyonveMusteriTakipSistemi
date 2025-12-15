package com.otel.controller;

import com.otel.database.OdaDB;
import com.otel.model.Oda;
import com.otel.view.OdaEkleFrame;

public class OdaEkleController {
    private OdaEkleFrame view;

    public OdaEkleController(OdaEkleFrame view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getBtnEkle().addActionListener(e -> {
            OdaDB odadb = new OdaDB();
            Oda oda = new Oda(4,view.getOdaNumarasi(), view.getOdaTipi(), view.getKapasite(), view.getFiyat(),"bhj");
            odadb.odaEkle(oda);
            view.showMessage("Oda Başarıyla Eklendi!");
        });
    }
}
