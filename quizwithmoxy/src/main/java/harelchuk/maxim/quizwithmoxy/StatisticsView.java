package harelchuk.maxim.quizwithmoxy;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface StatisticsView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showStatistics(String user_name, int level, int score, int number_of_answers,
                        int number_of_games, int percent_of_right);
}
