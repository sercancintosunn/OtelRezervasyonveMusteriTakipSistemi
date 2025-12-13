package com.otel.controller;
import com.otel.model.Rezervasyon;
import com.otel.view.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPageController {
    private MainPageFrame view;

    public MainPageController(MainPageFrame view) {
        this.view = view;
    }

    private void initController() {
        view.getBtnProfil().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.dispose();
                new ProfilFrame().setVisible(true);
            }
        });
        view.getBtnGecmisKonaklamalar().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.dispose();
                new GecmisKonaklamalarFrame().setVisible(true);
            }
        });
        view.getBtnOdalar().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.dispose();
                new OdalarFrame().setVisible(true);
            }
        });
        view.getBtnRezervasyon().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.dispose();
                new RezervasyonFrame().setVisible(true);
            }
        });
    }
}
