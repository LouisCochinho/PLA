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
      

type etat = int * action_etat  (* un état contient aussi une action d'état *)
		    
type transition = etat * condition * action_trans * etat
						
type automate = transition list;;





(* EXEMPLE D'AUTOMATE *)

let (e0: etat) = (0, Avancer(N));;
let (e1: etat) = (1, Avancer(E));;
let (e2: etat) = (2, Avancer(O));;
let (e3: etat) = (3, Avancer(S));;

let autTest =
  [ (0, Cassable(Mur,C), Casser, 0) ;
    (0, Peignable(SolNormal,C), Peindre(Ami),1);
    (1, Peignable(Sol(Ennemi),C),Peindre(Ami),3);
    (1, Peignable(SolNormal,C),Peindre(Ami),2);
    (2, Peignable(Sol(Ennemi),C),Peindre(Ami),0);
    (2, Cassable(Mur,C),Casser,3);
    (3, Peignable(Sol(Ennemi),C),Peindre(Ami),3);
    (3, Peignable(SolNormal,C), Poser_mur,2);
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
    
  | Peignable(objetpeignable,cellule) -> 0 + (objetpeignable_to_int objetpeignable) + (cellule_to_int cellule) (* 0..14 *)
  | Cassable(objetcassable,cellule) -> 15 + (objetcassable_to_int objetcassable) + (cellule_to_int cellule) (* 15..24 *)
(* rajouter amoi *)

let (action_trans_to_int: action_trans -> int) = function
   | Admirer -> 0
   | Poser_mur -> 1
   | Peindre(couleur) -> if couleur == Ami then 1 else 2
   | Casser -> 3
   (* | Regenerer -> 4 *)
   | _ -> 0

let (action_etat_to_int: action_etat -> int) = function
  | Avancer(cellule) -> cellule_to_int cellule;;

					      				
				       
let (etatA_to_int: etat -> int) = fun etat -> let (a,b) = etat in a
let (etatB_to_int: etat -> int) = fun etat -> let (a,b) = etat in action_etat_to_int b
				       
let (traduction_transition: transition -> (int*int) * int * int * (int*int)) = fun (src,condition,action_trans,tgt) ->
   ((etatA_to_int src,etatB_to_int src), condition_to_int condition, action_trans_to_int action_trans,(etatA_to_int tgt,etatB_to_int tgt))

let (traduction_automate: automate -> ((int*int) * int * int * (int*int)) list) = fun automate ->
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


let autStream = Stream.of_list trad_autTest;;
  
let test = parser

| [< '(a,b),c,d,(e,f) >] -> output_string fic_out b
| [< >] -> () ;;

let next = parser [< 'e >] -> e;;


test autStream;;
next autStream;;

let fic_out = open_out "test.txt";;
let fic_in = open_in "../exemple.xml";;

input_line fic_in;;
output_string fic_out a ;;

close_out fic_out;;    

(* ----------------------------------------------------------------------- *)

let list_action_etat = [(0,"DeplacerHaut");(1,"DeplacerBas");(2,"DeplacerGauche");(3,"DeplacerBas");(4,"DeplacerDroite")];;

let list_action_transition = [(0,"PendreAmi"),(1,"PeindreEnnemi"),(2,"Casser"),(3,"Poser_mur"),(4,"Admirer")];;

  

let rec (ecrire_action_etat: (int*string) list -> out_channel -> unit) = fun l fic_out -> match l with
  | [] -> fprintf fic_out ""
  |(a,b)::r -> fprintf fic_out "<action_etat id=\"%d\">%s</action_etat>\n"a b ; ecrire_action_etat r fic_out;;


let rec (ecrire_action_transition: (int*string) list -> out_channel -> unit) = fun l fic_out -> match l with
  | [] -> fprintf fic_out ""
  |(a,b)::r -> fprintf fic_out "<action_transition id=\"%d\">%s</action_transmission>\n"a b ; ecrire_action_etat r fic_out;;
						     
  
let fic_out = open_out "test.txt";;
fprintf fic_out "<root>\n";;
fprintf fic_out "<header>\n";;
fprintf fic_out "<liste_action_etat>\n";;
ecrire_action_etat list_action_etat fic_out;;
fprintf fic_out "</liste_action_etat>";;
fprintf fic_out "<liste_action_transition>";;
ecrire_action_transition list_action_transition fic_out;;

     
close_out fic_out;;  

