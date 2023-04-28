package org.model;


import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.Character.toUpperCase;
import static org.model.Ensurer.*;

public class Immobilienmakler
{
    private String name;
    private ArrayList<Immobilie> immobilien;

    // ----------------- ctor ---------------------
    public Immobilienmakler(String name) throws ImmobilienmaklerException
    {
        setName(name);
        immobilien = new ArrayList<>();
    }

    // ----------------- setter ---------------------
    public void setName(String name) throws ImmobilienmaklerException
    {
        this.name = stringEnsurer(name, "name");
    }

    //-------------------- GETTER ----------------------

    public String getName()
    {
        return name;
    }

    //------------------ Methoden --------------------
    public void  add(Immobilie immo) throws ImmobilienmaklerException
    {
        ensurerNotNull(immo, "immobilie");
        ensurerState(immo.berechneVerkehrswert() > 50_000, "Zu billig");
        ensurerState(immobilien.size() < 51, "Portfolio voll");
        immobilien.add(immo);
    }

    public int anzimmobilien()
    {
        return immobilien.size();
    }

    public double calcGesamtwert()
    {
        double sum = 0;

        for (Immobilie i:immobilien)
        {
            sum += i.berechneVerkehrswert();
        }
        return sum;
    }

    public int countWidmung(char widmung) throws ImmobilienmaklerException
    {
        ensurerState(toUpperCase(widmung) == 'B' || toUpperCase(widmung) == 'G' || toUpperCase(widmung) == 'N', "Ungueltige Widmung");

        int anz = 0;
        for (Immobilie i:immobilien)
        {
            if (((Grundstueck)i).getWidmung() == widmung)
                anz++;
        }
        return anz;
    }

    public boolean remove(int pos) throws ImmobilienmaklerException
    {
        ensurerState(immobilien.size() >= pos, "So viele Immobilien befinden sich nicht im Portfolio");
        immobilien.remove(immobilien.get(pos));
        return true;
    }

    public int remove(double maxQmPreis)
    {
        int anzRemoved = 0;

        Iterator<Immobilie> i = immobilien.iterator();
        while (i.hasNext())
        {
            if (i instanceof Grundstueck && ((Grundstueck)i).getQmPreis() > maxQmPreis)
            {
                immobilien.remove(i);
                anzRemoved ++;
            }
        }
            return anzRemoved;
    }

    public void sortVerkehrswert(String sort) throws ImmobilienmaklerException
    {
        ensurerState(sort == "aufsteigend" || sort == "absteigend", "So kannst du nicht sortieren");

        VerkehrswertComparator vkwComparator = new VerkehrswertComparator();
        if (sort == "aufsteigend")
        {
            immobilien.sort(vkwComparator);
        }
        else
            immobilien.sort(vkwComparator.reversed());
    }

    //------------------ To String --------------------
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        //------------------ Header --------------------
        builder.append(String.format("%s, derzeit %d Immobilien\nGesamtwert: %.2f", name, immobilien.size(), calcGesamtwert()));
        builder.append("--------------------------------------------");

        //------------------ Body --------------------
        for (Immobilie i:immobilien)
        {
            builder.append(i.toString());
        }
       return builder.toString();
    }
}

