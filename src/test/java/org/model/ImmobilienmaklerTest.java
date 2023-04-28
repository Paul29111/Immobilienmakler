package org.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ImmobilienmaklerTest
{

    private Immobilienmakler immoMakler;

    @BeforeEach
    public void setup() throws ImmobilienmaklerException
    {
        //GIVEN
        immoMakler = new Immobilienmakler("Ibiya-Real-Estate");
    }

    //---------------------------------- test setName -------------------------------
    @Test
    void setName_shouldSet() throws ImmobilienmaklerException
    {
        //WHEN
        immoMakler.setName("Ibiza-Real-Estate");

        //THEN
        assertEquals("Ibiza-Real-Estate", immoMakler.getName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "    \n"})
    void setName_shouldNotSetWithNull_Blank(String name)
    {
        try
        {
            //WHEN
            immoMakler.setName(name);
        } catch (ImmobilienmaklerException e)
        {
            //THEN
            assertEquals("name isBlank", e.getMessage());
        }
    }

    //---------------------------------- test add -------------------------------

    @Test
    void add_shouldAddToGalerie() throws ImmobilienmaklerException
    {
        //GIVEN
        Grundstueck grund = new Grundstueck("Spengergasse", 'b', 100,300);

        //WHEN
        immoMakler.add(grund);

        //THEN
        assertEquals(1, immoMakler.anzimmobilien());
    }

    @Test
    void add_shouldNotAddWhenImmobilieNull()
    {
        try
        {
            //WHEN
            immoMakler.add(null);

            fail();

        } catch (ImmobilienmaklerException e)
        {
            //THEN
            assertEquals("immobilie isNull", e.getMessage());
        }
    }

    @Test
    void add_shouldNotAddWhenZuBillig_below50_000()
    {
        try
        {
            //GIVEN
            Grundstueck grund = new Grundstueck("Spengergasse", 'n', 100,300);

            //WHEN
            immoMakler.add(grund);

            fail();

        } catch (ImmobilienmaklerException e)
        {
            //THEN
            assertEquals("Zu billig", e.getMessage());
        }
    }

    @Test
    void add_shouldNotAddWhenFull_over50immo()
    {
        try
        {
            //GIVEN
            Grundstueck grund = new Grundstueck("Spengergasse", 'B', 1000,700);


            //WHEN
            for (int i = 0; i < 52; i++)
            {
                immoMakler.add(grund);
            }


            fail();
        } catch (ImmobilienmaklerException e)
        {
            //THEN
            assertEquals("Portfolio voll", e.getMessage());
        }
    }

    //---------------------------------- test countWidmung -------------------------------
    @Test
    void countWidmung_shouldWorkWithValidValues_BNG() throws ImmobilienmaklerException
    {
        //GIVEN
        Grundstueck grund = new Grundstueck("Spengergasse", 'b', 100,300);
        immoMakler.add(grund);

        //WHEN
        immoMakler.countWidmung('b');

        //THEN
        assertEquals(1, immoMakler.countWidmung('b'));
    }

    @ParameterizedTest
    @ValueSource(chars = {'a','c','d','e','f','h','i','j','k','l','m','o','p','q','r','s','t','u','v','w','x','y','z'})
    void countWidmung_shouldNotWorkWithInvalidValues(char widmung) throws ImmobilienmaklerException
    {
        try
        {
            //GIVEN
            Grundstueck grund = new Grundstueck("Spengergasse", widmung, 100,300);
            immoMakler.add(grund);

            //WHEN
            immoMakler.countWidmung(widmung);

            fail();
        } catch (ImmobilienmaklerException e)
        {
            //THEN
            assertEquals("Ungueltige Widmung", e.getMessage());
        }
    }
}