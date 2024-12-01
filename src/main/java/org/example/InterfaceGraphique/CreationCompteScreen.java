package org.example.InterfaceGraphique;

import org.example.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static org.example.InterfaceGraphique.ConnexionScreen.connexionScreen;
import static org.example.InterfaceGraphique.VuePrincipale.utilisateurDAO;
import static org.example.InterfaceGraphique.VuePrincipale.valideurDAO;

public class CreationCompteScreen {

    public static JPanel creationCompteScreen;

    public static JTextField emailField;
    public static JTextField nomField;
    public static JTextField prenomField;
    public static JTextField adresseField;
    public static JPasswordField passwordField;
    public static JLabel userTypeLabel;

    /*public static void initCreationCompteScreen() {
        creationCompteScreen = new JPanel();
        creationCompteScreen.setBackground(Color.WHITE);
        creationCompteScreen.setLayout(new BoxLayout(creationCompteScreen, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Créer un compte");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel prenomLabel = new JLabel("Prénom :");
        prenomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        prenomField = new JTextField(15);
        prenomField.setMaximumSize(new Dimension(200, 25));

        JLabel nomLabel = new JLabel("Nom :");
        nomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nomField = new JTextField(15);
        nomField.setMaximumSize(new Dimension(200, 25));

        JLabel adresseLabel = new JLabel("Adresse :");
        adresseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        adresseField = new JTextField(15);
        adresseField.setMaximumSize(new Dimension(200, 25));

        JLabel emailLabel = new JLabel("Adresse e-mail :");
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField = new JTextField(15);
        emailField.setMaximumSize(new Dimension(200, 25));

        JLabel passwordLabel = new JLabel("Mot de passe :");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField = new JPasswordField(15);
        passwordField.setMaximumSize(new Dimension(200, 25));

        JButton createButton = Utils.creerBouton("Créer un compte");
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = Utils.creerBouton("Retour"); // Bouton pour revenir en arrière
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> VuePrincipale.allerALaPage("SelectionScreen"));

        creationCompteScreen.add(titleLabel);
        creationCompteScreen.add(Box.createVerticalStrut(20));
        creationCompteScreen.add(prenomLabel);
        creationCompteScreen.add(prenomField);
        creationCompteScreen.add(Box.createVerticalStrut(10));
        creationCompteScreen.add(nomLabel);
        creationCompteScreen.add(nomField);
        creationCompteScreen.add(Box.createVerticalStrut(10));
        creationCompteScreen.add(adresseLabel);
        creationCompteScreen.add(adresseField);
        creationCompteScreen.add(Box.createVerticalStrut(10));
        creationCompteScreen.add(emailLabel);
        creationCompteScreen.add(emailField);
        creationCompteScreen.add(Box.createVerticalStrut(10));
        creationCompteScreen.add(passwordLabel);
        creationCompteScreen.add(passwordField);
        creationCompteScreen.add(Box.createVerticalStrut(20));
        creationCompteScreen.add(createButton);
        creationCompteScreen.add(Box.createVerticalStrut(15));
        creationCompteScreen.add(backButton);

        createButton.addActionListener(e -> {
            try {
                createButton_listenner();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });


    }*/

