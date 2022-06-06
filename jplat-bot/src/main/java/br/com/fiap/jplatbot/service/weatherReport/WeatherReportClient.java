package br.com.fiap.jplatbot.service.weatherReport;

import br.com.fiap.jplatbot.service.weatherReport.POJOs.WeatherRoot;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "https://api.hgbrasil.com/weather?woeid=455827", url = "https://api.hgbrasil.com/weather?woeid=455827")
public interface WeatherReportClient {
    @GetMapping()
    public WeatherRoot getWeatherReport();
}
