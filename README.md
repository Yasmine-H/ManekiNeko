#ManekiNeko


#############Fonctionnalités implémentées#############

•	Création de comptes client afin de permettre, plus tard (ce sont des fonctionnalitésnon implémentées dans le cadre du projet), la sauvegarde de leurs préférences, leur offrir des suggestions personnalisées, synchroniser leurs comptes aux principaux réseaux sociaux afin de leur permettre le partage des photos et des impressions en temps réel.
•	Utilisation d'une base de données pour sauvegarder le menu du restaurant afin de permettre une mise à jour rapide et simplifiée de la carte sur toutes les tablettes connectées.
•	Affichage de la carte du restaurant de manière simple, avec des photos accompagnées de descriptions claires et concises. Nous avons également implémenté le système des tags permettant de mettre l'accent sur les principales caractéristiques des plats.
•	Permettre une navigation rapide et fluide en classant nos plats sous forme de listes déroulantes regroupées par catégories.
•	La possibilité de remplir son carnet (y ajouter des plats ou en supprimer) et de valider sa commande.


#############Interface#############

Notre application est constituée de trois vues : 
• La première est l'interface de connexion qui correspond à UserConnectionActivity. La création d'un compte y est également possible. Les utilisateurs non intéressés peuvent passer cette étape.
• La seconde, OrderActivity est l'interface d'affichage des plats classés par catégories. Les plats appartenant à une même catégorie sont présentés sous forme d'une scroll view (Recycler View) horizontale. Les plats sont décrits par une photo, une descritpion, leur prix et des tags relevant leurs principales caractéristiques telles que les allergènes. Les utilisateurs n'ont qu'à clquer sur le logo "+" sur la photo du plat pour l'ajouter à leur carnet (équivalent au carnet d'un serveur - une petite pointe d'humour).
• La troisième, CartActivity, et dernière interface est le dit carnet. Il permet aux clients de consulter la liste de leurs commandes et la valider ou supprimer des plats ajoutés.

#############Code#############

Notre code est divisés en 2 packages : 
• database : qui regroupe les classes et interfaces utilisant la bibliothèque retrofit2 qui permettent de lancer les scripts php et récupérer les informations demandées (informations sur les utilisateurs ou les plats). 
• design : regroupe les Activities de notre application ainsi que les Adapters redéfinis afin de charger nos plats dans des ListView personnalisées.
• res : le dossier res contient les photos des plats redimensionnées afin d'obtenir une présentation des plats homogène. On y trouve également des layouts personnalisés afin d'afficher les plats tels que nous le voulions (en utilisant un relativeLayout par exemple pour pouvoir afficher le bouton d'ajout de plats au carnet au dessus de l'image du plat). Les icônes de l'application sont sauvegardées dans les différents dossier mimap afin de permettre un affichage plus ou moins net selon le type et les caactéristiques des tablettes utilisées. 
• le fichier reprenant notre base de données se trouve dans le répertoire database (on y trouve une table des utilisateurs, une des plats, une regroupant les tags et une dernière permettant de joindre les deux tables plat-tags).
• les scripts php utilisés pour la partie base de données se trouvent dans le dossier database/php scripts. 

Remarque : n'ayant pasde tablettes à disposition, et la machine virtuelle ne selançant pas correctement sur nosmachines, les tests ont été effectués sur smartphone.