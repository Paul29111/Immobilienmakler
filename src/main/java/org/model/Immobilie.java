package org.model;


import static org.model.Ensurer.stringEnsurer;

public abstract class Immobilie
{
    private String adresse;
    private double flaeche;

    // ----------------- ctor --------------------
    public Immobilie(String adresse) throws ImmobilienmaklerException
    {
        super();
        setAdresse(adresse);
    }

    // ----------------- getter --------------------
    public String getAdresse()
    {
        return adresse;
    }

    public double getFlaeche()
    {
        return flaeche;
    }

    // ----------------- setter ---------------------
    public void setAdresse(String adresse) throws ImmobilienmaklerException
    {
        this.adresse = stringEnsurer(adresse, "adresse");
    }

    public void setFlaeche(double flaeche) throws ImmobilienmaklerException
    {
        this.flaeche = flaeche;
    }

    //------------------ Methoden --------------------
    public abstract double berechneVerkehrswert();


    // ----------------- toString ---------------------
    @Override
    public String toString()
    {
        return String.format("%s | %.1f m2", adresse, flaeche);
    }
}

