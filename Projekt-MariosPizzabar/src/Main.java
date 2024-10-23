import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private ArrayList<Bestillingsliste> menu;
    private ArrayList<Bestillingsliste> bestillinger;
    private Scanner scanner;

    public Main() {
        this.menu = BestillingslistePersistens.readMenu();
        this.bestillinger = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    private void visHovedmenu() {
        boolean fortsæt = true;
        while (fortsæt) {
            System.out.println("\n=== Hovedmenu ===");
            System.out.println("1. Opret ny bestilling");
            System.out.println("2. Afslut program");
            System.out.print("\nVælg en mulighed (1-2): ");

            String valg = scanner.nextLine();
            switch (valg) {
                case "1" -> opretNyBestilling();
                case "2" -> {
                    fortsæt = false;
                    System.out.println("Programmet afsluttes. På gensyn!");
                }
                default -> System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

    private void opretNyBestilling() {
        ArrayList<Bestillingsliste> aktuelBestilling = new ArrayList<>();
        double totalPris = 0;

        try {
            boolean tilføjFlerePizzaer = true;

            while (tilføjFlerePizzaer) {
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
                    aktuelBestilling.add(new Bestillingsliste(
                            valgtPizza.getNavn(),
                            valgtPizza.getIngredienser(),
                            valgtPizza.getPris()
                    ));
                    totalPris += valgtPizza.getPris();
                    System.out.println("Pizza tilføjet til bestillingen!");

                    System.out.print("\nVil du tilføje flere pizzaer til denne bestilling? (ja/nej): ");
                    String svar = scanner.nextLine().trim().toLowerCase();
                    tilføjFlerePizzaer = svar.equals("ja");
                } else {
                    System.out.println("Ugyldigt pizzanavn!");
                }
            }

            // Gem hele bestillingen på én gang
            BestillingslistePersistens.writeFullOrder(aktuelBestilling);

            // Vis ordreopsummering
            System.out.println("\n=== Ordreopsummering ===");
            for (Bestillingsliste pizza : aktuelBestilling) {
                System.out.println("- " + pizza.getNavn() + " - " + pizza.getPris() + " kr");
            }
            System.out.println("Total pris: " + totalPris + " kr");

            System.out.println("Bestilling gemt!");

        } catch (Exception e) {
            System.out.println("Fejl ved indlæsning af data. Prøv igen.");
            scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        Main mainProgram = new Main();
        mainProgram.visHovedmenu();
    }
}