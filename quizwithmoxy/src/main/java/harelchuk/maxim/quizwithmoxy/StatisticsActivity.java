package harelchuk.maxim.quizwithmoxy;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;


public class StatisticsActivity extends MvpAppCompatActivity implements StatisticsView {

    @InjectPresenter
    StatisticsPresenter statisticsPresenter;

    private ViewGroup viewGroup;
    private TextView userNameTV;
    private TextView levelTV;
    private TextView scoreTV;
    private TextView numberOfAnswersTV;
    private TextView numberOfGamesTV;
    private TextView percentOfRightTV;


    /*
    level 1 =  from 0  to 100
    level 2 100 to 300
    level 3 300 600
    level 4 600 1000
    level 5 1000 ++

     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void showStatistics(String user_name, int level, int score, int number_of_answers, int number_of_games, int percent_of_right) {
        setContentView(R.layout.statistics);
        viewGroup = findViewById(R.id.statisticsID);

        userNameTV = viewGroup.findViewById(R.id.userNameTV);
        levelTV = viewGroup.findViewById(R.id.levelTV);
        scoreTV = viewGroup.findViewById(R.id.scoreTV);
        numberOfAnswersTV = viewGroup.findViewById(R.id.numberOfAnswersTV);
        numberOfGamesTV = viewGroup.findViewById(R.id.numberOfGamesTV);
        percentOfRightTV = viewGroup.findViewById(R.id.percentOfRightTV);

        userNameTV.setText(user_name);
        levelTV.setText(String.valueOf(level));
        scoreTV.setText(String.valueOf(score));
        numberOfAnswersTV.setText(String.valueOf(number_of_answers));
        numberOfGamesTV.setText(String.valueOf(number_of_games));
        percentOfRightTV.setText(String.valueOf(percent_of_right));

    }
}
