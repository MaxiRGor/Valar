package harelchuk.maxim.quizwithmoxy;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface SettingsView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void show();
}
