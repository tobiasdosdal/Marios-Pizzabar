import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private ArrayList<Bestillingsliste> menu;
    private ArrayList<Bestillingsliste> bestillinger;
    private Scanner scanner;

    public Main() {
        this.menu = BestillingslistePersistens.readMenu(); // Indlæs menuen
        this.bestillinger = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    private void opretNyBestilling() {
        try {
            System.out.println("\n=== Menu ===");
            for (Bestillingsliste item : menu) {
                System.out.println("- " + item.getNavn() + " - " + item.getPris() + " kr");
            }

            System.out.print("\nIndtast navnet på pizzaen: ");
            String valgtNavn = scanner.nextLine().trim(); // Trim input fra brugeren

            // Debugging - udskriver det indtastede navn
            System.out.println("Du indtastede: " + valgtNavn);

            // Find pizzaen med det angivne navn
            Bestillingsliste valgtPizza = null;
            for (Bestillingsliste pizza : menu) {
                // Debugging - udskriver navnet på den pizza, der tjekkes
                System.out.println("Tjekker pizza: " + pizza.getNavn());
                if (pizza.getNavn().equalsIgnoreCase(valgtNavn)) { // Tjekker for det indtastede navn
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
                System.out.println("Ugyldigt pizzanavn!"); // Viser fejlmeddelelse
            }
        } catch (Exception e) {
            System.out.println("Fejl ved indlæsning af data. Prøv igen.");
            scanner.nextLine(); // Ryd bufferen
        }
    }

    // Tilføj metoder til hovedmenu og programflow her...

    public static void main(String[] args) {
        Main mainProgram = new Main();
        mainProgram.opretNyBestilling(); // Kald metoden for at køre programmet
    }
}