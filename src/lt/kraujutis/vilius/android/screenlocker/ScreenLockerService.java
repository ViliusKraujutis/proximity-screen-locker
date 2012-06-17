package lt.kraujutis.vilius.android.screenlocker;

import lt.kraujutis.vilius.android.utils.CallStatus;
import lt.kraujutis.vilius.android.utils.ProximitySensor;
import lt.kraujutis.vilius.android.utils.ScreenLock;
import lt.kraujutis.vilius.android.utils.Vibration;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class ScreenLockerService extends Service {

	protected static final String TAG = ScreenLockerService.class.getCanonicalName();
	BroadcastReceiver receiver;

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "onCreate");
		ProximitySensor.getInstance(getApplicationContext());
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);

		receiver = new BroadcastReceiver() {
			@Override
			public void onReceive(final Context context, Intent intent) {
				// Vibration.vibrate(getApplicationContext());
				try {
					if (!ProximitySensor.isFar(context)) {
						Log.d(TAG, "lock");
						if (CallStatus.isCallStateIdle(context)) {
							ScreenLock.lockScreen(context);
						}
						Vibration.vibrate(context);
					}
				} catch (Exception e) {
					Log.d(TAG, "Exception occurred: " + e);
				}
			}
		};

		registerReceiver(receiver, filter);

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}

}