    public static void initCreationCompteScreen() {

        Image bgImage;
        try {
            File imageFile = new File("src/main/resources/bg.jpg");
            bgImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            bgImage = null;
            e.printStackTrace();
        }

        Image finalBgImage = bgImage;
        creationCompteScreen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (finalBgImage != null) {
                    g.drawImage(finalBgImage, 0, 0, getWidth(), getHeight(), null);
                }
            }
        };

        creationCompteScreen.setLayout(new BorderLayout());

        // Créer le JPanel principal avec un BorderLayout
        //creationCompteScreen = new JPanel(new BorderLayout());

        // Panneau principal pour le formulaire
        JPanel formPanel = new JPanel();
        //formPanel.setBackground(Color.WHITE); // Fond blanc pour le formulaire
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        // Titre
        JLabel titleLabel = new JLabel("Créer un compte");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Titre plus grand
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(titleLabel);

        // Label dynamique "Valideur" ou "Utilisateur"
        userTypeLabel = new JLabel();
        userTypeLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Taille de police plus petite
        userTypeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.add(Box.createVerticalStrut(10)); // Espacement entre le titre et les champs
        formPanel.add(userTypeLabel);
        formPanel.add(Box.createVerticalStrut(20));

        // Champ Prénom
        JLabel prenomLabel = new JLabel("Prénom :");
        prenomLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Agrandir la police
        prenomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        prenomField = new JTextField(15);
        prenomField.setMaximumSize(new Dimension(300, 40)); // Agrandir le champ
        prenomField.setFont(new Font("Arial", Font.PLAIN, 18)); // Agrandir le texte

        // Champ Nom
        JLabel nomLabel = new JLabel("Nom :");
        nomLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Agrandir la police
        nomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nomField = new JTextField(15);
        nomField.setMaximumSize(new Dimension(300, 40)); // Agrandir le champ
        nomField.setFont(new Font("Arial", Font.PLAIN, 18)); // Agrandir le texte

        // Champ Adresse
        JLabel adresseLabel = new JLabel("Adresse :");
        adresseLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Agrandir la police
        adresseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        adresseField = new JTextField(15);
        adresseField.setMaximumSize(new Dimension(300, 40)); // Agrandir le champ
        adresseField.setFont(new Font("Arial", Font.PLAIN, 18)); // Agrandir le texte

        // Champ Email
        JLabel emailLabel = new JLabel("Adresse e-mail :");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Agrandir la police
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField = new JTextField(15);
        emailField.setMaximumSize(new Dimension(300, 40)); // Agrandir le champ
        emailField.setFont(new Font("Arial", Font.PLAIN, 18)); // Agrandir le texte

        // Champ Mot de Passe
        JLabel passwordLabel = new JLabel("Mot de passe :");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Agrandir la police
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField = new JPasswordField(15);
        passwordField.setMaximumSize(new Dimension(300, 40)); // Agrandir le champ
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18)); // Agrandir le texte

        // Bouton Créer un compte
        JButton createButton = Utils.creerBouton("Créer un compte");
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Bouton Retour discret
        JLabel backLabel = new JLabel("  <");
        backLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        backLabel.setForeground(Color.GRAY);
        backLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VuePrincipale.allerALaPage("SelectionScreen");
            }
        });

        // Espacements et ajout des composants au panneau
        formPanel.add(prenomLabel);
        formPanel.add(prenomField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(nomLabel);
        formPanel.add(nomField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(adresseLabel);
        formPanel.add(adresseField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(createButton);
        formPanel.add(Box.createVerticalStrut(15));

        // Ajouter le panneau du formulaire à l'écran principal
        creationCompteScreen.add(formPanel, BorderLayout.CENTER);

        // Ajouter le bouton retour en haut à gauche
        JPanel topPanel = new JPanel(new BorderLayout());
        //topPanel.setBackground(Color.WHITE);
        topPanel.setOpaque(false);
        topPanel.add(backLabel, BorderLayout.WEST);
        creationCompteScreen.add(topPanel, BorderLayout.NORTH);

        // Image de décoration en bas
        ImageIcon decoImage = new ImageIcon("src/main/resources/deco_creation_compte.png"); // Change selon ton chemin
        decoImage = Utils.redimImage(decoImage, 450, 250); // Redimensionner l'image
        JLabel decoLabel = new JLabel(decoImage);
        JPanel imagePanel = new JPanel(new BorderLayout());
        //imagePanel.setBackground(Color.WHITE);
        imagePanel.setOpaque(false);
        imagePanel.add(decoLabel, BorderLayout.SOUTH);

        // Ajouter l'image de déco en bas
        creationCompteScreen.add(imagePanel, BorderLayout.SOUTH);

        // Action pour créer le compte
        createButton.addActionListener(e -> {
            try {
                createButton_listenner();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private static void createButton_listenner() throws SQLException {
        if (validerCreation(prenomField, nomField, adresseField, emailField, passwordField, SelectionScreen.isEstValideur())) {
            // Si la connexion réussit, aller à l'écran utilisateur
            VuePrincipale.mainPanel.add(UtilisateurScreen.utilisateurScreen, "UtilisateurScreen");
            JOptionPane.showMessageDialog(connexionScreen, "Compte créé avec succés !",
                    "Compte créé", JOptionPane.INFORMATION_MESSAGE);
            VuePrincipale.allerALaPage("ConnexionScreen");
        }
        else {
            String password = new String(passwordField.getPassword());
            if (!Utils.emailValide(emailField.getText()))
            {
                JOptionPane.showMessageDialog(connexionScreen, "La création du compte a échoué. L'adresse e-mail n'est pas valide.",
                        "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
            }
            else if (!Utils.passwordValide(password))
            {
                JOptionPane.showMessageDialog(connexionScreen, "La création du compte a échoué. Le mot de passe n'est pas valide. Il doit contenir au moins un chiffre et une lettre.",
                        "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
            }
            else if (SelectionScreen.isEstValideur() && valideurDAO.emailExiste(emailField.getText()))
            {
                JOptionPane.showMessageDialog(connexionScreen, "La création du compte a échoué. L'email existe déjà.",
                        "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
            }
            else if (!SelectionScreen.isEstValideur() && utilisateurDAO.emailExiste(emailField.getText()))
            {
                JOptionPane.showMessageDialog(connexionScreen, "La création du compte a échoué. L'email existe déjà.",
                        "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                // Si la creation échoue, afficher un message d'erreur
                JOptionPane.showMessageDialog(connexionScreen, "La création du compte a échoué. Veuillez réessayer.",
                        "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private static boolean validerCreation(JTextField prenomField, JTextField nomField, JTextField adresseField, JTextField emailField, JPasswordField passwordField, boolean estValideur) {
        try {
            // Récupérer les valeurs des champs de texte
            String prenom = prenomField.getText();
            String nom = nomField.getText();
            String email = emailField.getText();
            String adresse = adresseField.getText();
            String password = new String(passwordField.getPassword());

            if (estValideur) {
                // Vérification pour un valideur
                if (Utils.enregistrerNouveauValideur(nom, prenom, email, adresse, password, VuePrincipale.valideurDAO)) {
                    System.out.println("Nouveau valideur ajouté à la base de donnée !");
                    return true;
                } else {
                    System.out.println("Échec de la creation du nouveau valideur.");
                }
            } else {
                // Vérification pour un utilisateur
                if (Utils.enregistrerNouvelUtilisateur(nom, prenom, email, adresse, password, VuePrincipale.utilisateurDAO)) {
                    System.out.println("Nouvel utilisateur ajouté à la base de donnée !");
                    //utiliser recupererUtilisateurConnecte
                    return true;
                } else {
                    System.out.println("Échec de la creation du nouvel utilisateur.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL lors de la tentative de création : " + e.getMessage());
        }
        return false;
    }

    public static void updateUserTypeLabel(JLabel userTypeLabel) {
        // Mise à jour du texte du label en fonction de la valeur de isEstValideur
        userTypeLabel.setText(SelectionScreen.isEstValideur() ? "Compte Valideur" : "Compte Utilisateur");
    }



}
