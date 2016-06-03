package pla;

import java.io.File;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

public class XMLParser {
    
    static org.jdom2.Document document;
    static Element racine;

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

      //Méthode définie dans la partie 3.2. de cet article
      //afficheALL();
    }
    
}