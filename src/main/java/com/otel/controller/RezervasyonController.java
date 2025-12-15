package com.otel.controller;

import com.otel.database.RezervasyonDB;
import com.otel.helper.SessionManager;
import com.otel.model.Rezervasyon;
import com.otel.strategy.KrediKarti;
import com.otel.strategy.Nakit;
import com.otel.strategy.StrategyManagment;
import com.otel.view.RezervasyonFrame;

import javax.swing.*;
import java.sql.Timestamp;

public class RezervasyonController {
    private RezervasyonFrame view;

    public RezervasyonController(RezervasyonFrame view) {
        this.view = view;
        initController();
    }

    private void initController(){
        view.getBtnRezervasyon().addActionListener(e -> {

            String[] odemeYontemleri = {"Nakit", "Kredi Kartı"};
            int secim = JOptionPane.showOptionDialog(
                    view,
                    "Ödeme yönteminizi seçin:",
                    "Ödeme Yöntemi",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    odemeYontemleri,
                    odemeYontemleri[0]
            );

            if (secim == -1) {

                return;
            }

            StrategyManagment strategyManagment = new StrategyManagment();
            boolean odemeBasarili = false;

            if (secim == 0) {

                strategyManagment.setPayment(new Nakit());
                odemeBasarili = strategyManagment.odemeYap(
                        view.getToplamFiyat(),
                        SessionManager.getInstance().getUser().getFullname()
                );
            } else if (secim == 1) {

                JPanel krediKartiPanel = new JPanel(new java.awt.GridLayout(4, 2, 10, 10));

                JTextField txtKartNumarasi = new JTextField(16);
                JTextField txtKartSahibi = new JTextField(30);
                JTextField txtSonKullanma = new JTextField(5);
                JTextField txtCVV = new JTextField(3);

                krediKartiPanel.add(new JLabel("Kart Numarası (16 hane):"));
                krediKartiPanel.add(txtKartNumarasi);
                krediKartiPanel.add(new JLabel("Kart Sahibi:"));
                krediKartiPanel.add(txtKartSahibi);
                krediKartiPanel.add(new JLabel("Son Kullanma (MM/YY):"));
                krediKartiPanel.add(txtSonKullanma);
                krediKartiPanel.add(new JLabel("CVV (3 hane):"));
                krediKartiPanel.add(txtCVV);

                int result = JOptionPane.showConfirmDialog(
                        view,
                        krediKartiPanel,
                        "Kredi Kartı Bilgileri",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (result == JOptionPane.OK_OPTION) {
                    String kartNumarasi = txtKartNumarasi.getText().trim();
                    String kartSahibi = txtKartSahibi.getText().trim();
                    String sonKullanma = txtSonKullanma.getText().trim();
                    String cvv = txtCVV.getText().trim();

                    if (kartNumarasi.isEmpty() || kartSahibi.isEmpty() ||
                            sonKullanma.isEmpty() || cvv.isEmpty()) {
                        view.showMessage("Lütfen tüm kart bilgilerini doldurun!");
                        return;
                    }

                    KrediKarti krediKarti = new KrediKarti(
                            kartNumarasi,
                            kartSahibi,
                            sonKullanma,
                            cvv
                    );

                    strategyManagment.setPayment(krediKarti);
                    odemeBasarili = strategyManagment.odemeYap(
                            view.getToplamFiyat(),
                            SessionManager.getInstance().getUser().getFullname()
                    );
                } else {

                    return;
                }
            }


            if (odemeBasarili) {
                int musteriID = SessionManager.getInstance().getUserId();
                int odaID = view.getOda().getId();

                String odemeYontemi = strategyManagment.getOdemeTipi();

                Rezervasyon rezervasyon = new Rezervasyon(
                        0,
                        musteriID,
                        odaID,
                        view.getGirisTarihi(),
                        view.getCikisTarihi(),
                        view.getKisiSayisi(),
                        view.getToplamFiyat(),
                        "BEKLEMEDE",
                        odemeYontemi,
                        new Timestamp(System.currentTimeMillis())
                );

                boolean rezervasyonKaydedildi = new RezervasyonDB().rezervasyonEkle(rezervasyon);

                if (rezervasyonKaydedildi) {
                    view.showMessage("Ödeme başarılı! Rezervasyonunuz oluşturuldu.");
                    view.dispose();
                } else {
                    view.showMessage("Rezervasyon kaydedilemedi. Lütfen tekrar deneyin.");
                }
            } else {
                view.showMessage("Ödeme işlemi başarısız! Rezervasyon oluşturulamadı.");
            }
        });
    }
}