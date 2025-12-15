package com.otel.controller;

import com.otel.database.OdaDB;
import com.otel.database.RezervasyonDB;
import com.otel.model.Oda;
import com.otel.model.Rezervasyon;
import com.otel.view.OdalarFrame;
import com.otel.view.RezervasyonFrame;

import javax.swing.*;

public class OdalarController {
    private OdalarFrame view;
    public OdalarController(OdalarFrame view) {
        this.view = view;
    }

    public void rezerveEt(Oda oda){
        new RezervasyonFrame(oda).setVisible(true);
    }
}
