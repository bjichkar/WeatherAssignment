package brj.com.weatherassignment.commonProcess.modelJson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Ideologi on 8/13/15.
 */
public class ListJson
{

    @SerializedName("pressure")
    private double pressure;

    @SerializedName("humidity")
    private int humidity;

    @SerializedName("speed")
    private float speed;

    @SerializedName("rain")
    private float rain;

    @SerializedName("deg")
    private int deg;

    @SerializedName("clouds")
    private int clouds;

    @SerializedName("temp")
    private ListObjTempJson temp;

    @SerializedName("weather")
    private ArrayList<WeatherJson> weather;

    public ArrayList<WeatherJson> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<WeatherJson> weather) {
        this.weather = weather;
    }

    public ListObjTempJson getTemp() {
        return temp;
    }

    public void setTemp(ListObjTempJson temp) {
        this.temp = temp;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getRain() {
        return rain;
    }

    public void setRain(float rain) {
        this.rain = rain;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
}


