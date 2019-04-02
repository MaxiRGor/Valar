package harelchuk.maxim.morgulis.fragments;

import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import harelchuk.maxim.morgulis.R;
import harelchuk.maxim.morgulis.model.CoinValuesSingleton;
import harelchuk.maxim.morgulis.model.UserDataSingleton;
import harelchuk.maxim.morgulis.presenter.SetBankPresenter;
import harelchuk.maxim.morgulis.view.SetBankView;

public class SetBankFragment extends MvpAppCompatFragment implements SetBankView {

    @InjectPresenter
    SetBankPresenter setBankPresenter;

    private View bankView;
    private TextView returnDebitTV;
    private TextView addDebitTV;
    private TextView getCreditTV;
    private TextView returnCreditTV;

    private Drawable alertDialogButtonDrawable;
    private Drawable alertDialogWindowDrawable;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
            alertDialogButtonDrawable = getResources().getDrawable(R.drawable.targ_button_selector);
            alertDialogWindowDrawable = getResources().getDrawable(R.drawable.targ_window);
        }
        if (theme == 1) {
            bankDebitBackground.setBackground(getResources().getDrawable(R.drawable.stark_window));
            bankCreditBackground.setBackground(getResources().getDrawable(R.drawable.stark_window));
            returnDebitTV.setBackground(getResources().getDrawable(R.drawable.stark_button_selector));
            addDebitTV.setBackground(getResources().getDrawable(R.drawable.stark_button_selector));
            getCreditTV.setBackground(getResources().getDrawable(R.drawable.stark_button_selector));
            returnCreditTV.setBackground(getResources().getDrawable(R.drawable.stark_button_selector));
            alertDialogButtonDrawable = getResources().getDrawable(R.drawable.stark_button_selector);
            alertDialogWindowDrawable = getResources().getDrawable(R.drawable.stark_window);
        }
        if (theme == 2) {
            bankDebitBackground.setBackground(getResources().getDrawable(R.drawable.lann_window));
            bankCreditBackground.setBackground(getResources().getDrawable(R.drawable.lann_window));
            returnDebitTV.setBackground(getResources().getDrawable(R.drawable.lann_button_selector));
            addDebitTV.setBackground(getResources().getDrawable(R.drawable.lann_button_selector));
            getCreditTV.setBackground(getResources().getDrawable(R.drawable.lann_button_selector));
            returnCreditTV.setBackground(getResources().getDrawable(R.drawable.lann_button_selector));
            alertDialogButtonDrawable = getResources().getDrawable(R.drawable.lann_button_selector);
            alertDialogWindowDrawable = getResources().getDrawable(R.drawable.lann_window);
        }
        if (theme == 3) {
            bankDebitBackground.setBackground(getResources().getDrawable(R.drawable.night_window));
            bankCreditBackground.setBackground(getResources().getDrawable(R.drawable.night_window));
            returnDebitTV.setBackground(getResources().getDrawable(R.drawable.night_button_selector));
            addDebitTV.setBackground(getResources().getDrawable(R.drawable.night_button_selector));
            getCreditTV.setBackground(getResources().getDrawable(R.drawable.night_button_selector));
            returnCreditTV.setBackground(getResources().getDrawable(R.drawable.night_button_selector));
            alertDialogButtonDrawable = getResources().getDrawable(R.drawable.night_button_selector);
            alertDialogWindowDrawable = getResources().getDrawable(R.drawable.night_window);
        }
        return bankView;
    }

    @Override
    public void showDebit(boolean is_debit, final int debit_time_to_increase, long debit_GD, long debit_AD, long debit_CP, final long user_money) {

        final ProgressBar timeToIncreaseProgressBar = bankView.findViewById(R.id.debitProgressBar);
        timeToIncreaseProgressBar.setMax(120);

        if (is_debit) {
            timeToIncreaseProgressBar.setProgress( debit_time_to_increase);
        } else timeToIncreaseProgressBar.setProgress(0);

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

        TextView creditName = bankView.findViewById(R.id.bankCreditTV);

        TextView debitName = bankView.findViewById(R.id.bankDebitTV);

        if (is_debit) {
            creditName.setTextColor(getResources().getColor(R.color.targUnreachable));
            this.getCreditTV.setTextColor(getResources().getColor(R.color.targUnreachable));
            this.returnCreditTV.setTextColor(getResources().getColor(R.color.targUnreachable));
            this.returnDebitTV.setTextColor(getResources().getColor(R.color.targColorAccent));
            View.OnClickListener onDebitClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = getResources().getString(R.string.credit);
                    String text = getResources().getString(R.string.debitAlreadyIs);
                    showAlertMessage(title, text);
                }
            };
            this.getCreditTV.setOnClickListener(onDebitClickListener);
            this.returnCreditTV.setOnClickListener(onDebitClickListener);

        }
        if (!is_debit) {
            if (is_credit) {
                debitName.setTextColor(getResources().getColor(R.color.targUnreachable));
                this.addDebitTV.setTextColor(getResources().getColor(R.color.targUnreachable));
                this.returnDebitTV.setTextColor(getResources().getColor(R.color.targUnreachable));
                this.getCreditTV.setTextColor(getResources().getColor(R.color.targUnreachable));
                this.returnCreditTV.setTextColor(getResources().getColor(R.color.targColorAccent));

                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title = getResources().getString(R.string.debit);
                        String text = getResources().getString(R.string.creditAlreadyIs);
                        showAlertMessage(title, text);
                    }
                };
                this.addDebitTV.setOnClickListener(onClickListener);
                this.returnDebitTV.setOnClickListener(onClickListener);
            }
            if (!is_credit) {
                debitName.setTextColor(getResources().getColor(R.color.targColorAccent));
                creditName.setTextColor(getResources().getColor(R.color.targColorAccent));
                this.getCreditTV.setTextColor(getResources().getColor(R.color.targColorAccent));
                this.addDebitTV.setTextColor(getResources().getColor(R.color.targColorAccent));
                this.returnCreditTV.setTextColor(getResources().getColor(R.color.targUnreachable));
                this.returnDebitTV.setTextColor(getResources().getColor(R.color.targUnreachable));
            }

        }
    }


    @Override
    public void showCredit(boolean is_credit, int credit_time_to_return, long[] credit_GAC, final long user_money) {

        ProgressBar creditTimeProgressBar = bankView.findViewById(R.id.bankCreditProgressBar);
        creditTimeProgressBar.setMax(120);  //in minutes, step = 1 minute
        if (is_credit) {
            creditTimeProgressBar.setProgress(credit_time_to_return);
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
                    if (is_credit) setBankPresenter.returnCredit();
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
                            setBankPresenter.getCredit(sum_to_get);
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
                    if (is_debit) setBankPresenter.returnDebit();
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
        long[] coinsGAC = CoinValuesSingleton.getInstance().convertCoinsToGAC(sum_to_get);
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
        long[] coinsGAC = CoinValuesSingleton.getInstance().convertCoinsToGAC(sum_to_add);
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
        background.setBackground(alertDialogWindowDrawable);

        Button closeDialogButton = dialogView.findViewById(R.id.alert_dialog_button);
        closeDialogButton.setBackground(alertDialogButtonDrawable);
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
