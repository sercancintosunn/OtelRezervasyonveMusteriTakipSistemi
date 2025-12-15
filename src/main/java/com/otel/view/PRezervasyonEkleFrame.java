package com.otel.view;

import com.otel.controller.RezervasyonController;
import com.otel.database.OdaDB;
import com.otel.model.Oda;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class PRezervasyonEkleFrame extends JFrame {

    private RezervasyonController controller;
    private Oda oda;

    private JButton btnRezervasyon;
    private JComboBox<Integer> comboKisiSayisi;
    private JComboBox<Integer> comboOdaNumarası;

    private JSpinner girisTarihi;
    private JSpinner cikisTarihi;

    private JLabel lblOdaTipi;
    private JLabel lblFiyatValue;

    private int toplamFiyat;

    private OdaDB odalar = new OdaDB();

    public PRezervasyonEkleFrame() {
        initUI();
    }

    private void initUI() {
        setTitle("Rezervasyon");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(7, 2, 10, 10));

        // ODA NUMARASI
        comboOdaNumarası = new JComboBox<>();
        for (Integer numara : odalar.getOdaNumaraları()) {
            comboOdaNumarası.addItem(numara);
        }

        mainPanel.add(new JLabel("Oda Numarası:"));
        mainPanel.add(comboOdaNumarası);

        // İLK ODA
        oda = odalar.getOdaNumarasi(comboOdaNumarası.getSelectedItem().toString());

        // ODA TİPİ
        lblOdaTipi = new JLabel(oda.getOdaTipi());
        mainPanel.add(new JLabel("Tip:"));
        mainPanel.add(lblOdaTipi);

        // KİŞİ SAYISI
        comboKisiSayisi = new JComboBox<>();
        mainPanel.add(new JLabel("Kişi Sayısı:"));
        mainPanel.add(comboKisiSayisi);
        kisiSayisiGuncelle(oda.getKapasite());

        // GİRİŞ TARİHİ
        girisTarihi = new JSpinner(new SpinnerDateModel());
        girisTarihi.setEditor(new JSpinner.DateEditor(girisTarihi, "dd.MM.yyyy"));
        mainPanel.add(new JLabel("Giriş Tarihi:"));
        mainPanel.add(girisTarihi);

        // ÇIKIŞ TARİHİ
        cikisTarihi = new JSpinner(new SpinnerDateModel());
        cikisTarihi.setEditor(new JSpinner.DateEditor(cikisTarihi, "dd.MM.yyyy"));
        mainPanel.add(new JLabel("Çıkış Tarihi:"));
        mainPanel.add(cikisTarihi);

        // FİYAT
        lblFiyatValue = new JLabel(String.valueOf((int) oda.getFiyat()));
        mainPanel.add(new JLabel("Fiyat:"));
        mainPanel.add(lblFiyatValue);

        // ODA DEĞİŞİNCE
        comboOdaNumarası.addActionListener(e -> {
            oda = odalar.getOdaNumarasi(
                    comboOdaNumarası.getSelectedItem().toString()
            );

            lblOdaTipi.setText(oda.getOdaTipi());
            kisiSayisiGuncelle(oda.getKapasite());
            fiyatGuncelle();
        });

        // DİĞER DEĞİŞİKLİKLER
        girisTarihi.addChangeListener(e -> fiyatGuncelle());
        cikisTarihi.addChangeListener(e -> fiyatGuncelle());
        comboKisiSayisi.addActionListener(e -> fiyatGuncelle());

        // BUTON
        btnRezervasyon = new JButton("Rezerve Et");
        mainPanel.add(new JLabel(""));
        mainPanel.add(btnRezervasyon);

        add(mainPanel, BorderLayout.CENTER);
    }

    // ===== YARDIMCI METODLAR =====

    private void kisiSayisiGuncelle(int kapasite) {
        comboKisiSayisi.removeAllItems();
        for (int i = 1; i <= kapasite; i++) {
            comboKisiSayisi.addItem(i);
        }
    }

    private void fiyatGuncelle() {
        Integer kisi = (Integer) comboKisiSayisi.getSelectedItem();
        if (kisi == null) return;

        int gun = gunSayisiHesaplama(getGirisTarihi(), getCikisTarihi());
        toplamFiyat = (int) (oda.getFiyat() * kisi * gun);

        lblFiyatValue.setText(String.valueOf(toplamFiyat));
    }

    public static int gunSayisiHesaplama(Date giris, Date cikis) {
        long farkMillis = cikis.getTime() - giris.getTime();
        int gun = (int) (farkMillis / (1000 * 60 * 60 * 24));
        return gun <= 0 ? 1 : gun;
    }

    // ===== GETTERLAR =====

    public JButton getBtnRezervasyon() {
        return btnRezervasyon;
    }

    public Date getGirisTarihi() {
        return (Date) girisTarihi.getValue();
    }

    public Date getCikisTarihi() {
        return (Date) cikisTarihi.getValue();
    }

    public int getKisiSayisi() {
        return (int) comboKisiSayisi.getSelectedItem();
    }

    public Oda getOda() {
        return oda;
    }

    public int getToplamFiyat() {
        return toplamFiyat;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}