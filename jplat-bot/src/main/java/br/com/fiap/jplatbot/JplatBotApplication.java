package br.com.fiap.jplatbot;

import br.com.fiap.jplatbot.service.TelegramBotImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class JplatBotApplication {
	@Autowired
	private TelegramBotImpl telegramBot;

	public static void main(String[] args) {
		SpringApplication.run(JplatBotApplication.class, args);
	}

	@PostConstruct
	public void init(){
		telegramBot.ChatSim();
	}

}
