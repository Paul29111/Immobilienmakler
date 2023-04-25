package org.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class GrundstueckTest
{
    private Grundstueck grund;
    @BeforeEach
    public void setup() throws ImmobilienmaklerException
    {
        //GIVEN
        grund = new Grundstueck("Spengergasse", 'n', 10,300);
    }

    @ParameterizedTest
    @ValueSource(doubles={100, 100.5, 99_999.5, 100_000})
    void setFlaeche_shouldSetWithValuesInBound(double flaeche) throws ImmobilienmaklerException
    {
        //WHEN
        grund.setFlaeche(flaeche);
        //THEN
        assertEquals(flaeche, grund.getFlaeche());
    }

     @ParameterizedTest
    @ValueSource(doubles={99, 99.5, 100_000.5, 100_001})
    void setFlaeche_shouldNotSetWithValuesOutOfBound(double flaeche)
    {
        try
        {
            //WHEN
            grund.setFlaeche(flaeche);
        } catch (ImmobilienmaklerException e)
        {
            //THEN
            assertEquals("Ungueltige Flaeche", e.getMessage());
        }


    }


}