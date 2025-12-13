package com.otel;

import com.otel.view.LoginFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] arg){
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });

    }
}
