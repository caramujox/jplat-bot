package br.com.fiap.jplatbot.service.rollDice;

import java.util.Random;

public class RollADice {

    public String getRandomNumber(){
        Random random = new Random();
        return String.valueOf(random.ints(1,7).findFirst().getAsInt());
    }
}
