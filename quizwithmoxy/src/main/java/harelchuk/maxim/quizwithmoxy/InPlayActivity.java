package harelchuk.maxim.quizwithmoxy;

import android.os.Bundle;
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

import harelchuk.maxim.quizwithmoxy.model.UserDataSingleton;
import harelchuk.maxim.quizwithmoxy.presenter.InPlayPresenter;
import harelchuk.maxim.quizwithmoxy.view.InPlayView;

public class InPlayActivity extends MvpAppCompatActivity implements InPlayView {

    @InjectPresenter
    InPlayPresenter inPlayPresenter;

    private ViewGroup viewGroupQuestionFrame;
    private TextView coinsGD;
    private TextView coinsAD;
    private TextView coinsCP;
    private ImageView coinGDImage;
    private ImageView coinADImage;
    private ImageView coinCPImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_play_empty_frame);
    }

    @Override
    public void findElement() {
        viewGroupQuestionFrame = findViewById(R.id.frameQuestionLayout);
        ImageView backgroundImage = findViewById(R.id.inPlayBackgroundIV);
        Picasso.get()
                .load(R.drawable.background_targ)
                .fit()
                .placeholder(R.drawable.blackscreen)
                .into(backgroundImage);
    }

    @Override
    public void showQuestion(int questionsToTheEnd, String question, String a1, String a2, String a3, String a4, int category,
                             int level, boolean inBook, boolean inSerial) {

        TextView questionLevelTV = findViewById(R.id.inPlayLevelTV);
        TextView questionCategoryTV = findViewById(R.id.inPlayCategoryTV);
        String[] categories = getResources().getStringArray(R.array.categories);
        ImageView bookFilmImage = findViewById(R.id.inPlayBookFilmImage);
        ImageView currentQuestionImage = findViewById(R.id.inPlayQuestionImage1);
        setQuestionCategoryImage(category);

        questionLevelTV.setText(String.format(getResources().getString(R.string.level) + " = %s", level));
        questionCategoryTV.setText(categories[category]);

        if (inBook) {
            if (inSerial) {
                bookFilmImage.setBackground(getResources().getDrawable(R.drawable.ic_set_books_films_red));
            } else
                bookFilmImage.setBackground(getResources().getDrawable(R.drawable.ic_set_books_red));
        } else
            bookFilmImage.setBackground(getResources().getDrawable(R.drawable.ic_set_films_red));

        bookFilmImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.from_left_to_center));

        int currentQuestion = 1 + 7 - questionsToTheEnd;
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

        viewGroupQuestionFrame.removeAllViews();
        View questionView = LayoutInflater.from(this).inflate(R.layout.in_play_question, viewGroupQuestionFrame, false);
        viewGroupQuestionFrame.addView(questionView);

        questionView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_top_to_center));

        final TextView qTTV = questionView.findViewById(R.id.questionTextTV);
        final TextView a1TV = questionView.findViewById(R.id.answer1TV);
        final TextView a2TV = questionView.findViewById(R.id.answer2TV);
        final TextView a3TV = questionView.findViewById(R.id.answer3TV);
        final TextView a4TV = questionView.findViewById(R.id.answer4TV);

        qTTV.setText(question);
        a1TV.setText(a1);
        a2TV.setText(a2);
        a3TV.setText(a3);
        a4TV.setText(a4);

        final ImageView finalCurrentQuestionImage = currentQuestionImage;
        View.OnClickListener onClickListener = new View.OnClickListener() {
            final Animation animationConstriction = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.constriction);
            final Animation animationFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

            @Override
            public void onClick(View v) {
                Picasso.get()
                        .load(R.drawable.ic_logo_dragon_yes)
                        .fit()
                        .placeholder(R.drawable.blackscreen)
                        .into(finalCurrentQuestionImage);
                switch (v.getId()) {
                    case (R.id.answer1TV):
                        animationConstriction.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                inPlayPresenter.checkAnswer(1);
                                qTTV.setVisibility(View.INVISIBLE);
                                a2TV.setVisibility(View.INVISIBLE);
                                a3TV.setVisibility(View.INVISIBLE);
                                a4TV.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
                        a1TV.startAnimation(animationConstriction);
                        qTTV.startAnimation(animationFadeOut);
                        a2TV.startAnimation(animationFadeOut);
                        a3TV.startAnimation(animationFadeOut);
                        a4TV.startAnimation(animationFadeOut);
                        break;

                    case (R.id.answer2TV):
                        animationConstriction.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                inPlayPresenter.checkAnswer(2);
                                qTTV.setVisibility(View.INVISIBLE);
                                a1TV.setVisibility(View.INVISIBLE);
                                a3TV.setVisibility(View.INVISIBLE);
                                a4TV.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
                        a2TV.startAnimation(animationConstriction);
                        qTTV.startAnimation(animationFadeOut);
                        a1TV.startAnimation(animationFadeOut);
                        a3TV.startAnimation(animationFadeOut);
                        a4TV.startAnimation(animationFadeOut);
                        break;

                    case (R.id.answer3TV):
                        animationConstriction.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                inPlayPresenter.checkAnswer(3);
                                qTTV.setVisibility(View.INVISIBLE);
                                a2TV.setVisibility(View.INVISIBLE);
                                a1TV.setVisibility(View.INVISIBLE);
                                a4TV.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
                        a3TV.startAnimation(animationConstriction);
                        qTTV.startAnimation(animationFadeOut);
                        a1TV.startAnimation(animationFadeOut);
                        a2TV.startAnimation(animationFadeOut);
                        a4TV.startAnimation(animationFadeOut);
                        break;

                    case (R.id.answer4TV):
                        animationConstriction.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                inPlayPresenter.checkAnswer(4);
                                qTTV.setVisibility(View.INVISIBLE);
                                a2TV.setVisibility(View.INVISIBLE);
                                a3TV.setVisibility(View.INVISIBLE);
                                a1TV.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
                        a4TV.startAnimation(animationConstriction);
                        qTTV.startAnimation(animationFadeOut);
                        a1TV.startAnimation(animationFadeOut);
                        a2TV.startAnimation(animationFadeOut);
                        a3TV.startAnimation(animationFadeOut);
                        break;
                }
            }
        };
        a1TV.setOnClickListener(onClickListener);
        a2TV.setOnClickListener(onClickListener);
        a3TV.setOnClickListener(onClickListener);
        a4TV.setOnClickListener(onClickListener);
    }

    void setQuestionCategoryImage(int category) {
        ImageView categoryImage = findViewById(R.id.inPlayCategoryImage);
        if (category == 0) {
            categoryImage.setBackground(getResources().getDrawable(R.drawable.ic_category_0_obschie));
        }
        if (category == 1) {
            categoryImage.setBackground(getResources().getDrawable(R.drawable.ic_category_1_syuzhet));
        }
        if (category == 2) {
            categoryImage.setBackground(getResources().getDrawable(R.drawable.ic_category_2_semi));
        }
        if (category == 3) {
            categoryImage.setBackground(getResources().getDrawable(R.drawable.ic_category_3_personazhi));
        }
        if (category == 4) {
            categoryImage.setBackground(getResources().getDrawable(R.drawable.ic_category_4_istoria));
        }
        if (category == 5) {
            categoryImage.setBackground(getResources().getDrawable(R.drawable.ic_category_5_geraldika));
        }
        if (category == 6) {
            categoryImage.setBackground(getResources().getDrawable(R.drawable.ic_category_6_religia));
        }
        if (category == 7) {
            categoryImage.setBackground(getResources().getDrawable(R.drawable.ic_category_7_skolko));
        }
        if (category == 8) {
            categoryImage.setBackground(getResources().getDrawable(R.drawable.ic_category_8_citaty));
        }
        if (category == 9) {
            categoryImage.setBackground(getResources().getDrawable(R.drawable.ic_category_9_dotrakiytsy));
        }
        if (category == 10) {
            categoryImage.setBackground(getResources().getDrawable(R.drawable.ic_category_10_geografia));
        }
        if (category == 11) {
            categoryImage.setBackground(getResources().getDrawable(R.drawable.ic_category_11_izobr));
        }
        categoryImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.from_right_to_center));
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
        Picasso.get()
                .load(R.drawable.background_targ)
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
    public void showFailure() {
        ViewGroup emptyView = findViewById(R.id.in_play_empty_frame);
        emptyView.removeAllViews();
        View userLose = LayoutInflater.from(this).inflate(R.layout.in_play_user_lose, emptyView, false);
        ImageView backgroundIV = userLose.findViewById(R.id.userLoseBackgroundIV);
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


        questionCategoryTV.setText(String.valueOf(userAnswer));
        questionsToEndTV.setText(String.valueOf(questionsToEnd));
        if(questionsToEnd>0) showQuestion();
        else{
            Toast.makeText(InPlayActivity.this,"GameEnded",Toast.LENGTH_SHORT).show();
        }
    }
    */
//@InjectViewState