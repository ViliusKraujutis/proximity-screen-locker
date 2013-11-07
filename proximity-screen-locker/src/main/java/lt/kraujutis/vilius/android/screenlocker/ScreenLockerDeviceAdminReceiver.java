package lt.kraujutis.vilius.android.screenlocker;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ScreenLockerDeviceAdminReceiver extends DeviceAdminReceiver {

    @Override
    public void onEnabled(Context context, Intent intent) {
        showToast(context, context.getString(R.string.device_admin_enabled_message));
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return String.format(context.getString(R.string.device_admin_disable_requested_message), context.getString(R.string.app_name));
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        showToast(context, String.format(context.getString(R.string.device_admin_disabled_message), context.getString(R.string.app_name)));
    }

    void showToast(Context context, CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
