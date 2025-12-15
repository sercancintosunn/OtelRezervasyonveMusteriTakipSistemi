package com.otel.view;

import com.otel.controller.MusteriEkleController;
import com.otel.controller.OdaEkleController;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.EnumMap;

public class MusteriEkleFrame extends JFrame {
    private MusteriEkleController controller;
    private JTextField ad;
    private JTextField soyad;
    private JTextField tc;
    private JTextField telefon;
    private JTextField email;
    private JTextField username;

    private JButton btnEkle;

    public MusteriEkleFrame() {
        initUI();
        controller = new MusteriEkleController(this);
    }

    private void initUI() {
        setTitle("Müşteri Ekle");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel contentPanel = new JPanel();

        contentPanel.setLayout(new GridLayout(7,2,15,15));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.add(new JLabel("Ad: "));
        ad = new JTextField();
        contentPanel.add(ad);
        contentPanel.add(new JLabel("Soyad:"));
        soyad = new JTextField();
        contentPanel.add(soyad);
        contentPanel.add(new JLabel("TC No::"));
        tc = new JTextField();
        contentPanel.add(tc);
        contentPanel.add(new JLabel("Telefon:"));
        telefon = new JTextField();
        contentPanel.add(telefon);
        contentPanel.add(new JLabel("Email:"));
        email = new JTextField();
        contentPanel.add(email);
        contentPanel.add(new JLabel("Username:"));
        username = new JTextField();
        contentPanel.add(username);


        btnEkle = new JButton("Müşteri Ekle");
        contentPanel.add(btnEkle);
        contentPanel.add(new JLabel(""));

        add(contentPanel);
    }

    public String getAd() {
        return ad.getText();
    }

    public String getSoyad() {
        return soyad.getText();
    }

    public String getTc() {
        return tc.getText();
    }

    public String getTelefon() {
        return telefon.getText();
    }

    public String getEmail() {
        return email.getText();
    }

    public JButton getBtnEkle() {
        return btnEkle;
    }

    public String getUsername() {
        return username.getText();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);}
}
