package harelchuk.maxim.quizwithmoxy;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import harelchuk.maxim.quizwithmoxy.model.CoinValuesSingleton;
import harelchuk.maxim.quizwithmoxy.model.UserDataSingleton;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connectToServer();
    }

    private void connectToServer() {
        //UserDataSingleton.getInstance();
        CoinValuesSingleton.getInstance().setVariables();
        Thread thread = new MyThread();
        thread.start();
    }

    private boolean isConnected() {
        //UserDataSingleton.getInstance().setUser_uuid();
        return UserDataSingleton.getInstance().isConnected();
    }

    private void startNewActivity(boolean isConnected) {
        if (isConnected) {
            Intent intent = new Intent(this, TabMenuActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private class MyThread extends Thread {
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                for (int counter = 0; counter < 150; counter++) {                                   //  max 15 seconds for connection
                    try {
                        Log.d("myLogs", "-------------------------COUNTER----------------------- ="
                                + String.valueOf(counter));
                        TimeUnit.MILLISECONDS.sleep(100);
                        if (isConnected()) {
                            Thread.currentThread().interrupt();
                            startNewActivity(true);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                if (!isConnected()) {
                    startNewActivity(false);
                }
            }
        }
    }

// --------------------------------------------------------------
/*

    private Scan scan;

    public void read() {
        try {
            // do stuff

            scan = new Scan();
            scan.start();
        } catch (UnknownHostException ex) {
            // handle exception
        } catch (IOException ex) {
            // handle exception
        }
    }

    private class Scan extends Thread {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    // my code goes here
                } catch (IOException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void stop() {
        if(scan != null){
            scan.interrupt();
        }
    }


*/
}