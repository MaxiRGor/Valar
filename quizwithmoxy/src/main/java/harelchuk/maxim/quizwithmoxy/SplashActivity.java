package harelchuk.maxim.quizwithmoxy;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.net.UnknownHostException;
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

        UserDataSingleton.getInstance();
        CoinValuesSingleton.getInstance();
        Thread thread = new MyThread();
        thread.start();
    }

    private boolean isMoneyNotNull() {
        final long money = UserDataSingleton.getInstance().getUser_money();
        return money != 0;
    }

    private void startNewActivity(boolean isConnected) {
        if (isConnected) {
            Intent intent = new Intent(this, TabMenuActivity.class);
            startActivity(intent);
            finish();
        }
    }


    private class MyThread extends Thread {
        final boolean[] isConnected = {false};
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                for (int counter = 0; counter < 150; counter++) {
                    try {
                        Log.d("myLogs", "---------COUNTER------- ="
                                + String.valueOf(counter));
                        TimeUnit.MILLISECONDS.sleep(100);
                        if (isMoneyNotNull()) {
                            Log.d("myLogs", "---------MONEY------- ="
                                    + String.valueOf(UserDataSingleton.getInstance().getUser_money()));
                            isConnected[0] = true;
                            Thread.currentThread().interrupt();
                            startNewActivity(true);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                if (!isConnected[0]) {
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