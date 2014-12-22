Projet d'APO
============

Le projet du cours d'algorithmique et programmation consiste à produire en Java un
ensemble de classes pour gérer un jeu de plateau abstrait puis à l'utiliser pour
créer un puissance ainsi qu'un morpion.

Le logiciel produit devra aussi contenir une interface utilisateur minimale pour
sélectionner à quel jeu l'utilisateur souhaite jouer et s'il désira jouer contre
une intelligence artificielle ou contre un autre joueur.

La librairie utilisée pour ce projet est `jre1.8.0_25`.

# Classes
L'ensemble des classes est regroupé dans un paquet nommé `games`.

## Jeu -> Game
La classe `Game` représente un jeu en lui-même avec les joueurs, le plateau et la
logique.

C'est une classe abstraite que le développeur doit utiliser pour créer un nouveau jeu en
redéfinissant la méthode `Game.run()`

Lors de sa création, le développeur donne une liste de joueurs initialisés
puis fait appel à la fonction `Game.run()` qui s'occupe de faire tourner le jeu jusqu'à
sa fin.

## Plateau -> Board
La classe `Board` représente un plateau 2D de jeu grâce à un tableau deux dimensions
de références vers des joueurs.

## Joueur -> Player

## Position -> Position

## Programme
### Menu principal

### Puissance 4 -> ConnecFour

### Morpion -> TicTacToe
