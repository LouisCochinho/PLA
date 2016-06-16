(*automate bernard*)

let (list_etat:etat list)=
 [
    EtatInit(0, Avancer(S));
    Etat(1, Avancer(O));
    Etat(2, Avancer(E));
    Etat(3, Avancer(N));
  ];;

let autTest =
  [ (0, Decor(Mur,C), Construire, 0) ;
    (0, Decor(SolNormal,C), Construire,1);
    (0, Decor(Sol(Ami),C), PeindreNeutre,2);
    (0, Decor(Sol(Ennemi),C), PeindreNeutre,1);
    (0, Decor(Muret,C), Construire,2);
    (0, Decor(Gendarmerie,C), Dupliquer,0);
    (0, Decor(Skatepark,C), Demolir,1);
    (0, Decor(BatimentDesaffecte,C), Admirer, 2);
    (0, Decor(BoucheEgout,C), Admirer ,0);
    (0, Decor(Velo,C), Admirer, 1);
    (0, Decor(BombePeinture,C), Admirer , 2);
    (0, Decor(BombeEau,C), Prendre, 0);
    
    (1, Decor(Mur,C), Construire, 3) ;
    (1, Decor(SolNormal,C), Admirer,0);
    (1, Decor(Sol(Ami),C), PeindreNeutre,1);
    (1, Decor(Sol(Ennemi),C), PeindreNeutre,0);
    (1, Decor(Muret,C), Construire,3);
    (1, Decor(Gendarmerie,C), Dupliquer,1);
    (1, Decor(Skatepark,C), Demolir,0);
    (1, Decor(BatimentDesaffecte,C), Admirer, 1);
    (1, Decor(BoucheEgout,C), Admirer ,3);
    (1, Decor(Velo,C), Admirer, 1);
    (1, Decor(BombePeinture,C), Admirer , 0);
    (1, Decor(BombeEau,C), Prendre, 3);

    (2, Decor(Mur,C), Construire, 3) ;
    (2, Decor(SolNormal,C), Admirer,0);
    (2, Decor(Sol(Ami),C), PeindreNeutre,2);
    (2, Decor(Sol(Ennemi),C), PeindreNeutre,2);
    (2, Decor(Muret,C), Construire,0);
    (2, Decor(Gendarmerie,C), Dupliquer,3);
    (2, Decor(Skatepark,C), Demolir,0);
    (2, Decor(BatimentDesaffecte,C), Admirer, 3);
    (2, Decor(BoucheEgout,C), Admirer ,2);
    (2, Decor(Velo,C), Admirer, 0);
    (2, Decor(BombePeinture,C), Admirer , 2);
    (2, Decor(BombeEau,C), Prendre, 3);

    (3, Decor(Mur,C), Construire, 2) ;
    (3, Decor(SolNormal,C), Admirer,1);
    (3, Decor(Sol(Ami),C), PeindreNeutre,3);
    (3, Decor(Sol(Ennemi),C), PeindreNeutre,2);
    (3, Decor(Muret,C), Construire,2);
    (3, Decor(Gendarmerie,C), Dupliquer,1);
    (3, Decor(Skatepark,C), Demolir,3);
    (3, Decor(BatimentDesaffecte,C), Admirer, 3);
    (3, Decor(BoucheEgout,C), Admirer ,2);
    (3, Decor(Velo,C), Admirer, 1);
    (3, Decor(BombePeinture,C), Admirer , 1);
    (3, Decor(BombeEau,C), Prendre, 1);

  ] ;;
