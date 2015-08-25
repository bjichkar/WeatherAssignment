package brj.com.weatherassignment.commonProcess.modelJson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ideologi on 8/24/15.
 */
public class CityJson {

        @SerializedName("name")
        public String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

}
