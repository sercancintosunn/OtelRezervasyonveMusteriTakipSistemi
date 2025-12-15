package com.otel.controller;

import com.otel.database.MusteriDB;
import com.otel.helper.SessionManager;
import com.otel.view.MusteriDuzenleFrame;
import com.otel.view.PMusterilerFrame;

public class MusteriDuzenleController {
    private MusteriDuzenleFrame view;

    public MusteriDuzenleController(MusteriDuzenleFrame view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getBtnGuncelle().addActionListener(e -> {
            String telefon = view.getTelefonGuncelle();
            String email = view.getEmailGuncelle();
            String username = view.getUsernameGuncelle();
            if(telefon.equals("") || email.equals("") ||  username.equals("")) {
                view.showMessage("Lütfen tüm alanları doldurunuz");
                return;
            }
            else{
                new MusteriDB().musteriBilgileriGuncelle(view.getMusteriID(), username, email, telefon);
                view.showMessage("Bilgiler başarıyla güncellendi!");
                view.dispose();
            }
        });
    }
}
