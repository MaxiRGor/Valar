package harelchuk.maxim.quizwithmoxy.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import harelchuk.maxim.quizwithmoxy.R;
import harelchuk.maxim.quizwithmoxy.model.SharedPreferencesFunctions;
import harelchuk.maxim.quizwithmoxy.presenter.SetBankPresenter;
import harelchuk.maxim.quizwithmoxy.view.SetBankView;

public class SetBankFragment extends MvpAppCompatFragment implements SetBankView {

    @InjectPresenter
    SetBankPresenter setBankPresenter;

    public View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bank_fragment, container, false);
        return view;
    }

    @Override
    public void showDebit(boolean is_debit, long time_to_increase_in_seconds, long debit_GD, long debit_AD, long debit_CP, final long user_money) {
        Log.d("myLogs", "4) is_debit " + is_debit + " time_to_increase_in_seconds " +
                time_to_increase_in_seconds + " debit_GD " + debit_GD + " debit_AD " + debit_AD + " debit_CP " + debit_CP + " user_money " + user_money);
        ProgressBar timeToIncreaseProgressBar = view.findViewById(R.id.debitProgressBar);
        timeToIncreaseProgressBar.setMax(Integer.valueOf(getResources().getString(R.string.sixHoursOr120Seconds)));         //in seconds
        timeToIncreaseProgressBar.setProgress((int) time_to_increase_in_seconds);
        TextView debitGDTV = view.findViewById(R.id.bankDebitSumGDTV);
        TextView debitADTV = view.findViewById(R.id.bankDebitSumADTV);
        TextView debitCPTV = view.findViewById(R.id.bankDebitSumCPTV);
        SeekBar addDebitSeekBar = view.findViewById(R.id.bankAddDebitSeekBar);
        final TextView bankDebitAddSumCPTV = view.findViewById(R.id.bankDebitAddSumCPTV);
        final TextView bankDebitAddSumADTV = view.findViewById(R.id.bankDebitAddSumADTV);
        final TextView bankDebitAddSumGDTV = view.findViewById(R.id.bankDebitAddSumGDTV);
        final SharedPreferencesFunctions sharedPreferencesFunctions = new SharedPreferencesFunctions();

        showAddDebitCoins(addDebitSeekBar.getProgress(), user_money,
                sharedPreferencesFunctions, bankDebitAddSumGDTV, bankDebitAddSumADTV, bankDebitAddSumCPTV);

        addDebitSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                showAddDebitCoins(progress, user_money, sharedPreferencesFunctions, bankDebitAddSumGDTV, bankDebitAddSumADTV, bankDebitAddSumCPTV);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        debitGDTV.setText(String.valueOf(debit_GD));
        debitADTV.setText(String.valueOf(debit_AD));
        debitCPTV.setText(String.valueOf(debit_CP));
        setOnClickListeners(user_money);
    }

    private void showAddDebitCoins(int progress, long user_money, SharedPreferencesFunctions sharedPreferencesFunctions,
                                   TextView bankDebitAddSumGDTV, TextView bankDebitAddSumADTV, TextView bankDebitAddSumCPTV) {
        final long sum_to_add = user_money * progress / 10;
        long[] coinsGAC = sharedPreferencesFunctions.coins_GD_AD_CP(sum_to_add);
        if (coinsGAC[0] != 0) {
            bankDebitAddSumGDTV.setText(String.valueOf(coinsGAC[0]));
            bankDebitAddSumGDTV.setVisibility(View.VISIBLE);
        } else bankDebitAddSumGDTV.setVisibility(View.INVISIBLE);
        if (coinsGAC[1] != 0) {
            bankDebitAddSumADTV.setVisibility(View.VISIBLE);
            bankDebitAddSumADTV.setText(String.valueOf(coinsGAC[1]));
        } else bankDebitAddSumADTV.setVisibility(View.INVISIBLE);
        if (coinsGAC[2] != 0) {
            bankDebitAddSumCPTV.setVisibility(View.VISIBLE);
            bankDebitAddSumCPTV.setText(String.valueOf(coinsGAC[2]));
        } else bankDebitAddSumCPTV.setVisibility(View.INVISIBLE);
    }

    private void setOnClickListeners(final long user_money) {
        final TextView returnAllTV = view.findViewById(R.id.bankReturnDebitTV);
        final TextView addDebitTV = view.findViewById(R.id.bankAddDebitTV);
        final SeekBar addDebitSeekBar = view.findViewById(R.id.bankAddDebitSeekBar);
        final ProgressBar timeToIncrease = view.findViewById(R.id.debitProgressBar);
        final TextView bankDebitAddSumCPTV = view.findViewById(R.id.bankDebitAddSumCPTV);
        final TextView bankDebitAddSumADTV = view.findViewById(R.id.bankDebitAddSumADTV);
        final TextView bankDebitAddSumGDTV = view.findViewById(R.id.bankDebitAddSumGDTV);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == returnAllTV) {
                    setBankPresenter.removeDebitFromSP();
                    addDebitSeekBar.setProgress(0);
                    timeToIncrease.setProgress(0);
                }
                if (v == addDebitTV) {
                    final int progress = addDebitSeekBar.getProgress();
                    final long sum_to_add = user_money * progress / 10;
                    if (sum_to_add > 0) {
                        bankDebitAddSumCPTV.setVisibility(View.INVISIBLE);
                        bankDebitAddSumADTV.setVisibility(View.INVISIBLE);
                        bankDebitAddSumGDTV.setVisibility(View.INVISIBLE);
                        setBankPresenter.writeDebitIntoSP(sum_to_add);
                    }
                }
            }
        };
        returnAllTV.setOnClickListener(onClickListener);
        addDebitTV.setOnClickListener(onClickListener);
    }

}
