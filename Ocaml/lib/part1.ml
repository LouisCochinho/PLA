open Dynlink
open Printf

(* LES TYPES *)

type couleur =
  | Ami
  | Ennemi

type cellule =
  | C (* case *)
  | N (* nord *) | S | E | O

type decor =
  | SolNormal
  | Sol of couleur
  | Muret
  | Mur
  | Gendarmerie
  | Skatepark
  | BatimentDesaffecte
  | BoucheEgout
  | Velo
  | BombePeinture
  | BombeEau

type conditionBis =
  | Decor of (decor * cellule)

type condition =   (* CECI SONT LES CONDITIONS *)
  | Decor of (decor * cellule)
  | Et of (conditionBis * conditionBis)


type action_etat =
  | Avancer of cellule

type action_trans =
  | PeindreNeutre
  | Peindre of couleur
  | Construire
  | Demolir
  | Admirer
  | Voyager
  | Prendre
  | Voler
  | LaisserTomber
  | Combattre
  | Dupliquer



type transition = int * condition * action_trans * int

type automate = transition list;;

type etat =
  | Etat of (int * action_etat)
  | EtatInit of (int*action_etat)
