/*
package org.example.InterfaceGraphique;

import javax.swing.*;
import java.awt.*;

public class UtilisateurScreen extends JFrame {

    public static JPanel utilisateurScreen;

    public static void initUtilisateurScreen() {
        utilisateurScreen = new JPanel();
        utilisateurScreen.setBackground(Color.WHITE);
        //utilisateurScreen.setLayout(new BoxLayout(utilisateurScreen, BoxLayout.Y_AXIS));
        utilisateurScreen.setLayout(new GridLayout(2, 2, 10, 10)); // 2x2 sections avec espace entre les panneaux

        // Section 1 : Missions que je peux accomplir
        JPanel missionsValideesPanel = createSectionPanel(
                "Missions que je peux accomplir",
                "avoirToutesMissionsValidees()",
                new String[]{"Demandeur", "Mission"}
        );

        // Section 2 : Missions que je propose
        JPanel missionsProposeesPanel = createSectionPanel(
                "Les missions que je propose",
                "missionsEnCoursDemandeur()",
                new String[]{"Mission", "Statut"}
        );

        // Section 3 : Missions proposées par les bénévoles
        JPanel missionsSpontaneesPanel = createSectionPanel(
                "Missions proposées par les bénévoles",
                "avoirToutesMissionsSpontaneesValidees()",
                new String[]{"Bénévole", "Mission"}
        );

        // Section 4 : Les missions que je demande
        JPanel missionsDemandeesPanel = createSectionPanel(
                "Les missions que je demande",
                "missionsEnCoursBenevole()",
                new String[]{"Mission", "Statut"}
        );

        // Ajouter les sections au layout principal
        utilisateurScreen.add(missionsValideesPanel);
        utilisateurScreen.add(missionsProposeesPanel);
        utilisateurScreen.add(missionsSpontaneesPanel);
        utilisateurScreen.add(missionsDemandeesPanel);

        utilisateurScreen.setVisible(true);
    }

    // Méthode pour créer chaque section
    private static JPanel createSectionPanel(String title, String methodDescription, String[] columns) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));

        // Ajouter la description de la méthode
        JLabel methodLabel = new JLabel(methodDescription);
        panel.add(methodLabel, BorderLayout.NORTH);

        // Exemple de tableau vide pour afficher les colonnes
        JTable table = new JTable(new Object[0][columns.length], columns);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UtilisateurScreen::new);
    }
}
*/




package org.example.InterfaceGraphique;

import org.example.ClassesLocales.Mission;
import org.example.ClassesLocales.Utilisateur;
import org.example.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UtilisateurScreen extends JFrame {

    public static JPanel utilisateurScreen;

    public static void initUtilisateurScreen() throws SQLException {
        Utilisateur utilisateur_courant = ConnexionScreen.utilisateur_courant;

        // Panel principal avec fond
        utilisateurScreen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image bg = ImageIO.read(getClass().getResource("/bg.jpg"));
                    g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        utilisateurScreen.setLayout(new BorderLayout());

        // Titre de bienvenue en haut
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        JLabel welcomeLabel = new JLabel("Bonjour ", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setForeground(Color.WHITE);
        topPanel.add(welcomeLabel);

        // Panel pour les boutons "Mon Profil" et "Se Déconnecter"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Bouton "Mon Profil"
        JButton profilButton = new JButton("Mon Profil");
        profilButton.setIcon(new ImageIcon(UtilisateurScreen.class.getResource("/mon_profil.png")));
        profilButton.setFont(new Font("Arial", Font.PLAIN, 16));
        profilButton.setContentAreaFilled(false);
        profilButton.setBorderPainted(false);
        profilButton.setForeground(Color.WHITE);
        profilButton.setFocusable(false);

        // Bouton "Se Déconnecter"
        JButton logoutButton = new JButton("Se Déconnecter");
        logoutButton.setIcon(new ImageIcon(UtilisateurScreen.class.getResource("/logout.png")));
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 16));
        logoutButton.setContentAreaFilled(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusable(false);

        buttonPanel.add(profilButton);
        buttonPanel.add(logoutButton);

        // Panel pour les missions et autres actions
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);

        // Section des missions à accomplir
        JPanel missionsPanel = createSectionPanel(
                "Missions que je peux accomplir",
                Utils.avoirToutesMissionsValidees(VuePrincipale.missionDAO),
                new String[]{"Demandeur", "Mission"}
        );

        // Bouton pour proposer une mission
        JButton proposeMissionButton = new JButton("Proposer une Mission");
        proposeMissionButton.setFont(new Font("Arial", Font.BOLD, 18));
        proposeMissionButton.setBackground(new Color(60, 60, 255));
        proposeMissionButton.setForeground(Color.WHITE);
        proposeMissionButton.setPreferredSize(new Dimension(250, 40));
        proposeMissionButton.setFocusPainted(false);

        // Ajouter le panel de missions et le bouton
        contentPanel.add(missionsPanel);
        contentPanel.add(Box.createVerticalStrut(20)); // espace entre les sections
        contentPanel.add(proposeMissionButton);

        // Ajouter les panels au layout principal
        utilisateurScreen.add(topPanel, BorderLayout.NORTH);
        utilisateurScreen.add(buttonPanel, BorderLayout.NORTH);
        utilisateurScreen.add(contentPanel, BorderLayout.CENTER);

        utilisateurScreen.setVisible(true);
    }


