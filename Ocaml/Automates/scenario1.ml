

(* EXEMPLE D'AUTOMATE *)

let (list_etat:etat list) =
  [
    EtatInit(0, Avancer(E));
    Etat(1, Avancer(S));
  ];;

let autTest =
  [ (0, Decor(SolNormal,C), Peindre(Ami),0);
	(0, Decor(Muret,C), Construire,0);
	(0, Decor(Mur,C), Construire,0);
	(0, Decor(Gendarmerie,C), Demolir,1);
	(1, Decor(SolNormal,C), Peindre(Ami),1);
	(1, Decor(Mur,C), Demolir,1);
	(1, Decor(Muret,C), Demolir,1);
  ] ;;
