(*test condition combinée*)

let (list_etat:etat list)=
 [
    EtatInit(0, Avancer(S));
    Etat(1, Avancer(O));
    Etat(2, Avancer(E));
    Etat(3, Avancer(N));
  ];;

let autTest =
  [ (0, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),N)),PeindreNeutre,3);
    (0, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),N)),PeindreNeutre,3);
    (0, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),E)),PeindreNeutre,2);
    (0, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),E)),PeindreNeutre,2);
    (0, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),O)),PeindreNeutre,1);
    (0, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),O)),PeindreNeutre,1);
    (0, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),S)),PeindreNeutre,0);
    (0, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),S)),PeindreNeutre,0);
    
    (1, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),N)),PeindreNeutre,3);
    (1, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),N)),PeindreNeutre,3);
    (1, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),E)),PeindreNeutre,2);
    (1, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),E)),PeindreNeutre,2);
    (1, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),O)),PeindreNeutre,1);
    (1, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),O)),PeindreNeutre,1);
    (1, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),S)),PeindreNeutre,0);
    (1, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),S)),PeindreNeutre,0);

    (2, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),N)),PeindreNeutre,3);
    (2, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),N)),PeindreNeutre,3);
    (2, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),E)),PeindreNeutre,2);
    (2, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),E)),PeindreNeutre,2);
    (2, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),O)),PeindreNeutre,1);
    (2, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),O)),PeindreNeutre,1);
    (2, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),S)),PeindreNeutre,0);
    (2, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),S)),PeindreNeutre,0);

    (3, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),N)),PeindreNeutre,3);
    (3, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),N)),PeindreNeutre,3);
    (3, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),E)),PeindreNeutre,2);
    (3, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),E)),PeindreNeutre,2);
    (3, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),O)),PeindreNeutre,1);
    (3, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),O)),PeindreNeutre,1);
    (3, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),S)),PeindreNeutre,0);
    (3, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),S)),PeindreNeutre,0);

    (0, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),N)),PeindreNeutre,3);
    (0, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),N)),PeindreNeutre,3);
    (0, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),E)),PeindreNeutre,2);
    (0, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),E)),PeindreNeutre,2);
    (0, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),O)),PeindreNeutre,1);
    (0, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),O)),PeindreNeutre,1);
    (0, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),S)),PeindreNeutre,0);
    (0, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),S)),PeindreNeutre,0);
    
    (1, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),N)),PeindreNeutre,3);
    (1, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),N)),PeindreNeutre,3);
    (1, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),E)),PeindreNeutre,2);
    (1, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),E)),PeindreNeutre,2);
    (1, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),O)),PeindreNeutre,1);
    (1, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),O)),PeindreNeutre,1);
    (1, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),S)),PeindreNeutre,0);
    (1, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),S)),PeindreNeutre,0);

    (2, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),N)),PeindreNeutre,3);
    (2, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),N)),PeindreNeutre,3);
    (2, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),E)),PeindreNeutre,2);
    (2, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),E)),PeindreNeutre,2);
    (2, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),O)),PeindreNeutre,1);
    (2, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),O)),PeindreNeutre,1);
    (2, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),S)),PeindreNeutre,0);
    (2, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),S)),PeindreNeutre,0);

    (3, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),N)),PeindreNeutre,3);
    (3, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),N)),PeindreNeutre,3);
    (3, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),E)),PeindreNeutre,2);
    (3, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),E)),PeindreNeutre,2);
    (3, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),O)),PeindreNeutre,1);
    (3, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),O)),PeindreNeutre,1);
    (3, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),S)),PeindreNeutre,0);
    (3, Et(Decor(Sol(Ami),C),Decor(Sol(Ami),S)),PeindreNeutre,0);
]
