package com.otel.view;
import com.otel.controller.OdalarController;

import javax.swing.*;
import java.awt.*;
import com.otel.database.OdaDB;
import com.otel.model.Oda;

public class OdalarFrame extends BaseMainFrame {
    private OdalarController controller;
    private OdaDB odaDB = new OdaDB();

    public OdalarFrame() {
        super();
        initContent();
        controller = new OdalarController(this);
    }

    @Override
    protected void initContent() {
        JPanel odalarContainer = new JPanel();
        odalarContainer.setLayout(new BoxLayout(odalarContainer, BoxLayout.Y_AXIS));
        odalarContainer.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(odalarContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        for (Oda oda : odaDB.tumOdalar()) {
            JPanel odaPanel = new JPanel(new GridLayout(6, 2, 10, 5));
            odaPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            odaPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
            odaPanel.setBackground(Color.WHITE);

            odaPanel.add(new JLabel("Oda No:"));
            odaPanel.add(new JLabel(oda.getOdaNumarasi()));

            odaPanel.add(new JLabel("Tip:"));
            odaPanel.add(new JLabel(oda.getOdaTipi()));

            odaPanel.add(new JLabel("Kapasite:"));
            odaPanel.add(new JLabel(String.valueOf(oda.getKapasite())));

            odaPanel.add(new JLabel("Fiyat:"));
            odaPanel.add(new JLabel(String.valueOf(oda.getFiyat())));

            odaPanel.add(new JLabel("Durum:"));
            odaPanel.add(new JLabel(oda.getDurum()));

            JButton btnRezerve = new JButton("Rezerve Et");

            if(oda.getDurum().equals("MUSAİT")){
                btnRezerve.setBackground(Color.BLUE);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnRezerve.setEnabled(true);

                btnRezerve.addActionListener(e-> {
                    controller.rezerveEt(oda);
                });
            }
            else{
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                btnRezerve.setBackground(Color.GRAY);
                btnRezerve.setEnabled(false);
            }

            odaPanel.add(btnRezerve);
            odaPanel.add(new JLabel(""));



            odalarContainer.add(odaPanel);
            odalarContainer.add(Box.createVerticalStrut(10));
        }
        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }
}
