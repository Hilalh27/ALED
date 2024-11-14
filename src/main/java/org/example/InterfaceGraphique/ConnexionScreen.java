package org.example.InterfaceGraphique;

import org.example.Utils;

import javax.swing.*;
import java.awt.*;

public class ConnexionScreen {
    public static JTextField emailField;
    public static JPasswordField passwordField;

    public static JPanel connexionScreen;

    public static void initConnexionScreen() {
        connexionScreen = new JPanel(new BorderLayout());
        connexionScreen.setBackground(Color.WHITE);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField = new JTextField(15);
        emailField.setMaximumSize(new Dimension(200, 25)); // Champ texte réduit

        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField = new JPasswordField(15);
        passwordField.setMaximumSize(new Dimension(200, 25)); // Champ texte réduit

        JButton connexionButton = Utils.creerBouton("Se connecter");
        connexionButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel createAccountLabel = new JLabel("<html><b>Créer un nouveau compte</b></html>");
        createAccountLabel.setForeground(Color.BLACK);
        createAccountLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createAccountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccountLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VuePrincipale.allerALaPage("CreationCompteScreen");
            }
        });

        JButton backButton = Utils.creerBouton("Retour"); // Bouton pour revenir en arrière
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> VuePrincipale.allerALaPage("SelectionScreen"));

        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(connexionButton);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(createAccountLabel);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(backButton);

        connexionScreen.add(formPanel, BorderLayout.CENTER);
    }
}
