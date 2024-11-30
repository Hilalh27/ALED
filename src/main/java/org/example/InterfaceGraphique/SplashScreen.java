package org.example.InterfaceGraphique;

import javax.swing.*;
import java.awt.*;

public class SplashScreen {

    public static JPanel splashScreen;
    private static JLabel splashImageLabel;

    public static void initSplashScreen() {
        // Créer le panel principal
        splashScreen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image splashImage = new ImageIcon("src/main/resources/aled_logo_splash.png").getImage();
                g.drawImage(splashImage, 0, 0, getWidth(), getHeight(), this); // Ajuste l'image à la taille du panel
            }
        };

        splashScreen.setBackground(Color.WHITE); // Couleur de fond blanche
        splashScreen.setLayout(new BorderLayout());

        // Ajouter un texte ou autre élément si nécessaire
        JLabel loadingText = new JLabel("Chargement en cours...", SwingConstants.CENTER);
        loadingText.setFont(new Font("Arial", Font.BOLD, 16));
        loadingText.setForeground(Color.GRAY);

        splashScreen.add(loadingText, BorderLayout.SOUTH); // Texte centré en bas
    }
}
