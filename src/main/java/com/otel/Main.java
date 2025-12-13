package com.otel;

import com.otel.view.LoginFrame;
import com.otel.view.MainPageFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] arg){
        SwingUtilities.invokeLater(() -> {
            new MainPageFrame().setVisible(true);
        });

    }
}
