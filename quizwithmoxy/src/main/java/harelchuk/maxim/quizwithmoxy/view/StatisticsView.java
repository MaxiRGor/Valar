package harelchuk.maxim.quizwithmoxy.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface StatisticsView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)

    void showStatistics(String user_name, int money_GD_temp, int money_AD_temp, int money_CP_temp,
                        int money_GD_all, int money_AD_all, int money_CP_all,
                        int number_easy_games, int number_medium_games, int number_hard_games,
                        int number_easy_winnings, int number_medium_winnings, int number_hard_winnings,
                        boolean is_books, boolean is_films,
                        boolean is_skin_targar, boolean is_skin_stark, boolean is_skin_lann, boolean is_skin_night,
                        boolean is_credit, long credit_time, int credit_sum,
                        boolean is_debit, long debit_time, int debit_sum);
}
