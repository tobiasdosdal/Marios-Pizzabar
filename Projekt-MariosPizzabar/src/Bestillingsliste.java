public class Bestillingsliste {
    private String navn;
    private String ingredienser;
    private int pris;

    // Constructor
    public Bestillingsliste(String navn, String ingredienser, int pris) {
        this.navn = navn;
        this.ingredienser = ingredienser;
        this.pris = pris;
    }

    // Getters
    public int getPris() {
        return pris;
    }

    public String getNavn() {
        return navn;
    }

    public String getIngredienser() {
        return ingredienser;
    }

    // Setters
    public void setPris(int pris) {
        this.pris = pris;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setIngredienser(String ingredienser) {
        this.ingredienser = ingredienser;
    }
}