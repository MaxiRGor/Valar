package harelchuk.maxim.quizwithmoxy;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
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

    //private View userWin;
    //private View userLose;

    private TextView questionLevelTV;
    private TextView questionCategoryTV;
    private ImageView bookFilmImage;
    private ImageView categoryImage;

    private Drawable unansweredLogo;
    private Drawable answeredLogo;

    private Drawable books;
    private Drawable films;
    private Drawable booksAndFilms;

    private Animation animationConstriction;
    private Animation animationFadeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_play_empty_frame);
        ImageView questionDescriptionBackground = findViewById(R.id.inPlayWindDescrIV);
        ImageView backgroundImage = findViewById(R.id.inPlayBackgroundIV);
        this.questionContainer = findViewById(R.id.frameQuestionLayout);
        this.questionLevelTV = findViewById(R.id.inPlayLevelTV);
        this.questionCategoryTV = findViewById(R.id.inPlayCategoryTV);
        this.booksAndFilms = getResources().getDrawable(R.drawable.all_books_films_unpressed);
        this.books = getResources().getDrawable(R.drawable.all_book_unpressed);
        this.films = getResources().getDrawable(R.drawable.all_films_unpressed);

        TextView levelNameTV = findViewById(R.id.inPlayLevelName);

        this.bookFilmImage = findViewById(R.id.inPlayBookFilmImage);
        int theme = UserDataSingleton.getInstance().getCurrent_theme();
        if (theme == 0) {
            setTheme(R.style.TargarAppTheme);
            Picasso.get()
                    .load(R.drawable.targ_background)
                    .fit()
                    .placeholder(R.drawable.blackscreen)
                    .into(backgroundImage);
            questionDescriptionBackground.setBackground(getResources().getDrawable(R.drawable.targ_window));
            this.unansweredLogo = getResources().getDrawable(R.drawable.logo_dragon_not);
            this.answeredLogo = getResources().getDrawable(R.drawable.logo_dragon_yes);

            this.questionLevelTV.setTextColor(getResources().getColor(R.color.targColorAccent));
            this.questionCategoryTV.setTextColor(getResources().getColor(R.color.targColorAccent));
            levelNameTV.setTextColor(getResources().getColor(R.color.targColorAccent));
            this.questionLevelTV.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.constantine));
            this.questionCategoryTV.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.constantine));
            levelNameTV.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.constantine));

        }
        if (theme == 1) {
            setTheme(R.style.StarkAppTheme);
            Picasso.get()
                    .load(R.drawable.stark_background)
                    .fit()
                    .placeholder(R.drawable.blackscreen)
                    .into(backgroundImage);
            questionDescriptionBackground.setBackground(getResources().getDrawable(R.drawable.stark_window));
            this.unansweredLogo = getResources().getDrawable(R.drawable.logo_wolf_not);
            this.answeredLogo = getResources().getDrawable(R.drawable.logo_wolf_yes);

            this.questionLevelTV.setTextColor(getResources().getColor(R.color.targColorAccent));
            this.questionCategoryTV.setTextColor(getResources().getColor(R.color.targColorAccent));
            levelNameTV.setTextColor(getResources().getColor(R.color.targColorAccent));
            this.questionLevelTV.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.bemountline));
            this.questionCategoryTV.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.bemountline));
            levelNameTV.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.bemountline));


        }

        if (theme == 2) {
            setTheme(R.style.LannAppTheme);
            Picasso.get()
                    .load(R.drawable.lann_background)
                    .fit()
                    .placeholder(R.drawable.blackscreen)
                    .into(backgroundImage);
            questionDescriptionBackground.setBackground(getResources().getDrawable(R.drawable.lann_window));
            this.unansweredLogo = getResources().getDrawable(R.drawable.logo_lion_not);
            this.answeredLogo = getResources().getDrawable(R.drawable.logo_lion_yes);

            this.questionLevelTV.setTextColor(getResources().getColor(R.color.targColorAccent));
            this.questionCategoryTV.setTextColor(getResources().getColor(R.color.targColorAccent));
            levelNameTV.setTextColor(getResources().getColor(R.color.targColorAccent));
            this.questionLevelTV.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.kotyhoroshko));
            this.questionCategoryTV.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.kotyhoroshko));
            levelNameTV.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.kotyhoroshko));
        }

        if (theme == 3) {
            setTheme(R.style.NightAppTheme);
            Picasso.get()
                    .load(R.drawable.night_background)
                    .fit()
                    .placeholder(R.drawable.blackscreen)
                    .into(backgroundImage);
            questionDescriptionBackground.setBackground(getResources().getDrawable(R.drawable.night_window));
            this.unansweredLogo = getResources().getDrawable(R.drawable.logo_nk_not);
            this.answeredLogo = getResources().getDrawable(R.drawable.logo_nk_yes);

            this.questionLevelTV.setTextColor(getResources().getColor(R.color.targColorAccent));
            this.questionCategoryTV.setTextColor(getResources().getColor(R.color.targColorAccent));
            levelNameTV.setTextColor(getResources().getColor(R.color.targColorAccent));
            this.questionLevelTV.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.gnutypewriter));
            this.questionCategoryTV.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.gnutypewriter));
            levelNameTV.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.gnutypewriter));

        }

        ImageView question1Logo = findViewById(R.id.inPlayQuestionImage1);
        ImageView question2Logo = findViewById(R.id.inPlayQuestionImage2);
        ImageView question3Logo = findViewById(R.id.inPlayQuestionImage3);
        ImageView question4Logo = findViewById(R.id.inPlayQuestionImage4);
        ImageView question5Logo = findViewById(R.id.inPlayQuestionImage5);
        ImageView question6Logo = findViewById(R.id.inPlayQuestionImage6);
        ImageView question7Logo = findViewById(R.id.inPlayQuestionImage7);

        question1Logo.setBackground(unansweredLogo);
        question2Logo.setBackground(unansweredLogo);
        question3Logo.setBackground(unansweredLogo);
        question4Logo.setBackground(unansweredLogo);
        question5Logo.setBackground(unansweredLogo);
        question6Logo.setBackground(unansweredLogo);
        question7Logo.setBackground(unansweredLogo);

        this.animationConstriction = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.constriction);
        this.animationFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

    }


    @Override
    public void showQuestion(int questionsToTheEnd, String question, String a1, String a2, String a3, String a4, int category,
                             int level, boolean inBook, boolean inSerial) {

        String[] categories = getResources().getStringArray(R.array.categories);

        this.questionContainer.removeAllViews();
        View currentQuestionView = LayoutInflater.from(this).inflate(R.layout.in_play_question, questionContainer, false);
        this.questionContainer.addView(currentQuestionView);

        setQuestionCategoryImage(category);

        questionLevelTV.setText(String.valueOf(level));
        questionCategoryTV.setText(categories[category]);

        if (inBook) {
            if (inSerial) {
                this.bookFilmImage.setBackground(this.booksAndFilms);
            } else
                this.bookFilmImage.setBackground(this.books);
        } else
            this.bookFilmImage.setBackground(films);

        bookFilmImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.from_left_to_center));

        int currentQuestion = 1 + 7 - questionsToTheEnd;

        ImageView currentQuestionImage = findViewById(R.id.inPlayQuestionImage1);

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
            qTTV.setBackground(getResources().getDrawable(R.drawable.targ_window));
            a1TV.setBackground(getResources().getDrawable(R.drawable.targ_button_selector));
            a2TV.setBackground(getResources().getDrawable(R.drawable.targ_button_selector));
            a3TV.setBackground(getResources().getDrawable(R.drawable.targ_button_selector));
            a4TV.setBackground(getResources().getDrawable(R.drawable.targ_button_selector));
        }
        if (theme == 1) {
            qTTV.setBackground(getResources().getDrawable(R.drawable.stark_window));
            a1TV.setBackground(getResources().getDrawable(R.drawable.stark_button_selector));
            a2TV.setBackground(getResources().getDrawable(R.drawable.stark_button_selector));
            a3TV.setBackground(getResources().getDrawable(R.drawable.stark_button_selector));
            a4TV.setBackground(getResources().getDrawable(R.drawable.stark_button_selector));
        }

        if (theme == 2) {
            qTTV.setBackground(getResources().getDrawable(R.drawable.lann_window));
            a1TV.setBackground(getResources().getDrawable(R.drawable.lann_button_selector));
            a2TV.setBackground(getResources().getDrawable(R.drawable.lann_button_selector));
            a3TV.setBackground(getResources().getDrawable(R.drawable.lann_button_selector));
            a4TV.setBackground(getResources().getDrawable(R.drawable.lann_button_selector));
        }
        if (theme == 3) {
            qTTV.setBackground(getResources().getDrawable(R.drawable.night_window));
            a1TV.setBackground(getResources().getDrawable(R.drawable.night_button_selector));
            a2TV.setBackground(getResources().getDrawable(R.drawable.night_button_selector));
            a3TV.setBackground(getResources().getDrawable(R.drawable.night_button_selector));
            a4TV.setBackground(getResources().getDrawable(R.drawable.night_button_selector));
        }

        currentQuestionView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_top_to_center));

        final ImageView finalCurrentQuestionImage = currentQuestionImage;

        View.OnClickListener onClickListener = new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                finalCurrentQuestionImage.setBackground(answeredLogo);

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

    private void setQuestionCategoryImage(int category) {
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
        View userWin = LayoutInflater.from(this).inflate(R.layout.in_play_user_win, questionContainer, false);
        this.questionCategoryTV.setVisibility(View.INVISIBLE);
        this.bookFilmImage.setVisibility(View.INVISIBLE);
        this.categoryImage.setVisibility(View.INVISIBLE);
        this.questionContainer.removeAllViews();
        ImageView userWinTextBackgroundTV = userWin.findViewById(R.id.userWinTextBackgroundTV);
        ImageView userWinAnimalImage = userWin.findViewById(R.id.userWinWarriorIV);
        this.questionContainer.addView(userWin);
        inPlayPresenter.sendInfoToUserStat(false);

        int theme = UserDataSingleton.getInstance().getCurrent_theme();
        if (theme == 0) {
            userWinTextBackgroundTV.setBackground(getResources().getDrawable(R.drawable.targ_window));
            Picasso.get()
                    .load(R.drawable.targ_personage_win)
                    .into(userWinAnimalImage);
        }

        if (theme == 1) {
            userWinTextBackgroundTV.setBackground(getResources().getDrawable(R.drawable.stark_window));
            Picasso.get()
                    .load(R.drawable.stark_personage_win)
                    .into(userWinAnimalImage);
        }

        if (theme == 2) {
            userWinTextBackgroundTV.setBackground(getResources().getDrawable(R.drawable.lann_window));
            Picasso.get()
                    .load(R.drawable.lann_personage_win)
                    .into(userWinAnimalImage);
        }

        if (theme == 3) {
            userWinTextBackgroundTV.setBackground(getResources().getDrawable(R.drawable.night_window));
            Picasso.get()
                    .load(R.drawable.night_personage_win)
                    .into(userWinAnimalImage);
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };
        this.questionContainer.setOnClickListener(onClickListener);

    }

    @Override
    public void userLose(int answered) {
        View userLose = LayoutInflater.from(this).inflate(R.layout.in_play_user_lose, questionContainer, false);
        this.questionCategoryTV.setVisibility(View.INVISIBLE);
        this.bookFilmImage.setVisibility(View.INVISIBLE);
        this.categoryImage.setVisibility(View.INVISIBLE);
        this.questionContainer.removeAllViews();
        ImageView userLoseTextBackgroundTV = userLose.findViewById(R.id.userLoseTextBackgroundTV);
        ImageView useLoseAnimalImage = userLose.findViewById(R.id.userLoseWarriorIV);
        TextView answeredTV = userLose.findViewById(R.id.loseAnsweredTV);
        this.questionContainer.addView(userLose);
        inPlayPresenter.sendInfoToUserStat(true);

        int theme = UserDataSingleton.getInstance().getCurrent_theme();
        if (theme == 0) {
            userLoseTextBackgroundTV.setBackground(getResources().getDrawable(R.drawable.targ_window));
            Picasso.get()
                    .load(R.drawable.targ_personage_fail)
                    .into(useLoseAnimalImage);
        }

        if (theme == 1) {
            userLoseTextBackgroundTV.setBackground(getResources().getDrawable(R.drawable.stark_window));
            Picasso.get()
                    .load(R.drawable.stark_personage_fail)
                    .into(useLoseAnimalImage);
        }

        if (theme == 2) {
            userLoseTextBackgroundTV.setBackground(getResources().getDrawable(R.drawable.lann_window));
            Picasso.get()
                    .load(R.drawable.lann_personage_fail)
                    .into(useLoseAnimalImage);
        }

        if (theme == 3) {
            userLoseTextBackgroundTV.setBackground(getResources().getDrawable(R.drawable.night_window));
            Picasso.get()
                    .load(R.drawable.night_personage_fail)
                    .into(useLoseAnimalImage);
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };
        this.questionContainer.setOnClickListener(onClickListener);
        answeredTV.setText(String.valueOf(answered));
    }

    @Override
    public void showFailure() {
        View userLose = LayoutInflater.from(this).inflate(R.layout.in_play_user_lose, questionContainer, false);
        this.questionCategoryTV.setVisibility(View.INVISIBLE);
        this.bookFilmImage.setVisibility(View.INVISIBLE);
        this.questionContainer.removeAllViews();
        ImageView userLoseTextBackgroundTV = userLose.findViewById(R.id.userLoseTextBackgroundTV);
        TextView answeredTV = userLose.findViewById(R.id.loseAnsweredTV);
        TextView notConnectedTV = userLose.findViewById(R.id.loseTV);
        this.questionContainer.addView(userLose);
        inPlayPresenter.sendFailToUserStat();

        int theme = UserDataSingleton.getInstance().getCurrent_theme();
        if (theme == 0) {
            userLoseTextBackgroundTV.setBackground(getResources().getDrawable(R.drawable.targ_window));
        }

        if (theme == 1) {
            userLoseTextBackgroundTV.setBackground(getResources().getDrawable(R.drawable.stark_window));
        }

        if (theme == 2) {
            userLoseTextBackgroundTV.setBackground(getResources().getDrawable(R.drawable.lann_window));
        }
        if (theme == 3) {
            userLoseTextBackgroundTV.setBackground(getResources().getDrawable(R.drawable.night_window));
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };
        this.questionContainer.setOnClickListener(onClickListener);
        notConnectedTV.setText(getResources().getString(R.string.notConnected));
        answeredTV.setText(String.valueOf(0));
    }

    @Override
    public void showAddedScore(boolean is_lose, int coinGD, int coinAD, int coinCP) {
        TextView coinsGD;
        TextView coinsAD;
        TextView coinsCP;
        ImageView coinGDImage;
        ImageView coinADImage;
        ImageView coinCPImage;
        View userEndGame;

        if (is_lose) {
            userEndGame = questionContainer.findViewById(R.id.in_play_user_lose);
            coinsGD = userEndGame.findViewById(R.id.userLoseCoinsGD);
            coinsAD = userEndGame.findViewById(R.id.userLoseCoinsAD);
            coinsCP = userEndGame.findViewById(R.id.userLoseCoinsCP);
            coinGDImage = userEndGame.findViewById(R.id.endGameGDImage);
            coinADImage = userEndGame.findViewById(R.id.endGameADImage);
            coinCPImage = userEndGame.findViewById(R.id.endGameCPImage);
        } else {
            userEndGame = questionContainer.findViewById(R.id.in_play_user_win);
            coinsGD = userEndGame.findViewById(R.id.userWinCoinsGD);
            coinsAD = userEndGame.findViewById(R.id.userWinCoinsAD);
            coinsCP = userEndGame.findViewById(R.id.userWinCoinsCP);
            coinGDImage = userEndGame.findViewById(R.id.endGameGDImage);
            coinADImage = userEndGame.findViewById(R.id.endGameADImage);
            coinCPImage = userEndGame.findViewById(R.id.endGameCPImage);
        }
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