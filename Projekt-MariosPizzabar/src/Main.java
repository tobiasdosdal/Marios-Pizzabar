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
            System.out.println("2. Bekræft betaling");
            System.out.println("3. Marker pizza som klar");
            System.out.println("4. Marker ordrer som afsluttet");
            System.out.println("5. Skift pizza pris");
            System.out.println("6. Afslut program");
            System.out.print("\nVælg en mulighed (1-6): ");

            String valg = scanner.nextLine();
            switch (valg) {
                case "1" -> opretNyBestilling();
                case "2" -> bekraeftBetaling();
                case "3" -> markPizzaDone();
                case "4" -> markOrderDone();
                case "5" -> opdaterPris();
                case "6" -> {
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
            BestillingslistePersistens.writeFullOrder(aktuelBestilling, totalPris);

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


    private void markPizzaDone()
    {
        System.out.println("\n=== Marker pizza som færdig ===");

        // Ingen pizza bestilling
        if (bestillinger.isEmpty())
        {
            System.out.println("Ingen pizzaer at markere");
            return;
        }

        // Hvis der er bestillinger
        for (int i = 0; i < bestillinger.size(); i++)
        {
            System.out.println((i + 1) + ". " + bestillinger.get(i).getNavn());
        }

        System.out.print("\nVælg pizza der skal markeres som færdig (1-" + bestillinger.size() + "): ");
        int valg = scanner.nextInt();
        scanner.nextLine();

        if (valg < 1 || valg > bestillinger.size())
        {
            System.out.println("Ugyldigt valg");
            return;
        }

        bestillinger.get(valg - 1).setErFaerdig(true);
        System.out.println("Pizza " + bestillinger.get(valg - 1).getNavn() + " er markeret som færdig");
    }


    // Betaling bekræftelse
    private void bekraeftBetaling()
    {
        System.out.println("\n=== Bekræft Betaling ===");
        if (bestillinger.isEmpty())
        {
            System.out.println("Ingen bestillinger at betale for");
            return;
        }

        for (int i = 0; i < bestillinger.size(); i++)
        {
            System.out.println((i + 1) + ". " + bestillinger.get(i).getNavn() + " - " + bestillinger.get(i).getPris() + " kr");
        }
        System.out.print("\nVælg bestilling der skal bekræftes (1-" + bestillinger.size() + "): ");
        int valg = scanner.nextInt();
        scanner.nextLine();


        if (valg < 1 || valg > bestillinger.size())
        {
            System.out.println("Ugyldigt valg.");
            return;
        }
        Bestillingsliste valgtPizza = bestillinger.get(valg - 1);
        System.out.println("Betaling for " + valgtPizza.getNavn() + " bekræftet");


        // Marker pizza som færdig efter bekræftelse
        valgtPizza.setErFaerdig(true);
        System.out.println("Pizza " + valgtPizza.getNavn() + " er nu markeret som færdig.");
    }


    // marker ordre - afsluttet
    private void markOrderDone()
    {
        System.out.println("\n=== Marker ordre som afsluttet ===");


        // Tjek om der er bestillinger
        if (bestillinger.isEmpty())
        {
            System.out.println("Ingen bestillinger at markere som afsluttet.");
            return;
        }

        // Bestillinger der ikke er markeret som afsluttet
        for (int i = 0; i < bestillinger.size(); i++)
        {
            if (!bestillinger.get(i).erAfsluttet())
            {
                System.out.println((i + 1) + ". " + bestillinger.get(i).getNavn() + " - " + bestillinger.get(i).getPris() + " kr");
            }
        }
        System.out.print("\nVælg bestilling som skal afsluttes (1-" + bestillinger.size() + "): ");
        int valg = scanner.nextInt();
        scanner.nextLine();

        if (valg < 1 || valg > bestillinger.size() || bestillinger.get(valg - 1).erAfsluttet())
        {
            System.out.println("Ugyldigt valg eller er allerede afsluttet");
            return;
        }

        // Marker valgt ordre som afsluttet
        bestillinger.get(valg - 1).setAfsluttet(true);
        System.out.println("Ordre for " + bestillinger.get(valg - 1).getNavn() + " er markeret som afsluttet");
    }


    // Pizza pris opdatering
    private void opdaterPris()
    {
        System.out.println("\n=== Ændre pris ===");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i).getNavn() + " - " + menu.get(i).getPris() + " kr");
        }

        System.out.print("\nVælg pizza der skal ændre pris (1-" + menu.size() + "): ");
        int valg = scanner.nextInt();
        scanner.nextLine();

        if (valg < 1 || valg > menu.size())
        {
            System.out.println("Ugyldigt valg.");
            return;
        }

        Bestillingsliste valgtPizza = menu.get(valg - 1);

        double nyPris;
        while (true)
        {
            System.out.print("Indtast ny pris for " + valgtPizza.getNavn() + ": ");
            nyPris = scanner.nextDouble();
            scanner.nextLine();

            if (nyPris > 0)
            {
                break;
            }
            else
            {
                System.out.println("Pris skal være et positivt tal");
            }
        }

        // Opdater pris
        valgtPizza.setPris(nyPris);
        System.out.println("Pris for " + valgtPizza.getNavn() + " er ændret til " + nyPris + "kr");

        // Gem ændringen i menuen
        BestillingslistePersistens.skrivMenu(menu);
    }
}