/*    public static void initUtilisateurScreen() throws SQLException {

        Utilisateur utilisateur_courant = ConnexionScreen.utilisateur_courant;
        utilisateurScreen = new JPanel();
        utilisateurScreen.setBackground(Color.WHITE);
        utilisateurScreen.setLayout(new GridLayout(2, 2, 10, 10)); // 2x2 sections avec espaces

        // Section 1 : Missions que je peux accomplir
        JPanel missionsValideesPanel = createSectionPanel(
                "Missions que je peux accomplir",
                Utils.avoirToutesMissionsValidees(VuePrincipale.missionDAO),
                new String[]{"Demandeur", "Mission"}
        );

        // bouton proposer une mission

        *//*
        // Section 2 : Missions proposées par les bénévoles
        JPanel missionsSpontaneesPanel = createSectionPanel(
                "Missions proposées par les bénévoles",
                Utils.avoirToutesMissionsSpontaneesValidees(VuePrincipale.missionDAO),
                new String[]{"Bénévole", "Mission"}
                // bouton demander cette mission
        );

        *//*

        // Ajouter les sections au layout principal
        utilisateurScreen.add(missionsValideesPanel);
        *//*
        utilisateurScreen.add(missionsSpontaneesPanel);
*//*

        utilisateurScreen.setVisible(true);
    }*/

    // Méthode pour créer chaque section avec les données des missions
    private static JPanel createSectionPanel(String title, List<Mission> missions, String[] columns) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));

        // Modèle de tableau pour ajouter les données dynamiquement
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        // Remplir le tableau avec les données des missions
        for (Mission mission : missions) {
            Object[] rowData = new Object[columns.length];
            for (int i = 0; i < columns.length; i++) {
                switch (columns[i]) {
                    case "Demandeur" -> rowData[i] = mission.getId_demandeur(); // Affiche l'ID du demandeur
                    case "Description" -> rowData[i] = mission.getDescription(); // Affiche la description de la mission
                    case "Statut" -> rowData[i] = mission.getStatut(); // Affiche le statut de la mission
                    case "Bénévole" -> rowData[i] = mission.getId_benevole(); // Affiche l'ID du bénévole
                    case "Valideur" -> rowData[i] = mission.getId_valideur(); // Affiche l'ID du valideur
                    case "Motif Refus" -> rowData[i] = mission.getMotif_refus(); // Affiche le motif du refus
                    case "Date" -> rowData[i] = mission.getDate(); // Affiche la date de la mission
                }
            }
            tableModel.addRow(rowData);
        }

        // Création du tableau
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }


}
