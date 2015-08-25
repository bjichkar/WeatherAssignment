package brj.com.weatherassignment.taskSecond;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import brj.com.weatherassignment.R;
import brj.com.weatherassignment.commonProcess.Date;
import brj.com.weatherassignment.commonProcess.modelJson.ResultJson;
import brj.com.weatherassignment.taskSecond.GPSTracker.GPSTracker;
import brj.com.weatherassignment.utilities.ConnectionDetector;
import brj.com.weatherassignment.utilities.Utilities;
import brj.com.weatherassignment.webservice.WebserviceData;

public class ForcastOnLocation extends Activity {
    TextView txtLat,txtLongi,txtCity;
    ProgressDialog pDialog;
    static String TAG=ForcastOnLocation.class.getSimpleName();
    RequestQueue queue;
    double latitude,longitude;
    String city,country;
    Button btnLocation,btnForecast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forcast_on_location);

        initUi();
    }//onCreate End

    private void initUi() {
        txtLat= (TextView) findViewById(R.id.txt_cur_lat_value);
        txtLongi= (TextView) findViewById(R.id.txt_cur_long_value);
        txtCity= (TextView) findViewById(R.id.txt_cur_city_value);
        pDialog=new ProgressDialog(this);
        pDialog.setMessage("Please wait while we are getting your requested Weather data..");
        pDialog.setCancelable(false);
        queue = Volley.newRequestQueue(this);
        btnLocation= (Button) findViewById(R.id.btn_getLocation);
        btnForecast= (Button) findViewById(R.id.btn_getForecast);
    }//initUi end

    public void getLoaction(View v)
    {
        GPSTracker gps=new GPSTracker(this);
        // check if GPS enabled
        if(gps.canGetLocation())
        {
            if(ConnectionDetector.networkStatus(this)) {
                latitude = gps.getLatitude();
                longitude = gps.getLongitude();
                city = gps.getCity(this);
                country = gps.getCountry(this);
                txtLat.setText("" + latitude);
                txtLongi.setText("" + longitude);
                txtCity.setText(city);
             }
            else {Utilities.alertBoxForSetting(this);}
        }else
        {
            gps.showSettingsAlert();
        }
    }//getLoaction End
    public void weatherWebservice(View v)
    {

        if(ConnectionDetector.networkStatus(this))
        {
            showDialog();
            String cityName=city+","+country;
            String uri= WebserviceData.WEATHER_API+"&q="+cityName;
            String url= Utilities.replace(uri);
            System.out.println("url temp service------------->" + url);
            StringRequest request=new StringRequest(Request.Method.GET, url,requestSuccessListener(),requestErrorListener());
            request.setTag(TAG);
            queue.add(request);
        }
        else {
            Utilities.alertBoxForSetting(this);
        }



    }//cityWebservice End
    public Response.Listener<String> requestSuccessListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Responce------------->" + response.toString());
                //Utilities.displayToastMsg(getActivity(), "Responce" + response.toString());
                //InputStream content = response.getEntity();
                hideDialog();
                Intent newIntent = new Intent(ForcastOnLocation.this, Date.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                newIntent.putExtra("Result", response);
                startActivity(newIntent);
            }
        };
    }//requestSuccessListener end
    public Response.ErrorListener requestErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
                System.out.println("Error------------->" + error.getMessage());
                Utilities.displayToastMsg(ForcastOnLocation.this, getResources().getString(R.string.net_connection_failed));
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
}//ForcastOnLocation End
