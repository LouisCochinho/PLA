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
    (0, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),S)),PeindreNeutre,0);]
