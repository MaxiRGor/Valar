package harelchuk.maxim.quizwithmoxy;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface InPlayView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showQuestion(int questionsToTheEnd, String question, String a1,
                      String a2, String a3, String a4);

    void userWin();

    void userLose(int answered);


}
