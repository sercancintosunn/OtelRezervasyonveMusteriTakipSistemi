package com.otel.view;

import com.otel.controller.OdaDuzenleController;
import com.otel.database.MusteriDB;
import com.otel.model.Musteri;
import com.otel.model.Oda;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class OdaDuzenleFrame extends JFrame {
    private Oda oda;
    private OdaDuzenleController controller;
    private JTextField odaNumarasiGuncelle;
    private JComboBox<String> odaTipiGuncelle;
    private JComboBox<Integer> kapasiteGuncelle;
    private JButton btnGuncelle;
    private JComboBox<String> durumGuncelle;
    private JFormattedTextField fiyatGuncelle;

    public OdaDuzenleFrame(Oda oda) {
        this.oda = oda;
        initUI();
        controller = new OdaDuzenleController(this);
    }

    private void initUI() {
        setTitle("Oda Düzenle");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel contentPanel = new JPanel();

        contentPanel.setLayout(new GridLayout(6,2,15,15));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.add(new JLabel("Oda Numarası: "));
        odaNumarasiGuncelle = new JTextField(oda.getOdaNumarasi());
        contentPanel.add(odaNumarasiGuncelle);
        contentPanel.add(new JLabel("Oda Tipi:"));
        odaTipiGuncelle = new JComboBox<String>(new String[]{"STANDART", "AILE", "SUİT"});
        contentPanel.add(odaTipiGuncelle);
        contentPanel.add(new JLabel("Kapasite:"));
        kapasiteGuncelle = new JComboBox<Integer>(new Integer[]{1,2,3,4,5,6,7,8});
        contentPanel.add(kapasiteGuncelle);
        contentPanel.add(new JLabel("Fiyat:"));
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumFractionDigits(2);

        fiyatGuncelle = new JFormattedTextField(numberFormat);
        fiyatGuncelle.setValue(oda.getFiyat());
        contentPanel.add(fiyatGuncelle);
        contentPanel.add(new JLabel("Durum:"));
        durumGuncelle = new JComboBox<String>(new String[]{"MUSAİT","DOLU"});
        contentPanel.add(durumGuncelle);

        btnGuncelle = new JButton("Bilgileri Güncelle");
        contentPanel.add(btnGuncelle);
        contentPanel.add(new JLabel(""));

        add(contentPanel);
    }

    public Oda getOda() {
        return oda;
    }

    public String getOdaNumarasiGuncelle() {
        return odaNumarasiGuncelle.getText();
    }

    public String getOdaTipiGuncelle() {
        return (String) odaTipiGuncelle.getSelectedItem();
    }

    public int getKapasiteGuncelle() {
        return (int) kapasiteGuncelle.getSelectedItem();
    }

    public JButton getBtnGuncelle() {
        return btnGuncelle;
    }

    public String getDurumGuncelle() {
        return (String) durumGuncelle.getSelectedItem();
    }

    public double getFiyatGuncelle() {
        return ((Number) fiyatGuncelle.getValue()).doubleValue();
    }
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);}
}
