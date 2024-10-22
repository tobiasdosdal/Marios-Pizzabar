import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create ArrayList to store orders
        ArrayList<Bestillingsliste> bestillinger = new ArrayList<>();

        // Create some test orders
        Bestillingsliste bestilling1 = new Bestillingsliste("Margherita", "Tomat, Ost, Basilikum", 85);
        Bestillingsliste bestilling2 = new Bestillingsliste("Pepperoni", "Tomat, Ost, Pepperoni", 95);

        // Add orders to ArrayList
        bestillinger.add(bestilling1);
        bestillinger.add(bestilling2);

        // Write orders to file
        writeBestillingToFile(bestillinger);

        // Read and display orders from file
        readBestillingFromFile();
    }

    public static void writeBestillingToFile(ArrayList<Bestillingsliste> bestillingsliste) {
        BestillingslistePersistens.writeBestilling(bestillingsliste);
    }

    public static void readBestillingFromFile() {
        BestillingslistePersistens.readBestilling();
    }
}