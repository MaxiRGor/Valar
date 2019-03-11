package harelchuk.maxim.quizwithmoxy.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


public interface TuneGameView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)

    void fillLevelList(int[] levels, int[] costs, int[] reward);

    void fillCoins(long[] coins_GAC);

}
