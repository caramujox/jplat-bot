package br.com.fiap.jplatbot.service.weatherReport.POJOs;

import lombok.ToString;

import java.util.ArrayList;

@ToString
public class Results {
    public int temp;
    public String date;
    public String time;
    public String condition_code;
    public String description;
    public String currently;
    public String cid;
    public String city;
    public String img_id;
    public int humidity;
    public String wind_speedy;
    public String sunrise;
    public String sunset;
    public String condition_slug;
    public String city_name;
    public ArrayList<Forecast> forecast;
}
