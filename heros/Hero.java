package heros;

public class Hero {
    
    // Variables des héros:
    protected String nom;
    protected int categorie, coutArmure;
    protected double coutArgent, ptsDeVie;

    // Constructeur:
    public Hero(String nom, int categorie, double coutArgent, int coutArmure,
    double ptsDeVie){
        this.nom = nom;
        this.categorie = categorie;
        this.coutArmure = coutArmure;
        this.coutArgent = coutArgent;
        this.ptsDeVie = ptsDeVie;
    }

    // Getters:
    public String getNom(){
        return nom;
    }

    public int getCategorie(){
        return categorie;
    }

    public int getCoutArmure(){
        return coutArmure;
    }

    public double getCoutArgent(){
        return coutArgent;
    }

    public double getPtsDeVie(){
        return Math.floor(ptsDeVie * 10) / 10;
    }

    // Setters:
    public void setNom(String nom){
        this.nom = nom;
    }

    public void setCoutArmure(int coutArmure){
        this.coutArmure = coutArmure;
    }

    public void setCoutArgent(double coutArgent){
        this.coutArgent = coutArgent;
    }
    
    // On entraine le héro en augmentant ses points de vie et sa catégorie.
    public void entrainer(){
        ptsDeVie *= 1.5;

        /* Si la catégorie du héro est maximale (4), on n'augmente que ses
        points de vie.*/ 
        if (categorie < 4){            
            categorie += 1;
        }
    }

    // Enlève des points de vie au héro:
    public void perdreVie(double viePerdue){
        ptsDeVie -= viePerdue;
    }

}
