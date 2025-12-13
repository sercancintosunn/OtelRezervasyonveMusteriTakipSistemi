package com.otel.controller;

import com.otel.view.LoginFrame;
import com.otel.database.MusteriDB;
import com.otel.view.RegisterFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class LoginController {

    private LoginFrame view;
    private MusteriDB musteriDB;

    public LoginController(LoginFrame view) {
        this.view = view;
        this.musteriDB = new MusteriDB();
        initController();
    }

    private void initController() {
        view.getBtnLogin().addActionListener(e -> login());

        view.getBtnKaydol().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.dispose();
                new RegisterFrame().setVisible(true);
            }
        });
    }

    public void login() {
        String tckn = view.getTCKN();
        String password = view.getPassword();

        if (tckn.isEmpty() || password.isEmpty()) {
            view.showMessage("Lütfen tüm alanları doldurun.");
            return;
        }

        if (musteriDB.login(tckn,password) != null) {
            view.showMessage("Giriş başarılı!");
            // new MainFrame().setVisible(true);
        } else {
            view.showMessage("Kullanıcı adı veya şifre hatalı!");
        }
    }

}