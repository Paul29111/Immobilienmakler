package org.model;

import java.util.Comparator;

public class VerkehrswertComparator implements Comparator<Immobilie>
{
    @Override
    public int compare(Immobilie o1, Immobilie o2)
    {
        return Double.compare(o1.berechneVerkehrswert(), o2.berechneVerkehrswert());
    }
}
