package lt.kraujutis.vilius.android.screenlocker;

import lt.kraujutis.vilius.android.utils.Vibration;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScreenTurnedOnReceiver extends BroadcastReceiver {
	private static final String TAG = ScreenTurnedOnReceiver.class.getCanonicalName();

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "ScreenTurnedOnService.onReceive");
		Vibration.vibrate(context);
	}
}
