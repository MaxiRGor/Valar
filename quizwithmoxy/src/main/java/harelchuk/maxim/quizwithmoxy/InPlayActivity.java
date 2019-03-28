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

    private ViewGroup questionContainer;
    private TextView coinsGD;
    private TextView coinsAD;
    private TextView coinsCP;
    private ImageView coinGDImage;
    private ImageView coinADImage;
    private ImageView coinCPImage;

    private TextView questionLevelTV;
    private TextView questionCategoryTV;
    private ImageView bookFilmImage;
    private ImageView categoryImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_play_empty_frame);
        ImageView questionDescriptionBackground = findViewById(R.id.inPlayWindDescrIV);
        ImageView backgroundImage = findViewById(R.id.inPlayBackgroundIV);
        this.questionContainer = findViewById(R.id.frameQuestionLayout);
        this.questionLevelTV = findViewById(R.id.inPlayLevelTV);
        this.questionCategoryTV = findViewById(R.id.inPlayCategoryTV);
        this.bookFilmImage = findViewById(R.id.inPlayBookFilmImage);
        int theme = UserDataSingleton.getInstance().getCurrent_theme();
        if (theme == 0) {
            Picasso.get()
                    .load(R.drawable.targ_background)
                    .fit()
                    .placeholder(R.drawable.blackscreen)
                    .into(backgroundImage);
            questionDescriptionBackground.setBackground(getResources().getDrawable(R.drawable.window_targariens));
        }
    }


    @Override
    public void showQuestion(int questionsToTheEnd, String question, String a1, String a2, String a3, String a4, int category,
                             int level, boolean inBook, boolean inSerial) {

        String[] categories = getResources().getStringArray(R.array.categories);

        this.questionContainer.removeAllViews();
        View currentQuestionView = LayoutInflater.from(this).inflate(R.layout.in_play_question, questionContainer, false);
        this.questionContainer.addView(currentQuestionView);

        ImageView currentQuestionImage = findViewById(R.id.inPlayQuestionImage1);
        setQuestionCategoryImage(category);

        questionLevelTV.setText(String.format(getResources().getString(R.string.level) + " = %s", level));
        questionCategoryTV.setText(categories[category]);

        if (inBook) {
            if (inSerial) {
                bookFilmImage.setBackground(getResources().getDrawable(R.drawable.targ_set_books_films_pressed));
            } else
                bookFilmImage.setBackground(getResources().getDrawable(R.drawable.targ_set_books_pressed));
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


        final TextView qTTV = currentQuestionView.findViewById(R.id.questionTextTV);
        final TextView a1TV = currentQuestionView.findViewById(R.id.answer1TV);
        final TextView a2TV = currentQuestionView.findViewById(R.id.answer2TV);
        final TextView a3TV = currentQuestionView.findViewById(R.id.answer3TV);
        final TextView a4TV = currentQuestionView.findViewById(R.id.answer4TV);

        qTTV.setText(question);
        a1TV.setText(a1);
        a2TV.setText(a2);
        a3TV.setText(a3);
        a4TV.setText(a4);


        int theme = UserDataSingleton.getInstance().getCurrent_theme();
        if (theme == 0) {
            qTTV.setBackground(getResources().getDrawable(R.drawable.window_targariens));
            a1TV.setBackground(getResources().getDrawable(R.drawable.targ_button_selector));
            a2TV.setBackground(getResources().getDrawable(R.drawable.targ_button_selector));
            a3TV.setBackground(getResources().getDrawable(R.drawable.targ_button_selector));
            a4TV.setBackground(getResources().getDrawable(R.drawable.targ_button_selector));
        }

        currentQuestionView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_top_to_center));

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
        this.categoryImage = findViewById(R.id.inPlayCategoryImage);
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
        //ViewGroup emptyView = findViewById(R.id.in_play_empty_frame);
        //emptyView.setBackground(getResources().getDrawable(R.drawable.blackscreen));
        this.questionCategoryTV.setVisibility(View.INVISIBLE);
        this.bookFilmImage.setVisibility(View.INVISIBLE);
        this.categoryImage.setVisibility(View.INVISIBLE);
        this.questionContainer.removeAllViews();
        View userWin = LayoutInflater.from(this).inflate(R.layout.in_play_user_win, questionContainer, false);
        this.questionContainer.addView(userWin);
        //ImageView backgroundIV = userWin.findViewById(R.id.userWinBackgroundIV);
        ImageView userWinTextBackgroundTV = userWin.findViewById(R.id.userWinTextBackgroundTV);
        ImageView userWinAnimalImage = userWin.findViewById(R.id.userWinWarriorIV);

        int theme = UserDataSingleton.getInstance().getCurrent_theme();
        if (theme == 0) {
            /*Picasso.get()
                    .load(R.drawable.targ_background)
                    .fit()
                    .placeholder(R.drawable.blackscreen)
                    .into(backgroundIV);*/
            userWinTextBackgroundTV.setBackground(getResources().getDrawable(R.drawable.window_targariens));
            Picasso.get()
                    .load(R.drawable.targ_win_warrior)
                    //.fit()
                    //.placeholder(R.drawable.blackscreen)
                    .into(userWinAnimalImage);
        }
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };

        this.questionContainer.setOnClickListener(onClickListener);

        this.coinsGD = userWin.findViewById(R.id.userWinCoinsGD);
        this.coinsAD = userWin.findViewById(R.id.userWinCoinsAD);
        this.coinsCP = userWin.findViewById(R.id.userWinCoinsCP);
        this.coinGDImage = userWin.findViewById(R.id.endGameGDImage);
        this.coinADImage = userWin.findViewById(R.id.endGameADImage);
        this.coinCPImage = userWin.findViewById(R.id.endGameCPImage);
        inPlayPresenter.sendInfoToUserStat();
    }

    @Override
    public void userLose(int answered) {
        //ViewGroup emptyView = findViewById(R.id.in_play_empty_frame);
        this.questionCategoryTV.setVisibility(View.INVISIBLE);
        this.bookFilmImage.setVisibility(View.INVISIBLE);
        this.categoryImage.setVisibility(View.INVISIBLE);
        this.questionContainer.removeAllViews();
        //emptyView.removeAllViews();
        //emptyView.setBackground(getResources().getDrawable(R.drawable.blackscreen));
        View userLose;
        userLose = LayoutInflater.from(this).inflate(R.layout.in_play_user_lose, questionContainer, false);
        questionContainer.addView(userLose);
        //ImageView backgroundIV = userLose.findViewById(R.id.userLoseBackgroundIV);

        ImageView userLoseTextBackgroundTV = userLose.findViewById(R.id.userLoseTextBackgroundTV);
        ImageView useLoseAnimalImage = userLose.findViewById(R.id.userLoseWarriorIV);

        int theme = UserDataSingleton.getInstance().getCurrent_theme();
        if (theme == 0) {
           /* Picasso.get()
                    .load(R.drawable.targ_background)
                    .fit()
                    .placeholder(R.drawable.blackscreen)
                    .into(backgroundIV);*/
            userLoseTextBackgroundTV.setBackground(getResources().getDrawable(R.drawable.window_targariens));
            Picasso.get()
                    .load(R.drawable.targ_warrior_fail)
                    .into(useLoseAnimalImage);
        }


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };
        this.questionContainer.setOnClickListener(onClickListener);
        TextView answeredTV = userLose.findViewById(R.id.loseAnsweredTV);
        answeredTV.setText(String.valueOf(answered));
        this.coinsGD = userLose.findViewById(R.id.userLoseCoinsGD);
        this.coinsAD = userLose.findViewById(R.id.userLoseCoinsAD);
        this.coinsCP = userLose.findViewById(R.id.userLoseCoinsCP);
        this.coinGDImage = userLose.findViewById(R.id.endGameGDImage);
        this.coinADImage = userLose.findViewById(R.id.endGameADImage);
        this.coinCPImage = userLose.findViewById(R.id.endGameCPImage);

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

        this.questionCategoryTV.setVisibility(View.INVISIBLE);
        this.bookFilmImage.setVisibility(View.INVISIBLE);
        this.questionContainer.removeAllViews();

        View userLose = LayoutInflater.from(this).inflate(R.layout.in_play_user_lose, questionContainer, false);
        questionContainer.addView(userLose);

        ImageView userLoseTextBackgroundTV = userLose.findViewById(R.id.userLoseTextBackgroundTV);

        int theme = UserDataSingleton.getInstance().getCurrent_theme();
        if (theme == 0) {
            userLoseTextBackgroundTV.setBackground(getResources().getDrawable(R.drawable.window_targariens));
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };
        this.questionContainer.setOnClickListener(onClickListener);

        TextView answeredTV = userLose.findViewById(R.id.loseAnsweredTV);
        TextView notConnectedTV = userLose.findViewById(R.id.loseTV);
        notConnectedTV.setText(getResources().getString(R.string.notConnected));
        this.coinGDImage = userLose.findViewById(R.id.endGameGDImage);
        this.coinADImage = userLose.findViewById(R.id.endGameADImage);
        this.coinCPImage = userLose.findViewById(R.id.endGameCPImage);
        answeredTV.setText(String.valueOf(0));
        this.coinsGD = userLose.findViewById(R.id.userLoseCoinsGD);
        this.coinsAD = userLose.findViewById(R.id.userLoseCoinsAD);
        this.coinsCP = userLose.findViewById(R.id.userLoseCoinsCP);

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