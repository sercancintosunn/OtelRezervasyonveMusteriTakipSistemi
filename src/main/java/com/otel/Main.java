package com.otel;

import com.otel.database.OdaDB;
import com.otel.helper.SessionManager;
import com.otel.model.Oda;
import com.otel.view.LoginFrame;
import com.otel.view.MainPageFrame;
import com.otel.view.OdalarFrame;
import com.otel.view.PRezervasyonFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] arg){

        SwingUtilities.invokeLater(() -> {
            OdaDB oda = new OdaDB();
            System.out.println(oda.getOdaNumaraları());
            new PRezervasyonFrame().setVisible(true);
        });

    }
}
