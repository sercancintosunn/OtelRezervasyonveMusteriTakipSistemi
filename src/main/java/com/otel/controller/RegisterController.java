package com.otel.controller;

import com.otel.view.LoginFrame;
import com.otel.database.MusteriDB;
import com.otel.view.RegisterFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        if (tckn.isEmpty() || password.isEmpty() || ad.isEmpty() || soyad.isEmpty() || telefon.isEmpty() || email.isEmpty()) {
            view.showMessage("Lütfen tüm alanları doldurun.");
            return;
        }
    }

}