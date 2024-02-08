import guildcommands.GuildCommand;
import guildcommands.GuildCommandSystem;

import heros.Hero;
import java.util.ArrayList;
import java.util.Comparator;

// Fait par Léane Lavigne (20190113) et Isaac Sauvé (20198696)

public class Main {
    /**
     * Args: array with
     * <ol>
     *     <li>guild:&lt;montant initial&gt;,&lt;armures initiales&gt;</li>
     * </ol>
     *
     * @param args
     */
    public static void main(String[] args) {

        GuildCommandSystem guildCommandSystem = new GuildCommandSystem(args);

        // Création de la guilde et de sa banque:
        Guilde maGuilde = makeGuilde(guildCommandSystem.actualCommand());
        Bank maBanque = new Bank(maGuilde.getArgent(),maGuilde.getNbArmures());

        /* Tableaux:
        -Héros: Gestion de l'inventaire des héros
        -Erreurs: Inventaire des erreurs */
        ArrayList<Hero> tableauHeros = new ArrayList<Hero>();
        ArrayList<String> tableauErreurs = new ArrayList<String>();

        // Messages d'erreurs possibles:
        String erreurAchatHero = "";
        String erreurAchatArmure = "";
        String decesHero = "";
        String erreurEntrainement = "";
        String erreurExistence = "";
        

        while (guildCommandSystem.hasNextCommand()) {

            GuildCommand command = guildCommandSystem.nextCommand();
            
            switch (command.getName()) {
                
                case "buy-hero"->{

                    // Variables pour l'achat d'héros:
                    String nom = command.nextString();
                    int categorie = command.nextInt();
                    double coutArgent = command.nextDouble();
                    int coutArmure = command.nextInt();
                    double ptsDeVie = command.nextDouble();
                    
                    /* Si on a assez d'argent et d'armure, on achète le héro et
                    on l'ajoute à tableauHeros. */
                    if (maBanque.verifierArgent(coutArgent) && 
                    maBanque.verifierArmure(coutArmure)){
                        
                        maBanque.retirerArgent(coutArgent);
                        maBanque.retirerArmure(coutArmure);

                        Hero hero = new Hero(nom, categorie, coutArgent, 
                        coutArmure, ptsDeVie);

                        tableauHeros.add(hero);
                    }

                    // Sinon, on ajoute le message d'erreur à tableauErreurs.
                    else {                      
                        erreurAchatHero += ("-Il vous manque de l'argent et/ou "
                        + "des armures pour acheter " + nom + ".");

                        tableauErreurs.add(erreurAchatHero);
                    }
                }
                
                case "buy-armor"-> {
                    
                    // Variables pour l'achat d'armures:
                    int nbArmures = command.nextInt();
                    int coutParArmure = command.nextInt();

                    // Coût total des armures:
                    int coutArgent = nbArmures * coutParArmure;

                    // Si on a assez d'argent, on achète les armures.
                    if (maBanque.verifierArgent(coutArgent)){

                        maBanque.retirerArgent(coutArgent);
                        maBanque.ajouterArmure(nbArmures);
                    }

                    // Sinon, on ajoute le message d'erreur à tableauErreurs.
                    else {
                        erreurAchatArmure += ("-Il vous manque de l'argent pour"
                        + " acheter des armures.");
                        tableauErreurs.add(erreurAchatArmure);
                    }
                }

                case "do-quest" -> {
                    
                    /* On ordonne d'abord en ordre décroissant de points de vie,
                    puis par catégorie. */
                    tableauHeros.sort(Comparator.comparing(
                        Hero::getPtsDeVie).reversed().thenComparing(
                        Hero::getCategorie));

                    // Variables pour faire une quête:
                    int categorie = command.nextInt();
                    double coutPtsVie = command.nextDouble();
                    int recompenseArgent = command.nextInt();
                    int recompenseArmure = command.nextInt();

                    // Sélection du héros selon la quête:
                    Quete quete = new Quete(categorie, coutPtsVie, 
                    recompenseArgent, recompenseArmure);

                    Hero heroChoisi = quete.choisirHero(tableauHeros);
                    
                    // Calculer et retirer ses points de vie:
                    coutPtsVie = coutPtsVie - (heroChoisi.getCategorie()
                    - categorie) * 1.5;
                    
                    heroChoisi.perdreVie(coutPtsVie);

                    // Si le héro survit, on récolte les récompenses.
                    if (heroChoisi.getPtsDeVie() > 0){
                        maBanque.ajouterArgent(recompenseArgent);
                        maBanque.ajouterArmure(recompenseArmure);
                    }
                    
                    /* Sinon, on ajoute le message d'erreur à tableauErreurs 
                    et on retire le héro du tableau. */
                    else {

                        decesHero += ("-La quête a échoué. RIP " 
                        + heroChoisi.getNom() + ".");

                        tableauErreurs.add(decesHero);
                        tableauHeros.remove(heroChoisi);
                    }
                }

                case "train-hero" -> {

                    // Variable pour entraîner un héro:
                    String nom = command.nextString();

                    /* On suppose que le héro à entraîner n'est pas dans le
                    tableau. */
                    Boolean existenceHero = false;

                    // On parcourt tableauHeros
                    for (Hero hero : tableauHeros){
                        
                        /* Si on trouve le héro, on calcule les coûts associés à
                        l'amélioration. */
                        if (hero.getNom().equals(nom)){
                            existenceHero = true;

                            double coutArgent = 20 * Math.log(
                                hero.getCategorie() + 10);
                            
                            int coutArmure = (int) Math.ceil(Math.log(
                                hero.getCategorie() + 10));
                            
                            /* Si on a assez d'argent et d'armures, on paie les
                            coûts et on améliore le héro. */
                            if (maBanque.verifierArgent(coutArgent) && 
                            maBanque.verifierArmure(coutArmure)){
                                
                                maBanque.retirerArgent(coutArgent);
                                maBanque.retirerArmure(coutArmure);
                                hero.entrainer();
                            }
                            
                            /* Sinon, on ajoute le message d'erreur à 
                            tableauErreurs */ 
                            else {
                                erreurEntrainement += ("-Il vous manque de " 
                                + "l'argent et/ou des armures pour améliorer " 
                                + nom + ".");

                                tableauErreurs.add(erreurEntrainement);
                            }
                        }
                    }

                    /* Si on n'a pas trouvé le héro, on ajoute le message 
                    d'erreur à tableauErreurs */
                    if (existenceHero == false){

                        erreurExistence += ("-Le héro du nom de " + nom + 
                        " n'apparaît pas dans la liste.");

                        tableauErreurs.add(erreurExistence);
                    }
                }
            }
        }

        // On affiche le résumé de la banque et des héros, s'il y en a:
        maBanque.afficherBank();
        maGuilde.afficherHeros(tableauHeros);

        // Si on a des messages d'erreurs, on les affiche.
        if (!tableauErreurs.isEmpty()){
            System.out.println("Erreur:");

            for (String messageErreur: tableauErreurs){
                System.out.println(messageErreur);
            }
        }
    }
    
    public static Guilde makeGuilde(GuildCommand command) {
        double montantInitial = command.nextDouble();
        int nbArmures = command.nextInt();
        return new Guilde(montantInitial, nbArmures);
    }
}