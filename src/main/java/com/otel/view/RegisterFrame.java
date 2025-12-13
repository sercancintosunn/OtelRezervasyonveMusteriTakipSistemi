package com.otel.view;
import javax.swing.*;
import java.awt.*;

import com.otel.controller.RegisterController;


public class RegisterFrame extends JFrame {
    private JTextField ad;
    private JTextField soyad;
    private JTextField email;
    private JTextField tckn;
    private JTextField telefon;
    private JTextField username;
    private JPasswordField password;
    private JButton btnKaydol;
    private JLabel btnLogin;
    private RegisterController controller;

    public RegisterFrame() {
        initUI();
        controller = new RegisterController(this);
    }

    private void initUI() {
        setTitle("Otel Yönetim Sistemi - Kayıt Ol");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setBackground(new Color(45, 72, 110));
        headerPanel.setPreferredSize(new Dimension(0, 60));

        JLabel title = new JLabel("OTEL YÖNETİM SİSTEMİ");
        title.setForeground(Color.white);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(title);
        JPanel loginPanel = new JPanel(new GridLayout(2,2,10,10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20,30,20,30));
        JPanel topside = new JPanel(new GridLayout(7,2,10,10));
        topside.add(new JLabel("Ad:"));
        ad = new JTextField(12);
        topside.add(ad);
        topside.add(new JLabel("Soyad:"));
        soyad = new JTextField(11);
        topside.add(soyad);
        topside.add(new JLabel("Email:"));
        email = new JTextField(40);
        topside.add(email);
        topside.add(new JLabel("Telefon Numarası:"));
        telefon = new JTextField(10);
        topside.add(telefon);
        topside.add(new JLabel("T.C. Kimlik Numarası:"));
        tckn = new JTextField(11);
        topside.add(tckn);
        topside.add(new JLabel("Kullanıcı Adı:"));
        username = new JTextField(15);
        topside.add(username);
        topside.add(new JLabel("Şifre:"));
        password = new JPasswordField(15);
        topside.add(password);
        JPanel bottomside = new JPanel();
        btnKaydol = new JButton("Kaydol");
        btnKaydol.setBackground(new Color(147, 185, 207));
        btnKaydol.setForeground(Color.white);
        bottomside.add(btnKaydol);
        loginPanel.add(topside);
        loginPanel.add(bottomside);
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(loginPanel);

        JPanel girisYonlendirme = new JPanel();
        girisYonlendirme.add(new JLabel("Hesabın var mı? "));
        btnLogin = new JLabel("Giriş Yap!");
        girisYonlendirme.add(btnLogin);
        add(centerWrapper, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);
        add(girisYonlendirme, BorderLayout.SOUTH);

    }

    public String getTCKN() {
        return tckn.getText();
    }

    public String getPassword() {
        return new String(password.getPassword());
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public JButton getBtnKaydol() {
        return btnKaydol;
    }

    public JLabel getBtnLogin() {
        return btnLogin;
    }

    public String getSoyad() {
        return soyad.getText();
    }

    public String getUsername() {
        return username.getText();
    }

    public String getTelefon() {
        return telefon.getText();
    }

    public String getEmail() {
        return email.getText();
    }

    public String getAd() {
        return ad.getText();
    }
}
