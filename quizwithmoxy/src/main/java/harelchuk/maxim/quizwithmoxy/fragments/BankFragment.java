package harelchuk.maxim.quizwithmoxy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;

import java.util.Date;

import harelchuk.maxim.quizwithmoxy.R;

public class BankFragment extends MvpAppCompatFragment {

    public TextView textView;
    public Date date1;
    public ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bank_fragment, container, false);
        setLogic(view);
        return view;
    }

    private void setLogic(final View view) {
        textView = view.findViewById(R.id.tab3textView);
        Button button = view.findViewById(R.id.button);
        progressBar = view.findViewById(R.id.progressBar);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.incrementProgressBy(1);
                progressBar.incrementSecondaryProgressBy(10);
                Date date = new Date();
                textView.setText(date.toString() + String.valueOf(date.getTime()));
                TextView textView1 = view.findViewById(R.id.tab3textView10);
                if(date1!=null) {textView1.setText(String.valueOf(date.getTime()-date1.getTime()));}
                date1 = date;
            }
        };
        button.setOnClickListener(onClickListener);
    }
}
