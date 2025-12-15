package com.otel.view;

import com.otel.database.OdaDB;
import com.otel.database.RezervasyonDB;
import com.otel.model.Oda;
import com.otel.model.Rezervasyon;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MusteriDetayiFrame extends JFrame {
    private int musteriID;

    public MusteriDetayiFrame(int musteriID) {
        this.musteriID = musteriID;
        initUI();
    }

    private void initUI(){
        setTitle("Rezervasyon");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        RezervasyonDB rezervasyonDB = new RezervasyonDB();

        List<Rezervasyon> list = rezervasyonDB.musteriRezervasyonlari(musteriID);;

        JPanel listePanel = new JPanel();
        listePanel.setLayout(new BoxLayout(listePanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(listePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        listePanel.add(new JLabel("Rezervasyonları:"));
        listePanel.add(new JLabel(""));
        for(Rezervasyon r : list){
            JPanel rezervasyonPanel = new JPanel(new GridLayout(7,2,10,10));
            if(r.getDurum().equals("TAMAMLANDI")){
                continue;
            }
            Oda oda = new OdaDB().getOda(r.getOdaId());
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
            listePanel.add(rezervasyonPanel);
            listePanel.add(Box.createVerticalStrut(15));
        }





        listePanel.add(new JLabel("Geçmiş Konaklamaları:"));
        listePanel.add(new JLabel(""));

        for(Rezervasyon r : list){
            JPanel gecmisKonaklamalarPanel = new JPanel(new GridLayout(8,2,10,10));


            if(r.getDurum().equals("TAMAMLANDI")){
                Oda oda = r.getOda();
                gecmisKonaklamalarPanel.add(new JLabel("Oda No:"));
                gecmisKonaklamalarPanel.add(new JLabel(oda.getOdaNumarasi()));

                gecmisKonaklamalarPanel.add(new JLabel("Tip:"));
                gecmisKonaklamalarPanel.add(new JLabel(oda.getOdaTipi()));

                gecmisKonaklamalarPanel.add(new JLabel("Fiyat:"));
                gecmisKonaklamalarPanel.add(new JLabel(String.valueOf(r.getToplamFiyat())));

                gecmisKonaklamalarPanel.add(new JLabel("Kişi Sayısı:"));
                gecmisKonaklamalarPanel.add(new JLabel(String.valueOf(r.getKisiSayisi())));

                gecmisKonaklamalarPanel.add(new JLabel("Giriş Tarihi:"));
                gecmisKonaklamalarPanel.add(new JLabel(String.valueOf(r.getGirisTarihi())));

                gecmisKonaklamalarPanel.add(new JLabel("Çıkış Tarihi:"));
                gecmisKonaklamalarPanel.add(new JLabel(String.valueOf(r.getCikisTarihi())));

                gecmisKonaklamalarPanel.add(new JLabel("Durum:"));
                gecmisKonaklamalarPanel.add(new JLabel(String.valueOf(r.getDurum())));
            }

            listePanel.add(gecmisKonaklamalarPanel);
            listePanel.add(Box.createVerticalStrut(15));
        }

        add(scrollPane, BorderLayout.CENTER);


    }

}
