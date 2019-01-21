package harelchuk.maxim.messagewithseconds;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class MainActivity extends MvpActivity implements HelloWorldView {

    @InjectPresenter
    HelloWorldPresenter mHelloWorldPresenter;

    private TextView mTimerTextView;
    private View mMessageView;
    private AlertDialog mMessageDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTimerTextView = (TextView) findViewById(R.id.timer_text_view);
    }
    @Override
    public void showTimer(){
        mTimerTextView.setVisibility(View.VISIBLE);
    }
    @Override
    public void hideTimer(){
        mTimerTextView.setVisibility(View.GONE);
    }
    @Override
    public void setTimer(int seconds){
        mTimerTextView.setText(getString(R.string.timer,seconds));
    }

    @Override
    public void showMessage(int message){
        ViewGroup rootView = (ViewGroup) findViewById(R.id.act_test);
        mMessageView = LayoutInflater.from(this).inflate(R.layout.item_message,rootView,false);
        rootView.addView(mMessageView);
        ((TextView) mMessageView.findViewById(R.id.message_text_view)).setText(message);
        mMessageView.findViewById(R.id.close_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mHelloWorldPresenter.onDismissMessage();
                    }
                });
    }

    @Override
    public void hideMessage(){
        ViewGroup rootView = (ViewGroup) findViewById(R.id.act_test);
        rootView.removeView(mMessageView);
    }
}




/*
@Override
    public void showMessage(int message){
        TextView messageTextView = new TextView(this);
        messageTextView.setText(message);
        messageTextView.setTextSize(40);
        messageTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        ((ViewGroup) findViewById(R.id.act_test)).addView(messageTextView);

        ViewGroup rootView = (ViewGroup) findViewById(R.id.act_test);
        mMessageView = LayoutInflater.from(this).inflate(R.layout.item_message,rootView,false);
        rootView.addView(mMessageView);
        ((TextView) mMessageView.findViewById(R.id.message_text_view)).setText(message);
        mMessageView.findViewById(R.id.close_button).
                setOnClickListener();

        mMessageDialog = new AlertDialog(this);

/*
        mMessageDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok,null)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        mHelloWorldPresenter.onDismissMessage();
                    }
                })
                .show();
    }
*/