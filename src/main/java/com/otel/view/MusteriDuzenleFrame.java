package com.otel.view;

import com.otel.controller.MusteriDuzenleController;
import com.otel.database.MusteriDB;
import com.otel.model.Musteri;

import javax.swing.*;
import java.awt.*;

public class MusteriDuzenleFrame extends JFrame {

    private JTextField usernameGuncelle;
    private JTextField emailGuncelle;
    private JTextField telefonGuncelle;
    private JButton btnGuncelle;
    private int musteriID;
    private MusteriDuzenleController controller;

    public MusteriDuzenleFrame(int musteriID) {
        this.musteriID = musteriID;
        initUI();
        controller = new MusteriDuzenleController(this);
    }

    private void initUI(){

        setTitle("Müşteri Düzenle");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel contentPanel = new JPanel();

        Musteri musteri = new MusteriDB().getMusteri(musteriID);

        contentPanel.setLayout(new GridLayout(8,2,15,15));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.add(new JLabel("Kullanıcı Adı: "));
        usernameGuncelle = new JTextField(musteri.getUserName());
        contentPanel.add(usernameGuncelle);
        contentPanel.add(new JLabel("E-mail:"));
        emailGuncelle = new JTextField(musteri.getEmail());
        contentPanel.add(emailGuncelle);
        contentPanel.add(new JLabel("Telefon:"));
        telefonGuncelle = new JTextField(musteri.getTelefon());
        contentPanel.add(telefonGuncelle);
        btnGuncelle = new JButton("Bilgileri Güncelle");
        contentPanel.add(btnGuncelle);
        contentPanel.add(new JLabel(""));

        add(contentPanel);
    }

    public JButton getBtnGuncelle() {
        return btnGuncelle;
    }

    public String getUsernameGuncelle() {
        return usernameGuncelle.getText();
    }

    public String getEmailGuncelle() {
        return emailGuncelle.getText();
    }

    public String getTelefonGuncelle() {
        return telefonGuncelle.getText();
    }
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public int getMusteriID() {
        return musteriID;
    }
}
