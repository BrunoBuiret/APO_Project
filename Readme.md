Projet d'APO
============

Le projet du cours d'algorithmique et programmation consiste à produire en Java un
ensemble de classes pour gérer un jeu de plateau abstrait puis à l'utiliser pour
créer un __puissance quatre__ ainsi qu'un __morpion__.

Le logiciel produit devra aussi contenir une interface utilisateur minimale pour
sélectionner à quel jeu l'utilisateur souhaite jouer et s'il désira jouer contre
une intelligence artificielle ou contre un autre joueur.

La librairie utilisée pour ce projet est `jre1.8.0_25` et il a été testé sous
Windows 7 64 bits ainsi que sous Xubuntu 64 bits.

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

# Morpion

# Problèmes rencontrés
## BufferedReader
Problème avec `BufferedReader.close()` qui fait parfois planter le programme donc tous ces
appels ont été supprimés.