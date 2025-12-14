package com.otel.controller;
import com.otel.database.MusteriDB;
import com.otel.helper.SessionManager;
import com.otel.view.ProfilFrame;
public class ProfilController {
    private ProfilFrame view;

    public ProfilController(ProfilFrame view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getBtnGuncelle().addActionListener(e -> {
            int id = SessionManager.getInstance().getUserId();
            String telefon = view.getTelefonGuncelle();
            String email = view.getEmailGuncelle();
            String sifre = view.getSifreGuncelle();
            String username = view.getUsernameGuncelle();
            if(telefon.equals("") || email.equals("") || sifre.equals("") || username.equals("")) {
                view.showMessage("Lütfen tüm alanları doldurunuz");
                return;
            }
            else{
                new MusteriDB().musteriBilgileriGuncelle(id, username, email, telefon, sifre);
                view.showMessage("Bilgileriniz başarıyla güncellendi!");
                return;
            }
        });
    }
}
