package com.otel.controller;

import com.otel.view.PRezervasyonEkleFrame;
import com.otel.view.PRezervasyonFrame;

public class PRezervasyonController {
    private PRezervasyonFrame view;

    public PRezervasyonController(PRezervasyonFrame view) {
        this.view = view;
        initController();
    }
    private void initController() {
        view.getBtnRezervasyonEkle().addActionListener(e -> {
            new PRezervasyonEkleFrame().setVisible(true);
        });
    }
}
