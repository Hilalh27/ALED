package org.example.InterfaceGraphique;

import org.example.ClassesLocales.Utilisateur;
import org.example.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CreationMissionSpontaneeScreen {

    public static JPanel creationMissionSpontaneeScreen;

    public static void initCreerMissionSpontanneeScreen() throws IOException {
        Image bgImage;
        try {
            File imageFile = new File("src/main/resources/bg.jpg");
            bgImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            bgImage = null;
            e.printStackTrace();
        }

        Image finalBgImage = bgImage;

        // Initialiser le panneau principal avec un fond personnalisé
        creationMissionSpontaneeScreen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (finalBgImage != null) {
                    g.drawImage(finalBgImage, 0, 0, getWidth(), getHeight(), null);
                }
            }
        };

        // Définir un BorderLayout pour le panneau principal
        creationMissionSpontaneeScreen.setLayout(new BorderLayout());

        // Panneau gauche : Formulaire de création de mission
        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Titre du formulaire
        JLabel titleLabel = new JLabel("Proposer une mission");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(titleLabel);
        leftPanel.add(Box.createVerticalStrut(20)); // Espacement

        // Champ de description
        JLabel descriptionLabel = new JLabel("Description de la mission :");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(descriptionLabel);

        JTextArea descriptionField = new JTextArea(5, 20) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    File imageFile = new File("src/main/resources/fond_benevole.png");
                    Image backgroundImage = ImageIO.read(imageFile);
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        descriptionField.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionField.setOpaque(true); // Fond visible
        //descriptionField.setBackground(Color.WHITE); // Fond blanc pour le texte
        descriptionField.setLineWrap(true);
        descriptionField.setWrapStyleWord(true);
        descriptionField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        leftPanel.add(descriptionField);
        leftPanel.add(Box.createVerticalStrut(20)); // Espacement

        // Bouton pour proposer une mission
        JButton proposeButton = Utils.creerBouton("Je propose une mission !");
        proposeButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Action lors de l'appui sur le bouton
        proposeButton.addActionListener(e -> {
            String description = descriptionField.getText().trim();
            if (description.isEmpty()) {
                JOptionPane.showMessageDialog(null, "La description ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Utilisateur utilisateurCourant = ConnexionScreen.utilisateur_courant;
                if (utilisateurCourant != null) {
                    Utils.proposerMission(
                            utilisateurCourant,
                            description,
                            VuePrincipale.missionDAO,
                            VuePrincipale.utilisateurDAO
                    );
                    JOptionPane.showMessageDialog(null, "Mission proposée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);

                    // Réinitialiser le champ de description
                    descriptionField.setText("");

                    // Revenir à l'écran précédent
                    VuePrincipale.allerALaPage("UtilisateurScreen");

                    // Mettre à jour les informations
                    UtilisateurProfilScreen.mesMissionsSpontanneesPanel = UtilisateurProfilScreen.AfficherMesMissionSpontannees(
                            "Missions que j'ai proposées",
                            Utils.avoirToutesMissions(VuePrincipale.missionDAO)
                    );
                    UtilisateurProfilScreen.missionsDemandeesPanel = UtilisateurProfilScreen.AfficherMesMissionsDemandees(
                            "Missions que j'ai demandées",
                            Utils.avoirToutesMissions(VuePrincipale.missionDAO)
                    );
                    UtilisateurProfilScreen.panelMissions.removeAll();
                    UtilisateurProfilScreen.panelMissions.add(UtilisateurProfilScreen.mesMissionsSpontanneesPanel);
                    UtilisateurProfilScreen.panelMissions.add(UtilisateurProfilScreen.missionsDemandeesPanel);
                    UtilisateurProfilScreen.panelMissions.revalidate();
                    UtilisateurProfilScreen.panelMissions.repaint();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la création de la mission.", "Erreur", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        leftPanel.add(proposeButton);

        // Panneau droit : Image
        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new BorderLayout());

        try {
            File imageFile = new File("src/main/resources/propose_aide.png");
            Image image = ImageIO.read(imageFile);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            rightPanel.add(imageLabel, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ajouter les panneaux au panneau principal
        creationMissionSpontaneeScreen.add(leftPanel, BorderLayout.WEST);
        creationMissionSpontaneeScreen.add(rightPanel, BorderLayout.CENTER);

        // Bouton Retour discret
        JLabel backLabel = new JLabel("  <");
        backLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        backLabel.setForeground(Color.GRAY);
        backLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VuePrincipale.allerALaPage("UtilisateurScreen");
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(backLabel, BorderLayout.WEST);

        // Ajouter le panneau supérieur
        creationMissionSpontaneeScreen.add(topPanel, BorderLayout.NORTH);
    }
}
