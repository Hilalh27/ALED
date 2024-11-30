package org.example;

import org.example.BDDCommunication.AvisDAO;
import org.example.BDDCommunication.MissionDAO;
import org.example.BDDCommunication.UtilisateurDAO;
import org.example.BDDCommunication.ValideurDAO;
import org.example.ClassesLocales.Utilisateur;
import org.example.ClassesLocales.Valideur;
import org.example.InterfaceGraphique.VuePrincipale;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {






        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        ValideurDAO valideurDAO = new ValideurDAO();
        MissionDAO missionDAO = new MissionDAO(UtilisateurDAO.getConnectionUtilisateurDAO());
        AvisDAO avisDAO = new AvisDAO(UtilisateurDAO.getConnectionUtilisateurDAO());

        if (Utils.connexionValideur("rglucks@lea.fr","abcd1"))
        {
            Valideur valideur = Utils.recupererValideurConnecte("rglucks@lea.fr", valideurDAO);
            System.out.println(Utils.avoirToutesMissionsEnAttente(missionDAO));
            Utils.validerMission(valideur, 44, missionDAO, valideurDAO);
            Utils.refuserMission(valideur, 2, "je refuse", missionDAO, valideurDAO);
        }

        VuePrincipale.lancerUI();


    /*
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        ValideurDAO valideurDAO = new ValideurDAO();
        MissionDAO missionDAO = new MissionDAO(UtilisateurDAO.getConnectionUtilisateurDAO());
        AvisDAO avisDAO = new AvisDAO(UtilisateurDAO.getConnectionUtilisateurDAO());

        Utils.enregistrerNouvelUtilisateur("MACRON", "Emmanuel", "emacron@elysee.fr", "palais de l'elysée", "abcd1", utilisateurDAO);

        if (Utils.connexionUtilisateur("emacron@elysee.fr","abcd1"))
        {
            Utilisateur user = Utils.recupererUtilisateurConnecte("emacron@elysee.fr", utilisateurDAO);
            Utils.demanderMission(user, "acheter du lait", missionDAO, utilisateurDAO);
            Utils.demanderMission(user, "acheter ketchup", missionDAO, utilisateurDAO);
        }
        //System.exit(0);

        Utils.enregistrerNouveauValideur("GLUCKSMANN", "Raphael", "rglucks@lea.fr", "5 rue du zoo", "abcd1", valideurDAO);

        if (Utils.connexionValideur("rglucks@lea.fr","abcd1"))
        {
            Valideur valideur = Utils.recupererValideurConnecte("rglucks@lea.fr", valideurDAO);
            System.out.println(Utils.avoirToutesMissionsEnAttente(missionDAO));
            Utils.validerMission(valideur, 44, missionDAO, valideurDAO);
            Utils.refuserMission(valideur, 2, "je refuse", missionDAO, valideurDAO);
        }
        //System.exit(0);

        Utils.enregistrerNouvelUtilisateur("GUACAMOLE", "Apache", "apache-guac@wanadoo.fr", "12 Place de la Motte", "abcd1", utilisateurDAO);

        if (Utils.connexionUtilisateur("apache-guac@wanadoo.fr","abcd1"))
        {
            Utilisateur user = Utils.recupererUtilisateurConnecte("apache-guac@wanadoo.fr", utilisateurDAO);
            Utils.accepterMissionDemandee(user,1, missionDAO, utilisateurDAO);
            Utils.accepterMissionDemandee(user,2, missionDAO, utilisateurDAO); //ne doit pas fonctionner car mission refusée
            Utils.proposerMission(user, "coller feuilles cahier", missionDAO, utilisateurDAO);
            Utils.donnerAvis(1, user, 3, "médiocre", utilisateurDAO, avisDAO, missionDAO); //ne doit pas fonctionner car mission non réalisée
        }
        //System.exit(0);

        /*if (Utils.connexionUtilisateur("mathisdubien@unilim.fr","abcd1"))
        {
            Utilisateur user = Utils.recupererUtilisateurConnecte("mathisdubien@unilim.fr", utilisateurDAO);
            Utils.finirMission(1,missionDAO);
            Utils.donnerAvis(1, user, 4, "bien Pierre B !", utilisateurDAO, avisDAO, missionDAO);
        }*/


    }

}