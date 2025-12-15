package com.otel.view;
import com.otel.controller.RezervasyonlarımController;
import com.otel.database.OdaDB;
import com.otel.database.RezervasyonDB;
import com.otel.model.Oda;
import com.otel.model.Rezervasyon;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RezervasyonlarımFrame extends BaseMainFrame {
    private RezervasyonlarımController controller;

    public RezervasyonlarımFrame(){
        super();
        initContent();
        controller = new RezervasyonlarımController(this);
    }

    @Override
    protected void initContent() {
        JPanel odalarContainer = new JPanel();
        odalarContainer.setLayout(new BoxLayout(odalarContainer, BoxLayout.Y_AXIS));
        odalarContainer.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(odalarContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        List<Rezervasyon> tumRezervasyonlar = new RezervasyonDB().tumRezervasyonlar();
        for(Rezervasyon r : tumRezervasyonlar){
            Oda oda = new OdaDB().getOda(r.getOdaId());

            JPanel rezervasyonPanel = new JPanel(new GridLayout(7, 2, 10, 5));
            rezervasyonPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            rezervasyonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
            rezervasyonPanel.setBackground(Color.WHITE);

            rezervasyonPanel.add(new JLabel("Oda No:"));
            rezervasyonPanel.add(new JLabel(oda.getOdaNumarasi()));

            rezervasyonPanel.add(new JLabel("Tip:"));
            rezervasyonPanel.add(new JLabel(oda.getOdaTipi()));

            rezervasyonPanel.add(new JLabel("Fiyat:"));
            rezervasyonPanel.add(new JLabel(String.valueOf(oda.getFiyat())));

            rezervasyonPanel.add(new JLabel("Kişi Sayısı:"));
            rezervasyonPanel.add(new JLabel(String.valueOf(r.getKisiSayisi())));

            rezervasyonPanel.add(new JLabel("Giriş Tarihi:"));
            rezervasyonPanel.add(new JLabel(String.valueOf(r.getGirisTarihi())));

            rezervasyonPanel.add(new JLabel("Çıkış Tarihi:"));
            rezervasyonPanel.add(new JLabel(String.valueOf(r.getGirisTarihi())));

            rezervasyonPanel.add(new JLabel("Durum:"));
            rezervasyonPanel.add(new JLabel(String.valueOf(r.getDurum())));

            odalarContainer.add(rezervasyonPanel);
            odalarContainer.add(Box.createVerticalStrut(10));
        }

        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }


}
