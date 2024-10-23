public class Bestillingsliste {
    private String navn;
    private String ingredienser;
    private double pris;

    public Bestillingsliste(String navn, String ingredienser, double pris) {
        this.navn = navn;
        this.ingredienser = ingredienser;
        this.pris = pris;
    }

    public String getNavn() {
        return navn;
    }

    public String getIngredienser() {
        return ingredienser;
    }

    public double getPris() {
        return pris;
    }
}