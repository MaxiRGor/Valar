package harelchuk.maxim.quizwithmoxy.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
    SharedPreferencesFunctions sharedPreferencesFunctions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bank_fragment, container, false);
        sharedPreferencesFunctions = new SharedPreferencesFunctions();
        return view;
    }

    @Override
    public void showDebit(boolean is_debit, final long debit_time_to_increase_in_seconds, long debit_GD, long debit_AD, long debit_CP, final long user_money) {

        final ProgressBar timeToIncreaseProgressBar = view.findViewById(R.id.debitProgressBar);
        timeToIncreaseProgressBar.setMax(Integer.valueOf(getResources().getString(R.string.sixHoursOr120Seconds)));

        if (is_debit) {
            timeToIncreaseProgressBar.setProgress((int) debit_time_to_increase_in_seconds);
        } else timeToIncreaseProgressBar.setProgress(0);
/*
        @SuppressLint("StaticFieldLeak") AsyncTask<Integer,Integer,Void> asyncTask = new AsyncTask<Integer, Integer, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {
                for( int i = (int) debit_time_to_increase_in_seconds; i<120; i++)
                try {
                    Thread.sleep(1000);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                timeToIncreaseProgressBar.setProgress(values[0]);
            }
        };

        asyncTask.execute(0);
*/
        TextView debitGDTV = view.findViewById(R.id.bankDebitSumGDTV);
        TextView debitADTV = view.findViewById(R.id.bankDebitSumADTV);
        TextView debitCPTV = view.findViewById(R.id.bankDebitSumCPTV);
        SeekBar addDebitSeekBar = view.findViewById(R.id.bankAddDebitSeekBar);
        final TextView bankDebitAddSumCPTV = view.findViewById(R.id.bankDebitAddSumCPTV);
        final TextView bankDebitAddSumADTV = view.findViewById(R.id.bankDebitAddSumADTV);
        final TextView bankDebitAddSumGDTV = view.findViewById(R.id.bankDebitAddSumGDTV);
        //final SharedPreferencesFunctions sharedPreferencesFunctions = new SharedPreferencesFunctions();

        //if (is_debit) addDebitSeekBar.setProgress(0);

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

        setDebitOnClickListeners(is_debit, user_money);

    }

    @Override
    public void setBrokenButtons(boolean is_debit, boolean is_credit) {

        TextView getCredit = view.findViewById(R.id.bankGetCreditTV);
        TextView returnCredit = view.findViewById(R.id.bankReturnCreditTV);
        TextView creditName = view.findViewById(R.id.bankCreditTV);

        TextView addDebit = view.findViewById(R.id.bankAddDebitTV);
        TextView returnDebit = view.findViewById(R.id.bankReturnDebitTV);
        TextView debitName = view.findViewById(R.id.bankDebitTV);

        if (is_debit) {
            creditName.setTextColor(getResources().getColor(R.color.unreachable));
            getCredit.setTextColor(getResources().getColor(R.color.unreachable));
            returnCredit.setTextColor(getResources().getColor(R.color.unreachable));
            returnDebit.setTextColor(getResources().getColor(R.color.colorAccent));
            View.OnClickListener onDebitClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = getResources().getString(R.string.credit);
                    String text = getResources().getString(R.string.debitAlreadyIs);
                    showAlertMessage(title, text);
                }
            };
            getCredit.setOnClickListener(onDebitClickListener);
            returnCredit.setOnClickListener(onDebitClickListener);

        } else {
            if (is_credit) {
                addDebit.setTextColor(getResources().getColor(R.color.unreachable));
                returnDebit.setTextColor(getResources().getColor(R.color.unreachable));
                debitName.setTextColor(getResources().getColor(R.color.unreachable));
                getCredit.setTextColor(getResources().getColor(R.color.unreachable));
                returnCredit.setTextColor(getResources().getColor(R.color.colorAccent));

                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title = getResources().getString(R.string.debit);
                        String text = getResources().getString(R.string.creditAlreadyIs);
                        showAlertMessage(title, text);
                    }
                };
                addDebit.setOnClickListener(onClickListener);
                returnDebit.setOnClickListener(onClickListener);
            } else {
                getCredit.setTextColor(getResources().getColor(R.color.colorAccent));
                addDebit.setTextColor(getResources().getColor(R.color.colorAccent));
                debitName.setTextColor(getResources().getColor(R.color.colorAccent));
                creditName.setTextColor(getResources().getColor(R.color.colorAccent));
                returnCredit.setTextColor(getResources().getColor(R.color.unreachable));
                returnDebit.setTextColor(getResources().getColor(R.color.unreachable));
            }

        }
    }


    @Override
    public void showCredit(boolean is_credit, long max_progress, long credit_time_to_return, long[] credit_GAC, final long user_money) {

        ProgressBar creditTimeProgressBar = view.findViewById(R.id.bankCreditProgressBar);
        creditTimeProgressBar.setMax((int) max_progress);
        if (is_credit) {
            creditTimeProgressBar.setProgress((int) credit_time_to_return);
        } else creditTimeProgressBar.setProgress(0);

        TextView creditGD = view.findViewById(R.id.bankCreditSumGDTV);
        creditGD.setText(String.valueOf(credit_GAC[0]));

        TextView creditAD = view.findViewById(R.id.bankCreditSumADTV);
        creditAD.setText(String.valueOf(credit_GAC[1]));

        TextView creditCP = view.findViewById(R.id.bankCreditSumCPTV);
        creditCP.setText(String.valueOf(credit_GAC[2]));

        final SeekBar getCreditSeekBar = view.findViewById(R.id.bankAddCreditSeekBar);
        final TextView creditGetGDTV = view.findViewById(R.id.bankCreditAddSumGDTV);
        final TextView creditGetADTV = view.findViewById(R.id.bankCreditAddSumADTV);
        final TextView creditGetCPTV = view.findViewById(R.id.bankCreditAddSumCPTV);

        if (is_credit) getCreditSeekBar.setProgress(0);

        showAddCreditCoins(getCreditSeekBar.getProgress(), user_money,
                sharedPreferencesFunctions, creditGetGDTV, creditGetADTV, creditGetCPTV);

        getCreditSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                showAddCreditCoins(progress, user_money, sharedPreferencesFunctions,
                        creditGetGDTV, creditGetADTV, creditGetCPTV);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        setCreditOnClickListeners(is_credit, user_money);
    }

    private void setCreditOnClickListeners(final boolean is_credit, final long user_money) {

        final TextView getCreditTV = view.findViewById(R.id.bankGetCreditTV);
        final TextView returnCreditTV = view.findViewById(R.id.bankReturnCreditTV);
        final ImageView getButtonBackground = view.findViewById(R.id.bankGetCreditImage);
        final ImageView returnButtonBackground = view.findViewById(R.id.bankReturnCreditImage);
        final SeekBar addCreditSeekBar = view.findViewById(R.id.bankAddCreditSeekBar);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == returnCreditTV) {
                    returnButtonBackground.performClick();
                    if (is_credit) setBankPresenter.returnCreditUsingSP();
                    else {
                        String title = getResources().getString(R.string.credit);
                        String text = getResources().getString(R.string.nothingToReturn);
                        showAlertMessage(title, text);
                    }
                }
                if (v == getCreditTV) {
                    getButtonBackground.performClick();
                    if (!is_credit) {
                        final int progress = addCreditSeekBar.getProgress();
                        final long sum_to_get = user_money * progress;
                        if (sum_to_get > 0) {
                            setBankPresenter.writeCreditIntoSP(sum_to_get);
                        } else{
                            String title = getResources().getString(R.string.credit);
                            String text = getResources().getString(R.string.selectSum);
                            showAlertMessage(title, text);
                        }
                    } else {
                        String title = getResources().getString(R.string.credit);
                        String text = getResources().getString(R.string.creditAlreadyIs);
                        showAlertMessage(title, text);
                    }
                }
            }
        };

        getCreditTV.setOnClickListener(onClickListener);
        returnCreditTV.setOnClickListener(onClickListener);
    }

    private void setDebitOnClickListeners(final boolean is_debit, final long user_money) {
        final TextView returnAllTV = view.findViewById(R.id.bankReturnDebitTV);
        final TextView addDebitTV = view.findViewById(R.id.bankAddDebitTV);
        final SeekBar addDebitSeekBar = view.findViewById(R.id.bankAddDebitSeekBar);
        final TextView bankDebitAddSumCPTV = view.findViewById(R.id.bankDebitAddSumCPTV);
        final TextView bankDebitAddSumADTV = view.findViewById(R.id.bankDebitAddSumADTV);
        final TextView bankDebitAddSumGDTV = view.findViewById(R.id.bankDebitAddSumGDTV);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == returnAllTV) {
                    if (is_debit) setBankPresenter.removeDebitFromSP();
                    else {
                        String title = getResources().getString(R.string.debit);
                        String text = getResources().getString(R.string.nothingToReturn);
                        showAlertMessage(title, text);
                    }
                }
                if (v == addDebitTV) {
                    final int progress = addDebitSeekBar.getProgress();
                    final long sum_to_add = user_money * progress / 10;
                    if (sum_to_add > 0) {
                        bankDebitAddSumCPTV.setVisibility(View.INVISIBLE);
                        bankDebitAddSumADTV.setVisibility(View.INVISIBLE);
                        bankDebitAddSumGDTV.setVisibility(View.INVISIBLE);
                        setBankPresenter.writeDebitIntoSP(sum_to_add);
                    } else {
                        String title = getResources().getString(R.string.debit);
                        String text = getResources().getString(R.string.selectSum);
                        showAlertMessage(title, text);
                    }
                }
            }
        };
        returnAllTV.setOnClickListener(onClickListener);
        addDebitTV.setOnClickListener(onClickListener);
    }

    private void showAddCreditCoins(int progress, long user_money,
                                    SharedPreferencesFunctions sharedPreferencesFunctions,
                                    TextView creditGetGDTV, TextView creditGetADTV, TextView creditGetCPTV) {
        final long sum_to_get = user_money * progress;
        long[] coinsGAC = sharedPreferencesFunctions.coins_GD_AD_CP(sum_to_get);
        if (coinsGAC[0] != 0) {
            creditGetGDTV.setText(String.valueOf(coinsGAC[0]));
            creditGetGDTV.setVisibility(View.VISIBLE);
        } else creditGetGDTV.setVisibility(View.INVISIBLE);
        if (coinsGAC[1] != 0) {
            creditGetADTV.setVisibility(View.VISIBLE);
            creditGetADTV.setText(String.valueOf(coinsGAC[1]));
        } else creditGetADTV.setVisibility(View.INVISIBLE);
        if (coinsGAC[2] != 0) {
            creditGetCPTV.setVisibility(View.VISIBLE);
            creditGetCPTV.setText(String.valueOf(coinsGAC[2]));
        } else creditGetCPTV.setVisibility(View.INVISIBLE);

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

    @Override
    public void showAlertMessage(String alertTitle, String alertText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alertdialog_text_message, null);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        Button closeDialogButton = dialogView.findViewById(R.id.alert_dialog_button);
        TextView titleTV = dialogView.findViewById(R.id.alert_dialog_text_title_TV);
        titleTV.setText(alertTitle);
        TextView textTV = dialogView.findViewById(R.id.alert_dialog_text_TV);
        textTV.setText(alertText);
        closeDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.getWindow().setDimAmount(0.8f);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        dialog.show();
    }
}
