package harelchuk.maxim.quizwithmoxy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private TextView questionCategory;
    private TextView coinsGD;
    private TextView coinsAD;
    private TextView coinsCP;
    private ImageView coinGDImage;
    private ImageView coinADImage;
    private ImageView coinCPImage;
    private ImageView currentQuestionImage;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_play_empty_frame);
    }


    @Override
    public void showQuestion(int questionsToTheEnd, String question, String a1, String a2, String a3, String a4, String category) {

        questionCategory.setText(category);

        int currentQuestion = 1 + 7 - questionsToTheEnd;
        currentQuestionImage = findViewById(R.id.inPlayQuestionImage1);
        switch (currentQuestion) {
            case 2:
                currentQuestionImage = findViewById(R.id.inPlayQuestionImage2);
                break;
            case 3:
                currentQuestionImage = findViewById(R.id.inPlayQuestionImage3);
                break;
            case 4:
                currentQuestionImage = findViewById(R.id.inPlayQuestionImage4);
                break;
            case 5:
                currentQuestionImage = findViewById(R.id.inPlayQuestionImage5);
                break;
            case 6:
                currentQuestionImage = findViewById(R.id.inPlayQuestionImage6);
                break;
            case 7:
                currentQuestionImage = findViewById(R.id.inPlayQuestionImage7);
                break;
        }
        currentQuestionImage.setVisibility(View.VISIBLE);

        viewGroup.removeAllViews();
        View questionView = LayoutInflater.from(this).inflate(R.layout.in_play_question, viewGroup, false);
        viewGroup.addView(questionView);

        questionView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_bottom_to_top));

        TextView qTTV = questionView.findViewById(R.id.questionTextTV);
        TextView a1TV = questionView.findViewById(R.id.answer1TV);
        TextView a2TV = questionView.findViewById(R.id.answer2TV);
        TextView a3TV = questionView.findViewById(R.id.answer3TV);
        TextView a4TV = questionView.findViewById(R.id.answer4TV);

        qTTV.setText(question);
        a1TV.setText(a1);
        a2TV.setText(a2);
        a3TV.setText(a3);
        a4TV.setText(a4);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().
                        load(R.drawable.ic_logo_dragon_yes)
                        .fit()
                        .placeholder(R.drawable.blackscreen)
                        .into(currentQuestionImage);
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
        ViewGroup emptyView = findViewById(R.id.in_play_empty_frame);
        emptyView.removeAllViews();
        View userWin = LayoutInflater.from(this).inflate(R.layout.in_play_user_win, emptyView, false);
        ImageView backgroundIV = userWin.findViewById(R.id.userWinBackgroundIV);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };
        backgroundIV.setOnClickListener(onClickListener);
        Picasso.get().
                load(R.drawable.background_targ)
                .fit()
                .placeholder(R.drawable.blackscreen)
                .into(backgroundIV);
        coinsGD = userWin.findViewById(R.id.userWinCoinsGD);
        coinsAD = userWin.findViewById(R.id.userWinCoinsAD);
        coinsCP = userWin.findViewById(R.id.userWinCoinsCP);
        coinGDImage = userWin.findViewById(R.id.endGameGDImage);
        coinADImage = userWin.findViewById(R.id.endGameADImage);
        coinCPImage = userWin.findViewById(R.id.endGameCPImage);
        emptyView.addView(userWin);
        inPlayPresenter.sendInfoToUserStat();
    }

    @Override
    public void userLose(int answered) {
        ViewGroup emptyView = findViewById(R.id.in_play_empty_frame);
        emptyView.removeAllViews();
        View userLose;
        userLose = LayoutInflater.from(this).inflate(R.layout.in_play_user_lose, emptyView, false);

        ImageView backgroundIV = userLose.findViewById(R.id.userLoseBackgroundIV);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };
        backgroundIV.setOnClickListener(onClickListener);
        //backgroundIV.setVisibility(View.VISIBLE);
        Picasso.get()
                .load(R.drawable.background_targ)
                .fit()
                .placeholder(R.drawable.blackscreen)
                .into(backgroundIV);

        TextView answeredTV = userLose.findViewById(R.id.loseAnsweredTV);
        answeredTV.setText(String.valueOf(answered));
        coinsGD = userLose.findViewById(R.id.userLoseCoinsGD);
        coinsAD = userLose.findViewById(R.id.userLoseCoinsAD);
        coinsCP = userLose.findViewById(R.id.userLoseCoinsCP);
        coinGDImage = userLose.findViewById(R.id.endGameGDImage);
        coinADImage = userLose.findViewById(R.id.endGameADImage);
        coinCPImage = userLose.findViewById(R.id.endGameCPImage);
        emptyView.addView(userLose);
        inPlayPresenter.sendInfoToUserStat();
    }

    @Override
    public void showAddedScore(int coinGD, int coinAD, int coinCP) {
        coinsGD.setText(String.valueOf(coinGD));
        if (coinGD == 0) {
            coinsGD.setVisibility(View.INVISIBLE);
            coinGDImage.setVisibility(View.INVISIBLE);
        }
        coinsAD.setText(String.valueOf(coinAD));
        if (coinAD == 0) {
            coinsAD.setVisibility(View.INVISIBLE);
            coinADImage.setVisibility(View.INVISIBLE);
        }
        coinsCP.setText(String.valueOf(coinCP));
        if (coinCP == 0) {
            coinsCP.setVisibility(View.INVISIBLE);
            coinCPImage.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void findElements() {
        viewGroup = findViewById(R.id.frameQuestionLayout);
        TextView questionLevelTV = findViewById(R.id.inPlayLevelTV);
        questionCategory = findViewById(R.id.inPlayCategoryTV);
        ImageView backgroundImage = findViewById(R.id.inPlayBackgroundIV);
        ImageView categoryImage = findViewById(R.id.inPlayCategoryIV);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Picasso.get()
                .load(R.drawable.background_targ)
                .fit()
                .placeholder(R.drawable.blackscreen)
                .into(backgroundImage);
        questionLevelTV.setText(String.format("Level = %s", String.valueOf(sharedPreferences.getInt("level", 0) + 1)));
    }

    @Override
    public void showFailure() {
        ViewGroup emptyView = findViewById(R.id.in_play_empty_frame);
        emptyView.removeAllViews();
        View userLose;
        userLose = LayoutInflater.from(this).inflate(R.layout.in_play_user_lose, emptyView, false);

        ImageView backgroundIV = userLose.findViewById(R.id.userLoseBackgroundIV);
        //backgroundIV.setVisibility(View.VISIBLE);
        Picasso.get()
                .load(R.drawable.background_targ)
                .fit()
                .placeholder(R.drawable.blackscreen)
                .into(backgroundIV);

        TextView answeredTV = userLose.findViewById(R.id.loseAnsweredTV);
        TextView notConnectedTV = userLose.findViewById(R.id.loseTV);
        notConnectedTV.setText(getResources().getString(R.string.notConnected));
        coinGDImage = userLose.findViewById(R.id.endGameGDImage);
        coinADImage = userLose.findViewById(R.id.endGameADImage);
        coinCPImage = userLose.findViewById(R.id.endGameCPImage);
        answeredTV.setText(String.valueOf(0));
        coinsGD = userLose.findViewById(R.id.userLoseCoinsGD);
        coinsAD = userLose.findViewById(R.id.userLoseCoinsAD);
        coinsCP = userLose.findViewById(R.id.userLoseCoinsCP);
        emptyView.addView(userLose);
        inPlayPresenter.sendFailToUserStat();
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