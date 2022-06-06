package br.com.fiap.jplatbot;

import br.com.fiap.jplatbot.service.TelegramChatBotEstadosImpl;
import br.com.fiap.jplatbot.util.RegexValidator;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static br.com.fiap.jplatbot.ControladorEstados.Estado.*;

@Service
public class ControladorEstados {
    @Autowired
    private TelegramChatBotEstadosImpl telegramChatBotEstados;

    public enum Estado {
        INICIAL,
        COMANDOS,
        DICE,
        WEATHER,
        ERRO
    }

    HashMap<Long, Estado> estados = new HashMap<>();

    public void comandaEstado(Long id, String mensagem, TelegramBot bot) {
        Estado novoEstado;
        Estado estadoAtual = estados.get(id);
        if (estadoAtual == null)
            estadoAtual = INICIAL;
        switch (estadoAtual) {
            case COMANDOS:
                novoEstado = controlaContextoComandos(estadoAtual, mensagem, id, bot);
                estados.put(id, novoEstado);
                break;
            case DICE:
                novoEstado =controlaContextoDice(estadoAtual, mensagem,id, bot);
                estados.put(id, novoEstado);
                break;
            case WEATHER:
                novoEstado= controlaContextoDice(estadoAtual, mensagem, id, bot);
                estados.put(id, novoEstado);
                break;
            case ERRO:
                novoEstado = controlaContextoErro(estadoAtual, mensagem, id, bot);
                estados.put(id, novoEstado);
                break;
            default:
                novoEstado = controlaContextoInicial(estadoAtual, estados.size(), mensagem, id, bot);
                estados.put(id, novoEstado);
                break;
        }
    }

    public Estado controlaContextoInicial(Estado estado, int estadosSize, String msg, Long id, TelegramBot bot) {
        if (!RegexValidator.checkValidText(msg).equals("comandos")) {
            telegramChatBotEstados.defaultAction(bot, id);
            if (estadosSize >= 1) {
                telegramChatBotEstados.executaTrataErro(bot, id);
                return estado;
            }
            else
                return estado;
        }
        telegramChatBotEstados.executaMostrarComandos(bot, id);
        return COMANDOS;
    }


    public Estado controlaContextoErro(Estado estado, String msg, Long id, TelegramBot bot) {
        telegramChatBotEstados.executaTrataErro(bot, id);
        return estado;
    }

    public Estado controlaContextoComandos(Estado estado, String msg, Long id, TelegramBot bot) {
        switch (msg) {
            case "1":
            case "roll a dice":
                telegramChatBotEstados.executaRollDice(bot,id);
                return DICE;
            case "2":
            case "weather report":
                telegramChatBotEstados.executaWeatherReport(bot, id);
                return WEATHER;
            case "voltar":
                telegramChatBotEstados.defaultAction(bot, id);
                return INICIAL;
            default:
                telegramChatBotEstados.executaMostrarComandos(bot, id);
                return COMANDOS;
        }
    }

    public Estado controlaContextoDice(Estado estado, String msg, Long id, TelegramBot bot) {
        switch (msg) {
            case "voltar":
                telegramChatBotEstados.executaMostrarComandos(bot, id);
                return COMANDOS;
            default:
                telegramChatBotEstados.executaRollDice(bot, id);
                return estado;
        }
    }
}



