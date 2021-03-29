package com.example.iotproject;

public class ClassModel

{
    private  Float temperature;
    private  Float humidite;

    public void setHumidite(Float humidite) {
        this.humidite = humidite;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getHumidite() {
        return humidite;
    }

    public Float getTemperature() {
        return temperature;
    }

    public ClassModel (Float humidite, Float temperature)
    {
        this.setTemperature(temperature);
        this.setHumidite(humidite);
    }
}
