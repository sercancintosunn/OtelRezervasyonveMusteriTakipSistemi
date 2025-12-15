package com.otel;

import com.otel.database.OdaDB;
import com.otel.helper.SessionManager;
import com.otel.model.Oda;
import com.otel.view.LoginFrame;
import com.otel.view.MainPageFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] arg){

        SwingUtilities.invokeLater(() -> {
            if (SessionManager.getInstance().getUser() == null) {
                new LoginFrame().setVisible(true);
            } else {
                new MainPageFrame().setVisible(true);
            }
        });

    }
}
