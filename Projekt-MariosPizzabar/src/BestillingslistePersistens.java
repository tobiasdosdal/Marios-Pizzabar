import java.io.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class BestillingslistePersistens {
    private static final String MENU_FILE = "Projekt-MariosPizzabar/src/resources/menu.txt";
    private static final String BESTILLING_FILE = "Projekt-MariosPizzabar/src/resources/bestillinger.txt";
    private static final String ORDER_NUMBER_FILE = "Projekt-MariosPizzabar/src/resources/ordernumber.txt";
    private static int orderCounter;

    static {
        loadOrderNumber(); // indlæser sidste ordre nummer
    }

    private static void loadOrderNumber() {
        try {
            File file = new File(ORDER_NUMBER_FILE);
            if (!file.exists()) {
                orderCounter = 0;
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                orderCounter = Integer.parseInt(line.trim());
            } else {
                orderCounter = 0;
            }
            reader.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Fejl ved læsning af ordrenummer: " + e.getMessage());
            orderCounter = 0;
        }
    }

    private static void saveOrderNumber() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_NUMBER_FILE));
            writer.write(String.valueOf(orderCounter));
            writer.close();
        } catch (IOException e) {
            System.out.println("Fejl ved gemning af ordrenummer: " + e.getMessage());
        }
    }

    public static int getNextOrderNumber() {
        orderCounter++; // nummer +1
        saveOrderNumber(); // Gem ved nyt nummer
        return orderCounter;
    }

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

    public static void writeFullOrder(ArrayList<Bestillingsliste> order, double totalPris) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String timestamp = now.format(formatter);

        int orderNumber = getNextOrderNumber(); // Næste ordre nummer

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BESTILLING_FILE, true))) {
            bw.newLine();
            bw.write("-------- ( ORDRE Nr: "+ orderNumber +" ) --------");
            bw.newLine();
            bw.write("------ ("+ timestamp +") -------");
            bw.newLine();
            for (Bestillingsliste item : order) {
                bw.write(item.getNavn() + "," + item.getPris());
                bw.newLine();
            }
            bw.write("---------------------------------");
            bw.newLine();
            bw.write("Total pris: " + totalPris + " kr");
            bw.newLine();
            bw.write("=================================");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil: " + e.getMessage());
        }
    }
}