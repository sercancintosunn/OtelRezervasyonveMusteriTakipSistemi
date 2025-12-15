package com.otel.view;

import com.otel.controller.OdaEkleController;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class OdaEkleFrame extends JFrame {
    private OdaEkleController controller;
    private JTextField odaNumarasi;
    private JComboBox<String> odaTipi;
    private JComboBox<Integer> kapasite;
    private JButton btnEkle;
    private JFormattedTextField fiyat;

    public OdaEkleFrame() {
        initUI();
        controller = new OdaEkleController(this);
    }

    private void initUI() {
        setTitle("Oda Ekle");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel contentPanel = new JPanel();

        contentPanel.setLayout(new GridLayout(6,2,15,15));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.add(new JLabel("Oda Numarası: "));
        odaNumarasi = new JTextField();
        contentPanel.add(odaNumarasi);
        contentPanel.add(new JLabel("Oda Tipi:"));
        odaTipi = new JComboBox<String>(new String[]{"STANDART", "AILE", "SUİT"});
        contentPanel.add(odaTipi);
        contentPanel.add(new JLabel("Kapasite:"));
        kapasite = new JComboBox<Integer>(new Integer[]{1,2,3,4,5,6,7,8});
        contentPanel.add(kapasite);
        contentPanel.add(new JLabel("Fiyat:"));
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumFractionDigits(2);

        fiyat = new JFormattedTextField(numberFormat);
        fiyat.setValue(0);
        contentPanel.add(fiyat);

        btnEkle = new JButton("Odayı Ekle");
        contentPanel.add(btnEkle);
        contentPanel.add(new JLabel(""));

        add(contentPanel);
    }

    public String getOdaNumarasi() {
        return odaNumarasi.getText();
    }

    public String getOdaTipi() {
        return odaTipi.getSelectedItem().toString();
    }

    public int getKapasite() {
        return (int) kapasite.getSelectedItem();
    }

    public JButton getBtnEkle() {
        return btnEkle;
    }

    public double getFiyat() {
        return ((Number) fiyat.getValue()).doubleValue();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);}
}
