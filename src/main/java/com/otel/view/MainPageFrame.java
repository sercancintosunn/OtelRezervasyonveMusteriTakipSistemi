package com.otel.view;
import javax.swing.*;
import java.awt.*;
import com.otel.controller.MainPageController;
import com.otel.helper.SessionManager;

    public class MainPageFrame extends BaseMainFrame {

        public MainPageFrame() {
            super();
            initContent();
        }

        @Override
        protected void initContent() {
            JLabel label = new JLabel("Ana Sayfa İçeriği");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(label, BorderLayout.CENTER);
        }
    }

