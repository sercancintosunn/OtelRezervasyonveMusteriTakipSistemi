package com.otel.view;
import com.otel.controller.RezervasyonlarımController;
import com.otel.database.OdaDB;
import com.otel.database.RezervasyonDB;
import com.otel.helper.SessionManager;
import com.otel.model.Oda;
import com.otel.model.Rezervasyon;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RezervasyonlarımFrame extends BaseMainFrame {
    private RezervasyonlarımController controller;
    private JButton btnIptal;
    public RezervasyonlarımFrame(){
        super();
        initContent();
        controller = new RezervasyonlarımController(this);
    }

    @Override
    protected void initContent() {
        JPanel rezervasyonContainer = new JPanel();
        rezervasyonContainer.setLayout(new BoxLayout(rezervasyonContainer, BoxLayout.Y_AXIS));
        rezervasyonContainer.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(rezervasyonContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        List<Rezervasyon> musteriRezarvasyonlari = new RezervasyonDB().musteriRezervasyonlari(SessionManager.getInstance().getUserId());
        for(Rezervasyon r : musteriRezarvasyonlari){
            Oda oda = new OdaDB().getOda(r.getOdaId());

            JPanel rezervasyonPanel = new JPanel(new GridLayout(8, 2, 10, 5));
            rezervasyonPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            rezervasyonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
            rezervasyonPanel.setBackground(Color.WHITE);

            rezervasyonPanel.add(new JLabel("Oda No:"));
            rezervasyonPanel.add(new JLabel(oda.getOdaNumarasi()));

            rezervasyonPanel.add(new JLabel("Tip:"));
            rezervasyonPanel.add(new JLabel(oda.getOdaTipi()));

            rezervasyonPanel.add(new JLabel("Fiyat:"));
            rezervasyonPanel.add(new JLabel(String.valueOf(r.getToplamFiyat())));

            rezervasyonPanel.add(new JLabel("Kişi Sayısı:"));
            rezervasyonPanel.add(new JLabel(String.valueOf(r.getKisiSayisi())));

            rezervasyonPanel.add(new JLabel("Giriş Tarihi:"));
            rezervasyonPanel.add(new JLabel(String.valueOf(r.getGirisTarihi())));

            rezervasyonPanel.add(new JLabel("Çıkış Tarihi:"));
            rezervasyonPanel.add(new JLabel(String.valueOf(r.getCikisTarihi())));

            rezervasyonPanel.add(new JLabel("Durum:"));
            JLabel lblDurum = new JLabel(r.getDurum());
            if(r.getDurum().equals("ONAYLANDI")) lblDurum.setForeground(new Color(0, 100, 0));
            else if(r.getDurum().equals("BEKLEMEDE")) lblDurum.setForeground(Color.ORANGE);
            else lblDurum.setForeground(Color.BLACK);
            rezervasyonPanel.add(lblDurum);

            if (r.getDurum().equals("BEKLEMEDE")) {

                btnIptal = new JButton("İptal Et");
                btnIptal.setBackground(Color.RED);
                btnIptal.setForeground(Color.WHITE);
                btnIptal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


                btnIptal.addActionListener(e -> {
                    int confirm = JOptionPane.showConfirmDialog(this,
                            "Rezervasyonu iptal etmek istediğinize emin misiniz?",
                            "İptal Onayı", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        new RezervasyonDB().rezervasyonSil(r.getId());
                        showMessage("Rezervasyonunuz başarıyla iptal edilmiştir!");
                        dispose();
                        new RezervasyonlarımFrame().setVisible(true);
                    }
                });

                rezervasyonPanel.add(new JLabel(""));
                rezervasyonPanel.add(btnIptal);
            }
            else {

                rezervasyonPanel.add(new JLabel(""));
                rezervasyonPanel.add(new JLabel(""));
            }

            rezervasyonContainer.add(rezervasyonPanel);
            rezervasyonContainer.add(Box.createVerticalStrut(10));
        }

        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }


}
