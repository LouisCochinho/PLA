package pla;

import java.io.File;
import java.util.HashMap;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

public class XMLParser {
    
    static org.jdom2.Document document;
    static Element racine;
    static HashMap<Integer, Action_etat> actionsEtat;
    static HashMap<Integer, Action_transition> actionsTransition;
    static HashMap<Integer, Decor> decors;
    static HashMap<Integer, Cellule> cellules;
    static HashMap<Integer, Etat> etats;
    
    static void header() {
        try {
            Element header = racine.getChild("header");

            // actionsEtat
            actionsEtat = new HashMap<Integer, Action_etat>();
            Element liste_action_etat = header.getChild("liste_action_etat");
            for(Element action_etat : liste_action_etat.getChildren()) {
                int id = action_etat.getAttribute("id").getIntValue();
                Class c = Class.forName("pla." + action_etat.getText());
                Action_etat a = (Action_etat)c.newInstance();
                actionsEtat.put(id, a);
            }
            
            // actionsTransition
            /*actionsTransition = new HashMap<Integer, Action_transition>();
            Element liste_action_transition = header.getChild("liste_action_transition");
            for(Element action_transition : liste_action_transition.getChildren()) {
                int id = action_transition.getAttribute("id").getIntValue();
                Class c = Class.forName("pla." + action_transition.getText());
                Action_transition a = (Action_transition)c.newInstance();
                actionsTransition.put(id, a);
            }
            
            // decors
            decors = new HashMap<Integer, Decor>();
            Element liste_decor = header.getChild("liste_decor");
            for(Element decor : liste_decor.getChildren()) {
                int id = decor.getAttribute("id").getIntValue();
                Class c = Class.forName("pla." + decor.getText());
                Decor d = (Decor)c.newInstance();
                decors.put(id, d);
            }*/
            
            // cellules
            cellules = new HashMap<Integer, Cellule>();
            Element liste_cellule = header.getChild("liste_cellule");
            for(Element cellule : liste_cellule.getChildren()) {
                int id = cellule.getAttribute("id").getIntValue();
                Cellule c = Cellule.valueOf(cellule.getText());
                cellules.put(id, c);
            }
        }
        catch (ClassNotFoundException e)
        {
          System.out.println("La classe " + e.getMessage() + " n'existe pas");
        }
        catch (InstantiationException e)
        {
          System.out.println("La classe est abstract ou est une interface ou n'a pas de constructeur accessible sans paramètre");
        }
        catch (IllegalAccessException e)
        {
          System.out.println("La classe n'est pas accessible");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    static boolean isInitial(Element etat) {
        return etat.getAttribute("type") != null && etat.getAttributeValue("type").equals("initial");
    }
    
    static void etats(Element automate) {
        try {
            etats = new HashMap<Integer, Etat>();
            Element liste_etat = automate.getChild("liste_etat");
            for(Element etat : liste_etat.getChildren()) {
                int id = etat.getAttribute("id").getIntValue();
                //boolean type = isInitial(etat);
                Action_etat ae = actionsEtat.get(Integer.parseInt(etat.getText()));
                etats.put(id, new Etat(ae));
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    static void automate() {
        Element automate = racine.getChild("automate");
        etats(automate);
    }

    public static void main(String[] args) {
        //On crée une instance de SAXBuilder
        SAXBuilder sxb = new SAXBuilder();
        try
        {
           //On crée un nouveau document JDOM avec en argument le fichier XML
           //Le parsing est terminé ;)
           document = sxb.build(new File("../exemple.xml"));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        //On initialise un nouvel élément racine avec l'élément racine du document.
        racine = document.getRootElement();

        header();
        automate();
    }
    
}