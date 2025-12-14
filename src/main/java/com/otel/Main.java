package com.otel;

import com.otel.database.OdaDB;
import com.otel.helper.SessionManager;
import com.otel.model.Oda;
import com.otel.view.LoginFrame;
import com.otel.view.MainPageFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] arg){

        OdaDB odaDB = new OdaDB();
        for (Oda oda : odaDB.tumOdalar()) {
            System.out.println("Oda No: " + oda.getOdaNumarasi());
            System.out.println("Tip: " + oda.getOdaTipi());
            System.out.println("Kapasite: " + oda.getKapasite());
            System.out.println("Fiyat: " + oda.getFiyat());
            System.out.println("Durum: " + oda.getDurum());
            System.out.println("--------------------");
        }

        SwingUtilities.invokeLater(() -> {
            if (SessionManager.getInstance().getUser() == null) {
                new LoginFrame().setVisible(true);
            } else {
                new MainPageFrame().setVisible(true);
            }
        });

    }
}
