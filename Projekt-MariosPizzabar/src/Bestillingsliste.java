public class Bestillingsliste {
    private String navn;
    private String ingredienser;
    private double pris;
    private boolean faerdig;
    private boolean afsluttet;

    public Bestillingsliste(String navn, String ingredienser, double pris) {
        this.navn = navn;
        this.ingredienser = ingredienser;
        this.pris = pris;
        this.faerdig = false;
        this.afsluttet = false;
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

    public boolean erFaerdig() {return faerdig;}

    public void setErFaerdig(boolean erFaerdig) {
        this.faerdig = faerdig;
    }

    public boolean erAfsluttet() {
        return afsluttet;
    }

    public void setAfsluttet(boolean afsluttet) {
        this.afsluttet = afsluttet;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

}