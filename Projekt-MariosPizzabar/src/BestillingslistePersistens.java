import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BestillingslistePersistens {
    public static void writeBestilling(ArrayList<Bestillingsliste> bestillingsliste) {
        String BestillingFile = "Projekt-MariosPizzabar/bestillingsliste.txt";

        try (FileWriter writer = new FileWriter(BestillingFile, false)) {
            for (Bestillingsliste p : bestillingsliste) {
                String navn = p.getNavn();
                String ingredienser = p.getIngredienser();
                int pris = p.getPris();

                writer.append(navn).append(",").append(ingredienser).append(",").append(String.valueOf(pris)).append("\n");
            }
            System.out.println("Bestillingsliste file written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Bestillingsliste> readBestilling() {
        ArrayList<Bestillingsliste> bestillinger = new ArrayList<>();
        String komma = ",";
        String line = "";
        String BestillingFile = "Projekt-MariosPizzabar/bestillingsliste.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(BestillingFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(komma);
                if(data.length >= 3) {
                    Bestillingsliste bestilling = new Bestillingsliste(
                            data[0],                    // navn
                            data[1],                    // ingredienser
                            Integer.parseInt(data[2])   // pris
                    );
                    bestillinger.add(bestilling);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bestillinger;
    }
}