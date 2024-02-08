import heros.Hero;
import java.lang.Math;

public class Bank extends Guilde{

    // Constructeur:
    public Bank(double argent, int nbArmures){
        super(argent, nbArmures);
    }

    // Fonctions pour ajouter ou retirer de l'argent et des armures:
    public void ajouterArgent(int recompenseArgent){
        argent+=recompenseArgent;
    }
    
    public void ajouterArmure(int recompenseArmure){
        nbArmures+=recompenseArmure;
    }

    public void retirerArgent(double coutArgent){
            argent-=coutArgent;     
    }

    public void retirerArmure(int coutArmure){
            nbArmures-=coutArmure;
    }

    // Calcul des coûts en argent et en armure pour entraîner un héro:
    public void entrainer(Hero hero){
        retirerArgent( 20 * Math.log( hero.getCategorie() + 10 ) );
        retirerArmure( (int) Math.ceil(Math.log(hero.getCategorie() + 10) ) );
    }

    // Vérifier qu'on a assez d'argent et d'armures pour payer les coûts:
    public boolean verifierArgent(double coutArgent){
        if (coutArgent <= argent){
            return true;
        }        
        return false;
    }
    
    public boolean verifierArmure(int coutArmure){
        if (coutArmure <= nbArmures){
            return true;
        }
        return false;
    }
}