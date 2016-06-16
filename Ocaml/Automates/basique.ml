

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
