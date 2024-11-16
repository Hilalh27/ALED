package org.example.InterfaceGraphique;

import org.example.Utils;

import javax.swing.*;
import java.awt.*;

public class SelectionScreen {

    public static JPanel selectionScreen;
    public static JLabel logoLabel;

    private static boolean estValideur;

    public static boolean isEstValideur() {
        return estValideur;
    }

    public static void initSelectionScreen() {

        selectionScreen = new JPanel(new BorderLayout());
        selectionScreen.setBackground(Color.WHITE);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);

        ImageIcon logoIcon = new ImageIcon("src/main/resources/aled_logo.png");
        logoIcon = Utils.redimImage(logoIcon, 100, 80); // Logo réduit
        logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("Bienvenue dans ALED");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton userButton = Utils.creerBouton("Utilisateur");
        JButton validatorButton = Utils.creerBouton("Valideur");

        userButton.addActionListener(e -> choixUtilisateur ());
        validatorButton.addActionListener(e -> choixValideur ());

        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(logoLabel);
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(userButton);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(validatorButton);

        selectionScreen.add(centerPanel, BorderLayout.CENTER);
    }

    private static void choixUtilisateur(){
        VuePrincipale.allerALaPage("ConnexionScreen");
        estValideur = false;
    }

    private static void choixValideur(){
        VuePrincipale.allerALaPage("ConnexionScreen");
        estValideur = true;
    }
}
