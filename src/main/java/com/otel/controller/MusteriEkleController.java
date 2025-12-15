package com.otel.controller;

import com.otel.database.MusteriDB;
import com.otel.factory.MusteriFactory;
import com.otel.model.Musteri;
import com.otel.view.MusteriEkleFrame;

public class MusteriEkleController {
    private MusteriEkleFrame view;

    public MusteriEkleController(MusteriEkleFrame view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getBtnEkle().addActionListener(e -> {
            MusteriFactory mf = new MusteriFactory();
            Musteri musteri = (Musteri) mf.createUser(view.getTc(),view.getAd(), view.getSoyad(),view.getEmail(), view.getTelefon(),view.getUsername(),"admin");
            MusteriDB musteriDB = new MusteriDB();
            musteriDB.musteriEkle(musteri);
            view.showMessage("Müşteri başarıyla eklendi.");
            view.dispose();
        });
    }
}
