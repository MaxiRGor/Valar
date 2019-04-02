package harelchuk.maxim.morgulis.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface SetEmblemView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void fillEmblems(int theme, boolean is_targar, boolean is_starks,boolean is_lann,boolean is_night);
}
