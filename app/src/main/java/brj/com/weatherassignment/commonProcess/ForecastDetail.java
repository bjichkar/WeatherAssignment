package brj.com.weatherassignment.commonProcess;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import brj.com.weatherassignment.R;

public class ForecastDetail extends Activity {
    TextView pressure,humidity,speed,deg,clouds,day,min,max,eve,morn,desc,mainDesc,night,rain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_detail);



        initUi();
    }

    private void initUi() {
        pressure= (TextView) findViewById(R.id.txt_pressure);
        humidity= (TextView) findViewById(R.id.txt_humidity);
        speed= (TextView) findViewById(R.id.txt_speed);
        deg= (TextView) findViewById(R.id.txt_deg);
        clouds= (TextView) findViewById(R.id.txt_clouds);

        rain= (TextView) findViewById(R.id.txt_rain);
        day= (TextView) findViewById(R.id.txt_day);
        min= (TextView) findViewById(R.id.txt_min);
        max= (TextView) findViewById(R.id.txt_max);
        eve= (TextView) findViewById(R.id.txt_eve);
        morn= (TextView) findViewById(R.id.txt_morn);
        night= (TextView) findViewById(R.id.txt_nightTemp);

        mainDesc= (TextView) findViewById(R.id.txt_weather);
        desc= (TextView) findViewById(R.id.txt_weather_desc);

        pressure.setText(""+getIntent().getDoubleExtra("resultPressure",0.00)+" hpa");
        humidity.setText(""+getIntent().getIntExtra("humidity", 0)+" %");
        speed.setText(""+getIntent().getFloatExtra("speed", 0)+" m/s ");
        deg.setText(""+getIntent().getIntExtra("deg", 00));
        clouds.setText(""+getIntent().getIntExtra("clouds", 00)+" %");
        rain.setText(""+getIntent().getFloatExtra("rain", 0.0f));
        day.setText(""+getIntent().getDoubleExtra("dayTemp", 0.00)+" \u2103");
        min.setText(""+getIntent().getDoubleExtra("minTemp", 0.00)+" \u2103");
        max.setText(""+getIntent().getDoubleExtra("maxTemp", 0.00)+" \u2103");
        eve.setText(""+getIntent().getDoubleExtra("eveTemp", 0.00)+" \u2103");
        morn.setText(""+getIntent().getDoubleExtra("mornTemp", 0.00)+" \u2103");
        night.setText(""+getIntent().getDoubleExtra("nightTemp", 0.00)+" \u2103");
        mainDesc.setText(""+getIntent().getStringExtra("weatherMain"));
        desc.setText(""+getIntent().getStringExtra("weatherDesc"));
    }//initUi End
}
