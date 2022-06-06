package br.com.fiap.jplatbot.service.weatherReport.POJOs;

import lombok.ToString;

@ToString
public class WeatherRoot {
    public String by;
    public boolean valid_key;
    public Results results;
    public double execution_time;
    public boolean from_cache;
}
