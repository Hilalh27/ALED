package org.example.InterfaceGraphique;

import org.example.BDDCommunication.UtilisateurDAO;
import org.example.BDDCommunication.ValideurDAO;
import org.example.ClassesLocales.Utilisateur;
import org.example.ClassesLocales.Valideur;
import org.example.Utils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

/*public class ConnexionScreen {
    public static JTextField emailField;
    public static JPasswordField passwordField;

    public static Utilisateur utilisateur_courant;
    public static Valideur valideur_courant;

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
            try {
                connexionButton_listener();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
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

    }*/

    public class ConnexionScreen {
        public static JTextField emailField;
        public static JPasswordField passwordField;

        public static Utilisateur utilisateur_courant;
        public static Valideur valideur_courant;

        public static JPanel connexionScreen;
        public static JLabel userTypeLabel;

        public static void initConnexionScreen() throws IOException {

            connexionScreen = new JPanel(new BorderLayout());

            connexionScreen.setBackground(Color.WHITE);

            JPanel formPanel = new JPanel();
            formPanel.setBackground(Color.WHITE);
            formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

            // Titre
            JLabel titleLabel = new JLabel("Se connecter");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Agrandir le titre
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            formPanel.add(titleLabel);

            formPanel.add(Box.createVerticalStrut(30)); // Espacement entre le titre et les champs

            // Label dynamique "Valideur" ou "Utilisateur"
            userTypeLabel = new JLabel();
            userTypeLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Taille de police plus petite
            userTypeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            //userTypeLabel.setText(SelectionScreen.isEstValideur() ? "Compte valideur" : "Compte utilisateur"); // Mise à jour en fonction de la variable

            formPanel.add(userTypeLabel);
            formPanel.add(Box.createVerticalStrut(20));

            // Champ Email
            JLabel emailLabel = new JLabel("Email:");
            emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            emailField = new JTextField(15);
            emailField.setMaximumSize(new Dimension(300, 40)); // Agrandir le champ
            emailField.setFont(new Font("Arial", Font.PLAIN, 18)); // Agrandir le texte

            // Champ Mot de Passe
            JLabel passwordLabel = new JLabel("Mot de passe:");
            passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            passwordField = new JPasswordField(15);
            passwordField.setMaximumSize(new Dimension(300, 40)); // Agrandir le champ
            passwordField.setFont(new Font("Arial", Font.PLAIN, 18)); // Agrandir le texte

            // Bouton de connexion
            JButton connexionButton = Utils.creerBouton("Se connecter");
            connexionButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            connexionButton.addActionListener(e -> {
                try {
                    connexionButton_listener();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });

            // Créer un compte
            JLabel createAccountLabel = new JLabel("<html><b>Créer un nouveau compte</b></html>");
            createAccountLabel.setForeground(Color.BLACK);
            createAccountLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            createAccountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            createAccountLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    CreationCompteScreen.updateUserTypeLabel(CreationCompteScreen.userTypeLabel);
                    VuePrincipale.allerALaPage("CreationCompteScreen");
                }
            });

            // Bouton Retour discret
            JLabel backLabel = new JLabel("<");
            backLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            backLabel.setForeground(Color.GRAY);
            backLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            backLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    VuePrincipale.allerALaPage("SelectionScreen");
                }
            });

            // Espacements
            formPanel.add(Box.createVerticalStrut(10));
            formPanel.add(emailLabel);
            formPanel.add(emailField);
            formPanel.add(Box.createVerticalStrut(15));
            formPanel.add(passwordLabel);
            formPanel.add(passwordField);
            formPanel.add(Box.createVerticalStrut(20));
            formPanel.add(connexionButton);
            formPanel.add(Box.createVerticalStrut(20));
            //formPanel.add(createAccountLabel);
            //formPanel.add(Box.createVerticalStrut(20));

            JPanel createAccountPanel = new JPanel();
            createAccountPanel.setBackground(Color.WHITE);
            createAccountPanel.add(createAccountLabel);
            createAccountPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            formPanel.add(createAccountPanel);

            // Ajouter le bouton retour
            JPanel topPanel = new JPanel(new BorderLayout());
            topPanel.setBackground(Color.WHITE);
            topPanel.add(backLabel, BorderLayout.WEST);

            connexionScreen.add(topPanel, BorderLayout.NORTH); // Ajouter le "Retour" en haut
            connexionScreen.add(formPanel, BorderLayout.CENTER);

            // Image de décoration en bas à droite
            ImageIcon decoImage = new ImageIcon("src/main/resources/deco_connexion_utilisateur.png");
            decoImage = Utils.redimImage(decoImage, 550, 350);
            JLabel decoLabel = new JLabel(decoImage);
            JPanel imagePanel = new JPanel(new BorderLayout());
            imagePanel.setBackground(Color.WHITE);
            imagePanel.add(decoLabel, BorderLayout.SOUTH);

            connexionScreen.add(imagePanel, BorderLayout.SOUTH);
        }


    private static void connexionButton_listener() throws SQLException {
        if (validerConnexion(emailField, passwordField, SelectionScreen.isEstValideur())) {
            // Si la connexion réussit, aller à l'écran utilisateur
            VuePrincipale.mainPanel.add(UtilisateurScreen.utilisateurScreen, "UtilisateurScreen");
            if(SelectionScreen.isEstValideur()) {
                valideur_courant = Utils.recupererValideurConnecte(emailField.getText(), VuePrincipale.valideurDAO);
                VuePrincipale.allerALaPage("ValideurScreen");
            }
            else{
                utilisateur_courant = Utils.recupererUtilisateurConnecte(emailField.getText(), VuePrincipale.utilisateurDAO);
                VuePrincipale.allerALaPage("UtilisateurScreen");
            }
        } else {
            // Si la connexion échoue, afficher un message d'erreur
            JOptionPane.showMessageDialog(connexionScreen, "Connexion échouée. Vérifiez vos identifiants.",
                    "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
        }
    }
    private static boolean validerConnexion(JTextField emailField, JPasswordField passwordField, boolean estValideur) {
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

        public static void updateUserTypeLabel(JLabel userTypeLabel) {
            // Mise à jour du texte du label en fonction de la valeur de isEstValideur
            userTypeLabel.setText(SelectionScreen.isEstValideur() ? "Compte Valideur" : "Compte Utilisateur");
        }



}
