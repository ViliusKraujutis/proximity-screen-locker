package lt.kraujutis.vilius.android.utils;

import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class ProximitySensor {

	private static final String TAG = ProximitySensor.class.getCanonicalName();
	private static ProximitySensor mProximitySensor;
	public float value;

	private ProximitySensor(Context context) {
		Log.d(TAG, "constructor");

		SensorManager mSensorManager = (SensorManager) context.getSystemService(Service.SENSOR_SERVICE);
		try{
            Sensor sensor = mSensorManager.getSensorList(Sensor.TYPE_PROXIMITY).get(0);
            SensorEventListener listener = new MySensorEventListener();
            mSensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        }catch (IndexOutOfBoundsException e){
            Log.
        }
    }

	public static ProximitySensor getInstance(Context context) {
		if (mProximitySensor == null) {
			mProximitySensor = new ProximitySensor(context);
		}
		return mProximitySensor;
	}
	
	public static boolean isFar(Context context){
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
