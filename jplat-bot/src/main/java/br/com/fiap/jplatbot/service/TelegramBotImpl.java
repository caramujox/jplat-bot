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
                if(!RegexValidator.isValidText(update.message().text()))
                    return UpdatesListener.CONFIRMED_UPDATES_NONE;
                if (update.message().text().compareToIgnoreCase("comandos") != 0) {

                    bot.execute(new SendMessage(update.message().chat().id(), "Ola, seja bem-vindo(a) ao JPLAT-BOT!"));
                    bot.execute(new SendMessage(update.message().chat().id(), "Como posso te ajudar? Digite \"comandos\" " +
                            "para saber os meus serviços..."));

                    System.out.println(RegexValidator.isValidText(update.message().text()));

                    System.out.println("Recebendo mensagem: " + update.message().text());

                    // Envio de "Escrevendo" antes de enviar a resposta.
                    var response = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));

                    // Verificacao de acao de chat foi enviada com sucesso.
                    System.out.println("Resposta de Chat Action Enviada? " + response.isOk());

                }
                else {
                    var response = bot.execute(new SendMessage(update.message().chat().id(), "vc selecionou comandos"));
                    System.out.println("Mensagem Enviada? " + response.isOk());
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}


