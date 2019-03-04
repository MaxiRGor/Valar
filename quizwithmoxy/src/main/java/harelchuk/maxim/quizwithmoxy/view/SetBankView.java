package harelchuk.maxim.quizwithmoxy.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface SetBankView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showDebit(boolean is_debit, int time_to_increase,
                   int debit_GD, int debit_AD, int debit_CP, int user_money);
}
