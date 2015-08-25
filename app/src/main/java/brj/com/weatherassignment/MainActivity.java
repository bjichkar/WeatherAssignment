package brj.com.weatherassignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import brj.com.weatherassignment.taskFirst.ForcastCities;
import brj.com.weatherassignment.taskSecond.ForcastOnLocation;

public class MainActivity extends Activity implements View.OnClickListener {
    Button btnTaskCities,btnTaskLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
        initListener();

    }//onCreate End

    private void initUi() {
        btnTaskCities= (Button) findViewById(R.id.btn_weather_of_cities);
        btnTaskLocation= (Button) findViewById(R.id.btn_weather_of_current_location);
    }//initUi End

    private void initListener() {
        btnTaskCities.setOnClickListener(this);
        btnTaskLocation.setOnClickListener(this);
    }//initListener Ens


    @Override
    public void onClick(View v) {
        Intent newIntent;
        switch (v.getId())
        {
            case R.id.btn_weather_of_cities:
                    newIntent=new Intent(getApplicationContext(), ForcastCities.class);
                    newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(newIntent);
                break;
            case R.id.btn_weather_of_current_location:
                newIntent=new Intent(getApplicationContext(), ForcastOnLocation.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(newIntent);
                break;
        }

    }//onClick End
}// MainActivity End
