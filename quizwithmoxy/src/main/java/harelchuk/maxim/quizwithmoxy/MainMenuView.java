package harelchuk.maxim.quizwithmoxy;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


public interface MainMenuView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void goToGame();

    void goToStatistics();

    void goToSettings();
}
