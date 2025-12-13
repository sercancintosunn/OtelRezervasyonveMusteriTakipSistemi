package com.otel.view;
import javax.swing.*;
import java.awt.*;
import com.otel.controller.LoginController;

public class LoginFrame extends JFrame {
    private JTextField tckn;
    private JPasswordField password;
    private JButton btnLogin;
    private JLabel btnKaydol;
    private LoginController controller;

    public LoginFrame() {
        initUI();
        controller = new LoginController(this);
    }

    private void initUI() {
        setTitle("Otel Yönetim Sistemi - Giriş");
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
        JPanel topside = new JPanel(new GridLayout(3,2,10,10));
        topside.add(new JLabel("T.C. Kimlik Numarası:"));
        tckn = new JTextField(11);
        topside.add(tckn);
        topside.add(new JLabel("Şifre:"));
        password = new JPasswordField(15);
        topside.add(password);
        JPanel bottomside = new JPanel();
        btnLogin = new JButton("Giriş Yap");
        btnLogin.setBackground(new Color(147, 185, 207));
        btnLogin.setForeground(Color.white);
        bottomside.add(btnLogin);
        loginPanel.add(topside);
        loginPanel.add(bottomside);
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(loginPanel);

        JPanel kaydolYonlendirme = new JPanel();
        kaydolYonlendirme.add(new JLabel("Hesabın yok mu? "));
        btnKaydol = new JLabel("Kaydol!");
        kaydolYonlendirme.add(btnKaydol);
        add(centerWrapper, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);
        add(kaydolYonlendirme, BorderLayout.SOUTH);

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

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JLabel getBtnKaydol() {
        return btnKaydol;
    }
}
