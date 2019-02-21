package harelchuk.maxim.quizwithmoxy;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.picasso.Picasso;

import harelchuk.maxim.quizwithmoxy.presenter.InPlayPresenter;
import harelchuk.maxim.quizwithmoxy.view.InPlayView;

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

    private Animation animation;

    ImageView imageView;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_play_empty_frame);
    }




    @Override
    public void showQuestion(int questionsToTheEnd, String question, String a1, String a2, String a3, String a4, String category) {

        questionsToEndTV.setText(String.valueOf(questionsToTheEnd));
        questionCategory.setText(category);

        viewGroup.removeAllViews();
        questionView = LayoutInflater.from(this).inflate(R.layout.in_play_question, viewGroup, false);
        //questionView.setVisibility(View.INVISIBLE);
        //questionView.setAlpha(0.0f);
        viewGroup.addView(questionView);

        questionView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_up));

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


        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_down);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case (R.id.answer1TV):
                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                inPlayPresenter.checkAnswer(1);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
                        a1TV.startAnimation(animation);
                        break;
                    case (R.id.answer2TV):
                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                inPlayPresenter.checkAnswer(2);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
                        a2TV.startAnimation(animation);
                        break;
                    case (R.id.answer3TV):

                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                inPlayPresenter.checkAnswer(3);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
                        a3TV.startAnimation(animation);
                        break;
                    case (R.id.answer4TV):

                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                inPlayPresenter.checkAnswer(4);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
                        a4TV.startAnimation(animation);
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

    @Override
    public void findElements() {

        viewGroup = findViewById(R.id.frameQuestionLayout);
        questionThemeTV = findViewById(R.id.themeTV);
        questionCategory = findViewById(R.id.categoryTV);
        questionsToEndTV = findViewById(R.id.quetionsToEndTV);
        imageView = findViewById(R.id.imageViewInPlay);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        questionThemeTV.setText(String.format("Level = %s", String.valueOf(sharedPreferences.getInt("level", 0) + 1)));

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        imageView = findViewById(R.id.imageViewInPlay);
        Picasso.get().
                load(R.drawable.backgr_targar1280)
                .resize(width, height)
                .placeholder(R.drawable.blackscreen)
                .into(imageView);
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