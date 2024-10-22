import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create ArrayList to store orders
        ArrayList<Bestillingsliste> bestillinger = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Load existing orders from file
        System.out.println("\nLæser produkter fra fil:");
        bestillinger = readBestillingFromFile();

        // Print loaded orders
        for (Bestillingsliste bestilling : bestillinger) {
            System.out.println(bestilling.getNavn() + " - " +
                    bestilling.getIngredienser() + " - " +
                    bestilling.getPris() + " kr");
        }

        // Start order system
        while (true) {
            System.out.println("\n=== Bestillingssystem ===");
            System.out.println("1. Opprett ny bestilling");
            System.out.println("2. Vis alle bestillinger");
            System.out.println("3. Lagre bestillinger");
            System.out.println("4. Avslutt");
            System.out.print("Velg en handling (1-4): ");

            int valg = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (valg) {
                case 1:
                    // Create new order
                    System.out.print("Skriv inn navn på retten: ");
                    String navn = scanner.nextLine();

                    System.out.print("Skriv inn ingredienser: ");
                    String ingredienser = scanner.nextLine();

                    System.out.print("Skriv inn pris: ");
                    int pris = scanner.nextInt();

                    Bestillingsliste nyBestilling = new Bestillingsliste(navn, ingredienser, pris);
                    bestillinger.add(nyBestilling);
                    System.out.println("Bestilling lagt til!");
                    break;

                case 2:
                    // Show all orders
                    System.out.println("\nAlle bestillinger:");
                    for (Bestillingsliste bestilling : bestillinger) {
                        System.out.println(bestilling.getNavn() + " - " +
                                bestilling.getIngredienser() + " - " +
                                bestilling.getPris() + " kr");
                    }
                    break;

                case 3:
                    // Save to file
                    writeBestillingToFile(bestillinger);
                    System.out.println("Bestillinger lagret til fil.");
                    break;

                case 4:
                    // Exit
                    System.out.println("Avslutter programmet...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Ugyldig valg. Prøv igjen.");
            }
        }
    }

    public static void writeBestillingToFile(ArrayList<Bestillingsliste> bestillingsliste) {
        BestillingslistePersistens.writeBestilling(bestillingsliste);
    }

    public static ArrayList<Bestillingsliste> readBestillingFromFile() {
        return BestillingslistePersistens.readBestilling();
    }
}