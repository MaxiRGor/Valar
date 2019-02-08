package harelchuk.maxim.quizwithmoxy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class InPlayActivity extends MvpAppCompatActivity implements InPlayView {

    @InjectPresenter
    InPlayPresenter inPlayPresenter;

    private ViewGroup viewGroup;
    private View questionView;

    private TextView questionThemeTV;
    private TextView questionCategory;
    private TextView questionsToEndTV;
    private TextView scoreAddedTV;

    private TextView qTTV;
    private TextView a1TV;
    private TextView a2TV;
    private TextView a3TV;
    private TextView a4TV;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_play_empty_frame);

        viewGroup = findViewById(R.id.frameQuestionLayout);
        questionThemeTV = findViewById(R.id.themeTV);
        questionCategory = findViewById(R.id.categoryTV);
        questionsToEndTV = findViewById(R.id.quetionsToEndTV);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        questionThemeTV.setText(String.format("Level = %s", String.valueOf(sharedPreferences.getInt("level", 0) + 1)));

    }


    @Override
    public void showQuestion(int questionsToTheEnd, String question, String a1, String a2, String a3, String a4, String category) {

        questionsToEndTV.setText(String.valueOf(questionsToTheEnd));
        questionCategory.setText(category);

        viewGroup.removeAllViews();
        questionView = LayoutInflater.from(this).inflate(R.layout.in_play_question, viewGroup, false);
        viewGroup.addView(questionView);
        qTTV = questionView.findViewById(R.id.questionTextTV);
        a1TV = questionView.findViewById(R.id.answer1TV);
        a2TV = questionView.findViewById(R.id.answer2TV);
        a3TV = questionView.findViewById(R.id.answer3TV);
        a4TV = questionView.findViewById(R.id.answer4TV);

        qTTV.setText(question);
        a1TV.setText(a1);
        a2TV.setText(a2);
        a3TV.setText(a3);
        a4TV.setText(a4);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case (R.id.answer1TV):
                        inPlayPresenter.checkAnswer(1);
                        break;
                    case (R.id.answer2TV):
                        inPlayPresenter.checkAnswer(2);
                        break;
                    case (R.id.answer3TV):
                        inPlayPresenter.checkAnswer(3);
                        break;
                    case (R.id.answer4TV):
                        inPlayPresenter.checkAnswer(4);
                        break;
                }
            }
        };
        a1TV.setOnClickListener(onClickListener);
        a2TV.setOnClickListener(onClickListener);
        a3TV.setOnClickListener(onClickListener);
        a4TV.setOnClickListener(onClickListener);
    }

    @Override
    public void userWin() {
        viewGroup.removeAllViews();
        questionView = LayoutInflater.from(this).inflate(R.layout.in_play_user_win, viewGroup, false);
        scoreAddedTV = questionView.findViewById(R.id.scoreAddedWinTV);
        viewGroup.addView(questionView);
    }

    @Override
    public void userLose(int answered) {
        viewGroup.removeAllViews();
        questionView = LayoutInflater.from(this).inflate(R.layout.in_play_user_lose, viewGroup, false);
        TextView answeredTV = questionView.findViewById(R.id.loseAnsweredTV);
        answeredTV.setText(String.valueOf(answered));
        scoreAddedTV = questionView.findViewById(R.id.scoreAddedLoseTV);
        viewGroup.addView(questionView);
    }

    @Override
    public void showAddedScore(int score) {
        scoreAddedTV.setText(String.valueOf(score));
    }


}


//====================================================================================================================





/*
    public void checkAnswer(int userAnswer){


        questionCategory.setText(String.valueOf(userAnswer));
        questionsToEndTV.setText(String.valueOf(questionsToEnd));
        if(questionsToEnd>0) showQuestion();
        else{
            Toast.makeText(InPlayActivity.this,"GameEnded",Toast.LENGTH_SHORT).show();
        }
    }
    */
//@InjectViewState