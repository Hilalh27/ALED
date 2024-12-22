# Projet Application Bénévolat : ALED

## **Contexte**

Le projet ALED (Avoir L’aide Et Demander) a été réalisé par Noé Caillet et Célian Hilal Hamdan dans le cadre du cours Processus de développement logiciel automatisé et Conduite de projet.

---

## **Description**

Ce projet met en application des techniques de conduite de projet via la méthode Agile en utilisant l’outil Jira, pour la conception et l’implémentation d’une application de **bénévolat** entre **particuliers**. Il utilise **Swing** pour l'interface graphique, **Maven** pour la gestion des dépendances, et met en place l’intégration continue notamment via une automatisation de tests de validité en utilisant **JUnit** et l’outil **GitHub Actions**.

---

## **Fonctionnalités**

- Création et connexion de comptes sécurisés.
- Gestion des missions :
  - Proposition ou demande de missions.
  - Validation des missions avant leur mise à disposition.
  - Affichage des missions disponibles ou propres à l'utilisateur.
- Suivi des missions :
  - Signalement de la fin des missions.
  - Publication d'avis après réalisation.
- Interface graphique conviviale pour toutes les interactions.

---

## **Structure du Projet**

```
ALED/
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── BDDCommunication/     # Gestion de la base de données
│   │   │   ├── ClassesLocales/       # Classes de logique métier
│   │   │   ├── InterfaceGraphique/   # Composants de l'interface graphique
│   │   │   ├── Main.java             # Classe principale
│   │   │   └── Utils.java            # Fonctions utilitaires
│   │   └── resources/                # Fichiers de configuration
│   ├── test/
│   │   ├── java/org/example/         # Tests unitaires
├── .gitignore                        # Fichiers à exclure du contrôle de version
├── pom.xml                           # Fichier de configuration Maven

```

---

## **Outils utilisés**

- **Java** : Langage de programmation.
- **Swing** : Construction de l'interface utilisateur.
- **Maven** : Gestion des dépendances.
- **JUnit** : Framework de tests unitaires.

---

## **Prérequis**

- **Java JDK 11** ou supérieur.
- **Maven 3.6+**.
- Un IDE supporté tel qu’IntelliJ IDEA, Eclipse ou NetBeans.
- Connexion au **réseau** ou au **VPN de l'INSA** pour utiliser l'application.

---

## **Installation**

1. Cloner le dépôt :
   ```bash
   git clone https://github.com/Hilalh27/ALED
   cd ALED

2. Générer (build) le projet en utilisant Maven :
   ```bash
   mvn clean install

3. Exécuter l'application :
   ```bash
   java -jar target/ALED-1.0-SNAPSHOT.jar

---

## **Utilisation**

- **Connexion/Inscription** :
  - Créez un compte ou connectez-vous avec un compte utilisateur ou valideur.
  - Exemples de comptes :
    - **Utilisateur** : adresse email : `mathisdubien@unilim.fr` -  mot de passe : `abcd1`
    - **Valideur** : adresse email : `galinabox@lid.fr` -  mot de passe : `abcd1`

![Écran de connexion](GUI_screenshots/connexion.png "Connexion")

- **Proposer ou demander une mission** (Utilisateur) :
  - Ajoutez une mission via l'écran dédié.
![Écran de demande de mission](GUI_screenshots/demander_mission.png "Demande mission")

- **Valider ou refuser des missions** (Valideur) :
  - Fournissez un motif en cas de refus.
![Écran d'accueil valideur](GUI_screenshots/accueil_v.png "Accueil valideur")

- **Accéder aux missions disponibles** (Utilisateur) :
  - Affichez les missions partagées par la communauté.
![Écran d'accueil utilisateur](GUI_screenshots/accueil_u.png "Accueil utilisateur")

- **Suivre vos missions** (Utilisateur) :
  - Consultez vos missions personnelles dans l'écran `Mon profil`.
  - Signalez la fin d’une mission accomplie.
![Profil utilisateur](GUI_screenshots/profil_u.png "Profil")

- **Publier un avis** :
  - Ajoutez un avis après l'achèvement d'une mission.
---

## **Tests**

- **Pour exécuter les tests :** :

```bash
mvn test

