(*automate joueur 1*)

let (list_etat:etat list)=
 [
    EtatInit(0, Avancer(N));
    Etat(1, Avancer(E));
    Etat(2, Avancer(O));
    Etat(3, Avancer(S));
  ];;

let autTest =
  [ (0, Decor(Mur,C), Construire, 0) ;
    (0, Decor(SolNormal,C), Peindre(Ami),1);
    (0, Decor(Sol(Ennemi),C), Peindre(Ami),1);
    (0, Decor(Muret,C), Construire,2);
    (0, Decor(Gendarmerie,C), Demolir ,0);
    (0, Decor(Skatepark,C), Dupliquer,1);
    (0, Decor(BatimentDesaffecte,C), Admirer, 2);
    (0, Decor(BoucheEgout,C), Voyager ,0);
    (0, Decor(Velo,C), Voler, 1);
    (0, Decor(BombePeinture,C), Prendre , 2);
    
    (1, Decor(Mur,C), Construire, 3) ;
    (1, Decor(SolNormal,C), Peindre(Ami),1);
    (1, Decor(Sol(Ennemi),C), Peindre(Ami),1);
    (1, Decor(Muret,C), Construire,3);
    (1, Decor(Gendarmerie,C), Demolir ,1);
    (1, Decor(Skatepark,C), Dupliquer,0);
    (1, Decor(BoucheEgout,C), Voyager ,3);
    (1, Decor(Velo,C), Voler, 1);
    (1, Decor(BombePeinture,C), Prendre, 0);

    (2, Decor(Mur,C), Construire, 3) ;
    (2, Decor(SolNormal,C),Peindre(Ami),2);
    (2, Decor(Sol(Ami),C), PeindreNeutre,2);
    (2, Decor(Sol(Ennemi),C), PeindreNeutre,2);
    (2, Decor(Muret,C), Construire,0);
    (2, Decor(Gendarmerie,C), Demolir,3);
    (2, Decor(Skatepark,C), Dupliquer,0);
    (2, Decor(BatimentDesaffecte,C), Admirer, 3);
    (2, Decor(BoucheEgout,C), Admirer ,2);
    (2, Decor(Velo,C), Voler, 0);
    (2, Decor(BombePeinture,C), Prendre , 2);

    (3, Decor(Mur,C), Construire, 2) ;
    (3, Decor(SolNormal,C), Peindre(Ami),3);
    (3, Decor(Sol(Ennemi),C), Peindre(Ami),3);
    (3, Decor(Muret,C), Construire,2);
    (3, Decor(Gendarmerie,C), Demolir,1);
    (3, Decor(Skatepark,C), Dupliquer,3);
    (3, Decor(BoucheEgout,C), Voyager ,2);
    (3, Decor(Velo,C),Voler, 1);
    (3, Decor(BombePeinture,C), Prendre , 1);

  ] ;;
