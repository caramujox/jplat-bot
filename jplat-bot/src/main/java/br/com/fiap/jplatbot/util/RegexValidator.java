package br.com.fiap.jplatbot.util;

import java.util.Locale;

public class RegexValidator {

    public static boolean isValidText(String text) {
        return text.matches("[A-Za-z[1-4]_ ]{1,}");
    }

    public static String checkValidText(String text) {
        if (!text.matches("[A-Za-z[1-4]_ ]{1,}")) {
            System.out.println("Texto Invalido!");
            return "erro";
        }
        return text.toLowerCase(Locale.ROOT);
    }
}
