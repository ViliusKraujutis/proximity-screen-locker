package lt.kraujutis.vilius.android.utils;

import android.content.Context;
import android.os.Vibrator;


/**
 * 
 * @author viliuskraujutis
 * 
 *         need to add these permisions
 * 
 *         <uses-permission android:name="android.permission.VIBRATE"/>
 */
public class Vibration {
	public static void vibrate(Context context) {

		// Get instance of Vibrator from current Context
		Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

		// Vibrate for 300 milliseconds
		v.vibrate(30);
	} 
}
 