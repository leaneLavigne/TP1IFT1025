import java.util.ArrayList;
import heros.Hero;

public class Quete {

    // Variables de la quête:
    private int categorie, recompenseArgent, recompenseArmure;
    private double coutPtsVie;

    // Constructeur:
    public Quete(int categorie, double coutPtsVie, int recompenseArgent, 
    int recompenseArmure){
        this.categorie=categorie;
        this.coutPtsVie=coutPtsVie;
        this.recompenseArgent=recompenseArgent;
        this.recompenseArmure=recompenseArmure;
    }

    // Getters:
    public int getCategorie(){
        return categorie;
    }

    public double getCoutPtsVie(){
        return coutPtsVie;
    }

    public int getRecompenseArgent(){
        return recompenseArgent;
    }

    public int getRecompenseArmure(){
        return recompenseArmure;
    }

    // Sélection du héro pour envoyer en quête:
    public Hero choisirHero(ArrayList<Hero> listeHeros){
        
        /* Création de la variable de la valeur de la catégorie du héro choisi. 
        On l'initialise à 0, la catégorie la plus basse.*/ 
        int categorieChoisie = 0;

        /* Prend en paramètre la liste de héros triée en ordre décroissant de
        points de vie et en ordre croissant de catégories. */ 
        for (Hero hero: listeHeros){

            /* Si on trouve une catégorie plus élevée, on l'assigne à 
            categorieChoisie */ 
            if (categorieChoisie < hero.getCategorie()){
                categorieChoisie = hero.getCategorie();
            }

            /* On choisit le premier héro qui a une catégorie plus grande ou 
            égale à celle de la quête. */ 
            if (hero.getCategorie() >= categorie){
                return hero;
            }
        }

        // Sinon, on prend le premier héro avec la plus haute catégorie.
        for (Hero hero: listeHeros){
            if (hero.getCategorie() == categorieChoisie){
                return hero;
            }
        }
        return null;
    }
}