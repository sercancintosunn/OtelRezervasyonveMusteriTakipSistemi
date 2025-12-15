package com.otel.view;

import com.otel.helper.SessionManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainPageFrame extends BaseMainFrame {

    public MainPageFrame() {
        super();
        initContent();
    }

    @Override
    protected void initContent() {
        // Ana taşıyıcı
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        // Arka plan rengini sildik, default gri kalsın

        // Bölümleri ekle
        mainContainer.add(createHeroSection());
        mainContainer.add(new JSeparator(SwingConstants.HORIZONTAL)); // Bölümler arası düz çizgi
        mainContainer.add(createTestimonialsSection());
        mainContainer.add(new JSeparator(SwingConstants.HORIZONTAL));
        mainContainer.add(createFeaturesSection());

        JScrollPane scrollPane = new JScrollPane(mainContainer);
        // Scrollbar ayarlarını da basitleştirdik
        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createHeroSection() {
        // Custom paintComponent (gradient/desen) kaldırıldı. Düz panel.
        JPanel heroPanel = new JPanel();
        heroPanel.setLayout(new BoxLayout(heroPanel, BoxLayout.Y_AXIS));
        // Basit siyah çerçeve

        // Üstten biraz boşluk
        heroPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Slogan - Basit font, siyah renk
        JLabel sloganLabel = new JLabel("Konforun ve Huzurun Adresi");
        sloganLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        sloganLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        heroPanel.add(sloganLabel);

        heroPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Kullanıcı adı
        String userName = SessionManager.getInstance().getUser() != null ?
                SessionManager.getInstance().getUser().getFullname() : "Misafir";

        JLabel welcomeLabel = new JLabel("Hoş Geldiniz: " + userName);
        welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        heroPanel.add(welcomeLabel);

        heroPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Alt metin
        JLabel subtitleLabel = new JLabel("İyi tatiller dileriz.");
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        heroPanel.add(subtitleLabel);

        // Alt boşluk
        heroPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        return heroPanel;
    }

    private JPanel createTestimonialsSection() {
        JPanel testimonialsPanel = new JPanel();
        testimonialsPanel.setLayout(new BoxLayout(testimonialsPanel, BoxLayout.Y_AXIS));
        // Arka plan rengi yok

        // Başlık
        JLabel titleLabel = new JLabel("Müşteri Yorumları");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        testimonialsPanel.add(titleLabel);

        testimonialsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Yorum kartları container
        JPanel cardsContainer = new JPanel(new GridLayout(1, 2, 10, 0)); // Boşlukları azalttık

        // Yıldız parametresini kaldırdık
        cardsContainer.add(createTestimonialCard(
                "Hizmet güzeldi, personel iyiydi. Tavsiye ederim.",
                "Ahmet Y.",
                "İstanbul"
        ));

        cardsContainer.add(createTestimonialCard(
                "Yemekler güzel, odalar temizdi.",
                "Ayşe K.",
                "Ankara"
        ));

        testimonialsPanel.add(cardsContainer);
        testimonialsPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        return testimonialsPanel;
    }

    // Yıldız parametresi metoddan çıkarıldı
    private JPanel createTestimonialCard(String comment, String name, String location) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new LineBorder(Color.BLACK)); // Basit siyah çerçeve (Acemi işi görünüm için birebir)
        card.setBackground(Color.WHITE); // Sadece kart beyaz kalsın okunması için

        // Yorum
        JTextArea commentArea = new JTextArea(comment);
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        commentArea.setEditable(false);
        // Font italik ve süslü değil, düz
        commentArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
        card.add(commentArea);

        // İsim
        JLabel nameLabel = new JLabel("- " + name);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(nameLabel);

        // Lokasyon
        JLabel locationLabel = new JLabel("(" + location + ")");
        locationLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
        locationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(locationLabel);

        return card;
    }

    private JPanel createFeaturesSection() {
        JPanel featuresPanel = new JPanel();
        featuresPanel.setLayout(new BoxLayout(featuresPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Özelliklerimiz");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        featuresPanel.add(titleLabel);

        featuresPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel featuresContainer = new JPanel(new GridLayout(2, 2, 5, 5)); // Sıkışık düzen

        // İkon parametrelerini sildik ("🏊" vb.)
        featuresContainer.add(createFeatureCard("Havuz", "Açık havuz var."));
        featuresContainer.add(createFeatureCard("Restoran", "Yemek servisi var."));
        featuresContainer.add(createFeatureCard("Otopark", "Araba park yeri mevcut."));
        featuresContainer.add(createFeatureCard("İnternet", "Wifi şifresi resepsiyonda."));

        featuresPanel.add(featuresContainer);
        featuresPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        return featuresPanel;
    }

    // İkon parametresi metoddan çıkarıldı
    private JPanel createFeatureCard(String title, String description) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEtchedBorder()); // Basit gömülü çerçeve

        // İkon yok, sadece yazı
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(titleLabel);

        JLabel descLabel = new JLabel(description);
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(descLabel);

        return card;
    }
}