package bandeau;

public class ExerciceAvecThreads {

    public static void main(String[] args) {
        ExerciceAvecThreads instance = new ExerciceAvecThreads();
        instance.exemple();
    }

    public void exemple() {

        Scenario s = makeScenario();
        // On cree les bandeaux
        Bandeau b1 = new Bandeau();
        Bandeau b2 = new Bandeau();
        Bandeau b3 = new Bandeau();

        System.out.println("CTRL-C pour terminer le programme");
        // On doit jouer le scénario en même temps sur les trois bandeaux
        /*s.playOn(b1);
        s.playOn(b2);
        s.playOn(b3);
        // On rejoue le scénario sur b1 quand le premier jeu est fini
        s.playOn(b1);*/

        // On peut répéter le cycle x fois (ici 3 fois)
        for (int i = 0; i <= 2; i++) {
            BandeauThreaded bandeauThreaded1 = new BandeauThreaded(b1, s); // On implémente la "sous classe" de Bandeau
            BandeauThreaded bandeauThreaded2 = new BandeauThreaded(b2, s);
            BandeauThreaded bandeauThreaded3 = new BandeauThreaded(b3, s);

            bandeauThreaded1.startScenario();
            bandeauThreaded2.startScenario();
            bandeauThreaded3.startScenario();

            try {
                bandeauThreaded1.getThread().join(); // Utilisation de join pour attendre que chaque Treads se terminent avant de continuer
                bandeauThreaded2.getThread().join();
                bandeauThreaded3.getThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Scenario makeScenario() {
        // On crée un scenario
        Scenario s = new Scenario();
        // On lui ajoute des effets
        s.addEffect(new RandomEffect("Le jeu du pendu", 500), 1);
        // s.addEffect(new TeleType("Je m'affiche caractère par caractère", 100), 1);
        // s.addEffect(new Blink("Je clignote 10x", 100), 10);
        // s.addEffect(new Zoom("Je zoome", 50), 1);
        // s.addEffect(new FontEnumerator(10), 1);
        s.addEffect(new Rainbow("Comme c'est joli !", 30), 1);
        s.addEffect(new Rotate("2 tours à droite", 180, 1500, true), 2);
        // s.addEffect(new Rotate("2 tours à gauche", 180, 4000, false), 2);
        return s;
    }
}
