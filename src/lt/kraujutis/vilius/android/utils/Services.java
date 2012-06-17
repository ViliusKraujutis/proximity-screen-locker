package lt.kraujutis.vilius.android.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

public class Services {
	public static boolean isMyServiceRunning(Context context, String serviceClassName) {
		ActivityManager manager = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
		for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			if (serviceClassName.equalsIgnoreCase(service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}
}
