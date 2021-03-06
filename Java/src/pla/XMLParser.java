package pla;

import pla.action.transition.Action_transition;
import pla.decor.Decor;
import pla.action.etat.Action_etat;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;


public class XMLParser {
    
    private static Automate automate;
    private static org.jdom2.Document document;
    private static Element racine;
    private static HashMap<Integer, Action_etat> actionsEtat;
    private static HashMap<Integer, Action_transition> actionsTransition;
    private static HashMap<Integer, Decor> decors;
    private static HashMap<Integer, Cellule> cellules;
    private static HashMap<Integer, Etat> etats;
    private static ArrayList<Transition> transitions;
    
    private static void header() {
        try {
            Element header = racine.getChild("header");

            // actionsEtat
            actionsEtat = new HashMap<Integer, Action_etat>();
            Element liste_action_etat = header.getChild("liste_action_etat");
            for(Element action_etat : liste_action_etat.getChildren()) {
                int id = action_etat.getAttribute("id").getIntValue();
                Class c = Class.forName("pla.action.etat." + action_etat.getText());
                Action_etat a = (Action_etat)c.newInstance();
                actionsEtat.put(id, a);
            }
            
            // actionsTransition
            actionsTransition = new HashMap<Integer, Action_transition>();
            Element liste_action_transition = header.getChild("liste_action_transition");
            for(Element action_transition : liste_action_transition.getChildren()) {
                int id = action_transition.getAttribute("id").getIntValue();
                Class c = Class.forName("pla.action.transition." + action_transition.getText());
                Action_transition a = (Action_transition)c.newInstance();
                actionsTransition.put(id, a);
            }
            
            // decors
            decors = new HashMap<Integer, Decor>();
            Element liste_decor = header.getChild("liste_decor");
            for(Element decor : liste_decor.getChildren()) {
                int id = decor.getAttribute("id").getIntValue();
                Class c = Class.forName("pla.decor." + decor.getText());
                Decor d = (Decor)c.newInstance();
                decors.put(id, d);
            }
            
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
        catch(DataConversionException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static boolean isInitial(Element etat) {
        return etat.getAttribute("type") != null && etat.getAttributeValue("type").equals("initial");
    }
    
    private static void getEtats(Element automate) {
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
        catch(DataConversionException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void getTransitions(Element automate) {
        try {
            transitions = new ArrayList<Transition>();
            Element liste_transition = automate.getChild("liste_transition");
            for(Element transition : liste_transition.getChildren()) {
                int idEtatDepart = Integer.parseInt(transition.getChild("etat_depart").getText());
                Etat etatDepart = etats.get(idEtatDepart);
                
                Condition condition = new Condition();
                Element liste_condition = transition.getChild("condition");
                for(Element conditionSimple : liste_condition.getChildren()) {
                    int idDecor = Integer.parseInt(conditionSimple.getChild("decor").getText());
                    Decor decor = decors.get(idDecor);
                    int idCellule = Integer.parseInt(conditionSimple.getChild("cellule").getText());
                    Cellule cellule = cellules.get(idCellule);
                    condition.addCondition(new ConditionSimple(decor, cellule));
                }
                
                int idActionTransition = Integer.parseInt(transition.getChild("action_transition").getText());
                Action_transition actionTransition = actionsTransition.get(idActionTransition);
                int idEtatArrivee = Integer.parseInt(transition.getChild("etat_arrivee").getText());
                Etat etatArrivee = etats.get(idEtatArrivee);
                
                transitions.add(new Transition(etatDepart, condition, actionTransition, etatArrivee));
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void setEtats() {
        for(HashMap.Entry<Integer, Etat> entry : etats.entrySet()) {
            automate.addEtat(entry.getValue());
        }
    }
    
    private static void setTransitions() {
        automate.setTransitions(transitions);
    }

    private static void automate() {
        Element automate = racine.getChild("automate");
        getEtats(automate);
        getTransitions(automate);
        setEtats();
        setTransitions();
    }

    public static void parse(Automate automate, String fileName) throws IOException {
        XMLParser.automate = automate;
        // Création d'une instance de SAXBuilder
        SAXBuilder sxb = new SAXBuilder();
        System.out.println("nom du fichier :"+fileName);
        try
        {
           // Création d'un document JDOM avec en argument le fichier XML
           document = sxb.build(new File(fileName));
          
        }
        catch(JDOMException e){
            System.out.println(e.getMessage());
        }

        // Initialisation de la racine
        racine = document.getRootElement();

        header();
        automate();
    }
}