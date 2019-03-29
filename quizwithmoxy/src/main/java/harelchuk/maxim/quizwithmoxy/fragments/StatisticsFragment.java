package harelchuk.maxim.quizwithmoxy.fragments;

import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import harelchuk.maxim.quizwithmoxy.R;
import harelchuk.maxim.quizwithmoxy.model.CoinConversation;
import harelchuk.maxim.quizwithmoxy.model.UserDataSingleton;
import harelchuk.maxim.quizwithmoxy.presenter.StatisticsPresenter;
import harelchuk.maxim.quizwithmoxy.view.StatisticsView;


public class StatisticsFragment extends MvpAppCompatFragment implements StatisticsView {

    @InjectPresenter
    StatisticsPresenter statisticsPresenter;

    private View statisticsView;
    private String[] names;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup mainContainerVG = getActivity().findViewById(R.id.main_container);
        statisticsView = inflater.inflate(R.layout.statistics, mainContainerVG, false);
        names = new String[2];
        names[0] = "";
        names[1] = "";
        return statisticsView;
    }

    private void setBackgroundsByTheme(int theme) {

        ImageView nameWindow = statisticsView.findViewById(R.id.statisticsWindowUsernameImage);
        ImageView coinsWindow = statisticsView.findViewById(R.id.statisticsWindowCoinsImage);
        ImageView roundsWindow = statisticsView.findViewById(R.id.statisticsWindowRoundsImage);
        ImageView winningsWindow = statisticsView.findViewById(R.id.statisticsWindowWinningsImage);
        ImageView emblemsWindow = statisticsView.findViewById(R.id.statisticsWindowEmblemsImage);
        ImageView debitWindow = statisticsView.findViewById(R.id.statisticsWindowDebitImage);
        ImageView creditWindow = statisticsView.findViewById(R.id.statisticsWindowCreditImage);
        if (theme == 0) {
            nameWindow.setBackground(getResources().getDrawable(R.drawable.targ_window));
            coinsWindow.setBackground(getResources().getDrawable(R.drawable.targ_window));
            roundsWindow.setBackground(getResources().getDrawable(R.drawable.targ_window));
            winningsWindow.setBackground(getResources().getDrawable(R.drawable.targ_window));
            emblemsWindow.setBackground(getResources().getDrawable(R.drawable.targ_window));
            debitWindow.setBackground(getResources().getDrawable(R.drawable.targ_window));
            creditWindow.setBackground(getResources().getDrawable(R.drawable.targ_window));
        }
        if (theme == 1) {
            nameWindow.setBackground(getResources().getDrawable(R.drawable.stark_window));
            coinsWindow.setBackground(getResources().getDrawable(R.drawable.stark_window));
            roundsWindow.setBackground(getResources().getDrawable(R.drawable.stark_window));
            winningsWindow.setBackground(getResources().getDrawable(R.drawable.stark_window));
            emblemsWindow.setBackground(getResources().getDrawable(R.drawable.stark_window));
            debitWindow.setBackground(getResources().getDrawable(R.drawable.stark_window));
            creditWindow.setBackground(getResources().getDrawable(R.drawable.stark_window));
        }
        if (theme == 2) {
            nameWindow.setBackground(getResources().getDrawable(R.drawable.lann_window));
            coinsWindow.setBackground(getResources().getDrawable(R.drawable.lann_window));
            roundsWindow.setBackground(getResources().getDrawable(R.drawable.lann_window));
            winningsWindow.setBackground(getResources().getDrawable(R.drawable.lann_window));
            emblemsWindow.setBackground(getResources().getDrawable(R.drawable.lann_window));
            debitWindow.setBackground(getResources().getDrawable(R.drawable.lann_window));
            creditWindow.setBackground(getResources().getDrawable(R.drawable.lann_window));
        }
        if (theme == 3) {
            nameWindow.setBackground(getResources().getDrawable(R.drawable.night_window));
            coinsWindow.setBackground(getResources().getDrawable(R.drawable.night_window));
            roundsWindow.setBackground(getResources().getDrawable(R.drawable.night_window));
            winningsWindow.setBackground(getResources().getDrawable(R.drawable.night_window));
            emblemsWindow.setBackground(getResources().getDrawable(R.drawable.night_window));
            debitWindow.setBackground(getResources().getDrawable(R.drawable.night_window));
            creditWindow.setBackground(getResources().getDrawable(R.drawable.night_window));
        }
    }

    @Override
    public void showStatistics(String user_name, final long money_GD_temp, final long money_AD_temp, final long money_CP_temp,
                               int number_easy_games, int number_medium_games, int number_hard_games,
                               int number_easy_winnings, int number_medium_winnings, int number_hard_winnings,
                               boolean is_books, boolean is_films, int current_theme,
                               boolean is_skin_targar, boolean is_skin_stark, boolean is_skin_lann, boolean is_skin_night,
                               boolean is_credit, long credit_time, long credit_sum,
                               boolean is_debit, long debit_time, long debit_sum) {

        setBackgroundsByTheme(current_theme);

        EditText user_name_TV = statisticsView.findViewById(R.id.userNameTV);
        ImageView booksFilmsImage = statisticsView.findViewById(R.id.userReadWatchIV);
        TextView user_GD_temp_TV = statisticsView.findViewById(R.id.userGDTempTV);
        final TextView user_AD_temp_TV = statisticsView.findViewById(R.id.userADTempTV);
        final TextView user_CP_temp_TV = statisticsView.findViewById(R.id.userCPTempTV);
        TextView user_rounds_GD_TV = statisticsView.findViewById(R.id.userRoundsGDTV);
        TextView user_rounds_AD_TV = statisticsView.findViewById(R.id.userRoundsADTV);
        TextView user_rounds_CP_TV = statisticsView.findViewById(R.id.userRoundsCPTV);
        TextView user_winnings_GD_TV = statisticsView.findViewById(R.id.userWinningsGDTV);
        TextView user_winnings_AD_TV = statisticsView.findViewById(R.id.userWinningsADTV);
        TextView user_winnings_CP_TV = statisticsView.findViewById(R.id.userWinningsCPTV);
        TextView user_skin_targar_TV = statisticsView.findViewById(R.id.userSkinTargarTV);
        TextView user_skin_stark_TV = statisticsView.findViewById(R.id.userSkinStarksTV);
        TextView user_skin_lann_TV = statisticsView.findViewById(R.id.userSkinLannTV);
        TextView user_skin_night_TV = statisticsView.findViewById(R.id.userSkinNKTV);
        ImageView userSkinTargImage = statisticsView.findViewById(R.id.userSkinTargIV);
        ImageView userSkinStarksImage = statisticsView.findViewById(R.id.userSkinStarkIV);
        ImageView userSkinLannImage = statisticsView.findViewById(R.id.userSkinLannIV);
        ImageView userSkinNKImage = statisticsView.findViewById(R.id.userSkinNKIV);
        final TextView user_debit_value_TV = statisticsView.findViewById(R.id.userDebitValueTV);
        final TextView user_credit_value_TV = statisticsView.findViewById(R.id.userCreditValueTV);
        final long coinsADLong = money_GD_temp * 210 + money_AD_temp;
        final long coinsCPLong = coinsADLong * 56 + money_CP_temp;


        Drawable alertDialogButtonImage1 = null;
        Drawable alertDialogWindowImage1 = null;

        if (current_theme == 0) {
            alertDialogButtonImage1 = getResources().getDrawable(R.drawable.targ_button_selector);
            alertDialogWindowImage1 = getResources().getDrawable(R.drawable.targ_window);
        }
        if (current_theme == 1) {
            alertDialogButtonImage1 = getResources().getDrawable(R.drawable.stark_button_selector);
            alertDialogWindowImage1 = getResources().getDrawable(R.drawable.stark_window);
        }

        if (current_theme == 2) {
            alertDialogButtonImage1 = getResources().getDrawable(R.drawable.lann_button_selector);
            alertDialogWindowImage1 = getResources().getDrawable(R.drawable.lann_window);
        }
        if (current_theme == 3) {
            alertDialogButtonImage1 = getResources().getDrawable(R.drawable.night_button_selector);
            alertDialogWindowImage1 = getResources().getDrawable(R.drawable.night_window);
        }


        final Drawable alertDialogButtonImage = alertDialogButtonImage1;
        final Drawable alertDialogWindowImage = alertDialogWindowImage1;


        ImageView coinsWindow = statisticsView.findViewById(R.id.statisticsWindowCoinsImage);
        coinsWindow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.alertdialog_money_describtion, null);
                builder.setView(dialogView);
                final AlertDialog dialog = builder.create();

                ImageView background = dialogView.findViewById(R.id.alertDialogBackgroundImage);
                background.setBackground(alertDialogWindowImage);

                TextView coinsGD = dialogView.findViewById(R.id.alertUsersGD);
                coinsGD.setText(String.valueOf(money_GD_temp));

                TextView coinsAD = dialogView.findViewById(R.id.alertUsersAD);
                coinsAD.setText(String.valueOf(coinsADLong));

                TextView coinsCP = dialogView.findViewById(R.id.alertUsersCP);
                coinsCP.setText(String.valueOf(coinsCPLong));

                Button closeDialogButton = dialogView.findViewById(R.id.alert_dialog_button);
                closeDialogButton.setBackground(alertDialogButtonImage);
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
        });

        ImageView roundsWindow = statisticsView.findViewById(R.id.statisticsWindowRoundsImage);
        ImageView winningsWindow = statisticsView.findViewById(R.id.statisticsWindowWinningsImage);
        ImageView emblemsWindow = statisticsView.findViewById(R.id.statisticsWindowEmblemsImage);
        ImageView debitWindow = statisticsView.findViewById(R.id.statisticsWindowDebitImage);
        ImageView creditWindow = statisticsView.findViewById(R.id.statisticsWindowCreditImage);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String alertTitle = "Title";
                String alertText = "Text";
                switch (v.getId()) {
                    case R.id.userReadWatchIV:
                        alertTitle = getResources().getString(R.string.booksOrFilms);
                        alertText = getResources().getString(R.string.booksFilmsStatistics);
                        break;
                    case R.id.statisticsWindowRoundsImage:
                        alertTitle = getResources().getString(R.string.rounds);
                        alertText = getResources().getString(R.string.roundsStatistics);
                        break;
                    case R.id.statisticsWindowWinningsImage:
                        alertTitle = getResources().getString(R.string.winnings);
                        alertText = getResources().getString(R.string.winningsStatistics);
                        break;
                    case R.id.statisticsWindowEmblemsImage:
                        alertTitle = getResources().getString(R.string.emblem);
                        alertText = getResources().getString(R.string.emblemsStatistics);
                        break;
                    case R.id.statisticsWindowDebitImage:
                        alertTitle = getResources().getString(R.string.debit);
                        alertText = getResources().getString(R.string.debitInfo);
                        break;
                    case R.id.statisticsWindowCreditImage:
                        alertTitle = getResources().getString(R.string.credit);
                        alertText = getResources().getString(R.string.creditInfo);
                        break;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();

                View dialogView = inflater.inflate(R.layout.alertdialog_text_message, null);
                builder.setView(dialogView);
                final AlertDialog dialog = builder.create();

                ImageView background = dialogView.findViewById(R.id.alertDialogBackgroundImage);
                background.setBackground(alertDialogWindowImage);

                Button closeDialogButton = dialogView.findViewById(R.id.alert_dialog_button);
                closeDialogButton.setBackground(alertDialogButtonImage);

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
        };
        roundsWindow.setOnClickListener(onClickListener);
        winningsWindow.setOnClickListener(onClickListener);
        emblemsWindow.setOnClickListener(onClickListener);
        booksFilmsImage.setOnClickListener(onClickListener);
        debitWindow.setOnClickListener(onClickListener);
        creditWindow.setOnClickListener(onClickListener);


        user_name_TV.setText(user_name);
        names[0] = user_name;
        names[1] = user_name;
        user_GD_temp_TV.setText(String.valueOf(money_GD_temp));
        user_AD_temp_TV.setText(String.valueOf(money_AD_temp));
        user_CP_temp_TV.setText(String.valueOf(money_CP_temp));
        user_rounds_GD_TV.setText(String.valueOf(number_hard_games));
        user_rounds_AD_TV.setText(String.valueOf(number_medium_games));
        user_rounds_CP_TV.setText(String.valueOf(number_easy_games));
        user_winnings_GD_TV.setText(String.valueOf(number_hard_winnings));
        user_winnings_AD_TV.setText(String.valueOf(number_medium_winnings));
        user_winnings_CP_TV.setText(String.valueOf(number_easy_winnings));

        user_name_TV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                names[1] = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        if (is_skin_targar) {
            userSkinTargImage.setBackground(getResources().getDrawable(R.drawable.logo_dragon_yes));
            if (current_theme == 0) {
                userSkinTargImage.setBackground(getResources().getDrawable(R.drawable.logo_dragon_en));
            }
        } else {
            user_skin_targar_TV.setText(R.string.unavailable);
        }
        if (is_skin_stark) {
            userSkinStarksImage.setBackground(getResources().getDrawable(R.drawable.logo_wolf_yes));
            if (current_theme == 1) {
                userSkinStarksImage.setBackground(getResources().getDrawable(R.drawable.logo_wolf_en));
            }
        } else {
            user_skin_stark_TV.setText(R.string.unavailable);
        }
        if (is_skin_lann) {
            userSkinLannImage.setBackground(getResources().getDrawable(R.drawable.logo_lion_yes));
            if (current_theme == 2) {
                userSkinLannImage.setBackground(getResources().getDrawable(R.drawable.logo_lion_en));
            }
        } else {
            user_skin_lann_TV.setText(R.string.unavailable);
        }
        if (is_skin_night) {
            userSkinNKImage.setBackground(getResources().getDrawable(R.drawable.logo_nk_yes));
            if (current_theme == 3) {
                userSkinNKImage.setBackground(getResources().getDrawable(R.drawable.logo_nk_en));
            }
        } else {
            user_skin_night_TV.setText(R.string.unavailable);
        }
        if (is_debit) {
            long coins[] = CoinConversation.coins_GD_AD_CP(debit_sum);
            ImageView GD = statisticsView.findViewById(R.id.userDebitGDImage);
            ImageView AD = statisticsView.findViewById(R.id.userDebitADImage);
            ImageView CP = statisticsView.findViewById(R.id.userDebitCPImage);
            TextView debitGD = statisticsView.findViewById(R.id.userDebitGDTV);
            TextView debitAD = statisticsView.findViewById(R.id.userDebitADTV);
            TextView debitCP = statisticsView.findViewById(R.id.userDebitCPTV);
            debitGD.setText(String.valueOf((int) coins[0]));
            debitAD.setText(String.valueOf((int) coins[1]));
            debitCP.setText(String.valueOf((int) coins[2]));
            debitGD.setVisibility(View.VISIBLE);
            debitAD.setVisibility(View.VISIBLE);
            debitCP.setVisibility(View.VISIBLE);
            GD.setVisibility(View.VISIBLE);
            AD.setVisibility(View.VISIBLE);
            CP.setVisibility(View.VISIBLE);

        } else {
            user_debit_value_TV.setText(getResources().getString(R.string.noDebit));
            user_debit_value_TV.setVisibility(View.VISIBLE);

        }
        if (is_credit) {
            long coins[] = CoinConversation.coins_GD_AD_CP(credit_sum);
            ImageView GD = statisticsView.findViewById(R.id.userCreditGDImage);
            ImageView AD = statisticsView.findViewById(R.id.userCreditADImage);
            ImageView CP = statisticsView.findViewById(R.id.userCreditCPImage);
            TextView creditGD = statisticsView.findViewById(R.id.userCreditGDTV);
            TextView creditAD = statisticsView.findViewById(R.id.userCreditADTV);
            TextView creditCP = statisticsView.findViewById(R.id.userCreditCPTV);
            creditGD.setText(String.valueOf((int) coins[0]));
            creditAD.setText(String.valueOf((int) coins[1]));
            creditCP.setText(String.valueOf((int) coins[2]));
            creditGD.setVisibility(View.VISIBLE);
            creditAD.setVisibility(View.VISIBLE);
            creditCP.setVisibility(View.VISIBLE);
            GD.setVisibility(View.VISIBLE);
            AD.setVisibility(View.VISIBLE);
            CP.setVisibility(View.VISIBLE);
        } else {
            user_credit_value_TV.setText(getResources().getString(R.string.noCredit));
            user_credit_value_TV.setVisibility(View.VISIBLE);
        }

        int theme = UserDataSingleton.getInstance().getCurrent_theme();
        Drawable[] knowleges = new Drawable[3];

        if (theme == 0) {
            knowleges[0] = getResources().getDrawable(R.drawable.targ_books_films_pressed);
            knowleges[1] = getResources().getDrawable(R.drawable.targ_books_pressed);
            knowleges[2] = getResources().getDrawable(R.drawable.targ_films_pressed);
        }

        if (theme == 1) {
            knowleges[0] = getResources().getDrawable(R.drawable.stark_books_films_pressed);
            knowleges[1] = getResources().getDrawable(R.drawable.stark_books_pressed);
            knowleges[2] = getResources().getDrawable(R.drawable.stark_films_pressed);
        }

        if (theme == 2) {
            knowleges[0] = getResources().getDrawable(R.drawable.lann_books_films_pressed);
            knowleges[1] = getResources().getDrawable(R.drawable.lann_books_pressed);
            knowleges[2] = getResources().getDrawable(R.drawable.lann_films_pressed);
        }

        if (theme == 3) {
            knowleges[0] = getResources().getDrawable(R.drawable.night_books_films_pressed);
            knowleges[1] = getResources().getDrawable(R.drawable.night_books_pressed);
            knowleges[2] = getResources().getDrawable(R.drawable.night_films_pressed);
        }

        if (is_books) {
            if (is_films) {
                booksFilmsImage.setBackground(knowleges[0]);
            } else {
                booksFilmsImage.setBackground(knowleges[1]);
            }
        } else {
            booksFilmsImage.setBackground(knowleges[2]);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!names[0].equals(names[1])) {
            statisticsPresenter.changeName(names[1]);
        }
    }
}
