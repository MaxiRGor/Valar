package harelchuk.maxim.quizwithmoxy.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface InPlayView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showQuestion(int questionsToTheEnd, String question, String a1,
                      String a2, String a3, String a4, int category, int level, boolean in_book, boolean in_serial);

    void userWin();
    void userLose(int answered);

    void showAddedScore(int coinGD, int coinAD, int coinCP);

    void showFailure();
}
