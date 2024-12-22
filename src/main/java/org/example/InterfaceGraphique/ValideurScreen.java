package org.example.InterfaceGraphique;

import org.example.ClassesLocales.Mission;
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

public class ValideurScreen extends JFrame {

    public static JPanel valideurScreen;
    public static JLabel welcomeLabel;
    public static JLabel adresseLabel;
    public static JPanel panelMissions;

    public static void initValideurScreen() throws SQLException {

        Image bgImage;
        try {
            File imageFile = new File("src/main/resources/bg.jpg");
            bgImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            bgImage = null;
            e.printStackTrace();
        }

        Image finalBgImage = bgImage;
        valideurScreen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (finalBgImage != null) {
                    g.drawImage(finalBgImage, 0, 0, getWidth(), getHeight(), null);
                }
            }
        };

        valideurScreen.setLayout(new BorderLayout());

        // Titre de bienvenue en haut
        JPanel titrePanel = new JPanel();
        titrePanel.setOpaque(false);
        titrePanel.setLayout(new BoxLayout(titrePanel, BoxLayout.Y_AXIS));
        welcomeLabel = new JLabel();
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 50));
        welcomeLabel.setForeground(Color.BLACK);
        titrePanel.add(welcomeLabel);

        adresseLabel = new JLabel();
        adresseLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Aligné à gauche
        adresseLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        adresseLabel.setForeground(Color.BLACK);

        titrePanel.add(Box.createVerticalStrut(10)); // Espacement vertical
        titrePanel.add(adresseLabel);

        // Panel pour les boutons "Mon Profil" et "Se Déconnecter"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Bouton "Se Déconnecter"
        JButton logoutButton = new JButton("Se Déconnecter");
        ImageIcon originalIcon2 = new ImageIcon("src/main/resources/logout.png");
        Image resizedImage2 = originalIcon2.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
        logoutButton.setIcon(resizedIcon2);
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
            UtilisateurProfilScreen.panelMissions.removeAll();
            ConnexionScreen.utilisateur_courant = null;
            ConnexionScreen.valideur_courant = null;
        });

        buttonPanel.add(logoutButton);

        // Panel général
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);

        panelMissions = new JPanel(new GridLayout(1, 1));
        panelMissions.setOpaque(false);

        ImageIcon decoIcon = new ImageIcon("src/main/resources/pro-sante.png");
        decoIcon = Utils.redimImage(decoIcon, 550, 350); // Taille adaptée à la décoration
        JLabel decoLabel = new JLabel(decoIcon);
        decoLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Ajouter les panneaux de missions
        contentPanel.add(Box.createVerticalStrut(20)); // espace entre les sections
        contentPanel.add(panelMissions, BorderLayout.CENTER);
        contentPanel.add(Box.createVerticalStrut(20)); // espace entre les sections

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        topPanel.add(titrePanel, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        // Ajouter les panneaux au layout principal
        valideurScreen.add(topPanel, BorderLayout.NORTH);
        valideurScreen.add(contentPanel, BorderLayout.CENTER);
        valideurScreen.add(decoLabel, BorderLayout.SOUTH);
        valideurScreen.setVisible(true);

        // Initialiser les missions
        updateMissions(panelMissions);
    }

    public static JPanel AfficherMissionValider(String title, List<Mission> missions) throws SQLException {
        System.out.println("Affichage des missions à valider...");

        // Panel général avec mise en page verticale
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
            JPanel missionCard = new JPanel();
            missionCard.setLayout(new BorderLayout(5, 5)); // Réduction des espaces
            missionCard.setOpaque(false);
            missionCard.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Réduction des marges

            // Section gauche : description et infos du demandeur
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

            // Ajout des labels dans le panneau
            infoPanel.add(descriptionLabel);
            infoPanel.add(dateLabel);

            // Bouton "Valider"
            JButton aiderButton = new JButton("Valider :)");
            aiderButton.setFocusable(false);
            aiderButton.setFont(new Font("Arial", Font.BOLD, 12));
            aiderButton.addActionListener(e -> {         panel.removeAll(); panel.revalidate();
                validerBoutonListener(mission.getId_mission(), panel);});
            aiderButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // Bouton "Refuser"
            JButton refuserButton = new JButton("Refuser :(");
            refuserButton.setFocusable(false);
            refuserButton.setFont(new Font("Arial", Font.BOLD, 12));
            refuserButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            refuserButton.addActionListener(e -> {
                // Ouvrir une fenêtre de dialogue pour saisir le motif
                String motifRefus = JOptionPane.showInputDialog(null, "Motif du refus :", "Refuser la mission", JOptionPane.PLAIN_MESSAGE);
                if (motifRefus != null && !motifRefus.trim().isEmpty()) {
                    try {
                        Utils.refuserMission(ConnexionScreen.valideur_courant, mission.getId_mission(), motifRefus, VuePrincipale.missionDAO, VuePrincipale.valideurDAO);
                        JOptionPane.showMessageDialog(null, "Mission refusée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                        updateMissions(panel); // Appel de la méthode de mise à jour
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Une erreur est survenue lors du refus de la mission : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Le motif du refus est requis.", "Attention", JOptionPane.WARNING_MESSAGE);
                }
            });

            // Ajouter les composants dans la carte
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setOpaque(false);
            buttonPanel.add(aiderButton);
            buttonPanel.add(Box.createVerticalStrut(5));
            buttonPanel.add(refuserButton);

            missionCard.add(infoPanel, BorderLayout.CENTER);
            missionCard.add(buttonPanel, BorderLayout.EAST);

            // Ajouter la carte dans le panneau des missions
            missionsPanel.add(missionCard);
            missionsPanel.add(Box.createVerticalStrut(2)); // Espacement réduit entre les cartes
        }

        // Ajouter un scroll si nécessaire
        JScrollPane scrollPane = new JScrollPane(missionsPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        // Ajouter le scrollPane au panneau principal
        panel.add(scrollPane, BorderLayout.CENTER);

        System.out.println("Affichage des missions à valider terminé.");

        return panel;
    }

    public static void updateMissions(JPanel panel) throws SQLException {
        System.out.println("Mise à jour des missions...");

        JPanel missionsValiderPanel = ValideurScreen.AfficherMissionValider(
                "Missions à valider",
                Utils.avoirToutesMissionsEnAttente(VuePrincipale.missionDAO)
        );

        System.out.println("Missions à valider : " + Utils.avoirToutesMissionsEnAttente(VuePrincipale.missionDAO));

        panel.removeAll();
        panel.add(missionsValiderPanel); // Le tableau des missions à valider
        panel.setOpaque(false);
        panel.revalidate();
        panel.repaint(); // Ajout explicite pour actualiser le rendu graphique
        valideurScreen.repaint();
        VuePrincipale.mainPanel.repaint();
        System.out.println("Mise à jour des missions terminée.");
    }

    public static void validerBoutonListener(int mission_id, JPanel panel) {
        System.out.println("Mission ID : " + mission_id);
        try {
            Utils.validerMission(ConnexionScreen.valideur_courant, mission_id, VuePrincipale.missionDAO, VuePrincipale.valideurDAO);
            updateMissions(panel); // Appel de la méthode de mise à jour
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
