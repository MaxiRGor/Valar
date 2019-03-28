package harelchuk.maxim.quizwithmoxy.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface StatisticsView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)

    void showStatistics(String user_name, long money_GD_temp, long money_AD_temp, long money_CP_temp,
                        int number_easy_games, int number_medium_games, int number_hard_games,
                        int number_easy_winnings, int number_medium_winnings, int number_hard_winnings,
                        boolean is_books, boolean is_films,
                        int current_theme, boolean is_skin_targar, boolean is_skin_stark,
                        boolean is_skin_lann, boolean is_skin_night,
                        boolean is_credit, long credit_time, long credit_sum,
                        boolean is_debit, long debit_time, long debit_sum);
}
