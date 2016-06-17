(*automate joueur 2*)

let (list_etat:etat list)=
 [
    EtatInit(0, Avancer(N));
    Etat(1, Avancer(E));
    Etat(2, Avancer(O));
    Etat(3, Avancer(S));
  ];;

let autTest =
  [ (0, Et(Decor(SolNormal,C),Decor(BombeEau,N)), Admirer, 0) ;
    (0, Et(Decor(SolNormal,C),Decor(BombeEau,E)), Admirer, 1) ;
    (0, Et(Decor(SolNormal,C),Decor(BombeEau,S)), Admirer, 3) ;
    (0, Et(Decor(SolNormal,C),Decor(BombeEau,O)), Admirer, 2) ;
    
    (1, Et(Decor(SolNormal,C),Decor(BombeEau,N)), Admirer, 0) ;
    (1, Et(Decor(SolNormal,C),Decor(BombeEau,E)), Admirer, 1) ;
    (1, Et(Decor(SolNormal,C),Decor(BombeEau,S)), Admirer, 3) ;
    (1, Et(Decor(SolNormal,C),Decor(BombeEau,O)), Admirer, 2) ;

    (2, Et(Decor(SolNormal,C),Decor(BombeEau,N)), Admirer, 0) ;
    (2, Et(Decor(SolNormal,C),Decor(BombeEau,E)), Admirer, 1) ;
    (2, Et(Decor(SolNormal,C),Decor(BombeEau,S)), Admirer, 3) ;
    (2, Et(Decor(SolNormal,C),Decor(BombeEau,O)), Admirer, 2) ;

    (3, Et(Decor(SolNormal,C),Decor(BombeEau,N)), Admirer, 0) ;
    (3, Et(Decor(SolNormal,C),Decor(BombeEau,E)), Admirer, 1) ;
    (3, Et(Decor(SolNormal,C),Decor(BombeEau,S)), Admirer, 3) ;
    (3, Et(Decor(SolNormal,C),Decor(BombeEau,O)), Admirer, 2) ;

  ] ;;
