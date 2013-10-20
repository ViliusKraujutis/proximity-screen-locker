package lt.kraujutis.vilius.android.utils;

import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.Toast;

import lt.kraujutis.vilius.android.screenlocker.R;

public class ProximitySensor {

	private static final String TAG = ProximitySensor.class.getCanonicalName();
    private static ProximitySensor mProximitySensor;
    public float value;

    private ProximitySensor(Context context) {
        Log.d(TAG, "constructor");

        SensorManager mSensorManager = (SensorManager) context.getSystemService(Service.SENSOR_SERVICE);
        try {
            Sensor sensor = mSensorManager.getSensorList(Sensor.TYPE_PROXIMITY).get(0);
            SensorEventListener listener = new MySensorEventListener();
            mSensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        } catch (IndexOutOfBoundsException e) {
            Log.e(TAG, e.getMessage(), e);
            Toast.makeText(context, context.getString(R.string.toast_no_proximity_sensor_found), Toast.LENGTH_LONG).show();
        }
    }

    public static ProximitySensor getInstance(Context context) {
        if (mProximitySensor == null) {
            mProximitySensor = new ProximitySensor(context);
        }
        return mProximitySensor;
    }

    public static boolean isFar(Context context) {
        return getInstance(context).value != 0;
    }

    class MySensorEventListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float value = event.values[0];
            Log.d(TAG, "Proximity value=" + value);
            ProximitySensor.this.value = value;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

    }
}
