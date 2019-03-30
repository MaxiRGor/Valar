package harelchuk.maxim.quizwithmoxy.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import harelchuk.maxim.quizwithmoxy.R;
import harelchuk.maxim.quizwithmoxy.TabMenuActivity;
import harelchuk.maxim.quizwithmoxy.model.UserDataSingleton;
import harelchuk.maxim.quizwithmoxy.presenter.SetEmblemPresenter;
import harelchuk.maxim.quizwithmoxy.view.SetEmblemView;

public class SetEmblemFragment extends MvpAppCompatFragment implements SetEmblemView {

    @InjectPresenter
    SetEmblemPresenter setEmblemPresenter;

    private View setEmblemView;
    private ImageView targEmblem;
    private ImageView starkEmblem;
    private ImageView lannEmblem;
    private ImageView nightEmblem;

    private TextView targButton;
    private TextView starkButton;
    private TextView lannButton;
    private TextView nightButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.setEmblemView = inflater.inflate(R.layout.set_emblem_fragment, container, false);

        int theme = UserDataSingleton.getInstance().getCurrent_theme();

        ImageView background = setEmblemView.findViewById(R.id.emblemImageViewBackground);
        if (theme == 0) {
            background.setBackground(getResources().getDrawable(R.drawable.targ_window));
        }
        if (theme == 1) {
            background.setBackground(getResources().getDrawable(R.drawable.stark_window));
        }
        if (theme == 2) {
            background.setBackground(getResources().getDrawable(R.drawable.lann_window));
        }
        if (theme == 3) {
            background.setBackground(getResources().getDrawable(R.drawable.night_window));
        }

        this.targEmblem = setEmblemView.findViewById(R.id.emblemTargImage);
        this.starkEmblem = setEmblemView.findViewById(R.id.emblemStarkImage);
        this.lannEmblem = setEmblemView.findViewById(R.id.emblemLannImage);
        this.nightEmblem = setEmblemView.findViewById(R.id.emblemNKImage);

        this.targButton = setEmblemView.findViewById(R.id.emblemUserSkinTargarTV);
        this.starkButton = setEmblemView.findViewById(R.id.emblemUserSkinStarksTV);
        this.lannButton = setEmblemView.findViewById(R.id.emblemUserSkinLannTV);
        this.nightButton = setEmblemView.findViewById(R.id.emblemUserSkinNKTV);

        setOnClickListeners();

        return setEmblemView;
    }

    private void setOnClickListeners() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.emblemUserSkinTargarTV:
                        //setEmblemPresenter.checkTargar();
                        UserDataSingleton.getInstance().setCurrent_theme(0);
                        UserDataSingleton.getInstance().setIs_skin_targar(true);
                        reloadActivity();
                        break;
                    case R.id.emblemUserSkinStarksTV:
                        //setEmblemPresenter.checkStark();
                        UserDataSingleton.getInstance().setCurrent_theme(1);
                        UserDataSingleton.getInstance().setIs_skin_stark(true);
                        reloadActivity();
                        break;
                    case R.id.emblemUserSkinLannTV:
                        //setEmblemPresenter.checkLann();
                        UserDataSingleton.getInstance().setCurrent_theme(2);
                        UserDataSingleton.getInstance().setIs_skin_lann(true);
                        reloadActivity();
                        break;
                    case R.id.emblemUserSkinNKTV:
                        //setEmblemPresenter.checkNight();
                        UserDataSingleton.getInstance().setCurrent_theme(3);
                        UserDataSingleton.getInstance().setIs_skin_night(true);
                        reloadActivity();
                        break;
                    }
                }
            };
        this.targButton.setOnClickListener(onClickListener);
        this.lannButton.setOnClickListener(onClickListener);
        this.starkButton.setOnClickListener(onClickListener);
        this.nightButton.setOnClickListener(onClickListener);
    }

    private void reloadActivity(){
        //Intent intent = getActivity().getIntent();
        Intent intent = new Intent(getActivity(),TabMenuActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void fillEmblems(int theme, boolean is_targar, boolean is_starks, boolean is_lann, boolean is_night) {
        if (is_targar) {
            this.targEmblem.setBackground(getResources().getDrawable(R.drawable.logo_dragon_yes));
        } else
            this.targEmblem.setBackground(getResources().getDrawable(R.drawable.logo_dragon_not));
        if (is_starks) {
            this.starkEmblem.setBackground(getResources().getDrawable(R.drawable.logo_wolf_yes));
        } else
            this.starkEmblem.setBackground(getResources().getDrawable(R.drawable.logo_wolf_not));
        if (is_lann) {
            this.lannEmblem.setBackground(getResources().getDrawable(R.drawable.logo_lion_yes));
        } else
            this.lannEmblem.setBackground(getResources().getDrawable(R.drawable.logo_lion_not));
        if (is_night) {
            this.nightEmblem.setBackground(getResources().getDrawable(R.drawable.logo_nk_yes));
        } else
            this.nightEmblem.setBackground(getResources().getDrawable(R.drawable.logo_nk_not));

        if (theme == 0) {
            this.targEmblem.setBackground(getResources().getDrawable(R.drawable.logo_dragon_en));
        }
        if (theme == 1) {
            this.starkEmblem.setBackground(getResources().getDrawable(R.drawable.logo_wolf_en));
        }
        if (theme == 2) {
            this.lannEmblem.setBackground(getResources().getDrawable(R.drawable.logo_lion_en));
        }
        if (theme == 3) {
            this.nightEmblem.setBackground(getResources().getDrawable(R.drawable.logo_nk_en));
        }
    }
}
