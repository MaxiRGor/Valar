package harelchuk.maxim.quizwithmoxy.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface SetBankView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showDebit(boolean is_debit, long time_to_increase,
                   long debit_GD, long debit_AD, long debit_CP, long user_money);
}
