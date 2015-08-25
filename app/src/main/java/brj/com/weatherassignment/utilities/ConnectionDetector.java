package brj.com.weatherassignment.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Html;

public class ConnectionDetector {
	/**
	 * Function to check either mobile or wifi network is available or not.
	 * 
	 * @param context
	 * @return true if either mobile or wifi network is available else it
	 *         returns false.
	 */
	//private static final String TAG = ConnectionDetector.class.getSimpleName();
	public static boolean networkStatus(Context context) {

		return (ConnectionDetector.isWifiAvailable(context) || ConnectionDetector
				.isMobileNetworkAvailable(context));
	}

	public static boolean isMobileNetworkAvailable(Context ctx) 
	{
		ConnectivityManager connecManager = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo myNetworkInfo = connecManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

		return (myNetworkInfo != null && myNetworkInfo.isConnected());

	}

	public static boolean isWifiAvailable(Context ctx) {
		ConnectivityManager myConnManager = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo myNetworkInfo = myConnManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		return (myNetworkInfo != null && myNetworkInfo.isConnected());

	}

    public static boolean isBluetoothAvailable(Context ctx)
    {
        boolean status=false;
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null)
        {
            // Device does not support Bluetooth
        }
        else
        {
            if (!mBluetoothAdapter.isEnabled())
            {
                // Bluetooth is not enable :)
            }
            else
            {
                status=true;
            }
        }
        return status;
    }

	public static boolean isGpsEnabled(Context ctx) {
		LocationManager locationManager = (LocationManager) ctx
				.getSystemService(Context.LOCATION_SERVICE);
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
			return true;
		else
			return false;
	}

	public static void displayNoNetworkDialog(final Activity activity) {


		Builder builder = new Builder(activity);
		builder.setMessage("Sorry, Network Connection Failure ")
		.setCancelable(false)
		.setPositiveButton("Retry",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {

				Intent intent = activity.getIntent();
				activity.finish();


				activity.startActivity(intent);
				dialog.cancel();
			}
		})
		.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
				activity.finish();
			}
		});
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}
	//Alert dailogue for slow connection.
	public static void displaySlowNetworkDialog(final Activity activity) {


		Builder builder = new Builder(activity);
		builder.setMessage(Html.fromHtml("Sorry,Slow Network Connection "))
		.setCancelable(false)
		.setPositiveButton("Retry",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {

				Intent intent = activity.getIntent();
				activity.finish();


				activity.startActivity(intent);
				dialog.cancel();
			}
		})
		.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
				activity.finish();
			}
		});
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}
	public static void displayDialogueNoInformation( Context context)
	{
		//Display Message. 
		Builder builder = new Builder(context);
		builder.setMessage("  No information....");
		builder.setCancelable(true);
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	public static void displayDialogueCoomingSoon( Context context)
	{
		//Display Message. 
		Builder builder = new Builder(context);
		builder.setMessage("  Coming Soon....");
		builder.setCancelable(true);
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	public static void displaySuccess( Context context)
	{
		//Display Message. 
		Builder builder = new Builder(context);
		builder.setMessage("  Successfuly Save....");
		builder.setCancelable(true);
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	public static void displayAlready( Context context)
	{
		//Display Message. 
		Builder builder = new Builder(context);
		builder.setMessage(" Already Booking......");
		builder.setCancelable(true);
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	public static void displayAlreadyVoted( Context context, String str_Notice)
	{
		//Display Message. 
		Builder builder = new Builder(context);
		builder.setMessage(str_Notice );
		builder.setCancelable(true);
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public boolean isConnectingToInternet() {
		// TODO Auto-generated method stub
		return false;
	}

}
