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
import static org.example.InterfaceGraphique.ConnexionScreen.utilisateur_courant;

public class UtilisateurScreen extends JFrame {

    public static JPanel utilisateurScreen;
    public static JLabel welcomeLabel;
    public static JPanel panelMissions;

    public static void initUtilisateurScreen() throws SQLException {
        Utilisateur utilisateur_courant = ConnexionScreen.utilisateur_courant;

        Image bgImage;
        try {
            File imageFile = new File("src/main/resources/bg.jpg");
            bgImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            bgImage = null;
            e.printStackTrace();
        }

        Image finalBgImage = bgImage;
        utilisateurScreen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (finalBgImage != null) {
                    g.drawImage(finalBgImage, 0, 0, getWidth(), getHeight(), null);
                }
            }
        };

        utilisateurScreen.setLayout(new BorderLayout());

        // Titre de bienvenue en haut
        JPanel titrePanel = new JPanel();
        titrePanel.setOpaque(false);
        //JLabel welcomeLabel = new JLabel("Bonjour ", );
        welcomeLabel = new JLabel();
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 50));
        welcomeLabel.setForeground(Color.BLACK);
        titrePanel.add(welcomeLabel);

        // Panel pour les boutons "Mon Profil" et "Se Déconnecter"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Bouton "Mon Profil"
        JButton profilButton = new JButton("Mon Profil");
        // Charge l'image originale
        ImageIcon originalIcon = new ImageIcon("src/main/resources//mon_profil.png");
        Image resizedImage = originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        profilButton.setIcon(resizedIcon);
        //profilButton.setIcon(new ImageIcon(Objects.requireNonNull(UtilisateurScreen.class.getResource("/mon_profil.png"))));
        profilButton.setFont(new Font("Arial", Font.PLAIN, 16));
        profilButton.setContentAreaFilled(false);
        profilButton.setBorderPainted(false);
        profilButton.setForeground(Color.BLACK);
        profilButton.setFocusable(false);
        profilButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


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
        logoutButton.addActionListener(e -> { VuePrincipale.allerALaPage("SelectionScreen");
                    JOptionPane.showMessageDialog(connexionScreen, "Vous avez été déconnecté.",
                            "Déconnexion", JOptionPane.INFORMATION_MESSAGE);});

        buttonPanel.add(profilButton);
        buttonPanel.add(logoutButton);

        // Panel general
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);

        panelMissions = new JPanel(new GridLayout(1, 2));
        panelMissions.setOpaque(false);

        // Bouton pour proposer une mission
        JButton proposeMissionButton = Utils.creerBouton("Proposer une Mission");

        JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonContainer.setOpaque(false); // Rendre transparent
        buttonContainer.add(proposeMissionButton);

        contentPanel.add(buttonContainer); // Ajouter le conteneur centré

        // Bouton pour demander de l'aide
        JButton demandeAide = Utils.creerBouton("J'ai besoin d'aide !");
        JPanel demandeAideButtonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        demandeAideButtonContainer.setOpaque(false); // Rendre transparent
        demandeAideButtonContainer.add(demandeAide);

        //contentPanel.add(Box.createVerticalStrut(20)); // Espace entre les sections

        JPanel buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.add(buttonContainer, BorderLayout.WEST);
        buttonsPanel.add(demandeAideButtonContainer, BorderLayout.EAST);
        buttonsPanel.setOpaque(false);

        ImageIcon decoIcon = new ImageIcon("src/main/resources/aide_dessin.png");
        decoIcon = Utils.redimImage(decoIcon, 550, 350); // Taille adaptée à la décoration
        JLabel decoLabel = new JLabel(decoIcon);
        decoLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Ajouter les panel de missions
        contentPanel.add(Box.createVerticalStrut(20)); // espace entre les sections
        contentPanel.add(panelMissions, BorderLayout.CENTER);
        contentPanel.add(Box.createVerticalStrut(20)); // espace entre les sections
        contentPanel.add(buttonsPanel, BorderLayout.SOUTH); // Ajouter le conteneur centré
        //contentPanel.add(proposeMissionButton, BorderLayout.SOUTH);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(titrePanel, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        // Ajouter les panels au layout principal
        utilisateurScreen.add(topPanel, BorderLayout.NORTH);
        utilisateurScreen.add(contentPanel, BorderLayout.CENTER);
        utilisateurScreen.add(decoLabel, BorderLayout.SOUTH);

        utilisateurScreen.setVisible(true);
    }

    public static JPanel AfficherMissionAccomplir(String title, List<Mission> missions) throws SQLException {
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
            if (utilisateur_courant != null && mission.getId_demandeur() != Utils.getUserId(utilisateur_courant, VuePrincipale.utilisateurDAO))
            {
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
                JLabel demandeurLabel = new JLabel(Utils.getUserPrenom(mission.getId_demandeur()));
                demandeurLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                JLabel adresseLabel = new JLabel(Utils.getUserAdresse(mission.getId_demandeur()));
                adresseLabel.setFont(new Font("Arial", Font.PLAIN, 12));

                // Formatage de la date au format jj-mm-aaaa
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String dateFormatted = simpleDateFormat.format(mission.getDate());
                JLabel dateLabel = new JLabel("Date : " + dateFormatted);
                dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));

                // Ajout des labels dans le panneau
                infoPanel.add(descriptionLabel);
                infoPanel.add(demandeurLabel);
                infoPanel.add(adresseLabel);
                infoPanel.add(dateLabel);

                // Bouton "Aider"
                JButton aiderButton = new JButton("Aider !");
                aiderButton.setFocusable(false);
                aiderButton.setFont(new Font("Arial", Font.BOLD, 12));
                aiderButton.addActionListener(e -> System.out.println("Mission ID : " + mission.getId_mission()));
                aiderButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                // Ajouter les composants dans la carte
                missionCard.add(infoPanel, BorderLayout.CENTER);
                missionCard.add(aiderButton, BorderLayout.EAST);

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

        // Fixer une taille réduite pour occuper le coin gauche
        //panel.setPreferredSize(new Dimension(100, 100)); // Ajuste les dimensions

        return panel;
    }

    public static JPanel AfficherMissionSpontannees(String title, List<Mission> missions) throws SQLException {
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
            if (utilisateur_courant != null && mission.getId_benevole() != Utils.getUserId(utilisateur_courant, VuePrincipale.utilisateurDAO))
            {
                JPanel missionCard = new JPanel();
                missionCard.setLayout(new BorderLayout(5, 5)); // Réduction des espaces
                missionCard.setOpaque(false);
                missionCard.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Réduction des marges

                // Section gauche : description et infos du bénévole
                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                infoPanel.setOpaque(false);

                JLabel descriptionLabel = new JLabel("<html><b>" + mission.getDescription() + "</b></html>");
                descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));

                // Utiliser l'id du bénévole pour récupérer ses informations
                JLabel benevoleLabel = new JLabel(Utils.getUserPrenom(mission.getId_benevole()));
                benevoleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                JLabel adresseLabel = new JLabel(Utils.getUserAdresse(mission.getId_benevole()));
                adresseLabel.setFont(new Font("Arial", Font.PLAIN, 12));

                // Formatage de la date au format jj-mm-aaaa
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String dateFormatted = simpleDateFormat.format(mission.getDate());
                JLabel dateLabel = new JLabel("Date : " + dateFormatted);
                dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));

                // Ajout des labels dans le panneau
                infoPanel.add(descriptionLabel);
                infoPanel.add(benevoleLabel);
                infoPanel.add(adresseLabel);
                infoPanel.add(dateLabel);

                // Bouton "En profiter"
                JButton enProfiterButton = new JButton("En profiter !");
                enProfiterButton.setFocusable(false);
                enProfiterButton.setFont(new Font("Arial", Font.BOLD, 12));
                enProfiterButton.addActionListener(e -> System.out.println("Mission ID : " + mission.getId_mission()));
                enProfiterButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                // Ajouter les composants dans la carte
                missionCard.add(infoPanel, BorderLayout.CENTER);
                missionCard.add(enProfiterButton, BorderLayout.EAST);

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

        // Fixer une taille réduite pour occuper le coin gauche
        //panel.setPreferredSize(new Dimension(100, 100)); // Ajuste les dimensions

        return panel;
    }
}
