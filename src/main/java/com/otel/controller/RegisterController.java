package com.otel.controller;

import com.otel.factory.MusteriFactory;
import com.otel.model.Musteri;
import com.otel.view.LoginFrame;
import com.otel.database.MusteriDB;
import com.otel.view.MainPageFrame;
import com.otel.view.RegisterFrame;
import com.otel.helper.SessionManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;

public class RegisterController {

    private RegisterFrame view;
    private MusteriDB musteriDB;

    public RegisterController(RegisterFrame view) {
        this.view = view;
        this.musteriDB = new MusteriDB();
        initController();
    }

    private void initController() {
        view.getBtnKaydol().addActionListener(e -> kaydol());

        view.getBtnLogin().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.dispose();
                new LoginFrame().setVisible(true);
            }
        });
    }

    public void kaydol() {
        String tckn = view.getTCKN();
        String password = view.getPassword();
        String ad = view.getAd();
        String soyad = view.getSoyad();
        String telefon = view.getTelefon();
        String email = view.getEmail();
        String username = view.getUsername();

        if (tckn.isEmpty() || password.isEmpty() || ad.isEmpty() || soyad.isEmpty() || telefon.isEmpty() || email.isEmpty()) {
            view.showMessage("Lütfen tüm alanları doldurun.");
            return;
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        MusteriFactory mf = new MusteriFactory();
        Musteri musteri = (Musteri) mf.createUser(tckn,ad,soyad,email,telefon, username, password);
        musteriDB.musteriEkle(musteri);
        view.showMessage("Kayıt Başarılı!");
        view.dispose();
        SessionManager session = SessionManager.getInstance();
        session.login(musteri);
        new MainPageFrame().setVisible(true);
    }

}