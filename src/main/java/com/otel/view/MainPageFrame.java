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

        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));



        mainContainer.add(createHeroSection());
        mainContainer.add(new JSeparator(SwingConstants.HORIZONTAL)); // Bölümler arası düz çizgi
        mainContainer.add(createTestimonialsSection());
        mainContainer.add(new JSeparator(SwingConstants.HORIZONTAL));
        mainContainer.add(createFeaturesSection());

        JScrollPane scrollPane = new JScrollPane(mainContainer);

        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createHeroSection() {

        JPanel heroPanel = new JPanel();
        heroPanel.setLayout(new BoxLayout(heroPanel, BoxLayout.Y_AXIS));



        heroPanel.add(Box.createRigidArea(new Dimension(0, 20)));


        JLabel sloganLabel = new JLabel("Konforun ve Huzurun Adresi");
        sloganLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        sloganLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        heroPanel.add(sloganLabel);

        heroPanel.add(Box.createRigidArea(new Dimension(0, 10)));


        String userName = SessionManager.getInstance().getUser() != null ?
                SessionManager.getInstance().getUser().getFullname() : "Misafir";

        JLabel welcomeLabel = new JLabel("Hoş Geldiniz: " + userName);
        welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        heroPanel.add(welcomeLabel);

        heroPanel.add(Box.createRigidArea(new Dimension(0, 10)));


        JLabel subtitleLabel = new JLabel("İyi tatiller dileriz.");
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        heroPanel.add(subtitleLabel);


        heroPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        return heroPanel;
    }

    private JPanel createTestimonialsSection() {
        JPanel testimonialsPanel = new JPanel();
        testimonialsPanel.setLayout(new BoxLayout(testimonialsPanel, BoxLayout.Y_AXIS));



        JLabel titleLabel = new JLabel("Müşteri Yorumları");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        testimonialsPanel.add(titleLabel);

        testimonialsPanel.add(Box.createRigidArea(new Dimension(0, 10)));


        JPanel cardsContainer = new JPanel(new GridLayout(1, 2, 10, 0)); // Boşlukları azalttık


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


    private JPanel createTestimonialCard(String comment, String name, String location) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new LineBorder(Color.BLACK));
        card.setBackground(Color.WHITE);


        JTextArea commentArea = new JTextArea(comment);
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        commentArea.setEditable(false);

        commentArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
        card.add(commentArea);


        JLabel nameLabel = new JLabel("- " + name);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(nameLabel);


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

        JPanel featuresContainer = new JPanel(new GridLayout(2, 2, 5, 5));


        featuresContainer.add(createFeatureCard("Havuz", "Açık havuz var."));
        featuresContainer.add(createFeatureCard("Restoran", "Yemek servisi var."));
        featuresContainer.add(createFeatureCard("Otopark", "Araba park yeri mevcut."));
        featuresContainer.add(createFeatureCard("İnternet", "Wifi şifresi resepsiyonda."));

        featuresPanel.add(featuresContainer);
        featuresPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        return featuresPanel;
    }


    private JPanel createFeatureCard(String title, String description) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEtchedBorder());


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