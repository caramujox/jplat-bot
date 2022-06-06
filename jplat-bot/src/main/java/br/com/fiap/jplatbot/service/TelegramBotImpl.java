package br.com.fiap.jplatbot.service;

import br.com.fiap.jplatbot.ControladorEstados;
import br.com.fiap.jplatbot.service.interfaces.ITelegramChatBotEstados;
import br.com.fiap.jplatbot.util.RegexValidator;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;
import kotlin.text.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelegramBotImpl {

    @Value("${bot.access.token}")
    private String accessToken;

    @Autowired
    TelegramChatBotEstadosImpl telegramChatBotEstados;

    @Autowired
    ControladorEstados controladorEstados;

    public void ChatSim() {
        TelegramBot bot = new TelegramBot(accessToken);

        GetUpdatesResponse updatesResponse;

        SendResponse sendResponse;

        final BaseResponse baseResponse;

        bot.setUpdatesListener(updates -> {
            for (Update update: updates){
                controladorEstados.comandaEstado(update.message().chat().id(),
                        RegexValidator.checkValidText(update.message().text()),
                        bot);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

//        bot.setUpdatesListener(updates -> {
//            int novoOffset = m;
//            for (Update update : updates) {
//                switch (RegexValidator.checkValidText(update.message().text())) {
//                    case "comandos":
//                        System.out.println(update.message().chat().id());
//                        telegramChatBotEstados.executaMostrarComandos(bot, update);
//                        novoOffset = update.updateId();
//                        break;
//                    case "1":
//                    case "roll a dice":
//                        telegramChatBotEstados.executaRollDice(bot, update);
//                        novoOffset = update.updateId();
//                        break;
//                    case "2":
//                    case "weather report":
//                        telegramChatBotEstados.executaWeatherReport(bot, update);
//                        novoOffset = update.updateId() +1 ;
//                        break;
//                    case "3":
//                    case "pokedex":
//                        telegramChatBotEstados.executaPokedex(bot, update);
//                        novoOffset = update.updateId();
//                        break;
//                    case "4":
//                    case "o que assistir":
//                        telegramChatBotEstados.executaOqueAssistir(bot, update);
//                        novoOffset = update.updateId();
//                        break;
//                    case "erro":
//                        var resp = bot.execute(new
//                                SendMessage(update.message().chat().id(), "Não entendi..."));
//                        novoOffset = update.updateId();
//
//                        System.out.println("Mensagem Enviada? " + resp.isOk());
//                    default:
//                        telegramChatBotEstados.defaultAction(bot, update);
//                        novoOffset = update.updateId();
//                }
//            }
//            return novoOffset;
//        });
    }
}


