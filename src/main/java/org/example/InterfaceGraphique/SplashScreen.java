package org.example.InterfaceGraphique;

import org.example.Utils;

import javax.swing.*;
import java.awt.*;

public class SplashScreen {

    public static JPanel splashScreen;
    public static JLabel splashImageLabel;


    public static void initSplashScreen() {
        splashScreen = new JPanel();
        splashScreen.setBackground(Color.WHITE);
        splashScreen.setLayout(new BorderLayout());

        ImageIcon splashImage = new ImageIcon("src/main/resources/aled_logo_splash.png");
        splashImage = Utils.redimImage(splashImage, 200, 150); // Taille réduite pour convenir à la fenêtre
        splashImageLabel = new JLabel(splashImage);
        splashImageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        splashScreen.add(splashImageLabel, BorderLayout.CENTER);
    }
}
