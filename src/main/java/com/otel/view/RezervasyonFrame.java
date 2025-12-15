package com.otel.view;
import com.otel.controller.RezervasyonController;
import com.otel.decorator.*;
import com.otel.model.Oda;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class RezervasyonFrame extends JFrame {
    private RezervasyonController controller;
    private Oda oda;
    private JButton btnRezervasyon;
    private JComboBox comboKisiSayisi;
    private JSpinner girisTarihi;
    private JSpinner cikisTarihi;

    private JLabel lblFiyatValue;

    private JCheckBox chkHavuz;
    private JCheckBox chkKahvalti;
    private JCheckBox chkOtopark;

    private int toplamFiyat;

    public RezervasyonFrame(Oda oda) {
        this.oda = oda;
        initUI();
        controller = new RezervasyonController(this);
    }

    private void initUI() {
        setTitle("Rezervasyon");
        setSize(600,500);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(9,2,10,10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainPanel.add(new JLabel("Oda Numarası:"));
        mainPanel.add(new JLabel(oda.getOdaNumarasi()));

        mainPanel.add(new JLabel("Tip:"));
        mainPanel.add(new JLabel(oda.getOdaTipi()));

        Integer[] kisiSayisi = new Integer[oda.getKapasite()];
        for (int i = 0; i < kisiSayisi.length; i++) {
            kisiSayisi[i] = i+1;
        }
        comboKisiSayisi = new JComboBox<Integer>(kisiSayisi);
        mainPanel.add(new JLabel("Kişi Sayısı:"));
        mainPanel.add(comboKisiSayisi);

        girisTarihi = new JSpinner(new SpinnerDateModel());
        girisTarihi.setEditor(new JSpinner.DateEditor(girisTarihi, "dd.MM.yyyy"));
        mainPanel.add(new JLabel("Giriş Tarihi:"));
        mainPanel.add(girisTarihi);

        cikisTarihi = new JSpinner(new SpinnerDateModel());
        cikisTarihi.setEditor(new JSpinner.DateEditor(cikisTarihi, "dd.MM.yyyy"));
        mainPanel.add(new JLabel("Çıkış Tarihi:"));
        mainPanel.add(cikisTarihi);

        mainPanel.add(new JLabel("Ekstra Hizmetler"));

        JPanel pnlEkstralar = new JPanel(new GridLayout(3,1,0,5));
        chkKahvalti = new JCheckBox("Kahvaltı (+300 TL)");
        chkHavuz = new JCheckBox("Havuz (+500 TL)");
        chkOtopark = new JCheckBox("Otapark (+700 TL");

        pnlEkstralar.add(chkHavuz);
        pnlEkstralar.add(chkKahvalti);
        pnlEkstralar.add(chkOtopark);

        mainPanel.add(pnlEkstralar);


        JLabel lblFiyatText = new JLabel("Fiyat:");
        JLabel lblFiyatValue = new JLabel(String.valueOf(oda.getFiyat()));
        mainPanel.add(lblFiyatText);
        mainPanel.add(lblFiyatValue);


        girisTarihi.addChangeListener(e -> {
            Integer secilenKisi = (Integer) comboKisiSayisi.getSelectedItem();

            if (secilenKisi != null) {
                IOdaComponent hesaplananOda = new IOdaComponent() {
                    @Override
                    public String getAciklama() {
                        return oda.getAciklama();
                    }

                    @Override
                    public double getFiyat() {
                        return oda.getFiyat();
                    }
                };
                if(chkKahvalti.isSelected()) hesaplananOda = new KahvaltiDecorator(hesaplananOda);
                if(chkHavuz.isSelected()) hesaplananOda = new HavuzDecorator(hesaplananOda);
                if(chkOtopark.isSelected()) hesaplananOda = new OtoparkDecorator(hesaplananOda);



                toplamFiyat = (int) (hesaplananOda.getFiyat() * secilenKisi * gunSayisiHesaplama(getGirisTarihi() , getCikisTarihi()));
                lblFiyatValue.setText(String.valueOf(toplamFiyat));
            }
        });
        cikisTarihi.addChangeListener(e -> {
            Integer secilenKisi = (Integer) comboKisiSayisi.getSelectedItem();

            if (secilenKisi != null) {
                IOdaComponent hesaplananOda = new IOdaComponent() {
                    @Override
                    public String getAciklama() { return oda.getOdaTipi(); }
                    @Override
                    public double getFiyat() { return oda.getFiyat(); }
                };
                if (chkKahvalti.isSelected()) hesaplananOda = new KahvaltiDecorator(hesaplananOda);
                if (chkHavuz.isSelected()) hesaplananOda = new HavuzDecorator(hesaplananOda);
                if (chkOtopark.isSelected()) hesaplananOda = new OtoparkDecorator(hesaplananOda);


                toplamFiyat = (int) (hesaplananOda.getFiyat() * secilenKisi * gunSayisiHesaplama(getGirisTarihi() , getCikisTarihi()));
                lblFiyatValue.setText(String.valueOf(toplamFiyat));
            }
        });

        comboKisiSayisi.addActionListener(e -> {
            Integer secilenKisi = (Integer) comboKisiSayisi.getSelectedItem();

            if (secilenKisi != null) {

                IOdaComponent hesaplananOda = new IOdaComponent() {
                    @Override
                    public String getAciklama() { return oda.getOdaTipi(); }
                    @Override
                    public double getFiyat() { return oda.getFiyat(); }
                };
                if (chkKahvalti.isSelected()) hesaplananOda = new KahvaltiDecorator(hesaplananOda);
                if (chkHavuz.isSelected()) hesaplananOda = new HavuzDecorator(hesaplananOda);
                if (chkOtopark.isSelected()) hesaplananOda = new OtoparkDecorator(hesaplananOda);


                toplamFiyat = (int) (hesaplananOda.getFiyat() * secilenKisi * gunSayisiHesaplama(getGirisTarihi() , getCikisTarihi()));
                lblFiyatValue.setText(String.valueOf(toplamFiyat));
            }
        });

        chkHavuz.addActionListener(e -> {
            Integer secilenKisi = (Integer) comboKisiSayisi.getSelectedItem();
            if (secilenKisi != null) {
                IOdaComponent hesaplananOda = new IOdaComponent() {
                    @Override
                    public String getAciklama() { return oda.getOdaTipi(); }
                    @Override
                    public double getFiyat() { return oda.getFiyat(); }
                };
                if (chkKahvalti.isSelected()) hesaplananOda = new KahvaltiDecorator(hesaplananOda);
                if (chkHavuz.isSelected()) hesaplananOda = new HavuzDecorator(hesaplananOda);
                if (chkOtopark.isSelected()) hesaplananOda = new OtoparkDecorator(hesaplananOda);

                toplamFiyat = (int) (hesaplananOda.getFiyat() * secilenKisi * gunSayisiHesaplama(getGirisTarihi(), getCikisTarihi()));
                lblFiyatValue.setText(String.valueOf(toplamFiyat));
            }
        });

        chkKahvalti.addActionListener(e -> {
            Integer secilenKisi = (Integer) comboKisiSayisi.getSelectedItem();
            if (secilenKisi != null) {
                IOdaComponent hesaplananOda = new IOdaComponent() {
                    @Override
                    public String getAciklama() { return oda.getOdaTipi(); }
                    @Override
                    public double getFiyat() { return oda.getFiyat(); }
                };
                if (chkKahvalti.isSelected()) hesaplananOda = new KahvaltiDecorator(hesaplananOda);
                if (chkHavuz.isSelected()) hesaplananOda = new HavuzDecorator(hesaplananOda);
                if (chkOtopark.isSelected()) hesaplananOda = new OtoparkDecorator(hesaplananOda);

                toplamFiyat = (int) (hesaplananOda.getFiyat() * secilenKisi * gunSayisiHesaplama(getGirisTarihi(), getCikisTarihi()));
                lblFiyatValue.setText(String.valueOf(toplamFiyat));
            }
        });

        chkOtopark.addActionListener(e -> {
            Integer secilenKisi = (Integer) comboKisiSayisi.getSelectedItem();
            if (secilenKisi != null) {
                IOdaComponent hesaplananOda = new IOdaComponent() {
                    @Override
                    public String getAciklama() { return oda.getOdaTipi(); }
                    @Override
                    public double getFiyat() { return oda.getFiyat(); }
                };
                if (chkKahvalti.isSelected()) hesaplananOda = new KahvaltiDecorator(hesaplananOda);
                if (chkHavuz.isSelected()) hesaplananOda = new HavuzDecorator(hesaplananOda);
                if (chkOtopark.isSelected()) hesaplananOda = new OtoparkDecorator(hesaplananOda);

                toplamFiyat = (int) (hesaplananOda.getFiyat() * secilenKisi * gunSayisiHesaplama(getGirisTarihi(), getCikisTarihi()));
                lblFiyatValue.setText(String.valueOf(toplamFiyat));
            }
        });

        btnRezervasyon = new JButton("Rezerve Et");
        mainPanel.add(new JLabel(""));
        mainPanel.add(btnRezervasyon);


        add(mainPanel, BorderLayout.CENTER);
    }

    public JButton getBtnRezervasyon(){
        return btnRezervasyon;
    }

    public Date getGirisTarihi(){
        return (Date) girisTarihi.getValue();
    }

    public Date getCikisTarihi(){
        return (Date) cikisTarihi.getValue();
    }

    public int getKisiSayisi(){
        return (int) comboKisiSayisi.getSelectedItem();
    }

    public Oda getOda() {
        return oda;
    }

    public int getToplamFiyat(){
        return toplamFiyat == 0 ? (int) oda.getFiyat() : toplamFiyat;
    }

    public static int gunSayisiHesaplama(Date giris,Date cikis){
        int farkMillis =
                (int) (cikis.getTime() - giris.getTime());

        int gunSayisi = farkMillis / (1000 * 60 * 60 * 24);
        return gunSayisi ==0 ? 1:gunSayisi;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }


}
