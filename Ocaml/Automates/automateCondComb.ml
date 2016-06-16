(*test condition combinée*)

let (list_etat:etat list)=
 [
    EtatInit(0, Avancer(S));
    Etat(1, Avancer(O));
    Etat(2, Avancer(E));
    Etat(3, Avancer(N));
  ];;

let autTest =
  [ (0, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),N)),PeindreNeutre(Ami),3);
    (0, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),N)),PeindreNeutre(Ami),3);
    (0, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),E)),PeindreNeutre(Ami),2);
    (0, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),E)),PeindreNeutre(Ami),2);
    (0, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),O)),PeindreNeutre(Ami),1);
    (0, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),O)),PeindreNeutre(Ami),1);
    (0, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),S)),PeindreNeutre(Ami),0);
    (0, Et(Decor(Sol(Ennemi),C),Decor(Sol(Ennemi),S)),PeindreNeutre(Ami),0);]
