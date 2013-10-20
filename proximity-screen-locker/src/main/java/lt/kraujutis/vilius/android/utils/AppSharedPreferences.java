package lt.kraujutis.vilius.android.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Vilius Kraujutis viliusk@gmail.com
 * @since 2013-10-20 21:03
 */
public class AppSharedPreferences {
    private static final String SP_FILE_NAME = AppSharedPreferences.class.getSimpleName();
    private static final String KEY_SHOUD_AUTO_START = "key-should-auto-start";

    public static void setAutoStart(Context context, boolean autoStart) {
        getSharedPreferences(context).edit().putBoolean(KEY_SHOUD_AUTO_START, autoStart).commit();
    }

    public static boolean isAutoStart(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_SHOUD_AUTO_START, false);
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
    }
}
