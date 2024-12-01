package org.example.InterfaceGraphique;

import org.example.ClassesLocales.Mission;
import org.example.ClassesLocales.Utilisateur;
import org.example.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.example.InterfaceGraphique.ConnexionScreen.connexionScreen;

public class UtilisateurProfilScreen extends JFrame {
    public static JPanel utilisateurProfilScreen;
    static Utilisateur utilisateur_courant;
    public static JPanel panelMissions;
    public static JPanel mesMissionsSpontanneesPanel;
    public static JPanel missionsDemandeesPanel;

    public static void initUtilisateurProfilScreen() throws SQLException {

        utilisateur_courant = ConnexionScreen.utilisateur_courant;

        // Chargement de l'image de fond
        Image bgImage;
        try {
            File imageFile = new File("src/main/resources/bg.jpg");
            bgImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            bgImage = null;
            e.printStackTrace();
        }

        Image finalBgImage = bgImage;
        utilisateurProfilScreen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (finalBgImage != null) {
                    g.drawImage(finalBgImage, 0, 0, getWidth(), getHeight(), null);
                }
            }
        };

        utilisateurProfilScreen.setLayout(new BorderLayout());

        // Panel pour le titre et l'adresse
        JPanel titrePanel = new JPanel();
        titrePanel.setOpaque(false);
        titrePanel.setLayout(new BoxLayout(titrePanel, BoxLayout.Y_AXIS));

        // Nom et prénom
        JLabel titreLabel = new JLabel(" " + utilisateur_courant.getPrenom() + " " + utilisateur_courant.getNom().toUpperCase());
        titreLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Aligné à gauche
        titreLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titreLabel.setForeground(Color.BLACK);

        // Adresse
        JLabel adresseLabel = new JLabel(" Mon adresse : " + utilisateur_courant.getAdresse());
        adresseLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Aligné à gauche
        adresseLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        adresseLabel.setForeground(Color.BLACK);

        // Ajouter le nom et l'adresse au panel
        titrePanel.add(titreLabel);
        titrePanel.add(Box.createVerticalStrut(10)); // Espacement vertical
        titrePanel.add(adresseLabel);

        // Panel pour les boutons "Accueil" et "Se Déconnecter"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Bouton "Accueil"
        JButton homeButton = new JButton("Accueil");
        ImageIcon originalIcon = new ImageIcon("src/main/resources/accueil.png");
        Image resizedImage = originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        homeButton.setIcon(new ImageIcon(resizedImage));
        homeButton.setFont(new Font("Arial", Font.PLAIN, 16));
        homeButton.setContentAreaFilled(false);
        homeButton.setBorderPainted(false);
        homeButton.setForeground(Color.BLACK);
        homeButton.setFocusable(false);
        homeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeButton.addActionListener(e -> VuePrincipale.allerALaPage("UtilisateurScreen"));

        // Bouton "Se Déconnecter"
        JButton logoutButton = new JButton("Se Déconnecter");
        ImageIcon originalIcon2 = new ImageIcon("src/main/resources/logout.png");
        Image resizedImage2 = originalIcon2.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        logoutButton.setIcon(new ImageIcon(resizedImage2));
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 16));
        logoutButton.setContentAreaFilled(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setForeground(Color.BLACK);
        logoutButton.setFocusable(false);
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.addActionListener(e -> {
            VuePrincipale.allerALaPage("SelectionScreen");
            JOptionPane.showMessageDialog(connexionScreen, "Vous avez été déconnecté.",
                    "Déconnexion", JOptionPane.INFORMATION_MESSAGE);
        });

        buttonPanel.add(homeButton);
        buttonPanel.add(logoutButton);

        // Panel supérieur contenant le titre et les boutons
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(titrePanel, BorderLayout.WEST); // Titre aligné à gauche
        topPanel.add(buttonPanel, BorderLayout.EAST); // Boutons alignés à droite

        mesMissionsSpontanneesPanel = AfficherMesMissionSpontannees(
                "Missions que j'ai proposées",
                Utils.avoirToutesMissions(VuePrincipale.missionDAO)
        );

        // Section des missions spontannées (missions dont je peux en profiter)
        missionsDemandeesPanel = AfficherMesMissionsDemandees(
                "Missions que j'ai demandées",
                Utils.avoirToutesMissions(VuePrincipale.missionDAO)
        );

        panelMissions = new JPanel(new GridLayout(1, 2));
        panelMissions.setOpaque(false);
        panelMissions.add(mesMissionsSpontanneesPanel); // Le premier tableau
        panelMissions.add(missionsDemandeesPanel); // Le deuxième tableau
        panelMissions.setOpaque(false);


        // Ajouter les panels au layout principal
        utilisateurProfilScreen.add(topPanel, BorderLayout.NORTH);
        utilisateurProfilScreen.add(panelMissions, BorderLayout.CENTER);
        utilisateurProfilScreen.setVisible(true);

    }

    public static JPanel AfficherMesMissionSpontannees(String title, List<Mission> missions) throws SQLException {
        // Panel principal avec une mise en page verticale
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(null, title, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16)));

        // Conteneur pour les cartes des missions
        JPanel missionsPanel = new JPanel();
        missionsPanel.setLayout(new BoxLayout(missionsPanel, BoxLayout.Y_AXIS));
        missionsPanel.setOpaque(false);

        // Ajouter une carte par mission
        for (Mission mission : missions) {
            if (utilisateur_courant != null && mission.getId_benevole() == Utils.getUserId(utilisateur_courant, VuePrincipale.utilisateurDAO)) {
                JPanel missionCard = new JPanel();
                missionCard.setLayout(new BorderLayout(5, 5)); // Réduction des espaces
                missionCard.setOpaque(false);
                missionCard.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Réduction des marges

                // Section gauche : description et infos du statut
                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                infoPanel.setOpaque(false);

                JLabel descriptionLabel = new JLabel("<html><b>" + mission.getDescription() + "</b></html>");
                descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));

                // Formatage de la date au format jj-mm-aaaa
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String dateFormatted = simpleDateFormat.format(mission.getDate());
                JLabel dateLabel = new JLabel("Date : " + dateFormatted);
                dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));

                // Statut de la mission
                JLabel statutLabel = new JLabel(mission.getStatut().toUpperCase());
                statutLabel.setFont(new Font("Arial", Font.BOLD, 14));

                // Définir la couleur du statut
                switch (mission.getStatut().toLowerCase()) {
                    case "validée":
                        statutLabel.setForeground(new Color(12, 113, 48)); // Vert
                        break;
                    case "refusée":
                        statutLabel.setForeground(Color.RED);
                        break;
                    case "acceptée":
                        statutLabel.setForeground(new Color(0, 128, 255)); // Bleu
                        statutLabel.setText("ACCEPTÉE PAR " + Utils.getUserPrenom(mission.getId_demandeur()).toUpperCase());
                        break;
                    case "en attente":
                        statutLabel.setForeground(new Color(250, 170, 64));
                        break;
                    case "réalisée":
                        statutLabel.setForeground(new Color(75, 0, 130)); // Indigo
                        statutLabel.setText("RÉALISÉE POUR " + Utils.getUserPrenom(mission.getId_demandeur()).toUpperCase());
                        break;
                    default:
                        statutLabel.setForeground(Color.BLACK);
                        break;
                }

                // Ajouter les composants dans le panneau d'informations
                infoPanel.add(descriptionLabel);
                infoPanel.add(dateLabel);
                infoPanel.add(statutLabel);

                // Si refusée, ajouter le motif du refus
                if ("refusée".equalsIgnoreCase(mission.getStatut()) && mission.getMotif_refus() != null && !mission.getMotif_refus().isEmpty()) {
                    JLabel motifLabel = new JLabel("<html><i>Motif : " + mission.getMotif_refus() + "</i></html>");
                    motifLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                    motifLabel.setForeground(Color.DARK_GRAY);
                    infoPanel.add(motifLabel);
                }

                // Si acceptée, ajouter le motif du refus
                if ("acceptée".equalsIgnoreCase(mission.getStatut())) {
                    JLabel acceptLabel = new JLabel("<html><i>" + Utils.getUserAdresse(mission.getId_demandeur()).toUpperCase() + " - " + Utils.getUserMail(mission.getId_demandeur()).toUpperCase() + "</i></html>");
                    acceptLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                    acceptLabel.setForeground(Color.DARK_GRAY);
                    infoPanel.add(acceptLabel);
                }

                // Si réalisée, donner un avis
                if ("réalisée".equalsIgnoreCase(mission.getStatut())) {
                    // Ajouter le bouton "Laisser un avis !" pour les missions réalisées
                    JButton leaveReviewButton = new JButton("Laisser un avis !");
                    leaveReviewButton.setFont(new Font("Arial", Font.BOLD, 12));
                    //leaveReviewButton.setForeground(Color.WHITE);
                    //leaveReviewButton.setBackground(new Color(0, 128, 255)); // Bleu
                    leaveReviewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    //leaveReviewButton.setOpaque(true);
                    //leaveReviewButton.setBorderPainted(false);

                    // Action lors du clic sur le bouton
                    leaveReviewButton.addActionListener(e -> {
                        // Afficher l'ID de la mission dans les logs
                        System.out.println("Mission ID pour avis: " + mission.getId_mission());
                    });
                    infoPanel.add(leaveReviewButton);
                }

                // Ajouter les composants dans la carte
                missionCard.add(infoPanel, BorderLayout.CENTER);

                // Ajouter la carte dans le panneau des missions
                missionsPanel.add(missionCard);
                missionsPanel.add(Box.createVerticalStrut(2)); // Espacement réduit entre les cartes
            }
        }

        // Ajouter un scroll si nécessaire
        JScrollPane scrollPane = new JScrollPane(missionsPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        // Ajouter le scrollPane au panneau principal
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public static JPanel AfficherMesMissionsDemandees(String title, List<Mission> missions) throws SQLException {
        // Panel principal avec une mise en page verticale
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(null, title, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16)));

        // Conteneur pour les cartes des missions
        JPanel missionsPanel = new JPanel();
        missionsPanel.setLayout(new BoxLayout(missionsPanel, BoxLayout.Y_AXIS));
        missionsPanel.setOpaque(false);

        // Ajouter une carte par mission
        for (Mission mission : missions) {
            if (utilisateur_courant != null && mission.getId_demandeur() == Utils.getUserId(utilisateur_courant, VuePrincipale.utilisateurDAO)) {
                JPanel missionCard = new JPanel();
                missionCard.setLayout(new BorderLayout(5, 5)); // Réduction des espaces
                missionCard.setOpaque(false);
                missionCard.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Réduction des marges

                // Section gauche : description et infos du statut
                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                infoPanel.setOpaque(false);

                JLabel descriptionLabel = new JLabel("<html><b>" + mission.getDescription() + "</b></html>");
                descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));

                // Formatage de la date au format jj-mm-aaaa
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String dateFormatted = simpleDateFormat.format(mission.getDate());
                JLabel dateLabel = new JLabel("Date : " + dateFormatted);
                dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));

                // Statut de la mission
                JLabel statutLabel = new JLabel(mission.getStatut().toUpperCase());
                statutLabel.setFont(new Font("Arial", Font.BOLD, 14));

                // Définir la couleur du statut
                switch (mission.getStatut().toLowerCase()) {
                    case "validée":
                        statutLabel.setForeground(new Color(12, 113, 48)); // Vert
                        break;
                    case "refusée":
                        statutLabel.setForeground(Color.RED);
                        break;
                    case "acceptée":
                        statutLabel.setForeground(new Color(0, 128, 255)); // Bleu
                        statutLabel.setText("ACCEPTÉE PAR " + Utils.getUserPrenom(mission.getId_benevole()).toUpperCase());
                        break;
                    case "en attente":
                        statutLabel.setForeground(new Color(250, 170, 64));
                        break;
                    case "réalisée":
                        statutLabel.setForeground(new Color(75, 0, 130)); // Indigo
                        statutLabel.setText("RÉALISÉE PAR " + Utils.getUserPrenom(mission.getId_benevole()).toUpperCase());
                        break;
                    default:
                        statutLabel.setForeground(Color.BLACK);
                        break;
                }

                // Ajouter les composants dans le panneau d'informations
                infoPanel.add(descriptionLabel);
                infoPanel.add(dateLabel);
                infoPanel.add(statutLabel);

                // Si acceptée, ajouter les informations du bénévole
                if ("acceptée".equalsIgnoreCase(mission.getStatut())) {
                    JLabel benevoleInfoLabel = new JLabel("<html><i>" + Utils.getUserAdresse(mission.getId_benevole()).toUpperCase() + " - " +
                            Utils.getUserMail(mission.getId_benevole()).toUpperCase() + "</i></html>");
                    benevoleInfoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                    benevoleInfoLabel.setForeground(Color.DARK_GRAY);
                    infoPanel.add(benevoleInfoLabel);
                }

                // Si réalisée, donner un avis
                if ("réalisée".equalsIgnoreCase(mission.getStatut())) {
                    // Ajouter le bouton "Laisser un avis !" pour les missions réalisées
                    JButton leaveReviewButton = new JButton("Laisser un avis !");
                    leaveReviewButton.setFont(new Font("Arial", Font.BOLD, 12));
                    //leaveReviewButton.setForeground(Color.WHITE);
                    //leaveReviewButton.setBackground(new Color(0, 128, 255)); // Bleu
                    leaveReviewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    //leaveReviewButton.setOpaque(true);
                    //leaveReviewButton.setBorderPainted(false);

                    // Action lors du clic sur le bouton
                    leaveReviewButton.addActionListener(e -> {
                        // Afficher l'ID de la mission dans les logs
                        System.out.println("Mission ID pour avis: " + mission.getId_mission());
                    });
                    infoPanel.add(leaveReviewButton);
                }

                // Ajouter les composants dans la carte
                missionCard.add(infoPanel, BorderLayout.CENTER);

                // Ajouter la carte dans le panneau des missions
                missionsPanel.add(missionCard);
                missionsPanel.add(Box.createVerticalStrut(2)); // Espacement réduit entre les cartes
            }
        }

        // Ajouter un scroll si nécessaire
        JScrollPane scrollPane = new JScrollPane(missionsPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        // Ajouter le scrollPane au panneau principal
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }



}
