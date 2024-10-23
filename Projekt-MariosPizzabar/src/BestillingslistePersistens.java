import java.io.*;
import java.util.ArrayList;

public class BestillingslistePersistens {
    private static final String MENU_FILE = "Projekt-MariosPizzabar/src/resources/menu.txt";
    //private static final String MENU_FILE = "menu.txt";
    private static final String BESTILLING_FILE = "Projekt-MariosPizzabar/src/resources/bestillinger.txt";

    public static ArrayList<Bestillingsliste> readMenu() {
        ArrayList<Bestillingsliste> menu = new ArrayList<>();
        System.out.println("In read menu");
        try (BufferedReader br = new BufferedReader(new FileReader(MENU_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String navn = parts[0].trim();
                String ingredienser = parts[1].trim();
                double pris = Double.parseDouble(parts[2].trim());
                menu.add(new Bestillingsliste(navn, ingredienser, pris));
            }
        } catch (IOException e) {
            System.out.println("Fejl ved indlæsning af menu: " + e.getMessage());
        }
        return menu;
    }

    public static void writeBestilling(ArrayList<Bestillingsliste> bestillinger) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BESTILLING_FILE, true))) {
            for (Bestillingsliste bestilling : bestillinger) {
                bw.newLine();
                bw.write("========( ny ordre )========");
                bw.newLine();
                bw.write(bestilling.getNavn() + "," + bestilling.getPris());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Fejl ved gemning af bestillinger: " + e.getMessage());
        }
    }
}