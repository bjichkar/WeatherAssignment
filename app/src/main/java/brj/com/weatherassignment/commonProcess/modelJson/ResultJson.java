package brj.com.weatherassignment.commonProcess.modelJson;

import com.google.gson.annotations.SerializedName;
import brj.com.weatherassignment.commonProcess.modelJson.CityJson;
import java.util.ArrayList;

/**
 * Created by Ideologi on 8/13/15.
 */
public class ResultJson
{
    @SerializedName("city")
    public CityJson city;

    public CityJson getCity() {
        return city;
    }

    public void setCity(CityJson city) {
        this.city = city;
    }

    @SerializedName("cod")
    private String cod;

    @SerializedName("list")
    private ArrayList<ListJson> list;


    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public ArrayList<ListJson> getList() {
        return list;
    }

    public void setList(ArrayList<ListJson> list) {
        this.list = list;
    }



}
