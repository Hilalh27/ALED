package org.example.InterfaceGraphique;

import org.example.BDDCommunication.AvisDAO;
import org.example.BDDCommunication.MissionDAO;
import org.example.BDDCommunication.UtilisateurDAO;
import org.example.BDDCommunication.ValideurDAO;
import org.example.ClassesLocales.Utilisateur;
import org.example.Utils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.example.BDDCommunication.DBConnection.getConnection;


public class VuePrincipale  extends JFrame {


    /////////////////////////////////
    // Création des DAO

    public static Connection connection;
    static {
        try {
            connection = getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static UtilisateurDAO utilisateurDAO;
    static {
        try {
            utilisateurDAO = new UtilisateurDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ValideurDAO valideurDAO;
    static {
        try {
            valideurDAO = new ValideurDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static AvisDAO avisDAO;
    static {
        try {
            avisDAO = new AvisDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static MissionDAO missionDAO;
    static {
        try {
            missionDAO = new MissionDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /////////////////////////////////

    static JPanel mainPanel;

    // passer d'une page à une autre
    public static void allerALaPage(String panelName) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, panelName);
    }

    private void showSplashScreen() {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "SplashScreen");
        Timer timer = new Timer(3500, e -> allerALaPage("SelectionScreen"));
        timer.setRepeats(false);
        timer.start();
    }

    public VuePrincipale() throws SQLException, IOException {
        setTitle("ALED Application");
        //setSize(400, 300);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 850));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel(new CardLayout());
        getContentPane().add(mainPanel);

        SplashScreen.initSplashScreen();
        SelectionScreen.initSelectionScreen();
        ConnexionScreen.initConnexionScreen();
        CreationCompteScreen.initCreationCompteScreen();
        UtilisateurScreen.initUtilisateurScreen();

        mainPanel.add(SplashScreen.splashScreen, "SplashScreen");
        mainPanel.add(SelectionScreen.selectionScreen, "SelectionScreen");
        mainPanel.add(ConnexionScreen.connexionScreen, "ConnexionScreen");
        mainPanel.add(CreationCompteScreen.creationCompteScreen, "CreationCompteScreen");
        mainPanel.add(UtilisateurScreen.utilisateurScreen, "UtilisateurScreen");

        showSplashScreen();
    }

    public static void lancerUI() {
        SwingUtilities.invokeLater(() -> {
            try {
                new VuePrincipale().setVisible(true);
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
