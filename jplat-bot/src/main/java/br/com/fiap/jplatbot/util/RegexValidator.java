package br.com.fiap.jplatbot.util;

public class RegexValidator {

    public static boolean isValidText(String text){
        return text.matches("[A-Za-z]{2,}");
    }
}
