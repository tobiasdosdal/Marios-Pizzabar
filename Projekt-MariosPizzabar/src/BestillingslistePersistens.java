import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BestillingslistePersistens {
    private static final String BESTILLING_FILE = "bestillingsliste.txt";

    public static void writeBestilling(ArrayList<Bestillingsliste> bestillingsliste) {
        try (FileWriter writer = new FileWriter(BESTILLING_FILE, false)) {
            for (Bestillingsliste p : bestillingsliste) {
                writer.append(p.getNavn())
                        .append(",")
                        .append(p.getIngredienser())
                        .append(",")
                        .append(String.valueOf(p.getPris()))
                        .append("\n");
            }
            System.out.println("Bestillingsliste file written successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static ArrayList<Bestillingsliste> readBestilling() {
        ArrayList<Bestillingsliste> loadedBestillinger = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(BESTILLING_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    Bestillingsliste bestilling = new Bestillingsliste(
                            data[0],                    // navn
                            data[1],                    // ingredienser
                            Integer.parseInt(data[2])   // pris
                    );
                    loadedBestillinger.add(bestilling);
                    System.out.println("Loaded: Navn: " + data[0] + ", Ingredienser: " + data[1] + ", Pris: " + data[2]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            e.printStackTrace();
        }

        return loadedBestillinger;
    }
}