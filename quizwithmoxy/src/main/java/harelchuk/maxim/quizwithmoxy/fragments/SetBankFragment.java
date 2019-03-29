package harelchuk.maxim.quizwithmoxy.fragments;

import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
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
import harelchuk.maxim.quizwithmoxy.model.CoinConversation;
import harelchuk.maxim.quizwithmoxy.model.UserDataSingleton;
import harelchuk.maxim.quizwithmoxy.presenter.SetBankPresenter;
import harelchuk.maxim.quizwithmoxy.view.SetBankView;

public class SetBankFragment extends MvpAppCompatFragment implements SetBankView {

    @InjectPresenter
    SetBankPresenter setBankPresenter;

    private View bankView;
    private TextView returnDebitTV;
    private TextView addDebitTV;
    private TextView getCreditTV;
    private TextView returnCreditTV;

    private Drawable alertDialogButtonImage;
    private Drawable alertDialogWindowImage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.bankView = inflater.inflate(R.layout.bank_fragment, container, false);
        ImageView bankDebitBackground = bankView.findViewById(R.id.bankDebitBackground);
        this.returnDebitTV = bankView.findViewById(R.id.bankReturnDebitTV);
        this.addDebitTV = bankView.findViewById(R.id.bankAddDebitTV);

        ImageView bankCreditBackground = bankView.findViewById(R.id.bankCreditBackground);
        this.getCreditTV = bankView.findViewById(R.id.bankGetCreditTV);
        this.returnCreditTV = bankView.findViewById(R.id.bankReturnCreditTV);

