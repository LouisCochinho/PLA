

(* EXEMPLE D'AUTOMATE *)

let (list_etat:etat list) =
  [
    EtatInit(0, Avancer(E));
    Etat(1, Avancer(E));
  ];;

let autTest =
  [ (0, Decor(SolNormal,C), Peindre(Ami),0);
  ] ;;
