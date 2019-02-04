package harelchuk.maxim.quizwithmoxy;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


public interface SetGameView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void chooseBook();

    void chooseSeries();

    void chooseLevel(int[] levels, int[] numberOfQuestions);

    void chooseCategory();
}