        int theme = UserDataSingleton.getInstance().getCurrent_theme();
        if (theme == 0) {
            bankDebitBackground.setBackground(getResources().getDrawable(R.drawable.targ_window));
            bankCreditBackground.setBackground(getResources().getDrawable(R.drawable.targ_window));
            returnDebitTV.setBackground(getResources().getDrawable(R.drawable.targ_button_selector));
            addDebitTV.setBackground(getResources().getDrawable(R.drawable.targ_button_selector));
            getCreditTV.setBackground(getResources().getDrawable(R.drawable.targ_button_selector));
            returnCreditTV.setBackground(getResources().getDrawable(R.drawable.targ_button_selector));
            alertDialogButtonImage = getResources().getDrawable(R.drawable.targ_button_selector);
            alertDialogWindowImage = getResources().getDrawable(R.drawable.targ_window);
        }
        if (theme == 1) {
            bankDebitBackground.setBackground(getResources().getDrawable(R.drawable.stark_window));
            bankCreditBackground.setBackground(getResources().getDrawable(R.drawable.stark_window));
            returnDebitTV.setBackground(getResources().getDrawable(R.drawable.stark_button_selector));
            addDebitTV.setBackground(getResources().getDrawable(R.drawable.stark_button_selector));
            getCreditTV.setBackground(getResources().getDrawable(R.drawable.stark_button_selector));
            returnCreditTV.setBackground(getResources().getDrawable(R.drawable.stark_button_selector));
            alertDialogButtonImage = getResources().getDrawable(R.drawable.stark_button_selector);
            alertDialogWindowImage = getResources().getDrawable(R.drawable.stark_window);
        }
        if (theme == 2) {
            bankDebitBackground.setBackground(getResources().getDrawable(R.drawable.lann_window));
            bankCreditBackground.setBackground(getResources().getDrawable(R.drawable.lann_window));
            returnDebitTV.setBackground(getResources().getDrawable(R.drawable.lann_button_selector));
            addDebitTV.setBackground(getResources().getDrawable(R.drawable.lann_button_selector));
            getCreditTV.setBackground(getResources().getDrawable(R.drawable.lann_button_selector));
            returnCreditTV.setBackground(getResources().getDrawable(R.drawable.lann_button_selector));
            alertDialogButtonImage = getResources().getDrawable(R.drawable.lann_button_selector);
            alertDialogWindowImage = getResources().getDrawable(R.drawable.lann_window);
        }
        if (theme == 3) {
            bankDebitBackground.setBackground(getResources().getDrawable(R.drawable.night_window));
            bankCreditBackground.setBackground(getResources().getDrawable(R.drawable.night_window));
            returnDebitTV.setBackground(getResources().getDrawable(R.drawable.night_button_selector));
            addDebitTV.setBackground(getResources().getDrawable(R.drawable.night_button_selector));
            getCreditTV.setBackground(getResources().getDrawable(R.drawable.night_button_selector));
            returnCreditTV.setBackground(getResources().getDrawable(R.drawable.night_button_selector));
            alertDialogButtonImage = getResources().getDrawable(R.drawable.night_button_selector);
            alertDialogWindowImage = getResources().getDrawable(R.drawable.night_window);
        }
        return bankView;
    }

    @Override
    public void showDebit(boolean is_debit, final long debit_time_to_increase_in_seconds, long debit_GD, long debit_AD, long debit_CP, final long user_money) {

        final ProgressBar timeToIncreaseProgressBar = bankView.findViewById(R.id.debitProgressBar);
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
        TextView debitGDTV = bankView.findViewById(R.id.bankDebitSumGDTV);
        TextView debitADTV = bankView.findViewById(R.id.bankDebitSumADTV);
        TextView debitCPTV = bankView.findViewById(R.id.bankDebitSumCPTV);
        SeekBar addDebitSeekBar = bankView.findViewById(R.id.bankAddDebitSeekBar);

        final TextView bankDebitAddSumCPTV = bankView.findViewById(R.id.bankDebitAddSumCPTV);
        final TextView bankDebitAddSumADTV = bankView.findViewById(R.id.bankDebitAddSumADTV);
        final TextView bankDebitAddSumGDTV = bankView.findViewById(R.id.bankDebitAddSumGDTV);

        showAddDebitCoins(addDebitSeekBar.getProgress(), user_money,
                bankDebitAddSumGDTV, bankDebitAddSumADTV, bankDebitAddSumCPTV);

        addDebitSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                showAddDebitCoins(progress, user_money, bankDebitAddSumGDTV, bankDebitAddSumADTV, bankDebitAddSumCPTV);
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

        //TextView getCreditTV = bankView.findViewById(R.id.bankGetCreditTV);
        //TextView returnCreditTV = bankView.findViewById(R.id.bankReturnCreditTV);
        TextView creditName = bankView.findViewById(R.id.bankCreditTV);

        //TextView addDebitTV = bankView.findViewById(R.id.bankAddDebitTV);
        //TextView returnDebitTV = bankView.findViewById(R.id.bankReturnDebitTV);
        TextView debitName = bankView.findViewById(R.id.bankDebitTV);

        if (is_debit) {
            creditName.setTextColor(getResources().getColor(R.color.targUnreachable));
            getCreditTV.setTextColor(getResources().getColor(R.color.targUnreachable));
            returnCreditTV.setTextColor(getResources().getColor(R.color.targUnreachable));
            returnDebitTV.setTextColor(getResources().getColor(R.color.targColorAccent));
            View.OnClickListener onDebitClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = getResources().getString(R.string.credit);
                    String text = getResources().getString(R.string.debitAlreadyIs);
                    showAlertMessage(title, text);
                }
            };
            getCreditTV.setOnClickListener(onDebitClickListener);
            returnCreditTV.setOnClickListener(onDebitClickListener);

        } else {
            if (is_credit) {
                addDebitTV.setTextColor(getResources().getColor(R.color.targUnreachable));
                returnDebitTV.setTextColor(getResources().getColor(R.color.targUnreachable));
                debitName.setTextColor(getResources().getColor(R.color.targUnreachable));
                getCreditTV.setTextColor(getResources().getColor(R.color.targUnreachable));
                returnCreditTV.setTextColor(getResources().getColor(R.color.targColorAccent));

                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title = getResources().getString(R.string.debit);
                        String text = getResources().getString(R.string.creditAlreadyIs);
                        showAlertMessage(title, text);
                    }
                };
                addDebitTV.setOnClickListener(onClickListener);
                returnDebitTV.setOnClickListener(onClickListener);
            } else {
                getCreditTV.setTextColor(getResources().getColor(R.color.targColorAccent));
                addDebitTV.setTextColor(getResources().getColor(R.color.targColorAccent));
                debitName.setTextColor(getResources().getColor(R.color.targColorAccent));
                creditName.setTextColor(getResources().getColor(R.color.targColorAccent));
                returnCreditTV.setTextColor(getResources().getColor(R.color.targUnreachable));
                returnDebitTV.setTextColor(getResources().getColor(R.color.targUnreachable));
            }

        }
    }


    @Override
    public void showCredit(boolean is_credit, long max_progress, long credit_time_to_return, long[] credit_GAC, final long user_money) {

        ProgressBar creditTimeProgressBar = bankView.findViewById(R.id.bankCreditProgressBar);
        creditTimeProgressBar.setMax((int) max_progress);
        if (is_credit) {
            creditTimeProgressBar.setProgress((int) credit_time_to_return);
        } else creditTimeProgressBar.setProgress(0);

        TextView creditGD = bankView.findViewById(R.id.bankCreditSumGDTV);
        creditGD.setText(String.valueOf(credit_GAC[0]));

        TextView creditAD = bankView.findViewById(R.id.bankCreditSumADTV);
        creditAD.setText(String.valueOf(credit_GAC[1]));

        TextView creditCP = bankView.findViewById(R.id.bankCreditSumCPTV);
        creditCP.setText(String.valueOf(credit_GAC[2]));

        final SeekBar getCreditSeekBar = bankView.findViewById(R.id.bankAddCreditSeekBar);
        final TextView creditGetGDTV = bankView.findViewById(R.id.bankCreditAddSumGDTV);
        final TextView creditGetADTV = bankView.findViewById(R.id.bankCreditAddSumADTV);
        final TextView creditGetCPTV = bankView.findViewById(R.id.bankCreditAddSumCPTV);

        if (is_credit) getCreditSeekBar.setProgress(0);

        showAddCreditCoins(getCreditSeekBar.getProgress(), user_money,
                creditGetGDTV, creditGetADTV, creditGetCPTV);

        getCreditSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                showAddCreditCoins(progress, user_money,
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

        final TextView getCreditTV = bankView.findViewById(R.id.bankGetCreditTV);
        final TextView returnCreditTV = bankView.findViewById(R.id.bankReturnCreditTV);
        //final ImageView getButtonBackground = bankView.findViewById(R.id.bankGetCreditImage);
        //final ImageView returnButtonBackground = bankView.findViewById(R.id.bankReturnCreditImage);
        final SeekBar addCreditSeekBar = bankView.findViewById(R.id.bankAddCreditSeekBar);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == returnCreditTV) {
                    //returnButtonBackground.performClick();
                    if (is_credit) setBankPresenter.returnCreditUsingSP();
                    else {
                        String title = getResources().getString(R.string.credit);
                        String text = getResources().getString(R.string.nothingToReturn);
                        showAlertMessage(title, text);
                    }
                }
                if (v == getCreditTV) {
                    //getButtonBackground.performClick();
                    if (!is_credit) {
                        final int progress = addCreditSeekBar.getProgress();
                        final long sum_to_get = user_money * progress;
                        if (sum_to_get > 0) {
                            setBankPresenter.writeCreditIntoSP(sum_to_get);
                        } else {
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
        final TextView returnAllTV = bankView.findViewById(R.id.bankReturnDebitTV);
        final TextView addDebitTV = bankView.findViewById(R.id.bankAddDebitTV);
        final SeekBar addDebitSeekBar = bankView.findViewById(R.id.bankAddDebitSeekBar);
        final TextView bankDebitAddSumCPTV = bankView.findViewById(R.id.bankDebitAddSumCPTV);
        final TextView bankDebitAddSumADTV = bankView.findViewById(R.id.bankDebitAddSumADTV);
        final TextView bankDebitAddSumGDTV = bankView.findViewById(R.id.bankDebitAddSumGDTV);

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
                        setBankPresenter.addDebit(sum_to_add);
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
                                    TextView creditGetGDTV, TextView creditGetADTV, TextView creditGetCPTV) {
        final long sum_to_get = user_money * progress;
        long[] coinsGAC = CoinConversation.coins_GD_AD_CP(sum_to_get);
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

    private void showAddDebitCoins(int progress, long user_money,
                                   TextView bankDebitAddSumGDTV, TextView bankDebitAddSumADTV, TextView bankDebitAddSumCPTV) {
        final long sum_to_add = user_money * progress / 10;
        long[] coinsGAC = CoinConversation.coins_GD_AD_CP(sum_to_add);
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

        ImageView background = dialogView.findViewById(R.id.alertDialogBackgroundImage);
        background.setBackground(alertDialogWindowImage);

        Button closeDialogButton = dialogView.findViewById(R.id.alert_dialog_button);
        closeDialogButton.setBackground(alertDialogButtonImage);
        closeDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        TextView titleTV = dialogView.findViewById(R.id.alert_dialog_text_title_TV);
        titleTV.setText(alertTitle);

        TextView textTV = dialogView.findViewById(R.id.alert_dialog_text_TV);
        textTV.setText(alertText);

        dialog.getWindow().setDimAmount(0.8f);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        dialog.show();
    }
}
