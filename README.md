# **Application de Gestion de Missions**

## **Créateurs**

Ce projet a été réalisé dans le cadre du cours Processus de développement logiciel automatisé et Conduite de projet.

---

## **Description**

L'application est un outil Java conçu pour faciliter la mise en relation entre **bénévoles** et **demandeurs** autour de missions communautaires. Grâce à une interface intuitive, elle permet aux utilisateurs de créer, valider et gérer des missions tout en garantissant un environnement sécurisé et organisé. Le projet utilise **Swing** pour l'interface graphique, **Maven** pour la gestion des dépendances et **JUnit** pour les tests.

---

## **Fonctionnalités**

### Rôles des utilisateurs
- **Utilisateur** :
  - Peut demander ou proposer des missions.
  - Accède aux coordonnées des participants à une mission validée.
- **Valideur** :
  - Régule les missions en les validant ou refusant avec un motif.
  - Dispose d'un écran dédié pour cette gestion.

### Fonctionnalités principales
- Création et connexion à des comptes sécurisés.
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

ApplicationGestionMissions/
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


---

## **Technologies**

- **Java** : Langage principal.
- **Swing** : Construction de l'interface utilisateur.
- **Maven** : Gestion des dépendances.
- **JUnit** : Framework de tests unitaires.

---

## **Prérequis**

- **Java JDK 11** ou supérieur.
- **Maven 3.6+**.
- Un IDE supporté tel qu’IntelliJ IDEA, Eclipse ou NetBeans.
- Connexion au **VPN de l'INSA** pour utiliser l'application.

---

## **Installation**

1. Clonez le dépôt :
   ```bash
   git clone <URL_du_dépôt>
   cd ApplicationGestionMissions

---

## **Utilisation**

- **Connexion/Inscription** :
  - Créez un compte ou connectez-vous avec un compte utilisateur ou valideur.
  - Exemples de comptes :
    - **Utilisateur** : mathisdubien@unilim.fr / abcd1
    - **Valideur** : galinabox@lid.fr / abcd1

- **Proposer ou demander une mission** :
  - Ajoutez une mission via l'écran dédié.

- **Valider ou refuser des missions** (Valideur uniquement) :
  - Fournissez un motif en cas de refus.

- **Accéder aux missions disponibles** :
  - Affichez les missions partagées par la communauté.

- **Suivre vos missions** :
  - Consultez vos missions personnelles dans l'écran "Mon profil".
  - Signalez la fin d’une mission accomplie.

- **Publier un avis** :
  - Ajoutez un avis après l'achèvement d'une mission.

---

## **Tests**

- **Pour exécuter les tests :** :

```bash
mvn test

