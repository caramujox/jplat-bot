package br.com.fiap.jplatbot.service.interfaces;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public interface ITelegramChatBotEstados {
    //Mostra comandos possiveis para o bot
    void executaMostrarComandos(TelegramBot bot, Long id);

    //Executa comando Roll a Dice
    void executaRollDice(TelegramBot bot, Long id);

    //Executa comando Weather Report
    void executaWeatherReport(TelegramBot bot, Long id);

    //Executa comando Pokedex
    void executaPokedex(TelegramBot bot, Long id);

    //Executa comando O que assistir
    void executaOqueAssistir(TelegramBot bot, Long id);

    //Executa ação Default para erros
    void defaultAction(TelegramBot bot, Long id);

    void executaTrataErro(TelegramBot bot, Long id);
}
