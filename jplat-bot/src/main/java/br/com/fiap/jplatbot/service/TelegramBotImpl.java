package br.com.fiap.jplatbot.service;

import br.com.fiap.jplatbot.service.interfaces.ITelegramChatBotEstados;
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
import kotlin.text.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TelegramBotImpl {

    @Value("${bot.access.token}")
    private String accessToken;

    @Autowired
    TelegramChatBotEstadosImpl telegramChatBotEstados;

    public void ChatSim() {
        TelegramBot bot = new TelegramBot(accessToken);

        GetUpdatesResponse updatesResponse;

        SendResponse sendResponse;

        final BaseResponse baseResponse;

        int m = 0;
        bot.setUpdatesListener(updates -> {
            for (Update update : updates) {

                switch (RegexValidator.checkValidText(update.message().text())) {
                    case "comandos":
                        telegramChatBotEstados.executaMostrarComandos(bot, update);
                        break;
                    case "1":
                    case "roll a dice":
                        telegramChatBotEstados.executaRollDice(bot, update);
                        break;
                    case "2":
                    case "weather report":
                        telegramChatBotEstados.executaWeatherReport(bot, update);
                        break;
                    case "3":
                    case "pokedex":
                        telegramChatBotEstados.executaPokedex(bot, update);
                        break;
                    case "4":
                    case "o que assistir":
                        telegramChatBotEstados.executaOqueAssistir(bot, update);
                        break;
                    case "erro":
                        var resp = bot.execute(new
                                SendMessage(update.message().chat().id(), "Não entendi..."));

                        System.out.println("Mensagem Enviada? " + resp.isOk());
                    default:
                        telegramChatBotEstados.defaultAction(bot, update);
                }
//                if (update.message().text().compareToIgnoreCase("comandos") != 0) {
//
//                    bot.execute(new SendMessage(update.message().chat().id(), "Ola, seja bem-vindo(a) ao JPLAT-BOT!"));
//                    bot.execute(new SendMessage(update.message().chat().id(), "Como posso te ajudar? Digite \"comandos\" " +
//                            "para saber como posso te ajudar..."));
//
//
//                    System.out.println("Recebendo mensagem: " + update.message().text());
//
//                    // Envio de "Escrevendo" antes de enviar a resposta.
//                    var response = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
//
//                    // Verificacao de acao de chat foi enviada com sucesso.
//                    System.out.println("Resposta de Chat Action Enviada? " + response.isOk());
//
//                } else {
//
//                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}


