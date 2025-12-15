package com.otel.view;

import com.otel.database.MusteriDB;
import com.otel.model.Musteri;

import javax.swing.*;
import java.awt.*;

public class PMusterilerFrame extends PBaseMainFrame{

    private JButton musteriDetayi;
    private JButton musteriyiDuzenle;

    public PMusterilerFrame(){
        super();
        initContent();
    }

    @Override
    protected void initContent() {
        JPanel musterilerContainer = new JPanel();
        musterilerContainer.setLayout(new BoxLayout(musterilerContainer, BoxLayout.Y_AXIS));
        musterilerContainer.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(musterilerContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        MusteriDB musteriDB = new MusteriDB();

        for (Musteri musteri : musteriDB.tumMusteriler()) {
            JPanel musteriPanel = new JPanel(new GridLayout(5, 2, 10, 5));
            musteriPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            musteriPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
            musteriPanel.setBackground(Color.WHITE);

            musteriPanel.add(new JLabel("Ad - Soyad: "));
            musteriPanel.add(new JLabel(musteri.getFullname()));

            musteriPanel.add(new JLabel("Kullanıcı Adı: "));
            musteriPanel.add(new JLabel(musteri.getUserName()));

            musteriPanel.add(new JLabel("Telefon: "));
            musteriPanel.add(new JLabel(musteri.getTelefon()));

            musteriPanel.add(new JLabel("E-mail: "));
            musteriPanel.add(new JLabel(musteri.getEmail()));

            musteriDetayi = new JButton("Müşteri Detayı");
            musteriyiDuzenle = new JButton("Müşteriyi Düzenle");

            musteriPanel.add(musteriDetayi);
            musteriPanel.add(musteriyiDuzenle);


            musterilerContainer.add(musteriPanel);
            musterilerContainer.add(Box.createVerticalStrut(10));
        }
        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }
}
