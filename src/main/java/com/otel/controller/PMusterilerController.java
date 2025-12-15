package com.otel.controller;

import com.otel.view.MusteriDetayiFrame;
import com.otel.view.MusteriDuzenleFrame;
import com.otel.view.PMusterilerFrame;

public class PMusterilerController {
    private PMusterilerFrame view;

    public PMusterilerController(PMusterilerFrame view) {
        this.view = view;
    }

    public void musteriDetayiAc(int musteriID){
        new MusteriDetayiFrame(musteriID).setVisible(true);
    }

    public void musteriDuzenleAc(int musteriID){
        new MusteriDuzenleFrame(musteriID).setVisible(true);
    }

}
