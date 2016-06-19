

(* EXEMPLE D'AUTOMATE *)

let (list_etat:etat list) =
  [
    EtatInit(0, Avancer(E));
    Etat(1, Avancer(O));
	Etat(2, Avancer(S));
  ];;

let autTest =
  [ (0, Decor(SolNormal,C), Peindre(Ami),0);
	(0, Decor(Velo,C), Voler,0);
	(0, Decor(BoucheEgout,C), Voyager,1);
	(0, Decor(Sol(Ami),C), Admirer,2);
	(1, Decor(SolNormal,C), Peindre(Ami),1);
	(1, Decor(Skatepark,C), Dupliquer,1);
	(2, Decor(SolNormal,C), Peindre(Ami),0);
  ] ;;
