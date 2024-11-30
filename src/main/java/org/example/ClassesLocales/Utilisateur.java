package org.example.ClassesLocales;

//import java.sql.SQLException;

//demandeur ou benevole
public class Utilisateur {

    //ATTRIBUTS
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String password;
/*    private ArrayList<Mission> missions_demandees_en_cours;
    private ArrayList<Mission> missions_demandees_historique;
    private ArrayList<Mission> missions_benevoles_en_cours;
    private ArrayList<Mission> missions_benevoles_historique;*/


    //CONSTRUCTEUR
    public Utilisateur(String nom, String prenom, String adresse, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email; // s'assurer du bon format
        this.password = password; //S'assurer qu'il y a des chiffres et des lettres...

/*        //demandeur
        this.missions_demandees_en_cours = new ArrayList<Mission>();
        this.missions_demandees_historique = new ArrayList<Mission>();

        //benevole
        this.missions_benevoles_en_cours= new ArrayList<Mission>();
        this.missions_benevoles_historique = new ArrayList<Mission>();*/

    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

/*
    //demandeur
    public Mission getLastMissionDemandee(){
        if (missions_demandees_en_cours.size() > 0){
            return missions_demandees_en_cours.get(missions_demandees_en_cours.size() - 1);
        }
        else{
            return null;
        }
    }

   public Mission creerMission(String categorie, String description, int temps, Date date){
        Mission mission = new Mission(categorie, description, temps, date);
        this.missions_demandees_en_cours.add(mission);
        mission.setDemandeur(this);
        return mission;
    }

    public void finirMission(Mission mission){
        if (mission.getDemandeur() == this)
        {
        mission.setStatut("réalisée");
        this.missions_demandees_en_cours.remove(mission);
        this.missions_demandees_historique.add(mission);
        if (mission.getBenevole() != null)
        {
            mission.getBenevole().missions_benevoles_en_cours.remove(mission);
            mission.getBenevole().missions_benevoles_historique.add(mission);
        }
        }
    }

    //benevole
    public Mission creerMissionSpontanee(String categorie, String description, int temps, Date date){
        Mission mission = new Mission(categorie, description, temps, date);
        this.missions_demandees_en_cours.add(mission);
        mission.setBenevole(this);
        return mission;
    }*/

}