package org.example.InterfaceGraphique;

import org.example.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SelectionScreen {

    public static JPanel selectionScreen;
    public static JLabel logoLabel;

    private static boolean estValideur;

    public static boolean isEstValideur() {
        return estValideur;
    }

    public static void initSelectionScreen() {

        Image bgImage;
        try {
            File imageFile = new File("src/main/resources/bg.jpg");
            bgImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            bgImage = null;
            e.printStackTrace();
        }

        Image finalBgImage = bgImage;
        selectionScreen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (finalBgImage != null) {
                    g.drawImage(finalBgImage, 0, 0, getWidth(), getHeight(), null);
                }
            }
        };

        selectionScreen.setLayout(new BorderLayout());

        //selectionScreen = new JPanel(new BorderLayout());
        //selectionScreen.setBackground(Color.WHITE);

        // Panel central pour le contenu principal
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        //centerPanel.setBackground(Color.WHITE);
        centerPanel.setOpaque(false);

        // Logo
        ImageIcon logoIcon = new ImageIcon("src/main/resources/aled_logo.png");
        logoIcon = Utils.redimImage(logoIcon, 300, 190); // Logo plus grand
        logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Titre
        JLabel titleLabel = new JLabel("Bienvenue dans ALED");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Titre plus grand
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Boutons
        JButton userButton = Utils.creerBouton("Je suis utilisateur");
        JButton validatorButton = Utils.creerBouton("Je suis valideur");

        userButton.addActionListener(e -> choixUtilisateur());
        validatorButton.addActionListener(e -> choixValideur());

        // Espacement et ajout au panel central
        centerPanel.add(Box.createVerticalStrut(40)); // Espacement en haut
        centerPanel.add(logoLabel);
        centerPanel.add(Box.createVerticalStrut(20)); // Espacement entre le logo et le titre
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createVerticalStrut(40)); // Espacement entre le titre et les boutons
        centerPanel.add(userButton);
        centerPanel.add(Box.createVerticalStrut(20)); // Espacement entre les boutons
        centerPanel.add(validatorButton);

        // Décoration en bas à droite
        ImageIcon decoIcon = new ImageIcon("src/main/resources/deco_selection.png");
        decoIcon = Utils.redimImage(decoIcon, 550, 350); // Taille adaptée à la décoration
        JLabel decoLabel = new JLabel(decoIcon);
        decoLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        //bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setOpaque(false);
        bottomPanel.add(decoLabel, BorderLayout.EAST); // Ajout de l'image à droite

        // Ajout des panels au screen principal
        selectionScreen.add(centerPanel, BorderLayout.CENTER);
        selectionScreen.add(bottomPanel, BorderLayout.SOUTH);
    }

    private static void choixUtilisateur(){
        estValideur = false;
        ConnexionScreen.updateUserTypeLabel(ConnexionScreen.userTypeLabel);
        VuePrincipale.allerALaPage("ConnexionScreen");
    }

    private static void choixValideur(){
        estValideur = true;
        ConnexionScreen.updateUserTypeLabel(ConnexionScreen.userTypeLabel);
        VuePrincipale.allerALaPage("ConnexionScreen");
    }
}
