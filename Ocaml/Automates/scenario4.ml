

(* EXEMPLE D'AUTOMATE *)

let (list_etat:etat list) =
  [
    EtatInit(0, Avancer(N));
    Etat(1, Avancer(E));
	Etat(2, Avancer(S));
	Etat(3, Avancer(O));
  ];;

let autTest =
  [ (0, Et(Decor(SolNormal,C),Decor(SolNormal,N)), Peindre(Ami),0);
	(0, Et(Decor(Mur,O),Decor(Mur,N)), Peindre(Ami),1);
	(1, Et(Decor(SolNormal,C),Decor(SolNormal,E)), Peindre(Ami),1);
	(1, Et(Decor(Mur,N),Decor(Mur,E)), Peindre(Ami),2);
	(2, Decor(SolNormal,C), Peindre(Ami),2);
	(2, Decor(BombePeinture,C), Prendre,3);
	(3, Decor(SolNormal,C), Peindre(Ami),3);
	(3, Decor(BombeEau,C), Prendre,3);
	(3, Decor(Sol(Ami),C), Admirer,3);
  ] ;;
