package org.model;

public abstract class Ensurer
{
    public static <T> T ensurerNotNull(T value, String param) throws ImmobilienmaklerException
    {
        if (value == null) //negativ testen
        {
            throw new ImmobilienmaklerException( param + " isNull");
        }
        return value;
    }

    public static String ensurerNotBlank(String value, String param) throws ImmobilienmaklerException
    {
        if (value.isBlank()) //negativ testen
            throw new ImmobilienmaklerException( param + " isBlank");

        return value;
    }

    public static String stringEnsurer(String value, String param) throws ImmobilienmaklerException
    {
        ensurerNotNull(value, param);
        ensurerNotBlank(value, param);
        return value;
    }

    public static void ensurerState(boolean state, String message) throws ImmobilienmaklerException
    {
        if (!state)
        {
            throw new ImmobilienmaklerException(message);
        }
    }
}
