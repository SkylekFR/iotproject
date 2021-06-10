package com.example.iotproject.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class SensorData

{
    private Float temperature;
    private Float humidity;

    public void setHumidity(Float humidity) {
        this.humidity = humidity;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getHumidity() {
        return humidity;
    }

    public Float getTemperature() {
        return temperature;
    }

    public SensorData(Float humidity, Float temperature)
    {
        this.setTemperature(temperature);
        this.setHumidity(humidity);
    }

    public SensorData() {
    }
}
