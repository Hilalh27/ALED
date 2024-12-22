# Projet Application Bénévolat : ALED

## **Contexte**

Ce projet a été réalisé par Noé Caillet et Célian Hilal Hamdan dans le cadre du cours Processus de développement logiciel automatisé et Conduite de projet.

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
    - **Utilisateur** : mathisdubien@unilim.fr / mot de passe : abcd1
    - **Valideur** : galinabox@lid.fr / mot de passe : abcd1

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

