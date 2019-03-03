package harelchuk.maxim.quizwithmoxy.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import harelchuk.maxim.quizwithmoxy.R;
import harelchuk.maxim.quizwithmoxy.presenter.StatisticsPresenter;
import harelchuk.maxim.quizwithmoxy.view.StatisticsView;


public class StatisticsFragment extends MvpAppCompatFragment implements StatisticsView {

    @InjectPresenter
    StatisticsPresenter statisticsPresenter;

    private View statisticsView;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup mainContainerVG = getActivity().findViewById(R.id.main_container);
        statisticsView = inflater.inflate(R.layout.statistics_new, mainContainerVG, false);
        return statisticsView;
    }

    @Override
    public void showStatistics(String user_name, int money_GD_temp, int money_AD_temp, int money_CP_temp,
                               int money_GD_all, int money_AD_all, int money_CP_all,
                               int number_easy_games, int number_medium_games, int number_hard_games,
                               int number_easy_winnings, int number_medium_winnings, int number_hard_winnings,
                               boolean is_books, boolean is_films,
                               boolean is_skin_targar, boolean is_skin_stark, boolean is_skin_lann, boolean is_skin_night,
                               boolean is_credit, long credit_time, int credit_sum,
                               boolean is_debit, long debit_time, int debit_sum) {
        TextView user_name_TV = statisticsView.findViewById(R.id.userNameTV);
        ImageView user_read_watch_TV = statisticsView.findViewById(R.id.userReadWatchIV);
        TextView user_GD_temp_TV = statisticsView.findViewById(R.id.userGDTempTV);
        TextView user_GD_all_TV = statisticsView.findViewById(R.id.userGDAllTV);
        TextView user_AD_temp_TV = statisticsView.findViewById(R.id.userADTempTV);
        TextView user_AD_all_TV = statisticsView.findViewById(R.id.userADAllTV);
        TextView user_CP_temp_TV = statisticsView.findViewById(R.id.userCPTempTV);
        TextView user_CP_all_TV = statisticsView.findViewById(R.id.userCPAllTV);
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
        TextView user_debit_value_TV = statisticsView.findViewById(R.id.userDebitValueTV);
        TextView user_credit_value_TV = statisticsView.findViewById(R.id.userCreditValueTV);
        user_name_TV.setText(user_name);
        user_GD_temp_TV.setText(String.valueOf(money_GD_temp));
        user_AD_temp_TV.setText(String.valueOf(money_AD_temp));
        user_CP_temp_TV.setText(String.valueOf(money_CP_temp));
        user_GD_all_TV.setText(String.valueOf(money_GD_all));
        user_AD_all_TV.setText(String.valueOf(money_AD_all));
        user_CP_all_TV.setText(String.valueOf(money_CP_all));
        user_rounds_GD_TV.setText(String.valueOf(number_hard_games));
        user_rounds_AD_TV.setText(String.valueOf(number_medium_games));
        user_rounds_CP_TV.setText(String.valueOf(number_easy_games));
        user_winnings_GD_TV.setText(String.valueOf(number_hard_winnings));
        user_winnings_AD_TV.setText(String.valueOf(number_medium_winnings));
        user_winnings_CP_TV.setText(String.valueOf(number_easy_winnings));
        if(is_skin_targar) {
            user_skin_targar_TV.setText(getResources().getString(R.string.targariens));
        } else {
            user_skin_targar_TV.setText(getResources().getString(R.string.unavailable));
        }
        if(is_skin_stark) {
            user_skin_stark_TV.setText(getResources().getString(R.string.starks));
        } else {
            user_skin_stark_TV.setText(getResources().getString(R.string.unavailable));
        }
        if(is_skin_lann) {
            user_skin_lann_TV.setText(getResources().getString(R.string.lannisters));
        } else {
            user_skin_lann_TV.setText(getResources().getString(R.string.unavailable));
        }
        if(is_skin_night) {
            user_skin_night_TV.setText(getResources().getString(R.string.nightKing));
        } else {
            user_skin_night_TV.setText(getResources().getString(R.string.unavailable));
        }
        if(is_debit) {
            user_debit_value_TV.setText(String.valueOf(debit_sum));
        } else {
            user_debit_value_TV.setText(getResources().getString(R.string.noDebit));
        }
        if(is_credit) {
            user_credit_value_TV.setText(String.valueOf(credit_sum));
        } else {
            user_credit_value_TV.setText(getResources().getString(R.string.noCredit));
        }
    }

}
