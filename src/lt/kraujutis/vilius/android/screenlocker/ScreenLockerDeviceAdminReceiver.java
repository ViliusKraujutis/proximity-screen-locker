package lt.kraujutis.vilius.android.screenlocker;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class ScreenLockerDeviceAdminReceiver extends DeviceAdminReceiver {

	static SharedPreferences getSamplePreferences(Context context) {
		return context.getSharedPreferences(DeviceAdminReceiver.class.getName(), 0);
	}

	@Override
	public void onEnabled(Context context, Intent intent) {
		showToast(context, "App 'Proximity Screen Locker' is enabled as Device Admin.");
	}

	@Override
	public CharSequence onDisableRequested(Context context, Intent intent) {
		return "Admin rights are beeing requested to be disabled for the app called: '" + context.getString(R.string.app_name) + "'.";
	}

	@Override
	public void onDisabled(Context context, Intent intent) {
		showToast(context, "Admin rights are disabled for the app called: '" + context.getString(R.string.app_name) + "'.");
	}

	@Override
	public void onPasswordChanged(Context context, Intent intent) {
		// TODO showtoast
	}

	@Override
	public void onPasswordFailed(Context context, Intent intent) {
		// TODO showtoast
	}

	@Override
	public void onPasswordSucceeded(Context context, Intent intent) {
		// TODO showtoast
	}

	void showToast(Context context, CharSequence msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
}
