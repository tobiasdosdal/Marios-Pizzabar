import java.util.ArrayList;
import java.util.Scanner;

public class BestillingsSystem {
    private ArrayList<Bestillingsliste> menu;
    private ArrayList<Bestillingsliste> bestillinger;
    private Scanner scanner;

    public BestillingsSystem() {
        this.menu = BestillingslistePersistens.readMenu();
        this.bestillinger = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void startBestillingsSystem() {
        while (true) {
            visMenu();
            int valg = scanner.nextInt();
            scanner.nextLine();

            switch (valg) {
                case 1:
                    opretNyBestilling();
                    break;
                case 2:
                    visBestillinger();
                    break;
                case 3:
                    gemBestillinger();
                    break;
                case 4:
                    gemBestillinger();
                    System.out.println("Afslutter systemet...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

    private void visMenu() {
        System.out.println("\n=== Bestillingssystem ===");
        System.out.println("1. Opret ny bestilling");
        System.out.println("2. Vis alle bestillinger");
        System.out.println("3. Gem bestillinger");
        System.out.println("4. Afslut");
        System.out.print("Vælg en handling (1-4): ");
    }

    private void opretNyBestilling() {
        System.out.println("\n=== Menu ===");
        for (Bestillingsliste item : menu) {
            System.out.println("- " + item.getNavn() + " - " + item.getPris() + " kr");
        }

        System.out.print("\nIndtast navnet på pizzaen: ");
        String valgtNavn = scanner.nextLine().trim();

        Bestillingsliste valgtPizza = null;
        for (Bestillingsliste pizza : menu) {
            if (pizza.getNavn().equalsIgnoreCase(valgtNavn)) {
                valgtPizza = pizza;
                break;
            }
        }

        if (valgtPizza != null) {
            bestillinger.add(new Bestillingsliste(
                    valgtPizza.getNavn(),
                    valgtPizza.getIngredienser(),
                    valgtPizza.getPris()
            ));
            System.out.println("Bestilling tilføjet!");
        } else {
            System.out.println("Ugyldigt pizzanavn!");
        }
    }

    private void visBestillinger() {
        System.out.println("\n=== Alle bestillinger ===");
        if (bestillinger.isEmpty()) {
            System.out.println("Ingen bestillinger fundet.");
            return;
        }

        for (int i = 0; i < bestillinger.size(); i++) {
            Bestillingsliste bestilling = bestillinger.get(i);
            System.out.println("Bestilling " + (i + 1) + ": " + bestilling.getNavn() + " - " + bestilling.getPris() + " kr");
        }
    }

    private void gemBestillinger() {
        BestillingslistePersistens.writeBestilling(bestillinger);
        System.out.println("Bestillinger gemt.");
    }
}