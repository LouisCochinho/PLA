/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import pla.Automate;
import static pla.XMLParser.parse;

/**
 *
 * @author antoi
 */
public class TestXMLParser {
    public static void main(String[] args) {
        Automate a = new Automate();
        parse(a, "../OCaml/test.xml");
        a.afficher();
    }
}
