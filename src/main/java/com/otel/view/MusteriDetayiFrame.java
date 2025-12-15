package com.otel.view;

import com.otel.database.OdaDB;
import com.otel.database.RezervasyonDB;
import com.otel.model.Oda;
import com.otel.model.Rezervasyon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MusteriDetayiFrame extends JFrame {
    private int musteriID;

    public MusteriDetayiFrame(int musteriID) {
        this.musteriID = musteriID;
        initUI();
    }

    private void initUI() {
        setTitle("Müşteri Detayları ve Rezervasyon Geçmişi");
        setSize(550, 750);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setBackground(Color.WHITE);
        mainContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JScrollPane scrollPane = new JScrollPane(mainContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);


        RezervasyonDB rezervasyonDB = new RezervasyonDB();

        List<Rezervasyon> tumRezervasyonlar = rezervasyonDB.musteriRezervasyonlari(musteriID);

        List<Rezervasyon> aktifList = new ArrayList<>();
        List<Rezervasyon> gecmisList = new ArrayList<>();

        for (Rezervasyon r : tumRezervasyonlar) {
            String durum = r.getDurum().toUpperCase();

            if (durum.equals("TAMAMLANDI") || durum.equals("IPTAL")) {
                gecmisList.add(r);
            } else {

                aktifList.add(r);
            }
        }


        addSectionHeader(mainContainer, "Aktif Konaklamalar");

        if (aktifList.isEmpty()) {
            addInfoLabel(mainContainer, "Aktif bir rezervasyon bulunmamaktadır.");
        } else {
            for (Rezervasyon r : aktifList) {
                mainContainer.add(createRezervasyonCard(r, true));
                mainContainer.add(Box.createVerticalStrut(15));
            }
        }


        mainContainer.add(Box.createVerticalStrut(20));
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        separator.setForeground(Color.GRAY);
        mainContainer.add(separator);
        mainContainer.add(Box.createVerticalStrut(20));


        addSectionHeader(mainContainer, "Geçmiş Konaklamalar");

        if (gecmisList.isEmpty()) {
            addInfoLabel(mainContainer, "Geçmiş konaklama kaydı bulunmamaktadır.");
        } else {
            for (Rezervasyon r : gecmisList) {
                mainContainer.add(createRezervasyonCard(r, false));
                mainContainer.add(Box.createVerticalStrut(15));
            }
        }
    }


    private void addSectionHeader(JPanel container, String title) {
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(new Color(45, 72, 110));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.add(label);
        container.add(Box.createVerticalStrut(15));
    }


    private void addInfoLabel(JPanel container, String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.ITALIC, 13));
        label.setForeground(Color.GRAY);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.add(label);
        container.add(Box.createVerticalStrut(10));
    }


    private JPanel createRezervasyonCard(Rezervasyon r, boolean isActive) {

        JPanel card = new JPanel(new GridLayout(8, 2, 5, 2));


        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(isActive ? new Color(100, 149, 237) : Color.LIGHT_GRAY, isActive ? 2 : 1),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));


        card.setBackground(isActive ? Color.WHITE : new Color(245, 245, 245));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 220));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);


        Oda oda = new OdaDB().getOda(r.getOdaId());


        addLabelToCard(card, "Rezervasyon No:", String.valueOf(r.getId()));
        addLabelToCard(card, "Oda No:", oda != null ? oda.getOdaNumarasi() : "-");
        addLabelToCard(card, "Oda Tipi:", oda != null ? oda.getOdaTipi() : "-");
        addLabelToCard(card, "Giriş Tarihi:", String.valueOf(r.getGirisTarihi()));
        addLabelToCard(card, "Çıkış Tarihi:", String.valueOf(r.getCikisTarihi()));
        addLabelToCard(card, "Toplam Tutar:", r.getToplamFiyat() + " TL");

        card.add(new JLabel("Ödeme Yöntemi:"));
        card.add(createOdemeBadge(r.getOdemeYontemi()));

        JLabel lblDurumBaslik = new JLabel("Durum:");
        lblDurumBaslik.setFont(new Font("Arial", Font.BOLD, 12));
        card.add(lblDurumBaslik);

        JLabel lblDurum = new JLabel(r.getDurum());
        lblDurum.setFont(new Font("Arial", Font.BOLD, 12));


        if (r.getDurum().equalsIgnoreCase("ONAYLANDI")) {
            lblDurum.setForeground(new Color(0, 128, 0));
        } else if (r.getDurum().equalsIgnoreCase("BEKLEMEDE")) {
            lblDurum.setForeground(new Color(255, 140, 0));
        } else if (r.getDurum().equalsIgnoreCase("IPTAL")) {
            lblDurum.setForeground(Color.RED);
        } else {
            lblDurum.setForeground(Color.DARK_GRAY);
        }
        card.add(lblDurum);

        return card;
    }

    private void addLabelToCard(JPanel card, String title, String value) {
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 12));
        lblTitle.setForeground(Color.DARK_GRAY);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Arial", Font.BOLD, 12));
        lblValue.setForeground(Color.BLACK);

        card.add(lblTitle);
        card.add(lblValue);
    }

    private JPanel createOdemeBadge(String odemeTipi) {
        JPanel badgePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        badgePanel.setOpaque(false);

        JLabel lblOdeme = new JLabel();
        lblOdeme.setFont(new Font("Arial", Font.BOLD, 12));
        lblOdeme.setOpaque(true);
        lblOdeme.setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 6)); // Padding

        if (odemeTipi == null) odemeTipi = "BELIRSIZ";

        switch (odemeTipi.toUpperCase()) {
            case "NAKIT":
                lblOdeme.setText("Nakit");

                lblOdeme.setForeground(new Color(0, 100, 0));
                break;
            case "KREDIKARTI":
            case "KREDİ KARTI":
                lblOdeme.setText("Kredi Kartı");
                lblOdeme.setForeground(new Color(0, 0, 139));
                break;
            default:
                lblOdeme.setText(odemeTipi);
                lblOdeme.setBackground(Color.LIGHT_GRAY);
                lblOdeme.setForeground(Color.BLACK);
                break;
        }

        badgePanel.add(lblOdeme);
        return badgePanel;
    }
}