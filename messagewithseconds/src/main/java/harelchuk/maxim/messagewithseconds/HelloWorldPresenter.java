package harelchuk.maxim.messagewithseconds;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.concurrent.TimeUnit;

@InjectViewState
public class HelloWorldPresenter extends MvpPresenter<HelloWorldView> {

    public HelloWorldPresenter(){

        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void,Integer,Void> asyncTask = new AsyncTask<Void, Integer, Void>() {

            @Override
            protected void onPreExecute(){
                getViewState().showTimer();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                //sleepSecond();
                for(int i=5; i>0;i--){
                    publishProgress(i);
                    sleepSecond();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                //super.onProgressUpdate(values);
                getViewState().setTimer(values[0]);
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                getViewState().hideTimer();
                getViewState().showMessage(R.string.hello);
            }
            private void sleepSecond(){
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException ignore) {}
            }


        };
        asyncTask.execute();
    }

    public void onDismissMessage(){
        getViewState().hideMessage();
    }
}
