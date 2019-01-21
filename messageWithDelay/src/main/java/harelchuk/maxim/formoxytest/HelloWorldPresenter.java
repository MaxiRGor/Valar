package harelchuk.maxim.formoxytest;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.concurrent.TimeUnit;

@InjectViewState
public class HelloWorldPresenter extends MvpPresenter<HelloWorldView> {
    public HelloWorldPresenter(){
        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void,Void,Void> asyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                sleepSecond();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                getViewState().showMessage(R.string.hello);
            }
            private void sleepSecond(){
                try{
                    TimeUnit.SECONDS.sleep(5);
            }catch (InterruptedException ignore) {}
        };
    };
        asyncTask.execute();
    }
}

//    annotationProcessor 'com.arello-mobile:moxy-compiler:1.1.1'
//    annotationProcessor 'com.google.auto.service:auto-service:1.0-rc2'
