package com.otel.view;
import javax.swing.*;
import java.awt.*;
import com.otel.controller.MainPageController;

public class MainPageFrame extends JFrame{
    private JButton btnMain;
    private JButton btnProfil;
    private JButton btnOdalar;
    private JButton btnRezervasyon;
    private JButton btnGecmisKonaklamalar;
    private MainPageController controller;

    public MainPageFrame(){
        initUI();
        controller = new MainPageController(this);
    }

    private void initUI(){
        setTitle("Otel Rezervasyon Sistemi");
        setSize(1000,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(0,60));
        headerPanel.setBackground(new Color(45, 72, 110));
        JLabel titleLabel = new JLabel("Rezervasyon Sistemi");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel sideBar = new JPanel();
        sideBar.setPreferredSize(new Dimension(250, 0));
        sideBar.setBackground(Color.BLACK);
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));

        btnMain = createMenuButton("Anasayfa");
        btnProfil = createMenuButton("Profilim");
        btnOdalar = createMenuButton("Odalar");
        btnRezervasyon = createMenuButton("Rezervasyonlarım");
        btnGecmisKonaklamalar = createMenuButton("Geçmiş Konaklamalarım");

        sideBar.add(btnMain);
        sideBar.add(btnProfil);
        sideBar.add(btnOdalar);
        sideBar.add(btnRezervasyon);
        sideBar.add(btnGecmisKonaklamalar);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        mainPanel.add(sideBar, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBackground(Color.RED);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.PLAIN, 15));
        btn.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public JButton getBtnMain() {
        return btnMain;
    }

    public JButton getBtnProfil() {
        return btnProfil;
    }

    public JButton getBtnOdalar() {
        return btnOdalar;
    }

    public JButton getBtnRezervasyon() {
        return btnRezervasyon;
    }

    public JButton getBtnGecmisKonaklamalar() {
        return btnGecmisKonaklamalar;
    }
}
