package brj.com.weatherassignment.commonProcess.modelJson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ideologi on 8/24/15.
 */
public class MappingClass
{
    @SerializedName("cityName")
    public String cityName;

    @SerializedName("ResultJson")
    public String resultJson;

    public String getCityName() {
        return cityName;
    }

    public MappingClass(String cityName, String resultJson) {
        this.cityName = cityName;
        this.resultJson = resultJson;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getResultJson() {
        return resultJson;
    }

    public void setResultJson(String resultJson) {
        this.resultJson = resultJson;
    }
}
