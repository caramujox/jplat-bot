package br.com.fiap.jplatbot.service.weatherReport.POJOs;

import lombok.ToString;

@ToString
public class Forecast {
    public String date;
    public String weekday;
    public int max;
    public int min;
    public String description;
    public String condition;
}
