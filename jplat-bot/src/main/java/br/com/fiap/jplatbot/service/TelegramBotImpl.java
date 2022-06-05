package br.com.fiap.jplatbot.service;

import br.com.fiap.jplatbot.util.RegexValidator;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TelegramBotImpl {

    @Value("${bot.access.token}")
    private String accessToken;

    public void ChatSim() {
        TelegramBot bot = new TelegramBot(accessToken);

        GetUpdatesResponse updatesResponse;

        SendResponse sendResponse;

        final BaseResponse baseResponse;

        int m = 0;
        bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                if (update.message().text().compareToIgnoreCase("comandos") != 0) {

                    bot.execute(new SendMessage(update.message().chat().id(), "Ola, seja bem-vindo(a) ao JPLAT-BOT!"));
                    bot.execute(new SendMessage(update.message().chat().id(), "Como posso te ajudar? Digite \"comandos\" " +
                            "para saber como posso te ajudar..."));

                    System.out.println(RegexValidator.isValidText(update.message().text()));

                    System.out.println("Recebendo mensagem: " + update.message().text());

                    // Envio de "Escrevendo" antes de enviar a resposta.
                    var response = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));

                    // Verificacao de acao de chat foi enviada com sucesso.
                    System.out.println("Resposta de Chat Action Enviada? " + response.isOk());

                }
                else {
                    var response = bot.execute(new SendMessage(update.message().chat().id(), "Aqui estão as opções de comando:\n" +
                            "1- Roll a Dice! - Teste sua sorte jogando o dado!\n" +
                            "2- Wheater Report - Veja como está o clima agora em sua localização.\n" +
                            "3- Pokedex - Consulte as Informações de um Pókemon!\n" +
                            "4- O que assistir? - Receba uma recomendação de filme!\n" +
                            "\n" +
                            "Caso deseje voltar ao inicio, digite \"voltar\""));
                    
                    System.out.println("Mensagem Enviada? " + response.isOk());
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}


