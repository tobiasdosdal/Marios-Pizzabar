import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create ArrayList to store orders
        ArrayList<Bestillingsliste> bestillinger = new ArrayList<>();

        // Create some test orders
        Bestillingsliste bestilling1 = new Bestillingsliste("Margherita", "Tomat, Ost, Basilikum", 85);
        Bestillingsliste bestilling2 = new Bestillingsliste("Pepperoni", "Tomat, Ost, Pepperoni", 95);

        // Add orders to ArrayList
        //bestillinger.add(bestilling1);
        //bestillinger.add(bestilling2);

        // Write orders to file
        //writeBestillingToFile(bestillinger);

        // Read and display orders from file
        System.out.println("\nLæser produkter fra fil:");
        ArrayList<Bestillingsliste> loadedBestillinger = readBestillingFromFile();

        // Print loaded orders to verify
        for (Bestillingsliste bestilling : loadedBestillinger) {
            System.out.println(bestilling.getNavn() + " - " +
                    bestilling.getIngredienser() + " - " +
                    bestilling.getPris() + " kr");
        }
    }

    public static void writeBestillingToFile(ArrayList<Bestillingsliste> bestillingsliste) {
        BestillingslistePersistens.writeBestilling(bestillingsliste);
    }

    public static ArrayList<Bestillingsliste> readBestillingFromFile() {
        return BestillingslistePersistens.readBestilling();
    }
}