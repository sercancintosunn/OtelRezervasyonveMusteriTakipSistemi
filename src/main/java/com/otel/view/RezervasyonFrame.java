package com.otel.view;
import com.otel.controller.RezervasyonController;
import com.otel.model.Oda;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class RezervasyonFrame extends JFrame {
    private RezervasyonController controller;
    private Oda oda;
    private JButton btnRezervasyon;
    private JComboBox comboKisiSayisi;
    private JSpinner girisTarihi;
    private JSpinner cikisTarihi;
    private int toplamFiyat;

    public RezervasyonFrame(Oda oda) {
        this.oda = oda;
        initUI();
        controller = new RezervasyonController(this);
    }

    private void initUI() {
        setTitle("Rezervasyon");
        setSize(600,400);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(7,2));
        mainPanel.add(new JLabel("Oda Numarası:"));
        mainPanel.add(new JLabel(oda.getOdaNumarasi()));

        mainPanel.add(new JLabel("Tip:"));
        mainPanel.add(new JLabel(oda.getOdaTipi()));

        Integer[] kisiSayisi = new Integer[oda.getKapasite()];
        for (int i = 0; i < kisiSayisi.length; i++) {
            kisiSayisi[i] = i+1;
        }
        comboKisiSayisi = new JComboBox<Integer>(kisiSayisi);
        mainPanel.add(new JLabel("Kişi Sayısı:"));
        mainPanel.add(comboKisiSayisi);

        girisTarihi = new JSpinner(new SpinnerDateModel());
        girisTarihi.setEditor(new JSpinner.DateEditor(girisTarihi, "dd.MM.yyyy"));
        mainPanel.add(new JLabel("Giriş Tarihi:"));
        mainPanel.add(girisTarihi);

        cikisTarihi = new JSpinner(new SpinnerDateModel());
        cikisTarihi.setEditor(new JSpinner.DateEditor(cikisTarihi, "dd.MM.yyyy"));
        mainPanel.add(new JLabel("Çıkış Tarihi:"));
        mainPanel.add(cikisTarihi);

        JLabel lblFiyatText = new JLabel("Fiyat:");
        JLabel lblFiyatValue = new JLabel(String.valueOf(oda.getFiyat()));
        mainPanel.add(lblFiyatText);
        mainPanel.add(lblFiyatValue);

        comboKisiSayisi.addActionListener(e -> {
            Integer secilenKisi = (Integer) comboKisiSayisi.getSelectedItem();

            if (secilenKisi != null) {
                toplamFiyat = (int) (oda.getFiyat() * secilenKisi);
                lblFiyatValue.setText(String.valueOf(toplamFiyat));
            }
        });

        btnRezervasyon = new JButton("Rezerve Et");
        mainPanel.add(new JLabel(""));
        mainPanel.add(btnRezervasyon);


        add(mainPanel, BorderLayout.CENTER);
    }

    public JButton getBtnRezervasyon(){
        return btnRezervasyon;
    }

    public Date getGirisTarihi(){
        return (Date) girisTarihi.getValue();
    }

    public Date getCikisTarihi(){
        return (Date) cikisTarihi.getValue();
    }

    public int getKisiSayisi(){
        return (int) comboKisiSayisi.getSelectedItem();
    }

    public Oda getOda() {
        return oda;
    }

    public int getToplamFiyat(){
        return toplamFiyat == 0 ? (int) oda.getFiyat() : toplamFiyat;
    }
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
