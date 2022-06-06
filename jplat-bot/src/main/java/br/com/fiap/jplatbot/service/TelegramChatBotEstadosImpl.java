package br.com.fiap.jplatbot.service;

import br.com.fiap.jplatbot.service.rollDice.RollADice;
import br.com.fiap.jplatbot.service.interfaces.ITelegramChatBotEstados;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendVideo;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class TelegramChatBotEstadosImpl implements ITelegramChatBotEstados {

    public void defaultAction(TelegramBot bot, Long id) {
        bot.execute(new SendMessage(id, "Ola, seja bem-vindo(a) ao JPLAT-BOT!"));
        bot.execute(new SendMessage(id, "Como posso te ajudar? Digite \"comandos\" " +
                "para saber como posso te ajudar..."));

        //System.out.println("Recebendo mensagem: " + update.message().text());

        // Envio de "Escrevendo" antes de enviar a resposta.
        var response = bot.execute(new SendChatAction(id, ChatAction.typing.name()));

        // Verificacao de acao de chat foi enviada com sucesso.
        System.out.println("Resposta de Chat Action Enviada? " + response.isOk());

    }

    public void executaMostrarComandos(TelegramBot bot, Long id) {
        var response = bot.execute(new SendMessage(id, "Aqui estão as opções de comando:\n" +
                "1- Roll a Dice! - Teste sua sorte jogando o dado!\n" +
                "2- Weather Report - Veja como está o clima agora em sua localização.\n" +
                "3- Pokedex - Consulte as Informações de um Pókemon!\n" +
                "4- O que assistir? - Receba uma recomendação de filme!\n" +
                "\n" +
                "Caso deseje voltar ao inicio, digite \"voltar\""));


    }

    public void executaRollDice(TelegramBot bot, Long id) {
        RollADice rad = new RollADice();
        bot.execute(new SendMessage(id, "Rolando o dado! Seu numero foi: \n" +
                "\n" +
                "Para voltar para o menu anterior, digite \"voltar\" "));
        var response = bot.execute(new SendMessage(id, rad.getRandomNumber()));
        System.out.println("Mensagem Enviada? " + response.isOk());
    }

    public void executaWeatherReport(TelegramBot bot, Long id) {
        bot.execute(new SendVideo(id, "https://i.pinimg.com/originals/1c/73/96/1c7396935ff6792cc6d0e7c226c3664f.gif"));
    }

    public void executaOqueAssistir(TelegramBot bot, Long id) {

    }

    public void executaPokedex(TelegramBot bot, Long id) {

    }

    public void executaTrataErro(TelegramBot bot, Long id) {
        var resp = bot.execute(new
                SendMessage(id, "Não entendi..."));
    }
}
