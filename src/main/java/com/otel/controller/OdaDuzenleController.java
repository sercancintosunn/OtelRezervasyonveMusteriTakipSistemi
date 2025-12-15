package com.otel.controller;

import com.otel.database.OdaDB;
import com.otel.model.Oda;
import com.otel.view.OdaDuzenleFrame;

public class OdaDuzenleController {
    private OdaDuzenleFrame view;
    private OdaDB odadb = new OdaDB();
    public OdaDuzenleController(OdaDuzenleFrame view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getBtnGuncelle().addActionListener(e -> {
            Oda oda = new Oda(view.getOda().getId(),view.getOdaNumarasiGuncelle(),view.getOdaTipiGuncelle(),view.getKapasiteGuncelle(),view.getFiyatGuncelle(),view.getDurumGuncelle());

            odadb.odaGuncelle(oda);
            view.showMessage("Başarıyla Düzenlendi!");
            view.dispose();
        });
    }
}
