package harelchuk.maxim.quizwithmoxy.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import harelchuk.maxim.quizwithmoxy.R;
import harelchuk.maxim.quizwithmoxy.presenter.SetBankPresenter;
import harelchuk.maxim.quizwithmoxy.view.SetBankView;

public class SetBankFragment extends MvpAppCompatFragment implements SetBankView {

    @InjectPresenter
    SetBankPresenter setBankPresenter;

    public View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bank_fragment, container, false);
        setAlertDialogs();

        return view;
    }


    @Override
    public void showDebit(boolean is_debit, long time_to_increase_in_seconds, long debit_GD, long debit_AD, long debit_CP, long user_money) {
        Log.d("myLogs", "4) is_debit " + is_debit + " time_to_increase_in_seconds " +
                time_to_increase_in_seconds + " debit_GD " + debit_GD + " debit_AD " + debit_AD + " debit_CP " + debit_CP + " user_money " + user_money);
        ProgressBar timeToIncreaseProgressBar = view.findViewById(R.id.DebitProgressBar);
        timeToIncreaseProgressBar.setMax(Integer.valueOf(getResources().getString(R.string.sixHoursOr120Seconds)));         //in seconds
        timeToIncreaseProgressBar.setProgress((int) time_to_increase_in_seconds);
        TextView debitGDTV = view.findViewById(R.id.bankDebitSumGDTV);
        TextView debitADTV = view.findViewById(R.id.bankDebitSumADTV);
        TextView debitCPTV = view.findViewById(R.id.bankDebitSumCPTV);
        SeekBar addDebitSeekBar = view.findViewById(R.id.bankAddDebitSeekBar);
        TextView returnAllTV = view.findViewById(R.id.bankReturnDebitTV);
        TextView addDebitTV = view.findViewById(R.id.bankAddDebitTV);

        debitGDTV.setText(String.valueOf(debit_GD));
        debitADTV.setText(String.valueOf(debit_AD));
        debitCPTV.setText(String.valueOf(debit_CP));
        setOnClickListeners(user_money);
    }

    private void setOnClickListeners(final long user_money) {
        final TextView returnAllTV = view.findViewById(R.id.bankReturnDebitTV);
        final TextView addDebitTV = view.findViewById(R.id.bankAddDebitTV);
        final SeekBar addDebitSeekBar = view.findViewById(R.id.bankAddDebitSeekBar);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == returnAllTV) {
                    //Toast.makeText(getContext(),"return",Toast.LENGTH_LONG).show();
                    setBankPresenter.removeDebitFromSP();
                }
                if (v == addDebitTV) {
                    final int progress = addDebitSeekBar.getProgress();
                    final long sum_to_add = user_money * progress / 10;
                    setBankPresenter.writeDebitIntoSP(sum_to_add);
                    //Toast.makeText(getContext(),"add " + sum_to_add,Toast.LENGTH_LONG).show();
                }
            }
        };
        returnAllTV.setOnClickListener(onClickListener);
        addDebitTV.setOnClickListener(onClickListener);
    }

    private void setAlertDialogs() {

        final ImageButton bankDebitIB = view.findViewById(R.id.bankDebitInfoImageButton);
        //ImageButton bankCreditIB = view.findViewById(R.id.bankCreditInfoImageButton);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message;
                String title;
                if (v == bankDebitIB) {
                    message = getResources().getString(R.string.debitInfo);
                    title = getResources().getString(R.string.debit);
                } else {
                    message = getResources().getString(R.string.creditInfo);
                    title = getResources().getString(R.string.credit);
                }
                //AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.MyDialogTheme);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setIcon(R.drawable.ic_set_bank)
                        .setTitle(title)
                        .setMessage(message)
                        .setCancelable(false)
                        .setNegativeButton("Return",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        };
        bankDebitIB.setOnClickListener(onClickListener);
        //bankCreditIB.setOnClickListener(onClickListener);
    }


}





        /*textView = view.findViewById(R.id.tab3textView);
        Button button_targ = view.findViewById(R.id.button_targ);
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
        button_targ.setOnClickListener(onClickListener);*/