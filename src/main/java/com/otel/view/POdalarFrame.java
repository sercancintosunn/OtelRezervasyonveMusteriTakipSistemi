package com.otel.view;

import com.otel.database.OdaDB;
import com.otel.model.Oda;

import javax.swing.*;
import java.awt.*;

public class POdalarFrame extends PBaseMainFrame {

    private OdaDB odaDB = new OdaDB();

    public POdalarFrame(){
        super();
        initContent();
    }

    @Override
    protected void initContent() {
        JPanel odalarContainer = new JPanel();
        odalarContainer.setLayout(new BoxLayout(odalarContainer, BoxLayout.Y_AXIS));
        odalarContainer.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(odalarContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        for (Oda oda : odaDB.tumOdalar()) {
            JPanel odaPanel = new JPanel(new GridLayout(5, 2, 10, 5));
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


            odalarContainer.add(odaPanel);
            odalarContainer.add(Box.createVerticalStrut(10));
        }
        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }
}
