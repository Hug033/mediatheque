
# Projet xMediatek

Le projet à été réalisé du 27 avril 2020 au 07 mai 2020. Par les trinôme Antoine Zych, Hugo Bouillard et Louis Roussel.

L'application xMediatek est une application de gestion des emprunts, des stocks ainsi que des utilisateurs dans une médiathèque.

## Outils utilisés
---
|Utilisation|Outil/Langage|Compétence initiale|
|----------------|-------------------------------|------|
|Interface Graphique|JavaFX|5/10|
|Interface Controller|Java 11|7/10|
|API Serveur|Java 11|3/10|
|Base de données|PostreSQL 12|8/10|
|Création de l'interface|Scene Builder|6/10|
|Développement de l’application|IntelliJ|7/10|
|Versionning de l’application|Git|7/10|


## Composition de l'application 

L'application est composé de plusieurs interface permettant plusieurs chose. Une Interface de Connexion, une interface Lecteur, une Interface Opérateur ainsi qu'un Interface d'Administrateur. Lorsqu'un utilisateur est connecté il peut se déconnecter en fermant l'application et elle se rouvre sur la page de connexion.

###  - Interface de connexion
---
<img align="right" height="25%" width="25%" src="https://www.roussel-louis.fr/xMediatek/Login.png"/>
Cette interface permet la connexion d'un utilisateur grâce aux deux champs de saisie. Il y a une vérification de lors de la saisie de l'email affichant une message d'erreur.
Si le login et le mot de passe entrant sont correct une connexion est établie et l'interface lier au rôle de la personne s'ouvre, sinon un message d'erreur apparaît.
<br/>

### - Interface utilisateur
---
Le rôle Lecteur est le rôle pour chaque personne arrivant à la médiathèque sans droit particulier. Le Lecteur peut consulter les médias via une recherche et un tri, il peut aussi effectuer une demande d'emprunt d'un média.
> Il peut aussi modifier son mot de passe.

Un lecteur peut être banni lorsqu'il à trop de retard dans ses emprunt ou qu'il rend les média dégradé. Il est notifié lors d'un essai de connexion, un administrateur peut le dé-bannir.

<img height="25%" width="25%" src="https://www.roussel-louis.fr/xMediatek/User.png"/>

### - Interface opérateur
---
Le rôle Opérateur est le rôle d'une ou d'un médiathécaire. L'Opérateur permet de visualiser l'ensemble des emprunt en les triant, valider les emprunts demandés par les Lecteurs.
> Il peut aussi ajouter des médias via un formulaire.

<img height="25%" width="25%" src="https://www.roussel-louis.fr/xMediatek/Operateur.png"/>

### - Interface administrateur
---
Le rôle Administrateur est le rôle du chef de la médiathèque. L'Administrateur permet de gérer tout les utilisateurs et de les afficher selon leur rôle. Il peut bannir et dé-bannir les Lecteurs ainsi que de modifier les mots de passe pour les ré-initialiser.  Il peut également ajouter un utilisateur via un formulaire. 

<img height="25%" width="25%" src="https://www.roussel-louis.fr/xMediatek/Admin.png"/>

## API serveur
---
L'API coté serveur à été faite en Java, les connexions avec le client sont réalisés avec l'API Socket. Elle permet d’exécuter les requêtes SQL demandées par le client et de renvoyer les données sous format JSON pour les afficher. Elle permet également de renvoyé des code erreurs permettant d'informer le client. Elle est Multi-thread ce qui permet de traiter plusieurs requête et demande en même temps. Il est possible de la déployer sur n'importe quel serveur puisqu'elle s'affiche seulement en console.

## Base de données
---
La base de données est en PostreSQL 12, elle contient 10 tables. Un jeu d'essai à été réaliser afin de remplir la baser au minimum, comportant 6 médias, 3 catégories, 3 thèmes, 10 utilisateurs ainsi que 10 auteur/réalisateur pour une taille de 25Ko.
<img height="25%" width="25%" src="https://www.roussel-louis.fr/xMediatek/BDD.png"/>

## Diagramme de classe
---
Le diagramme de classe est réalisé avec le plugin plantUML et graphviz sur IntelliJ.
<img height="25%" width="25%" src="https://www.roussel-louis.fr/xMediatek/UML.png"/>

## La suite
---
Si nous avions eu plus de temps nous aurions pu apporter différents changements, détails ou fonctionnalités.

- Connexion
	- Une gestion du token pour vérifier l'utilisateur.
- Application Client
	- Un temps de chargement lors d'une sélection pour informer le client.
	- Ajout d'un recherche vide pour pas laisser un blanc.
	- Notation d'un produit par un client après l'emprunt.
	- Ajout d'un média par l'opérateur.
- API Serveur
	- Ajout de requête correspondant aux mise à jour du Client.
	- Ajout de cryptographie des données sensibles.
	- Envoie d'un mail si retard d'un emprunt
