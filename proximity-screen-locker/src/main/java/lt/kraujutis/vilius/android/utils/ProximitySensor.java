package lt.kraujutis.vilius.android.utils;

import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import lt.kraujutis.vilius.android.screenlocker.R;

public class ProximitySensor {
    private static final String TAG = ProximitySensor.class.getCanonicalName();
    private static ProximitySensor mProximitySensor;
    private final ProximitySensorListener proximitySensorListener;

    private ProximitySensor(final Context context) {
        Log.d(TAG, "constructor");

        SensorManager mSensorManager = (SensorManager) context.getSystemService(Service.SENSOR_SERVICE);
        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_PROXIMITY);
        if (sensorList.size() > 0) {
            Sensor proximitySensor = sensorList.get(0);
            proximitySensorListener = new ProximitySensorListener();
            mSensorManager.registerListener(proximitySensorListener, proximitySensor, SensorManager.SENSOR_DELAY_FASTEST);
        } else {
            proximitySensorListener = null;
            Toast.makeText(context, context.getString(R.string.toast_no_proximity_sensor_found), Toast.LENGTH_LONG).show();
        }
    }

    public static ProximitySensor getInstance(final Context context) {
        if (mProximitySensor == null) {
            mProximitySensor = new ProximitySensor(context);
        }
        return mProximitySensor;
    }

    /**
     * Check if Proximity sensor measures that device is far.
     * For example:
     * <p/>
     * - Screen is covered
     * - Device is in a pocket or bag
     * <p/>
     * Should call and check if {@link #hasProximitySensorHardware()}, before checking if
     */
    public boolean isClose() {
        return proximitySensorListener.isClose();
    }

    public boolean hasProximitySensorHardware() {
        return proximitySensorListener != null;
    }

    class ProximitySensorListener implements SensorEventListener {
        private float value;

        @Override
        public void onSensorChanged(SensorEvent event) {
            float value = event.values[0];
            Log.d(TAG, "Proximity value=" + value);
            this.value = value;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        protected boolean isClose() {
            return value == 0;// TODO allow users to choose this value (might be different on devices)
        }
    }
}
