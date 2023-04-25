package org.model;

import static java.lang.Character.*;
import static org.model.Ensurer.ensurerState;

public class Grundstueck extends Immobilie
{
    private char widmung;
    private double qmPreis;

    // ----------------- ctor ---------------------
    public Grundstueck(String adresse, char widmung, double qmPreis, double flaeche) throws ImmobilienmaklerException
    {
        super(adresse);
        setWidmung(widmung);
        setQmPreis(qmPreis);
        setFlaeche(flaeche);
    }

    // ----------------- getter ---------------------
    public char getWidmung()
    {
        return widmung;
    }

    public double getQmPreis()
    {
        return qmPreis;
    }

    // ----------------- setter ---------------------
    public void setWidmung(char widmung) throws ImmobilienmaklerException
    {
        Ensurer.ensurerState(toUpperCase(widmung) == 'B' || toUpperCase(widmung) == 'G' || toUpperCase(widmung) == 'N', "Ungueltige Widmung");
        this.widmung = widmung;
    }

    public void setQmPreis(double qmPreis) throws ImmobilienmaklerException
    {
        ensurerState(qmPreis > 0 && qmPreis <= 10_000, "Ungueltiger qmPreis");
        this.qmPreis = qmPreis;
    }

    @Override
    public void setFlaeche(double flaeche) throws ImmobilienmaklerException
    {
        ensurerState(flaeche >= 100 && flaeche <= 100_000,"Ungueltige Flaeche");
        super.setFlaeche(flaeche);
    }

    //------------------ Methoden --------------------
    public double berechneVerkehrswert()
    {
        if (widmung == 'b' || widmung == 'B')
            return qmPreis * getFlaeche() * 2;
        else if (widmung == 'n' || widmung == 'N')
            return (qmPreis * getFlaeche()) / 2;
        else
            return qmPreis * getFlaeche();
    }

    @Override
    public String toString()
    {
        return String.format("Grundstueck | %s | %s | %.2f", super.toString(), toUpperCase(widmung) == 'B'?"Bauland":toUpperCase(widmung) == 'N'?"Naturschutzgebiet":"Gruenland", berechneVerkehrswert());
    }
}
