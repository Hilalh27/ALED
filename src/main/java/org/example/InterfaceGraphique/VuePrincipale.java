package org.example.InterfaceGraphique;

import javax.swing.*;
import java.awt.*;


public class VuePrincipale  extends JFrame {

    private static JPanel mainPanel;

    // passer d'une page à une autre
    public static void allerALaPage(String panelName) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, panelName);
    }

    private void showSplashScreen() {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "SplashScreen");
        Timer timer = new Timer(1000, e -> allerALaPage("SelectionScreen"));
        timer.setRepeats(false);
        timer.start();
    }

    public VuePrincipale() {
        setTitle("ALED Application");
        setSize(400, 300); // Taille légèrement plus grande
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel(new CardLayout());
        getContentPane().add(mainPanel);

        SplashScreen.initSplashScreen();
        SelectionScreen.initSelectionScreen();
        ConnexionScreen.initConnexionScreen();
        CreationCompteScreen.initCreationCompteScreen();

        mainPanel.add(SplashScreen.splashScreen, "SplashScreen");
        mainPanel.add(SelectionScreen.selectionScreen, "SelectionScreen");
        mainPanel.add(ConnexionScreen.connexionScreen, "ConnexionScreen");
        mainPanel.add(CreationCompteScreen.creationCompteScreen, "CreationCompteScreen");

        showSplashScreen();
    }

    public static void lancerUI() {
        SwingUtilities.invokeLater(() -> new VuePrincipale().setVisible(true));
    }
}
