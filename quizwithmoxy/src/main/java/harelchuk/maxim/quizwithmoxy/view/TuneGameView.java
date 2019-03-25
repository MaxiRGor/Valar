package harelchuk.maxim.quizwithmoxy.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.ArrayList;
import java.util.Map;

import harelchuk.maxim.quizwithmoxy.model.DataAdapter;


public interface TuneGameView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)

    void fillLevelList(ArrayList<Map<String, Integer>> data, int[] costs);

    void fillCoins(long[] coins_GAC);

}
