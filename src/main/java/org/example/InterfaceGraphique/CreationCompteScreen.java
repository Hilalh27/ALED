package org.example.InterfaceGraphique;

import org.example.Utils;

import javax.swing.*;
import java.awt.*;

public class CreationCompteScreen {

    public static JPanel creationCompteScreen;
    public static JTextField emailField;
    public static JPasswordField passwordField;

    public static void initCreationCompteScreen() {
        creationCompteScreen = new JPanel();
        creationCompteScreen.setBackground(Color.WHITE);
        creationCompteScreen.setLayout(new BoxLayout(creationCompteScreen, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Créer un compte");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField nameField = new JTextField(15);
        nameField.setMaximumSize(new Dimension(200, 25));
        JTextField surnameField = new JTextField(15);
        surnameField.setMaximumSize(new Dimension(200, 25));
        emailField = new JTextField(15);
        emailField.setMaximumSize(new Dimension(200, 25));
        passwordField = new JPasswordField(15);
        passwordField.setMaximumSize(new Dimension(200, 25));

        JButton createButton = Utils.creerBouton("Créer un compte");
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = Utils.creerBouton("Retour"); // Bouton pour revenir en arrière
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> VuePrincipale.allerALaPage("SelectionScreen"));

        creationCompteScreen.add(titleLabel);
        creationCompteScreen.add(Box.createVerticalStrut(20));
        creationCompteScreen.add(nameField);
        creationCompteScreen.add(Box.createVerticalStrut(10));
        creationCompteScreen.add(surnameField);
        creationCompteScreen.add(Box.createVerticalStrut(10));
        creationCompteScreen.add(emailField);
        creationCompteScreen.add(Box.createVerticalStrut(10));
        creationCompteScreen.add(passwordField);
        creationCompteScreen.add(Box.createVerticalStrut(20));
        creationCompteScreen.add(createButton);
        creationCompteScreen.add(Box.createVerticalStrut(15));
        creationCompteScreen.add(backButton);
    }
}
