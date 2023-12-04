import java.io.IOException;
import java.util.List;

public class SpectacleController {
    private SpectacleStore spectacleStore = null;

    private static SpectacleController spectacleCTR = null;

    public static String monPath = "";

    public static String getMonPath() {
        return monPath;
    }

    public static void setMonPath(String path) {
        monPath = path;
    }

    private SpectacleController() {
        if(spectacleStore == null){
            this.spectacleStore = new SpectacleStore(monPath);
        }
    }

    public static SpectacleController obtenirSpectacleController() {
        if (spectacleCTR == null){
            spectacleCTR = new SpectacleController();
        }
        return spectacleCTR;
    }

    public List<Spectacle> chargerSpectacles() throws IOException {
        return spectacleStore.chargerSpectacles();
    }

    public void ajouterSpectacle(List<Spectacle> listeSpectacles) {
        spectacleStore.sauvegarderSpectacles(listeSpectacles);
    }

    public void supprimerSpectacle(List<Spectacle> listeSpectacles) {
        spectacleStore.sauvegarderSpectacles(listeSpectacles);
    }

    public void modifierSpectacle(List<Spectacle> listeSpectacles) {
        spectacleStore.sauvegarderSpectacles(listeSpectacles);
    }

    public void sauvegarderSpectacles(List<Spectacle> listeSpectacles) {
        spectacleStore.sauvegarderSpectacles(listeSpectacles);
    }
}
