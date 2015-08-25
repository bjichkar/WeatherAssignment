package brj.com.weatherassignment.commonProcess;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import brj.com.weatherassignment.R;
import brj.com.weatherassignment.commonProcess.modelJson.ListObjTempJson;
import brj.com.weatherassignment.commonProcess.modelJson.ResultJson;
import brj.com.weatherassignment.utilities.Utilities;

public class Date extends Activity {
    Context activityContext;
    ArrayList<String> arrayListDate;
    ListView listViewDate;
    String response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        response = getIntent().getStringExtra("Result");
        System.out.println("Date Output Result------------->" + response);
        initUi();
    }

    private void initUi() {
        activityContext=Date.this;
        arrayListDate=new ArrayList<String>();
        listViewDate= (ListView) findViewById(R.id.list_dates);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEE-dd-MMM-yyyy");
        df.format(c.getTime());
        c.add(Calendar.DAY_OF_MONTH, -1);
        int j=1;
        for(int i=0;i<14;i++)
        {
            c.add(Calendar.DAY_OF_MONTH, 1);
            String formattedDate = df.format(c.getTime());
            arrayListDate.add("Day " + j + " - " + formattedDate);
            j++;
        }
        listViewDate.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayListDate));
        listViewDate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Utilities.displayToastMsg(activityContext,arrayListDate.get(position).toString());

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                ResultJson model = gson.fromJson(response, ResultJson.class);
                //resultJson.add(response.toString());
                double resultPressure=model.getList().get(position).getPressure();
                int humidity=model.getList().get(position).getHumidity();
                float speed=model.getList().get(position).getSpeed();
                float rain=model.getList().get(position).getRain();
                int deg=model.getList().get(position).getDeg();
                int clouds=model.getList().get(position).getClouds();
                double minTemp=model.getList().get(position).getTemp().getMin();
                double maxTemp=model.getList().get(position).getTemp().getMax();
                double dayTemp=model.getList().get(position).getTemp().getDay();
                double eveTemp=model.getList().get(position).getTemp().getEve();
                double nightTemp=model.getList().get(position).getTemp().getNight();
                double mornTemp=model.getList().get(position).getTemp().getMorn();
                String weatherMain=model.getList().get(position).getWeather().get(0).getMain();
                String weatherDesc=model.getList().get(position).getWeather().get(0).getDescription();

                Intent newIntent = new Intent(Date.this, ForecastDetail.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                newIntent.putExtra("resultPressure", resultPressure);
                newIntent.putExtra("humidity", humidity);
                newIntent.putExtra("speed", speed);
                newIntent.putExtra("rain", rain);
                newIntent.putExtra("deg", deg);
                newIntent.putExtra("clouds", clouds);
                newIntent.putExtra("minTemp", minTemp);
                newIntent.putExtra("maxTemp", maxTemp);
                newIntent.putExtra("dayTemp", dayTemp);
                newIntent.putExtra("eveTemp", eveTemp);
                newIntent.putExtra("nightTemp", nightTemp);
                newIntent.putExtra("mornTemp", mornTemp);
                newIntent.putExtra("weatherMain", weatherMain);
                newIntent.putExtra("weatherDesc", weatherDesc);
                startActivity(newIntent);
            }
        });
    }


}
