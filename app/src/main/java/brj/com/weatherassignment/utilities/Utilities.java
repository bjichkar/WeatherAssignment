package brj.com.weatherassignment.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Switch;
import android.widget.Toast;

import brj.com.weatherassignment.R;


public class Utilities
{
	public static void hideSoftKeyboard(Activity activity) 
	{
		InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}
	public static boolean IsNullOrEmptyWhiteSpace(String param) 
	{
		return param == null || param.trim().length() == 0;
	}

	/*
	 * Regex string used to catch every valid email address
	 */
	private static Pattern usrNamePtrn = Pattern.compile("^[A-Za-z0-9_]{6,15}$");
	private static Pattern fullNamePtrn = Pattern.compile("^[A-Za-z]+\\s+[A-Za-z]+\\s+[A-Za-z]{1,}$");
	private static final Pattern PASSWORD_PATTERN =Pattern.compile("((?=.*\\d)(?=.*[A-Za-z]).{6,20})");
	public static final Pattern REGEX_EMAIL_VALIDATION_PATTERN = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
	private static Pattern onlyAlphaPtrn = Pattern.compile("^[A-Za-z]{1,15}$");
	private static Pattern onlyNumberPtrn = Pattern.compile("^[0-9]{0,18}$");
    private static Pattern noSpace = Pattern.compile("^\\s*$");

    public static boolean noSpace(String text){

        Matcher mtch = noSpace.matcher(text);
        if(mtch.matches()){
            return false;
        }
        return true;
    }
	public static boolean noSpaceInWord(String text)
	{
		if(text.trim().equalsIgnoreCase(""))
		{
			return false;
		}
		return true;

	}
	public static boolean isEmpty(String etText) {
		return etText.trim().length() != 0;
	}
	public static boolean validatePassword(String pass){

		Matcher mtch = PASSWORD_PATTERN.matcher(pass);
		if(mtch.matches()){
			return true;
		}
		return false;
	}
	public static boolean validateFullName(String fullName){

		Matcher mtch = fullNamePtrn.matcher(fullName);
		if(mtch.matches()){
			return true;
		}
		return false;
	}
	public static boolean validateUserName(String userName){

		Matcher mtch = usrNamePtrn.matcher(userName);
		if(mtch.matches()){
			return true;
		}
		return false;
	}

	public static boolean onlyAlphabets(String strName){

		Matcher mtch = onlyAlphaPtrn.matcher(strName);
		if(mtch.matches()){
			return true;
		}
		return false;
	}
	public static boolean onlyNumbers(String strNumner){

		Matcher mtch = onlyNumberPtrn.matcher(strNumner);
		if(mtch.matches()){
			return true;
		}
		return false;
	}
	public static boolean validateEmail(String oldEmail) 
	{
		return REGEX_EMAIL_VALIDATION_PATTERN.matcher(oldEmail).matches();
	}

	public static boolean hideKeyboard(Activity context)
	{
		InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE); 
		return inputManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}

	public static void showKeyboard(Activity context)
	{
		InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE); 
		inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	public static Typeface getMuseuo500Font(Activity activity) 
	{
		return Typeface.createFromAsset(activity.getAssets(), "fonts/MuseoSans_500-webfont.ttf");	
	}


	public static void displayToastMsg(Context context, String message)
	{
		Toast.makeText(context, message,Toast.LENGTH_SHORT).show();
	}
	
	public static String getDateFormatted(String date)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = dateFormat.parse(date); 
			Calendar cal = Calendar.getInstance();
			cal.setTime(d1);
			
			int datevalue = cal.get(Calendar.DAY_OF_MONTH);
			
			int yearvalue = cal.get(Calendar.YEAR);
			
			int mon = cal.get(Calendar.MONTH);
			
			return ""+datevalue+"/"+(mon+1)+"/"+yearvalue; 
		} catch (ParseException e) {
			e.printStackTrace();
		} 

		return date; 
	}

	public static void showError(Context context,View v)
	{

		//Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
		//v.setBackgroundResource(R.drawable.edt_error_border);
		//v.startAnimation(shake);

	}

    public static void alertBox(final Context context,final Intent intent,String title,String msg)
    {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(msg);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                ((Activity) context).finish();
                ((Activity) context).startActivity(intent);
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();


    }
	public static void alertBoxForSetting(final Context context)
	{

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle("Weather Alert");
		alertDialogBuilder.setMessage("Please connect to Internet first and then click on Get Forecast button. For enabling internet either go to setting or Click on following setting button.");
		alertDialogBuilder.setCancelable(false);
		alertDialogBuilder.setPositiveButton("Setting",new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				Intent dialogIntent = new Intent(android.provider.Settings.ACTION_SETTINGS);
				dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(dialogIntent);
			}
		});
		alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.cancel();
			}
		});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();
		// show it
		alertDialog.show();
	}

	public static String replace(String str) {
		return str.replaceAll(" ", "%20");
	}

	public static float calculateDistance(double lat1,double lon1,double lat2,double lon2,String distType)
	{
		Location loc1 = new Location("start");
		loc1.setLatitude(lat1);
		loc1.setLongitude(lon1);

		Location loc2 = new Location("end");
		loc2.setLatitude(lat2);
		loc2.setLongitude(lon2);

		float requiredDistance=0.0f;
		float distanceInMeters = loc1.distanceTo(loc2);

		switch(distType)
		{
			case "km":
				requiredDistance=distanceInMeters/1000;
				break;

			default:
				System.out.println("-***********- Conversion Error" + distanceInMeters);

		}

		return requiredDistance;
	}

	public static boolean deleteDirectory(File path) {
		if( path.exists() ) {
			File[] files = path.listFiles();
			if (files == null) {
				return true;
			}
			for(int i=0; i<files.length; i++) {
				if(files[i].isDirectory()) {
					deleteDirectory(files[i]);
				}
				else {
					files[i].delete();
				}
			}
		}
		return( path.delete() );
	}
	//String extr = Environment.getExternalStorageDirectory().toString();
	//File imagesFolder = new File(extr + "/NextTeeFolder");

	public static void createDirIfNotExists() {

		String extr = Environment.getExternalStorageDirectory().toString();
		File imagesFolder = new File(extr + "/NextTeeFolder");
		if (!imagesFolder.exists())
		{
			imagesFolder.mkdir();

		}
	}

}