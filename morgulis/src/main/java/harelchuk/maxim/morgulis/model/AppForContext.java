package harelchuk.maxim.morgulis.model;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

public class AppForContext extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
