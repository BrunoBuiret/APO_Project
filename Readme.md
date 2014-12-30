Projet d'APO
============

Le projet du cours d'algorithmique et programmation consiste à produire en Java un
ensemble de classes pour gérer un jeu de plateau abstrait puis à l'utiliser pour
créer un puissance quatre ainsi qu'un morpion.

Le logiciel produit devra aussi contenir une interface utilisateur minimale pour
sélectionner à quel jeu l'utilisateur souhaite jouer et s'il désira jouer contre
une intelligence artificielle ou contre un autre joueur.

Le projet a été développé avec Eclipse et la librairie `jre1.8.0_25`. Il a été
testé sous Windows 7 64 bits ainsi que sous Xubuntu 64 bits.

# Paquetage de base
Le paquetage de base, ici nommé `games`, contient un ensemble de classes pour commencer
le développement d'un jeu de plateau. On peut y trouver, entre autres :

* `games.Game` qui représente un jeu avec sa logique, c'est une classe abstraite dont on
doit hériter afin d'implémenter les fonctions spécifiques à un jeu, par exemple, jouer ou
vérifier si le jeu a été gagné ;
* `games.Board` qui représente le plateau de jeu avec les fonctions d'accès nécessaires
au placement des pions ;
* `games.Player` qui représente un joueur avec son numéro, c'est une classe abstraite dont
on doit hériter afin de créer les intelligences artificielles ou les joueurs humains ;
* `games.Position` qui représente une position en deux dimensions ;

# Puissance 4
Voir le paquetage `games.connectfour`.

# Morpion
Voir le paquetage `games.tictactoe`.

# Problèmes rencontrés
## Entrée de données par l'utilisateur
Un des problèmes rencontrés a été l'utilisation des classes de lecture de données
entrées par l'utilisateur.

En effet, lorsqu'une lecture est mauvaise, on demande de nouveau à l'utilisateur de
rentrer quelque chose mais parfois la seconde lecture provoquait une boucle infinie. Après avoir
débugué le code, je me suis rendu compte que cela provenait de la méthode `close()` de
`BufferedReader`.

J'ai donc retiré tous les appels à cette méthode dans mes propres méthodes et résolu mon
problème. Selon la documentation, la classe `BufferedReader` implémente l'interface
`AutoCloseable` ainsi je n'ai pas à me soucier de savoir si je dois appeler ou non la
méthode `close()`.

## Redondance de code
Lors du développement des deux jeux, le puissance 4 et le morpion, je me suis rendu compte
que beaucoup de code pour la boucle de jeu principale était identique. J'ai donc essayé
de ramener un maximum du code dans la classe mère `games.Game`.

Il en est de même pour le chargement d'une partie, demander à l'utilisateur le nom d'un
fichier et faire appel à la méthode de chargement est indépendant du jeu, j'ai donc aussi
ramené ce morceau de code dans la classe mère.

## Désérialisation
Eclipse génère un avertissement lors de désérialisation des listes de joueurs et des entrées
de l'historique :

> Type safety: Unchecked cast from Object to List<HistoryEntry>

Toutefois, même en utilisant un objet de type `Object` et en faisant un `instanceof` dessus,
l'avertissement reste présent. J'ai donc supprimé ce test.