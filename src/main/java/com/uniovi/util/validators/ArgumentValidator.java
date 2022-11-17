package com.uniovi.util.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ArgumentValidator {

    public static void isNotNull(final Object obj) {
        isTrue(obj != null, " Cannot be null ");
    }

    public static void isNotNull(final Object obj, String msg) {
        isTrue(obj != null, msg);
    }

    public static void isNull(final Object obj) {
        isTrue(obj == null, " Must be null ");
    }

    public static void isTrue(final boolean test) {
        isTrue(test, "Condition must be true");
    }

    public static void isTrue(final boolean test, final String msg) {
        if (test == true) {
            return;
        }
        throwException(msg);
    }

    public static void isNotEmpty(final String str) {
        isTrue(str != null && str.length() > 0,
                "The string cannot be null not empty");
    }

    public static void isNotEmpty(final String str, final String msg) {
        isTrue(str != null && str.length() > 0, msg);
    }

    protected static void throwException(final String msg) {
        throw new IllegalArgumentException(msg);
    }


    public static void checkEmail(String email) {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        isTrue(matcher.find(), "Email mal formado");
    }

    public static void checkDni(String dni) {
        Pattern pattern = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
        Matcher matcher = pattern.matcher(dni);
        isTrue(matcher.find(), "DNI mal formado");
    }

    public static void checkPassword(String password) {
        //Al menos un dígito, una mayúscula y una minúscia y la longitud entre 8 y 16 caracteres
        Pattern pattern = Pattern
                .compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$");
        Matcher matcher = pattern.matcher(password);
        isTrue(matcher.find(), "Contraseña mal formada");

    }
}
