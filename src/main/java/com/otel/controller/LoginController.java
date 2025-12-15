package com.otel.controller;

import com.otel.database.PersonelDB;
import com.otel.helper.SessionManager;
import com.otel.view.LoginFrame;
import com.otel.database.MusteriDB;
import com.otel.view.MainPageFrame;
import com.otel.view.PRezervasyonFrame;
import com.otel.view.RegisterFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class LoginController {

    private LoginFrame view;
    private MusteriDB musteriDB;
    private PersonelDB personelDB;
    public LoginController(LoginFrame view) {
        this.view = view;
        this.musteriDB = new MusteriDB();
        this.personelDB = new PersonelDB();
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
            view.dispose();
            SessionManager session = SessionManager.getInstance();
            session.login(musteriDB.login(tckn, password));
            new MainPageFrame().setVisible(true);
        } else if (personelDB.login(tckn,password) != null) {
            view.showMessage("Giriş başarılı!");
            view.dispose();
            SessionManager session = SessionManager.getInstance();
            session.login(personelDB.login(tckn, password));
            new PRezervasyonFrame().setVisible(true);
        }
        else {
            view.showMessage("Kullanıcı adı veya şifre hatalı!");
        }
    }

}