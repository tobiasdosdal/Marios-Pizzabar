import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Opret ArrayList til at gemme bestillinger
        ArrayList<Bestillingsliste> bestillinger = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Indlæs eksisterende bestillinger fra fil
        System.out.println("\nIndlæser produkter fra fil:");
        bestillinger = læsBestillingerFraFil();

        // Vis indlæste bestillinger
        for (Bestillingsliste bestilling : bestillinger) {
            System.out.println(bestilling.getNavn() + " - " +
                    bestilling.getIngredienser() + " - " +
                    bestilling.getPris() + " kr");
        }

        // Start bestillingssystem
        while (true) {
            System.out.println("\n=== Bestillingssystem ===");
            System.out.println("1. Opret ny bestilling");
            System.out.println("2. Vis alle bestillinger");
            System.out.println("3. Gem bestillinger");
            System.out.println("4. Afslut");
            System.out.print("Vælg en handling (1-4): ");

            int valg = scanner.nextInt();
            scanner.nextLine(); // Ryd buffer

            switch (valg) {
                case 1:
                    // Opret ny bestilling
                    System.out.print("Indtast navn på retten: ");
                    String navn = scanner.nextLine();

                    System.out.print("Indtast ingredienser: ");
                    String ingredienser = scanner.nextLine();

                    System.out.print("Indtast pris: ");
                    int pris = scanner.nextInt();

                    Bestillingsliste nyBestilling = new Bestillingsliste(navn, ingredienser, pris);
                    bestillinger.add(nyBestilling);
                    System.out.println("Bestilling tilføjet!");
                    break;

                case 2:
                    // Vis alle bestillinger
                    System.out.println("\nAlle bestillinger:");
                    for (Bestillingsliste bestilling : bestillinger) {
                        System.out.println(bestilling.getNavn() + " - " +
                                bestilling.getIngredienser() + " - " +
                                bestilling.getPris() + " kr");
                    }
                    break;

                case 3:
                    // Gem til fil
                    skrivBestillingTilFil(bestillinger);
                    System.out.println("Bestillinger gemt til fil.");
                    break;

                case 4:
                    // Afslut
                    System.out.println("Afslutter programmet...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

    public static void skrivBestillingTilFil(ArrayList<Bestillingsliste> bestillingsliste) {
        BestillingslistePersistens.writeBestilling(bestillingsliste);
    }

    public static ArrayList<Bestillingsliste> læsBestillingerFraFil() {
        return BestillingslistePersistens.readBestilling();
    }
}