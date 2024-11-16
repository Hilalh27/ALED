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

        VuePrincipale.lancerUI();

    /*
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        ValideurDAO valideurDAO = new ValideurDAO();
        MissionDAO missionDAO = new MissionDAO(UtilisateurDAO.getConnectionUtilisateurDAO());
        AvisDAO avisDAO = new AvisDAO(UtilisateurDAO.getConnectionUtilisateurDAO());

        Utils.enregistrerNouvelUtilisateur("DUBIEN", "Mathis", "mathisdubien@unilim.fr", "30 Place Denis Dussoubs", "abcd1", utilisateurDAO);

        if (Utils.connexionUtilisateur("mathisdubien@unilim.fr","abcd1"))
        {
            Utilisateur user = Utils.recupererUtilisateurConnecte("mathisdubien@unilim.fr", utilisateurDAO);
            Utils.demanderMission(user, "acheter du lait", missionDAO, utilisateurDAO);
            Utils.demanderMission(user, "acheter ketchup", missionDAO, utilisateurDAO);
        }
        //System.exit(0);

        Utils.enregistrerNouveauValideur("BOX", "Galina", "galinabox@lid.fr", "5 rue du zoo", "abcd1", valideurDAO);

        if (Utils.connexionValideur("galinabox@lid.fr","abcd1"))
        {
            Valideur valideur = Utils.recupererValideurConnecte("galinabox@lid.fr", valideurDAO);
            System.out.println(Utils.avoirToutesMissionsEnAttente(missionDAO));
            Utils.validerMission(valideur, 1, missionDAO, valideurDAO);
            Utils.refuserMission(valideur, 2, "je refuse", missionDAO, valideurDAO);
        }
        //System.exit(0);

        Utils.enregistrerNouvelUtilisateur("BOURRANDY", "Pierre", "pierrebourrandy@unilim.fr", "12 Place de la Motte", "abcd1", utilisateurDAO);

        if (Utils.connexionUtilisateur("pierrebourrandy@unilim.fr","abcd1"))
        {
            Utilisateur user = Utils.recupererUtilisateurConnecte("pierrebourrandy@unilim.fr", utilisateurDAO);
            Utils.accepterMissionDemandee(user,1, missionDAO, utilisateurDAO);
            Utils.accepterMissionDemandee(user,2, missionDAO, utilisateurDAO); //ne doit pas fonctionner car mission refusée
            Utils.proposerMission(user, "coller feuilles cahier", missionDAO, utilisateurDAO);
            Utils.donnerAvis(1, user, 3, "médiocre", utilisateurDAO, avisDAO, missionDAO); //ne doit pas fonctionner car mission non réalisée
        }
        //System.exit(0);

        if (Utils.connexionUtilisateur("mathisdubien@unilim.fr","abcd1"))
        {
            Utilisateur user = Utils.recupererUtilisateurConnecte("mathisdubien@unilim.fr", utilisateurDAO);
            Utils.finirMission(1,missionDAO);
            Utils.donnerAvis(1, user, 4, "bien Pierre B !", utilisateurDAO, avisDAO, missionDAO);
        }

     */
    }

}