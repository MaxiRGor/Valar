package harelchuk.maxim.quizwithmoxy.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import harelchuk.maxim.quizwithmoxy.R;
import harelchuk.maxim.quizwithmoxy.presenter.StatisticsPresenter;
import harelchuk.maxim.quizwithmoxy.view.StatisticsView;


public class StatisticsFragment extends MvpAppCompatFragment implements StatisticsView {

    @InjectPresenter
    StatisticsPresenter statisticsPresenter;

    private ViewGroup mainConteinerVG;
    private View statisticsView;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainConteinerVG = getActivity().findViewById(R.id.main_container);
        statisticsView = inflater.inflate(R.layout.statistics, mainConteinerVG, false);
        return statisticsView;
    }

    @Override
    public void showStatistics(String user_name, int level, int score, int number_of_answers, int number_of_games, int percent_of_right) {
        //setContentView(R.layout.statistics);
        //statisticsView = findViewById(R.id.statisticsID);

        userNameTV = statisticsView.findViewById(R.id.userNameTV);
        levelTV = statisticsView.findViewById(R.id.levelTV);
        scoreTV = statisticsView.findViewById(R.id.scoreTV);
        numberOfAnswersTV = statisticsView.findViewById(R.id.numberOfAnswersTV);
        numberOfGamesTV = statisticsView.findViewById(R.id.numberOfGamesTV);
        percentOfRightTV = statisticsView.findViewById(R.id.percentOfRightTV);

        userNameTV.setText(user_name);
        levelTV.setText(String.valueOf(level));
        scoreTV.setText(String.valueOf(score));
        numberOfAnswersTV.setText(String.valueOf(number_of_answers));
        numberOfGamesTV.setText(String.valueOf(number_of_games));
        percentOfRightTV.setText(String.valueOf(percent_of_right));

    }
}
