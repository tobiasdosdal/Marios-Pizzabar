import java.util.ArrayList;
import java.util.Scanner;

public class BestillingsSystem {
    private ArrayList<Bestillingsliste> bestillinger;
    private Scanner scanner;

    public BestillingsSystem() {
        this.bestillinger = BestillingslistePersistens.readBestilling();
        this.scanner = new Scanner(System.in);
    }

    public void startBestillingsSystem() {
        while (true) {
            visMenu();
            int valg = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (valg) {
                case 1:
                    opprettNyBestilling();
                    break;
                case 2:
                    visBestillinger();
                    break;
                case 3:
                    lagreBestillinger();
                    break;
                case 4:
                    System.out.println("Avslutter systemet...");
                    scanner.close();  // Close the scanner before exiting
                    return;
                default:
                    System.out.println("Ugyldig valg. Prøv igjen.");
            }
        }
    }

    private void visMenu() {
        System.out.println("\n=== Bestillingssystem ===");
        System.out.println("1. Opprett ny bestilling");
        System.out.println("2. Vis alle bestillinger");
        System.out.println("3. Lagre bestillinger");
        System.out.println("4. Avslutt");
        System.out.print("Velg en handling (1-4): ");
    }

    private void opprettNyBestilling() {
        try {
            System.out.println("\n=== Opprett ny bestilling ===");

            System.out.print("Skriv inn navn på retten: ");
            String navn = scanner.nextLine();

            System.out.print("Skriv inn ingredienser (kommaseparert): ");
            String ingredienser = scanner.nextLine();

            System.out.print("Skriv inn pris: ");
            int pris = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            Bestillingsliste nyBestilling = new Bestillingsliste(navn, ingredienser, pris);
            bestillinger.add(nyBestilling);

            System.out.println("Bestilling lagt til!");
        } catch (Exception e) {
            System.out.println("Feil ved innlesing av data. Prøv igjen.");
            scanner.nextLine(); // Clear the buffer in case of error
        }
    }

    private void visBestillinger() {
        System.out.println("\n=== Alle bestillinger ===");
        if (bestillinger.isEmpty()) {
            System.out.println("Ingen bestillinger funnet.");
            return;
        }

        for (int i = 0; i < bestillinger.size(); i++) {
            Bestillingsliste bestilling = bestillinger.get(i);
            System.out.println("Bestilling " + (i + 1) + ":");
            System.out.println("Navn: " + bestilling.getNavn());
            System.out.println("Ingredienser: " + bestilling.getIngredienser());
            System.out.println("Pris: " + bestilling.getPris() + " kr");
            System.out.println("--------------------");
        }
    }

    private void lagreBestillinger() {
        BestillingslistePersistens.writeBestilling(bestillinger);
        System.out.println("Bestillinger lagret til fil.");
    }
}