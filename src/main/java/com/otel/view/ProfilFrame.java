package com.otel.view;
import javax.swing.*;
import java.awt.*;
import com.otel.controller.ProfilController;
import com.otel.model.Musteri;
import com.otel.database.*;
import com.otel.helper.SessionManager;
public class ProfilFrame extends BaseMainFrame {
    private Musteri musteri;
    private int musteriID;
    private ProfilController controller;
    private JButton btnGuncelle;
    private JTextField usernameGuncelle;
    private JTextField sifreGuncelle;
    private JTextField emailGuncelle;
    private JTextField telefonGuncelle;
    public ProfilFrame() {
        super();
        musteriID = SessionManager.getInstance().getUserId();
        musteri = new MusteriDB().getMusteri(musteriID);
        initContent();
        controller = new ProfilController(this);


    }

    @Override
    protected void initContent() {
        contentPanel.setLayout(new GridLayout(8,2,15,15));
        contentPanel.setBackground(Color.WHITE);
        JLabel hosgeldinL = new JLabel("Hoşgeldiniz: " + musteri.getFullname());
        hosgeldinL.setForeground(Color.BLACK);
        hosgeldinL.setFont(new Font("Arial", Font.BOLD, 18));
        contentPanel.add(hosgeldinL);
        contentPanel.add(new JLabel(""));
        contentPanel.add(new JLabel("TC Kimlik Numarası: "));
        contentPanel.add(new JLabel(musteri.getTcNo()));
        contentPanel.add(new JLabel("Kullanıcı Adı: "));
        usernameGuncelle = new JTextField(musteri.getUserName());
        contentPanel.add(usernameGuncelle);
        contentPanel.add(new JLabel("E-mail:"));
        emailGuncelle = new JTextField(musteri.getEmail());
        contentPanel.add(emailGuncelle);
        contentPanel.add(new JLabel("Telefon:"));
        telefonGuncelle = new JTextField(musteri.getTelefon());
        contentPanel.add(telefonGuncelle);
        contentPanel.add(new JLabel("Sifre:"));
        sifreGuncelle = new JTextField(musteri.getSifre());
        contentPanel.add(sifreGuncelle);
        contentPanel.add(new JLabel("Kayıt Tarihi: "));
        contentPanel.add(new JLabel(String.valueOf(musteri.getKayitTarihi())));
        btnGuncelle = new JButton("Bilgileri Güncelle");
        contentPanel.add(btnGuncelle);
        contentPanel.add(new JLabel(""));
    }

    public JButton getBtnGuncelle() {
        return btnGuncelle;
    }

    public String getUsernameGuncelle() {
        return usernameGuncelle.getText();
    }

    public String getSifreGuncelle() {
        return sifreGuncelle.getText();
    }

    public String getEmailGuncelle() {
        return emailGuncelle.getText();
    }

    public String getTelefonGuncelle() {
        return telefonGuncelle.getText();
    }
}
