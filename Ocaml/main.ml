#load "dynlink.cma"
#load "camlp4o.cma"
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





(* EXEMPLE D'AUTOMATE *)

let (list_etat:etat list) =
  [
    EtatInit(0, Avancer(N));
    Etat(1, Avancer(E));
    Etat(2, Avancer(O));
    Etat(3, Avancer(S));
  ];;

let autTest =
  [ (0, Decor(Mur,C), Demolir, 0) ;
    (0, Decor(SolNormal,C), Peindre(Ami),1);
    (1, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),N)),Peindre(Ami),3);
    (1, Decor(SolNormal,C),Peindre(Ami),2);
    (2, Decor(Sol(Ennemi),C),Peindre(Ami),0);
    (2, Decor(Mur,C), Demolir,3);
    (2, Decor(Velo,C), Prendre, 2);
    (3, Decor(Sol(Ennemi),C),Peindre(Ami),3);
    (3, Decor(SolNormal,C), Construire,2);
    (3, Et( Decor(Sol(Ami),N), Decor(Sol(Ami),O)), Admirer, 3);
  ] ;;
   
(* ON PEUT GÉNÉRER CERTAINES PARTIES DE L'AUTOMATE *)
(*
let (en_garde: etat -> etat -> automate) = fun src tgt ->
      List.map  (fun direction -> (src, Ennemi(direction), Tourner_vers(direction), tgt) ) [N;S;E;O]

let aut2 =
  (en_garde 1 2) 
    @
  [ (2, Ennemi(N), Frapper, 3) ;
    (3, Vide, Avancer, 4) ;
    (4, Amoi(C), Restaurer, 1)
  ]
;;

 *)
    
(* NON DETERMINISME 

   Notez que cet automate est non-déterministe. 
   Il se peut qu'il y ait un ennemi au nord et au sud ;
   dans ce cas les deux premières transitions sont exécutables.

   Dans le simulateur java :
   Il faut en choisir par tirage au sort une parmi celles qui sont exécutables.
*)


   
(* TRADUCTION DES CONDITIONS COMPLEXES EN ENTIER *)


 
let (cellule_to_int: cellule -> int) = function
  | C -> 0
  | N -> 1
  | S -> 2
  | E -> 3
  | O -> 4

let (decor_to_int: decor -> int) = function
  | SolNormal -> 0
  | Sol(Ami) -> 1
  | Sol(Ennemi) -> 2
  | Muret -> 3
  | Mur -> 4
  | Gendarmerie -> 5
  | Skatepark -> 6
  | BatimentDesaffecte -> 7
  | BoucheEgout -> 8
  | Velo -> 9
  | BombePeinture -> 10
  | BombeEau -> 11


let (condition_to_int: condition -> int) =  function
    
  | Decor(decor,cellule) ->  (((decor_to_int decor *10)+ cellule_to_int cellule)*1000) +999   (* XXXXXX     DECOR(2)+POSITION(1)+DECOR(2)+POSITION(1)  *)
  | Et(Decor(decor1,cellule1),Decor(decor2,cellule2)) ->  ((((decor_to_int decor1 *10)+cellule_to_int cellule1)*100) + decor_to_int decor2)*10 + cellule_to_int cellule2 

let (action_trans_to_int: action_trans -> int) = function
   | PeindreNeutre -> 0
   | Peindre(couleur) -> if couleur == Ami then 1 else 2
   | Construire -> 3
   | Demolir -> 4
   | Admirer -> 5
   | Voyager -> 6
   | Prendre -> 7
   | Voler -> 8
   | LaisserTomber -> 9
   | Combattre -> 10
   | Repliquer -> 11

let (action_etat_to_int: action_etat -> int) = function
  | Avancer(cellule) -> cellule_to_int cellule;;

					      				
				       
let (etatA_to_int: etat -> int) = function
  | Etat(a,b) -> a
  | EtatInit(a,b) -> a;;
		      
let (etatB_to_int: etat -> int) = function
  | Etat(a,b) -> action_etat_to_int b
  | EtatInit(a,b) -> action_etat_to_int b;;
  
				       
let (traduction_transition: transition -> int * int * int * int) = fun (src,condition,action_trans,tgt) ->
   (src, condition_to_int condition, action_trans_to_int action_trans,tgt)

let (traduction_automate: automate -> (int * int * int * int) list) = fun automate ->
   List.map traduction_transition automate ;;


let trad_autTest = traduction_automate autTest ;;
  
(* On obtient
   [ ((0,1), 15, 3, (0,1)); 
     ((0,1), 0, 1, (1,3)); 
     ((1,3), 10, 1, (3, 2));
     ((1, 3), 0, 1, (2, 4)); 
     ((2, 4), 10, 1, (0, 1));
     ((2, 4), 15, 3, (3, 2));
     ((3, 2), 10, 1, (3, 2));
     ((3, 2), 0, 1, (2, 4))
   ]

  à partir duquel on peut constuire le tableau des transitions et celui des actions.

 *)

  
  

(* ----------------------------------------------------------------------- *)

let list_cellule =
  [
    ((cellule_to_int C), "Case");
    ((cellule_to_int N), "Nord");
    ((cellule_to_int S), "Sud");
    ((cellule_to_int E), "Est");
    ((cellule_to_int O), "Ouest");
  ];;
  
let list_action_etat =
  [
    (action_etat_to_int (Avancer(C)),"NePasBouger");
    (action_etat_to_int (Avancer(N)),"DeplacerHaut");
    (action_etat_to_int (Avancer(S)),"DeplacerBas");
    (action_etat_to_int (Avancer(E)),"DeplacerDroite");
    (action_etat_to_int (Avancer(O)),"DeplacerGauche");
  ];;

let list_action_transition =
  [
    (action_trans_to_int PeindreNeutre,"PeindreNeutre");
    (action_trans_to_int (Peindre(Ami)),"PeindreAmi");
    (action_trans_to_int (Peindre(Ennemi)),"PeindreEnnemi");
    (action_trans_to_int Construire,"Construire");
    (action_trans_to_int Demolir,"Demolir");
    (action_trans_to_int Admirer,"Admirer");
    (action_trans_to_int Voyager,"Voyager");
    (action_trans_to_int Prendre,"Prendre");
    (action_trans_to_int Voler,"Voler");
    (action_trans_to_int LaisserTomber,"LaisserTomber");
    (action_trans_to_int Combattre,"Combattre");
    (action_trans_to_int Repliquer,"Repliquer");
    ];;

let list_symbole =
  [
    (decor_to_int SolNormal,"SolNormal");
    (decor_to_int (Sol(Ami)),"SolAmi");
    (decor_to_int (Sol(Ennemi)),"SolEnnemi");
    (decor_to_int Muret,"Muret");
    (decor_to_int Mur,"Mur");
    (decor_to_int Gendarmerie,"Gendarmerie");
    (decor_to_int Skatepark,"Skatepark");
    (decor_to_int BatimentDesaffecte,"BatimentNeutre");
    (decor_to_int BoucheEgout,"BoucheEgout");
    (decor_to_int Velo,"Velo");
    (decor_to_int BombePeinture,"BombePeinture");
    (decor_to_int BombeEau,"BombeEau");
  ];;

  

let rec (ecrire_action_etat: (int*string) list -> out_channel -> unit) = fun l fic_out -> match l with
  | [] -> fprintf fic_out ""
  |(a,b)::r -> fprintf fic_out "<action_etat id=\"%d\">%s</action_etat>\n"a b ; ecrire_action_etat r fic_out;;


let rec (ecrire_action_transition: (int*string) list -> out_channel -> unit) = fun l fic_out -> match l with
  | [] -> fprintf fic_out ""
  |(a,b)::r -> fprintf fic_out "<action_transition id=\"%d\">%s</action_transition>\n"a b ; ecrire_action_transition r fic_out;;

  
let rec (ecrire_decor: (int*string) list -> out_channel -> unit) = fun l fic_out -> match l with
  | [] -> fprintf fic_out ""
  |(a,b)::r -> fprintf fic_out "<decor id=\"%d\">%s</decor>\n"a b ; ecrire_decor r fic_out;;


let rec (ecrire_etat: etat list -> out_channel -> unit) = fun l fic_out -> match l with
  | [] -> fprintf fic_out ""
  | EtatInit(a,b)::r ->  fprintf fic_out "<etat type=\"initial\" id=\"%d\">%d</etat>\n"a (action_etat_to_int b) ; ecrire_etat r fic_out
  | Etat(a,b)::r -> fprintf fic_out "<etat id=\"%d\">%d</etat>\n"a (action_etat_to_int b) ; ecrire_etat r fic_out;; 

let rec (ecrire_cellule: (int*string) list -> out_channel -> unit) = fun l fic_out -> match l with
  | [] -> fprintf fic_out ""
  |(a,b)::r -> fprintf fic_out "<cellule id=\"%d\">%s</cellule>\n"a b ; ecrire_cellule r fic_out;;

let rec (ecrire_transition: (int*int*int*int) list -> out_channel -> unit) = fun l fic_out -> match l with
  | [] -> fprintf fic_out ""
  |(a,b,c,d)::r -> fprintf fic_out "<transition>\n" ;
		   (if (b mod 1000) = 999 then (fprintf fic_out "<etat_depart>%d</etat_depart>\n<condition>\n<condition_simple>\n<decor id=\"0\">%d</decor>\n<cellule id=\"0\">%d</cellule>\n</condition_simple>\n</condition>\n<action_transition>%d</action_transition>\n<etat_arrivee>%d</etat_arrivee>\n" a (b/10000) (b/1000 mod 10) c d )
		   else fprintf fic_out "<etat_depart>%d</etat_depart>\n<condition>\n<condition_simple>\n<decor id=\"0\">%d</decor>\n<cellule id=\"0\">%d</cellule>\n</condition_simple>\n<condition_simple>\n<decor id=\"1\">%d</decor>\n<cellule id=\"1\">%d</cellule>\n</condition_simple>\n</condition>\n<action_transition>%d</action_transition>\n<etat_arrivee>%d</etat_arrivee>\n" a (b/10000) (b/1000 mod 10) (b/10 mod 100) (b mod 10) c d); fprintf fic_out "</transition>\n" ; ecrire_transition r fic_out ;;
		   
  
  
let fic_out = open_out "test.xml";;
fprintf fic_out "<root>\n";
fprintf fic_out "<header>\n";
fprintf fic_out "<liste_action_etat>\n";
ecrire_action_etat list_action_etat fic_out;
fprintf fic_out "</liste_action_etat>\n";
fprintf fic_out "<liste_action_transition>\n";
ecrire_action_transition list_action_transition fic_out;
fprintf fic_out "</liste_action_transition>\n";
fprintf fic_out "<liste_decor>\n";
ecrire_decor list_symbole fic_out; 
fprintf fic_out "</liste_decor>\n";
fprintf fic_out "<liste_cellule>\n";
ecrire_cellule list_cellule fic_out;
fprintf fic_out "</liste_cellule>\n";  
fprintf fic_out "</header>\n";
fprintf fic_out "<automate>\n";
fprintf fic_out "<liste_etat>\n";   
ecrire_etat list_etat fic_out;
fprintf fic_out "</liste_etat>\n";
fprintf fic_out "<liste_transition>\n";
ecrire_transition trad_autTest fic_out;
fprintf fic_out "</liste_transition>\n";
fprintf fic_out "</automate>";
fprintf fic_out "</root>";
   
close_out fic_out;;

  
