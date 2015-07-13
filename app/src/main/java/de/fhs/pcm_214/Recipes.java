package de.fhs.pcm_214;

import java.util.ArrayList;

public class Recipes {
    public static String[] init(){
        String[] recipes = new String[21];

        recipes[0]="kein Gericht ausgewählt";
        recipes[1]="Penne-Hähnchen-Auflauf mit Mozzarella-Brot-Kruste";
        recipes[2]="Italienischer Nudelauflauf mit Tomaten und Bacon";
        recipes[3]="Nudel-Auflauf mit Blumenkohl und Spinat zu Tomatensoße";
        recipes[4]="Makkaroni-Bratwurst-Auflauf mit Möhren und Champignons";
        recipes[5]="Kohlrabi-Kartoffelsuppe mit Räucherlachs und Kresse";
        recipes[6]="Cremige Apfel-Sellerie-Suppe";
        recipes[7]="Hühnersuppe mit Nudeln";
        recipes[8]="Portugiesischer Maisgrieß-Kuchen";
        recipes[9]="Luftiger Himbeerkuchen";
        recipes[10]="Kokos-Streuselkuchen mit Ananas";
        recipes[11]="Kartoffel-Tortilla";
        recipes[12]="Portugiesische Kartoffelsuppe mit Chorizo";
        recipes[13]="Kartoffelpuffer mit Apfelmus";
        recipes[14]="Chicken-Burger mit Mangosalsa und Guacamole";
        recipes[15]="Zwiebelringe in Bierteig";
        recipes[16]="Schweinebraten-Burger mit Texassoße";
        recipes[17]="Backkartoffelsalat mit Rucolapesto";
        recipes[18]="Kartoffelsalat mit gebratener Zucchini, Paprika und Kichererbsen";
        recipes[19]="Kartoffel-Kräutersalat";
        recipes[20]="Klassischer Kartoffelsalat mit Brühe";

        return recipes;
    }
    String[] recipes ;



    public Recipes() {
        recipes = new String[21];

        recipes[0]="kein Gericht ausgewählt";
        recipes[1]="Penne-Hähnchen-Auflauf mit Mozzarella-Brot-Kruste";
        recipes[2]="Italienischer Nudelauflauf mit Tomaten und Bacon";
        recipes[3]="Nudel-Auflauf mit Blumenkohl und Spinat zu Tomatensoße";
        recipes[4]="Makkaroni-Bratwurst-Auflauf mit Möhren und Champignons";
        recipes[5]="Kohlrabi-Kartoffelsuppe mit Räucherlachs und Kresse";
        recipes[6]="Cremige Apfel-Sellerie-Suppe";
        recipes[7]="Hühnersuppe mit Nudeln";
        recipes[8]="Portugiesischer Maisgrieß-Kuchen";
        recipes[9]="Luftiger Himbeerkuchen";
        recipes[10]="Kokos-Streuselkuchen mit Ananas";
        recipes[11]="Kartoffel-Tortilla";
        recipes[12]="Portugiesische Kartoffelsuppe mit Chorizo";
        recipes[13]="Kartoffelpuffer mit Apfelmus";
        recipes[14]="Chicken-Burger mit Mangosalsa und Guacamole";
        recipes[15]="Zwiebelringe in Bierteig";
        recipes[16]="Schweinebraten-Burger mit Texassoße";
        recipes[17]="Backkartoffelsalat mit Rucolapesto";
        recipes[18]="Kartoffelsalat mit gebratener Zucchini, Paprika und Kichererbsen";
        recipes[19]="Kartoffel-Kräutersalat";
        recipes[20]="Klassischer Kartoffelsalat mit Brühe";

    }

    public String getRecipes(int pos) {return recipes[pos];}

}

