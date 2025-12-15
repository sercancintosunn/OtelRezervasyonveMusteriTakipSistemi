package com.otel.view;
import com.otel.helper.SessionManager;

import javax.swing.*;
import java.awt.*;

public abstract class PBaseMainFrame extends JFrame {

    protected JButton btnRezervasyon;
    protected JButton btnMusteriler;
    protected JButton btnOdalar;
    protected JButton btnCikisYap;

    protected JPanel contentPanel;

    public PBaseMainFrame() {
        initUI();
        initMenuActions();
    }

    private void initUI() {
        setTitle("Otel Rezervasyon Sistemi");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(0,60));
        headerPanel.setBackground(new Color(45, 72, 110));

        JLabel titleLabel = new JLabel("Rezervasyon Sistemi");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0,15,0,0));

        headerPanel.add(titleLabel, BorderLayout.WEST);


        JPanel sideBar = new JPanel();
        sideBar.setPreferredSize(new Dimension(250, 0));
        sideBar.setBackground(Color.BLACK);
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));

        btnOdalar = createMenuButton("Odalar");
        btnRezervasyon = createMenuButton("Rezervasyonlar");
        btnMusteriler = createMenuButton("Musteriler");
        btnCikisYap = createMenuButton("Çıkış Yap");

        btnCikisYap.setBackground(Color.red);
        btnCikisYap.setForeground(Color.white);

        sideBar.add(btnOdalar);
        sideBar.add(btnRezervasyon);
        sideBar.add(btnMusteriler);


        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        JPanel mainPanel = new JPanel(new BorderLayout());
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

    protected abstract void initContent();

    protected void initMenuActions() {

        btnOdalar.addActionListener(e -> {
            dispose();
            new POdalarFrame().setVisible(true);
        });
        btnRezervasyon.addActionListener(e -> {
            dispose();
            new PRezervasyonFrame().setVisible(true);
        });
        btnMusteriler.addActionListener(e -> {
            dispose();
            new PMusterilerFrame().setVisible(true);
        });
        btnCikisYap.addActionListener(e -> {
            dispose();
            SessionManager.getInstance().logOut();
            new LoginFrame().setVisible(true);
        });
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}