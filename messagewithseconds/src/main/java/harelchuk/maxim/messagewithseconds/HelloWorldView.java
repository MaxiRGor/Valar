package harelchuk.maxim.messagewithseconds;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface HelloWorldView extends MvpView {
    void showTimer();
    void hideTimer();
    void setTimer(int seconds);
    void showMessage(int message);
    void hideMessage();
}
