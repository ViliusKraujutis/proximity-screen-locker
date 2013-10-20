package lt.kraujutis.vilius.android.screenlocker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import lt.kraujutis.vilius.android.utils.AppSharedPreferences;

/**
 * @author Vilius Kraujutis viliusk@gmail.com
 * @since 2013-10-20 20:45
 */
public class RebootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean autoStart = AppSharedPreferences.isAutoStart(context);
        if (autoStart) {
            Intent serviceIntent = new Intent(context, ScreenLockerService.class);
            context.startService(serviceIntent);
        }
    }
}
