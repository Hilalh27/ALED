package org.example.InterfaceGraphique;

import org.example.BDDCommunication.UtilisateurDAO;
import org.example.BDDCommunication.ValideurDAO;
import org.example.Utils;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ConnexionScreen {
    public static JTextField emailField;
    public static JPasswordField passwordField;

    public static JPanel connexionScreen;

    public static void initConnexionScreen () {
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

        connexionButton.addActionListener(e -> {
            UtilisateurDAO utilisateurDAO = null;
            try {
                utilisateurDAO = new UtilisateurDAO();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ValideurDAO valideurDAO = null;
            try {
                valideurDAO = new ValideurDAO();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (validerConnexion(emailField, passwordField, SelectionScreen.isEstValideur(), utilisateurDAO, valideurDAO)) {
                // Si la connexion réussit, aller à l'écran utilisateur
                VuePrincipale.mainPanel.add(UtilisateurScreen.splashScreen, "UtilisateurScreen");
                if(SelectionScreen.isEstValideur()) {
                    VuePrincipale.allerALaPage("ValideurScreen");
                }
                else{
                    VuePrincipale.allerALaPage("UtilisateurScreen");
                }
            } else {
                // Si la connexion échoue, afficher un message d'erreur
                JOptionPane.showMessageDialog(connexionScreen, "Connexion échouée. Vérifiez vos identifiants.",
                        "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
            }
        });


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

    public static boolean validerConnexion(JTextField emailField, JPasswordField passwordField, boolean estValideur, UtilisateurDAO utilisateurDAO, ValideurDAO valideurDAO) {
        try {
            // Récupérer les valeurs des champs de texte
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (estValideur) {
                // Vérification pour un valideur
                if (Utils.connexionValideur(email, password)) {
                    System.out.println("Connexion valideur réussie !");
                    //utiliser recupererValideurConnecte
                    return true;
                } else {
                    System.out.println("Échec de la connexion en tant que valideur.");
                }
            } else {
                // Vérification pour un utilisateur
                if (Utils.connexionUtilisateur(email, password)) {
                    System.out.println("Connexion utilisateur réussie !");
                    //utiliser recupererUtilisateurConnecte
                    return true;
                } else {
                    System.out.println("Échec de la connexion en tant qu'utilisateur.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL lors de la tentative de connexion : " + e.getMessage());
        }
        return false;
    }



}
