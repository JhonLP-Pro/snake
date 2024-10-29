# Jeu Snake en Java

Un jeu Snake classique développé en Java avec interface graphique Swing.

## Description du Jeu

Dans ce jeu, vous contrôlez un serpent qui doit manger des pommes pour grandir. Le but est d'obtenir le meilleur score possible sans heurter les murs ou vous-même.

## Fonctionnalités

- Score affiché en haut à droite
- Pommes rouges qui apparaissent aléatoirement
- Serpent vert qui grandit en mangeant les pommes
- Option de rejouer avec la touche ENTER après Game Over

## Contrôles

- Flèche GAUCHE : déplacer le serpent vers la gauche
- Flèche DROITE : déplacer le serpent vers la droite
- Flèche HAUT : déplacer le serpent vers le haut
- Flèche BAS : déplacer le serpent vers le bas
- ENTER : recommencer une partie après Game Over

## Structure du Code

Le jeu est composé de deux fichiers principaux :
- `Main.java` : Configure la fenêtre du jeu
- `Snake.java` : Contient toute la logique du jeu

## Comment Jouer

1. Compilez et exécutez les fichiers : 
```sh
javac Snake.java Main.java
java Main
```
