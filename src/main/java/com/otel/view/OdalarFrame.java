package com.otel.view;
import com.otel.controller.OdalarController;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import com.otel.database.OdaDB;
import com.otel.model.Oda;

public class OdalarFrame extends BaseMainFrame {
    private OdalarController controller;
    private OdaDB odaDB = new OdaDB();

    private JSpinner girisTarihi;
    private JSpinner cikisTarihi;
    private JComboBox<String> comboOdaTipi;
    private JComboBox<Integer> comboKisiSayisi;
    private JButton btnAra;
    private JPanel odalarContainer;

    public OdalarFrame() {
        super();
        initContent();
        controller = new OdalarController(this);
    }

    @Override
    protected void initContent() {
        // Ana panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Filtreleme paneli
        JPanel filterPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        filterPanel.setBackground(Color.WHITE);
        filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Üst satır - Tarihler
        JPanel topRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topRow.setBackground(Color.WHITE);

        topRow.add(new JLabel("Giriş Tarihi:"));
        girisTarihi = new JSpinner(new SpinnerDateModel());
        girisTarihi.setEditor(new JSpinner.DateEditor(girisTarihi, "dd.MM.yyyy"));
        girisTarihi.setPreferredSize(new Dimension(120, 25));
        topRow.add(girisTarihi);

        topRow.add(Box.createHorizontalStrut(10));

        topRow.add(new JLabel("Çıkış Tarihi:"));
        cikisTarihi = new JSpinner(new SpinnerDateModel());
        cikisTarihi.setEditor(new JSpinner.DateEditor(cikisTarihi, "dd.MM.yyyy"));
        cikisTarihi.setPreferredSize(new Dimension(120, 25));
        topRow.add(cikisTarihi);

        filterPanel.add(topRow);

        // Alt satır - Oda tipi ve Kişi sayısı
        JPanel bottomRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomRow.setBackground(Color.WHITE);

        bottomRow.add(new JLabel("Oda Tipi:"));
        comboOdaTipi = new JComboBox<>(new String[]{"Tümü", "Standart", "Aile", "Suite"});
        comboOdaTipi.setPreferredSize(new Dimension(120, 25));
        bottomRow.add(comboOdaTipi);

        bottomRow.add(Box.createHorizontalStrut(10));

        bottomRow.add(new JLabel("Kişi Sayısı:"));
        comboKisiSayisi = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6});
        comboKisiSayisi.setPreferredSize(new Dimension(80, 25));
        bottomRow.add(comboKisiSayisi);

        bottomRow.add(Box.createHorizontalStrut(10));

        btnAra = new JButton("Müsait Odaları Ara");
        btnAra.setBackground(new Color(45, 72, 110));
        btnAra.setForeground(Color.WHITE);
        btnAra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bottomRow.add(btnAra);

        filterPanel.add(bottomRow);

        mainPanel.add(filterPanel, BorderLayout.NORTH);

        // Odalar container
        odalarContainer = new JPanel();
        odalarContainer.setLayout(new BoxLayout(odalarContainer, BoxLayout.Y_AXIS));
        odalarContainer.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(odalarContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(mainPanel, BorderLayout.CENTER);

        // İlk yüklemede tüm odaları göster
        odalariYukle(null, null, null, 1);

        // Arama butonu dinleyicisi
        btnAra.addActionListener(e -> {
            java.util.Date giris = (java.util.Date) girisTarihi.getValue();
            java.util.Date cikis = (java.util.Date) cikisTarihi.getValue();

            if (giris.after(cikis)) {
                showMessage("Giriş tarihi çıkış tarihinden sonra olamaz!");
                return;
            }

            Date sqlGiris = new Date(giris.getTime());
            Date sqlCikis = new Date(cikis.getTime());

            // Oda tipi seçimi
            String secilenOdaTipi = (String) comboOdaTipi.getSelectedItem();
            String odaTipi = secilenOdaTipi.equals("Tümü") ? null : secilenOdaTipi;

            // Kişi sayısı
            int kisiSayisi = (int) comboKisiSayisi.getSelectedItem();

            odalariYukle(sqlGiris, sqlCikis, odaTipi, kisiSayisi);
        });
    }

    private void odalariYukle(Date girisTarihi, Date cikisTarihi, String odaTipi, int minKisiSayisi) {
        odalarContainer.removeAll();

        java.util.List<Oda> odalar;

        if (girisTarihi != null && cikisTarihi != null) {
            // Filtreli arama
            odalar = odaDB.odaAra(girisTarihi, cikisTarihi, odaTipi, minKisiSayisi);

            if (odalar.isEmpty()) {
                JPanel mesajPanel = new JPanel();
                mesajPanel.setBackground(Color.WHITE);
                JLabel mesaj = new JLabel("Seçilen kriterlere uygun müsait oda bulunamadı.");
                mesaj.setFont(new Font("Arial", Font.BOLD, 14));
                mesaj.setForeground(Color.RED);
                mesajPanel.add(mesaj);
                odalarContainer.add(mesajPanel);
            }
        } else {
            // Tüm odaları göster
            odalar = odaDB.tumOdalar();
        }

        for (Oda oda : odalar) {
            JPanel odaPanel = new JPanel(new GridLayout(6, 2, 10, 5));
            odaPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            odaPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
            odaPanel.setBackground(Color.WHITE);

            odaPanel.add(new JLabel("Oda No:"));
            odaPanel.add(new JLabel(oda.getOdaNumarasi()));

            odaPanel.add(new JLabel("Tip:"));
            odaPanel.add(new JLabel(oda.getOdaTipi()));

            odaPanel.add(new JLabel("Kapasite:"));
            odaPanel.add(new JLabel(String.valueOf(oda.getKapasite())));

            odaPanel.add(new JLabel("Fiyat:"));
            odaPanel.add(new JLabel(String.valueOf(oda.getFiyat()) + " TL"));

            odaPanel.add(new JLabel("Durum:"));
            odaPanel.add(new JLabel(oda.getDurum()));

            JButton btnRezerve = new JButton("Rezerve Et");

            if(oda.getDurum().equals("MUSAİT") || girisTarihi != null) {
                btnRezerve.setBackground(Color.BLUE);
                btnRezerve.setForeground(Color.WHITE);
                btnRezerve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnRezerve.setEnabled(true);

                btnRezerve.addActionListener(e-> {
                    controller.rezerveEt(oda);
                });
            }
            else{
                btnRezerve.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                btnRezerve.setBackground(Color.GRAY);
                btnRezerve.setEnabled(false);
            }

            odaPanel.add(btnRezerve);
            odaPanel.add(new JLabel(""));

            odalarContainer.add(odaPanel);
            odalarContainer.add(Box.createVerticalStrut(10));
        }

        odalarContainer.revalidate();
        odalarContainer.repaint();
    }
}