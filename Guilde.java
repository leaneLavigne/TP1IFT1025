import heros.Hero;
import java.util.ArrayList;

public class Guilde {
    
    // Variables de la guilde:
    protected double argent;
    protected int nbArmures;

    // Constructeur:
    public Guilde(double argent, int nbArmures){
        this.argent = argent;
        this.nbArmures = nbArmures;
    }

    // Getters:
    public double getArgent(){
        return Math.floor(argent * 10) / 10;
    }

    public int getNbArmures(){
        return nbArmures;
    }
    
    // Message du résumé de la banque:
    public String resumerBank(){
        return "Guild Bank account: " + getArgent() + " gold & " + 
        getNbArmures() + " armours";
    }

    // Affichage du message du résumé de la banque:
    public void afficherBank(){
        System.out.println(resumerBank());
    }

    // Message du résumé des héros de la guilde:
    public String resumerHeros(ArrayList<Hero> tableauHeros){
        
        String resumeHeros = "";

        /* Si on a des héros, on les ajoute à resumeHeros avec leur catégorie et 
        leurs points de vie */ 
        if (!tableauHeros.isEmpty()){
            resumeHeros += "Heroes:\n";
            
            for (Hero hero: tableauHeros){
                resumeHeros += "-"+ hero.getNom() + ": Level=" 
                + hero.getCategorie() + ", HP=" + hero.getPtsDeVie() +"\n";
            }
            // Retourne le message sans le dernier saut à la ligne:
            return resumeHeros.substring(0, resumeHeros.length() - 1);
        }

        // Retourne le message vide
        return resumeHeros;
    }

    // Affichage du message du résumé des héros de la guilde:
    public void afficherHeros(ArrayList<Hero> tableauHeros){
        
        // Si la liste de héros n'est pas vide, on l'affiche.
        if (!resumerHeros(tableauHeros).isEmpty()){
            System.out.println(resumerHeros(tableauHeros));
        }
        
    }
}
