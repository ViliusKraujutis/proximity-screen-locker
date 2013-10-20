package lt.kraujutis.vilius.android.screenlocker;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import lt.kraujutis.vilius.android.utils.AppSharedPreferences;
import lt.kraujutis.vilius.android.utils.ServiceUtils;

public class ScreenLockerActivity extends Activity implements OnClickListener {

    static final int RESULT_ENABLE = 1;
    protected static final String TAG = ScreenLockerActivity.class.getCanonicalName();

    DevicePolicyManager mDPM;
    ComponentName mDeviceAdminReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        showIfServiceIsRunning();

        findViewById(R.id.main_startService_b).setOnClickListener(this);
        findViewById(R.id.main_disableAdmin_b).setOnClickListener(this);

        mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        mDeviceAdminReceiver = new ComponentName(ScreenLockerActivity.this, ScreenLockerDeviceAdminReceiver.class);
        enableAdmin();
    }

    @Override
    protected void onResume() {
        enableAdmin();
        super.onResume();
    }

    private boolean isMyServiceRunning() {
        return ServiceUtils.isMyServiceRunning(this, ScreenLockerService.class.getCanonicalName());
    }

    private void showIfServiceIsRunning() {
        ((TextView) findViewById(R.id.main_serviceRunningStatus_tv))
                .setText(getString(isMyServiceRunning() ? R.string.main_service_is_running : R.string.main_service_is_not_running));
        ((Button) findViewById(R.id.main_startService_b)).setText(getString(isMyServiceRunning() ? R.string.main_stop_service
                : R.string.main_start_service));
    }

    private void stopScreenLockerService() {
        Intent serviceIntent = new Intent(this, ScreenLockerService.class);
        stopService(serviceIntent);
        showIfServiceIsRunning();
        AppSharedPreferences.setAutoStart(this, false);
    }

    private void startScreenLockerService() {
        Intent serviceIntent = new Intent(this, ScreenLockerService.class);
        startService(serviceIntent);
        showIfServiceIsRunning();
        AppSharedPreferences.setAutoStart(this, true);
    }

    public void enableAdmin() {
        Log.d(TAG, "mEnableListener onclick");

        boolean active = mDPM.isAdminActive(mDeviceAdminReceiver);
        if (!active) {
            // Launch the activity to have the user enable our admin.
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdminReceiver);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "This app needs admin rights to be able to lock the screen.");
            startActivityForResult(intent, RESULT_ENABLE);
        }
    }

    public void disableAdmin() {
        Log.d(TAG, "disableAdmin");
        mDPM.removeActiveAdmin(mDeviceAdminReceiver);
        // mDPM.removeActiveAdmin(new ComponentName(ScreenLockerActivity.this,
        // ScreenLockerDeviceAdminReceiver.class));
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick");
        switch (v.getId()) {
            case R.id.main_startService_b:
                if (isMyServiceRunning()) {
                    stopScreenLockerService();
                } else {
                    enableAdmin();
                    startScreenLockerService();
                }
                break;
            case R.id.main_disableAdmin_b:
                disableAdmin();
                stopScreenLockerService();
                break;
            default:
                break;
        }
    }
}