package harelchuk.maxim.quizwithmoxy.model;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

// простите за код
// не нашёл решения лучше

// удалить при использовнии RXJava

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
