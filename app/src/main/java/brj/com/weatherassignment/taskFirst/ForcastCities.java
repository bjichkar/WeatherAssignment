package brj.com.weatherassignment.taskFirst;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import brj.com.weatherassignment.R;
import brj.com.weatherassignment.commonProcess.Date;
import brj.com.weatherassignment.commonProcess.modelJson.MappingClass;
import brj.com.weatherassignment.commonProcess.modelJson.ResultJson;
import brj.com.weatherassignment.utilities.ConnectionDetector;
import brj.com.weatherassignment.utilities.Utilities;
import brj.com.weatherassignment.webservice.WebserviceData;

public class ForcastCities extends Activity {
    EditText cityName;
    Button getForcast;
    ListView cityList;
    List stuff = new ArrayList<String>();
    static String TAG=ForcastCities.class.getSimpleName();
    RequestQueue queue;
    ProgressDialog pDialog;
    ArrayList<MappingClass> mappingList=new ArrayList<MappingClass>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forcast_cities);
        initUi();
    }//onCreate End

    private void initUi() {
        queue = Volley.newRequestQueue(this);
        pDialog=new ProgressDialog(ForcastCities.this);
        pDialog.setMessage("Please wait while we are getting your requested data..");
        pDialog.setCancelable(false);
        cityName= (EditText) findViewById(R.id.forcast_edt_city_name);
        getForcast= (Button) findViewById(R.id.btn_weather_of_cities);
        cityList= (ListView) findViewById(R.id.forcast_list_city);
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), stuff.get(position).toString() + "->" + position, Toast.LENGTH_SHORT).show();

                for (int i = 0; i < mappingList.size(); i++) {
                    if (stuff.get(position).toString().equalsIgnoreCase(mappingList.get(i).getCityName()))
                    {
                        System.out.println("Output Result------------->" + mappingList.get(i).getResultJson());
                        Intent newIntent = new Intent(ForcastCities.this, Date.class);
                        newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        newIntent.putExtra("Result", mappingList.get(i).getResultJson());
                        startActivity(newIntent);
                    }
                    else {
                        //Utilities.displayToastMsg(ForcastCities.this,"Unable to recognise city name. Please enter proper city name.");
                    }
                }

            }
        });
    }

    public void getForecst(View v)
    {
        stuff.clear();
        mappingList.clear();

        String value = cityName.getText().toString();
        String line = value;
        String delim = ",";
        stuff.addAll(Arrays.asList(line.split(delim)));
        for(int i=0;i<stuff.size();i++)
        {
            if(stuff.get(i).toString().trim().equalsIgnoreCase(""))
            {
                stuff.remove(i);
            }
        }
        stuff.removeAll(Arrays.asList(null, ""));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stuff );
        cityList.setAdapter(arrayAdapter);

        if(ConnectionDetector.networkStatus(this))
        {
            getForcastofCities();
        }
        else {
            Utilities.alertBoxForSetting(this);
        }

    }

    public void getForcastofCities()
    {
        for(int i=0;i<stuff.size();i++)
        {
            weatherData(stuff.get(i).toString());
        }
    }

    private void weatherData(String cityName) {
        showDialog();
        //http://api.openweathermap.org/data/2.5/forecast/city?q=London&APPID=cfc0e23db02f58abeb804b84e26355fa
        String uri= WebserviceData.WEATHER_API+"&q="+cityName;
        String url=Utilities.replace(uri);
        System.out.println("url temp service------------->" + url);
        StringRequest request=new StringRequest(Request.Method.GET, url,requestSuccessListener(),requestErrorListener());
        request.setTag(TAG);
        queue.add(request);
    }//weatherData End
    public Response.Listener<String> requestSuccessListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Responce------------->" + response.toString());
                //Utilities.displayToastMsg(activityContext,"Responce"+response.toString());
                hideDialog();

                parseOutputtedDate(response);
            }
        };
    }//requestSuccessListener end
    public Response.ErrorListener requestErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
                //System.out.println("Error------------->" + error.getMessage());
                Utilities.displayToastMsg(ForcastCities.this, getResources().getString(R.string.net_connection_failed));
            }
        };
    }//requestErrorListener End
    private void showDialog()
    {
        if(!pDialog.isShowing())
        {
            pDialog.show();
        }
    }//showDialog end
    private void hideDialog()
    {
        if(pDialog.isShowing()) {
            pDialog.dismiss();

        }

    }//hideDialog End
    private void parseOutputtedDate(String response)
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        ResultJson model = gson.fromJson(response, ResultJson.class);
        //resultJson.add(response.toString());

        String resultTagName=model.getCod();
        //Utilities.displayToastMsg(getActivity(),resultTagName);
        if(resultTagName.equalsIgnoreCase("200"))
        {
            int size= model.getList().size();
            String cityName=model.getCity().getName();
            for(int i=0;i<stuff.size();i++)
            {
                if(cityName.equalsIgnoreCase(stuff.get(i).toString()))
                {
                    mappingList.add(new MappingClass(cityName,response));
                }

            }
        }
        else
        {
            Utilities.displayToastMsg(this,"No record found.");
        }


    }//parseOutputtedDate End


}//ForcastCities End
