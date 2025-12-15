package com.otel.view;

import com.otel.controller.PRezervasyonController;
import com.otel.database.MusteriDB;
import com.otel.database.OdaDB;
import com.otel.database.RezervasyonDB;
import com.otel.model.Musteri;
import com.otel.model.Oda;
import com.otel.model.Rezervasyon;
import com.otel.observer.Email;
import com.otel.observer.RezervasyonSubject;
import com.otel.state.IRezervasyonState;
import com.otel.state.RezervasyonFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PRezervasyonFrame extends PBaseMainFrame {
    private JButton btnIptal;
    private JButton btnOnayla;
    private JButton btnRezervasyonEkle;
    private PRezervasyonController controller;

    public PRezervasyonFrame(){
        initContent();
        controller = new PRezervasyonController(this);
    }

    @Override
    protected void initContent() {
        JPanel rezervasyonContainer = new JPanel();
        rezervasyonContainer.setLayout(new BoxLayout(rezervasyonContainer, BoxLayout.Y_AXIS));
        rezervasyonContainer.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(rezervasyonContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        List<Rezervasyon> tumRezervasyonlar = new RezervasyonDB().tumRezervasyonlar();
        btnRezervasyonEkle = new JButton("Rezervasyon Ekle");
        rezervasyonContainer.add(btnRezervasyonEkle);


        RezervasyonSubject rezervasyonSubject = new RezervasyonSubject();
        rezervasyonSubject.ekle(new Email());

        for(Rezervasyon r : tumRezervasyonlar){
            Oda oda = new OdaDB().getOda(r.getOdaId());

            JPanel rezervasyonPanel = new JPanel(new GridLayout(10, 2, 10, 5));
            rezervasyonPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            rezervasyonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
            rezervasyonPanel.setBackground(Color.WHITE);

            MusteriDB musteriDB = new MusteriDB();
            Musteri musteri = musteriDB.getMusteri(r.getMusteriId());

            rezervasyonPanel.add(new JLabel("Müşteri:"));
            rezervasyonPanel.add(new JLabel(musteri.getFullname()));

            rezervasyonPanel.add(new JLabel("TC No:"));
            rezervasyonPanel.add(new JLabel(musteri.getTcNo()));

            rezervasyonPanel.add(new JLabel("Oda No:"));
            rezervasyonPanel.add(new JLabel(oda.getOdaNumarasi()));

            rezervasyonPanel.add(new JLabel("Tip:"));
            rezervasyonPanel.add(new JLabel(oda.getOdaTipi()));

            rezervasyonPanel.add(new JLabel("Fiyat:"));
            rezervasyonPanel.add(new JLabel(String.valueOf(r.getToplamFiyat())));

            rezervasyonPanel.add(new JLabel("Kişi Sayısı:"));
            rezervasyonPanel.add(new JLabel(String.valueOf(r.getKisiSayisi())));

            rezervasyonPanel.add(new JLabel("Giriş Tarihi:"));
            rezervasyonPanel.add(new JLabel(String.valueOf(r.getGirisTarihi())));

            rezervasyonPanel.add(new JLabel("Çıkış Tarihi:"));
            rezervasyonPanel.add(new JLabel(String.valueOf(r.getCikisTarihi())));

            rezervasyonPanel.add(new JLabel("Durum:"));
            rezervasyonPanel.add(new JLabel(String.valueOf(r.getDurum())));

            if(String.valueOf(r.getDurum()).equals("BEKLEMEDE")){
                btnIptal = new JButton("İptal Et");
                btnOnayla = new JButton("Onayla");
                btnOnayla.setBackground(Color.GREEN);
                btnOnayla.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnIptal.setBackground(Color.RED);
                btnIptal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                rezervasyonPanel.add(btnOnayla);
                rezervasyonPanel.add(btnIptal);


                btnIptal.addActionListener(e -> {

                    r.setMusteri(musteri);
                    r.setOda(oda);


                    IRezervasyonState durumBeklemede = RezervasyonFactory.getState("BEKLEMEDE");
                    durumBeklemede.iptalEt(r);


                    rezervasyonSubject.bilgiObserver(r, "IPTAL");


                    showMessage("Rezervasyon başarıyla iptal edilmiştir!");
                    dispose();
                    new PRezervasyonFrame().setVisible(true);
                });


                btnOnayla.addActionListener(e -> {

                    r.setMusteri(musteri);
                    r.setOda(oda);


                    IRezervasyonState durumBeklemede = RezervasyonFactory.getState("BEKLEMEDE");
                    durumBeklemede.onayla(r);


                    rezervasyonSubject.bilgiObserver(r, "ONAYLANDI");


                    showMessage("Rezervasyon başarıyla onaylanmıştır!");
                    dispose();
                    new PRezervasyonFrame().setVisible(true);
                });
            }

            rezervasyonContainer.add(rezervasyonPanel);
            rezervasyonContainer.add(Box.createVerticalStrut(10));
        }

        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }

    public JButton getBtnRezervasyonEkle() {
        return btnRezervasyonEkle;
    }
}