package lt.kraujutis.vilius.android.utils;

import lt.kraujutis.vilius.android.screenlocker.ScreenLockerDeviceAdminReceiver;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

public class ScreenLock {
	static final int RESULT_ENABLE = 1;
	private static final String TAG = ScreenLock.class.getCanonicalName();

	public static void lockScreen(Context context) {
		Log.d(TAG, "lockScreen");
		forceLock(context);
	}

	private static void forceLock(Context context) {
		ComponentName componentName = new ComponentName(context, ScreenLockerDeviceAdminReceiver.class);

		if (ActivityManager.isUserAMonkey()) {
			// Don't trust monkeys to do the right thing!
			AlertDialog.Builder builder = new AlertDialog.Builder(context.getApplicationContext());
			builder.setMessage("You can't lock my screen because you are a monkey!");
			builder.setPositiveButton("I admit defeat", null);
			builder.show();
			return;
		}
		boolean active = ((DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE)).isAdminActive(componentName);
		if (active) {
			((DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE)).lockNow();
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(context.getApplicationContext());
			builder.setMessage("You can't lock my screen because you are not an active Admin! \n\nGo Settings and enable an admin.");
			builder.setPositiveButton("I admit defeat", null);
			builder.show();
			return;
		}
	}
}
