public class Spectacle {
    private int num;
    private String titre;
    private double prix;
    private int nbBillets;

    private int NbBilletsInitial;
    
    public static int nbSpecs = 0;

    // constructeur par défaut
    public Spectacle() {
        this.num = 0;
        this.titre = "";
        this.prix = 0.0;
        this.NbBilletsInitial = 0;
        ++nbSpecs;
    }

    // constructeur par paramètre
    public Spectacle(int num, String titre, double prix, int nbBillets){
        this.num = num;
        this.titre = titre;
        this.prix = prix;
        this.nbBillets = nbBillets;
        this.NbBilletsInitial = 0;
        nbSpecs++;
    }

    // getter
    public int getNum() {
        return num;
    }

    public String getTitre() {
        return titre;
    }

    public double getPrix() {
        return prix;
    }

    public int getNbBillets() {
        return nbBillets;
    }

    public int getNbBilletsInitial(){
        return NbBilletsInitial;
    }

    public static int getNbSpecs() {
        return nbSpecs;
    }

    // setter
    public void setNum(int num) {
        this.num = num;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setNbBillets(int nbBillets) {
        this.nbBillets = nbBillets;
    }

    public void setNbBilletsInitial(int NbBilletsInitial){
        this.NbBilletsInitial = NbBilletsInitial;
    }

    // toString
    @Override
    public String toString() {
        return "Spectacle{" +
                "num=" + num +
                ", titre='" + titre + '\'' +
                ", prix=" + prix + '\'' +
                "nombre=" + nbBillets +
                '}';
    }

    public int compareTo(Spectacle autre, int attribut) {
        if (attribut == 1) {
            return Integer.compare(this.getNum(), autre.num);
        } else if (attribut == 2) {
            return this.getTitre().compareTo(autre.getTitre());
        } else if (attribut == 3){
            return Double.compare(this.getPrix(), autre.prix);
        } else {
            throw new IllegalArgumentException(attribut + "n'est pas valide");
        }
    }

}
