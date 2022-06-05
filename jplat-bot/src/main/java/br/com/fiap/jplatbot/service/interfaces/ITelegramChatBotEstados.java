package br.com.fiap.jplatbot.service.interfaces;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public interface ITelegramChatBotEstados {
    //Mostra comandos possiveis para o bot
    void executaMostrarComandos(TelegramBot bot, Update update);

    //Executa comando Roll a Dice
    void executaRollDice(TelegramBot bot, Update update);

    //Executa comando Weather Report
    void executaWeatherReport(TelegramBot bot, Update update);

    //Executa comando Pokedex
    void executaPokedex(TelegramBot bot, Update update);

    //Executa comando O que assistir
    void executaOqueAssistir(TelegramBot bot, Update update);

    //Executa ação Default para erros
    void defaultAction(TelegramBot bot, Update update);
}
