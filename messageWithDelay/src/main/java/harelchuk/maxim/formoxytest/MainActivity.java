package harelchuk.maxim.formoxytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class MainActivity extends MvpActivity implements HelloWorldView {

    @InjectPresenter
    HelloWorldPresenter mHelloWorldPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void showMessage(int message){
        TextView messageTextView = new TextView(this);
        messageTextView.setText(message);
        messageTextView.setTextSize(40);
        messageTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        ((ViewGroup) findViewById(R.id.act_test)).addView(messageTextView);
    }
}
