package lt.kraujutis.vilius.android.utils;

import android.content.Context;
import android.media.AudioManager;
import android.telephony.TelephonyManager;

public class CallStatus {
	public static boolean isOnCall(Context context) {
		return ((AudioManager) context.getSystemService(Context.AUDIO_SERVICE)).getMode() == AudioManager.MODE_IN_CALL;
	}

	public static boolean isCallStateIdle(Context context) {
		return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getCallState() == TelephonyManager.CALL_STATE_IDLE;
	}
}
