(* LES TYPES *)

type couleur =
  | Ami
  | Ennemi
      
type cellule =
  | C (* case *)
  | N (* nord *) | S | E | O

type objetcassable =
  | Mur
  | Vitre

type objetpeignable =
  | Sol of couleur
  | SolNormal
      

type condition =   (* CECI SONT LES SYMBOLES *)
  | Cassable of (objetcassable * cellule)
  | Peignable of (objetpeignable * cellule)
(*  | Amoi of couleur * cellule   *)
  
type action_etat =
  | Avancer of cellule 

type action_trans =
  | Poser_mur
  | Peindre of couleur
  | Casser
  | Admirer
  | Regenerer
      

type etat = int * action_etat
		    
type transition = etat * condition * action_trans * etat
						
type automate = transition list



(* EXEMPLE D'AUTOMATE *)
      
	
let aut1 = 	
  [ (1, Ennemi(N), Tourner_vers(N), 2) ;
    (1, Ennemi(S), Tourner_vers(S), 2) ;
    (1, Ennemi(E), Tourner_vers(E), 2) ;
    (1, Ennemi(O), Tourner_vers(O), 2) ;

    (2, Ennemi(N), Frapper, 3) ;
    (3, Vide, Avancer, 4) ;
    (4, Amoi(C), Restaurer, 1)
  ]
;;

let autTest =
  [ ((0,Avancer(N)), Cassable(Mur,C), Casser, (0,Avancer(N))) ;
    ((0,Avancer(N)), Peignable(SolNormal,C), Peindre(Ami),(1,Avancer(E)));
    ((1,Avancer(E)), Peignable(Sol(Ennemi),C),Peindre(Ami),(3,Avancer(S)));
    ((1,Avancer(E)), Peignable(SolNormal,C),Peindre(Ami),(2,Avancer(O)));
    ((2,Avancer(O)), Peignable(Sol(Ennemi),C),Peindre(Ami),(0,Avancer(N)));
    ((2,Avancer(O)), Cassable(Mur,C),Casser,(3,Avancer(S)));
    ((3,Avancer(S)), Peignable(Sol(Ennemi),C),Peindre(Ami),(3,Avancer(S)));
    ((3,Avancer(S)), Peignable(SolNormal,C), Poser_mur,(2,Avancer(O)));
  ] ;;
   
(* ON PEUT GÉNÉRER CERTAINES PARTIES DE L'AUTOMATE *)

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

let (objetpeignable_to_int: objetpeignable -> int) = function
  | SolNormal -> 0
  | Sol(Ami) -> 5
  | Sol(Ennemi) -> 10 (* penser à espacer de 5 si on rajoute objet *)

let (objetcassable_to_int: objetcassable -> int ) = function
  | Mur -> 0
  | Vitre -> 5 (* penser à espacer de 5 si on rajoute objet *)

let (condition_to_int: condition -> int) =  function
    
  | Peignable(objetpeignable,cellule) -> 0 + (objetpeignable_to_int objetpeignable) + (cellule_to_int cellule) (* 1..9 *)
  | Cassable(objetcassable,cellule) -> 15 + (objetcassable_to_int objetcassable) + (cellule_to_int cellule) (* 10..19 *)
(* rajouter amoi *)

let (action_trans_to_int: action_trans -> int) = function
   | Admirer -> 0
   | Poser_mur -> 1
   | Peindre(couleur) -> if couleur == Ami then 1 else 2
   | Casser -> 3
   (* | Regenerer -> 4 *)
   | _ -> 0

let (action_etat_to_int: action_etat -> int) = function
  | Avancer(cellule) -> cellule_to_int cellule
				       
let (etatA_to_int: etat -> int) = fun etat -> let (a,b) = etat in a
let (etatB_to_int: etat -> int) = fun etat -> let (a,b) = etat in action_etat_to_int b
				       
let (traduction_transition: transition -> (int*int) * int * int * (int*int)) = fun (src,condition,action_trans,tgt) ->
   ((etatA_to_int src,etatB_to_int src), condition_to_int condition, action_trans_to_int action_trans,(etatA_to_int tgt,etatB_to_int tgt))

let (traduction_automate: automate -> ((int*int) * int * int * (int*int)) list) = fun automate ->
   List.map traduction_transition automate ;;


let trad_autTest = traduction_automate autTest ;;

(* On obtient
   [ (1, 7, 6, 2); 
     (1, 8, 7, 2); 
     (1, 9, 8, 2); 
     (1, 10, 9, 2); 
     (2, 7, 3, 3);
     (3, 0, 1, 4); 
     (4, 0, 0, 1) ]

  à partir duquel on peut constuire le tableau des transitions et celui des actions.

 *)

   
(* LE SIMULATEUR examine le voisinage de la position (x,y) du personnage 
   
         ?      |  Ennemi(N)    |  ?
 ------------------------------------------------
  Comestible(O) | Comestible(C) | Comestible(E)
 ------------------------------------------------
         ?      |  Ennemi(S)    |  ?

qui correspond aux conditions 

    ?  | 7  | ?
    15 | 11 | 14
     ? | 8  | ? 

Supposons que l'automate du personnage soit dans l'état 1,
le simulateur cherche les transitions exécutables de l'automate 
dans l'état 1 sur les conditions/symboles {7,8,11,14,15}
il y en a deux transitions possibles :

 (1, 7, 6, 2)  et (1, 8, 7, 2);

Le simulateur en prend tire une parmi celle là est l'exécute.
*)
