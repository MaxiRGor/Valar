package harelchuk.maxim.quizwithmoxy.fragments;

import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatFragment;

import harelchuk.maxim.quizwithmoxy.R;
import harelchuk.maxim.quizwithmoxy.model.AppForContext;
import harelchuk.maxim.quizwithmoxy.model.UserDataSingleton;

public class SetUsersBooksFilmsFragment extends MvpAppCompatFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.set_users_preferences_fragment, container, false);

        final CheckBox checkBoxFilms = view.findViewById(R.id.checkBoxSeries);
        final CheckBox checkBoxBooks = view.findViewById(R.id.checkBoxBooks);
        final ImageView windowBooks = view.findViewById(R.id.userPrefBackgroundBooks);
        final ImageView windowFilms = view.findViewById(R.id.userPrefBackgroundFilms);

        int theme = UserDataSingleton.getInstance().getCurrent_theme();

        if(theme==0){
            windowBooks.setBackground(getResources().getDrawable(R.drawable.targ_window));
            windowFilms.setBackground(getResources().getDrawable(R.drawable.targ_window));
            checkBoxBooks.setButtonDrawable(getResources().getDrawable(R.drawable.targ_books_selector));
            checkBoxFilms.setButtonDrawable(getResources().getDrawable(R.drawable.targ_films_selector));
        }

        if(theme==1){
            windowBooks.setBackground(getResources().getDrawable(R.drawable.stark_window));
            windowFilms.setBackground(getResources().getDrawable(R.drawable.stark_window));
            checkBoxBooks.setButtonDrawable(getResources().getDrawable(R.drawable.stark_books_selector));
            checkBoxFilms.setButtonDrawable(getResources().getDrawable(R.drawable.stark_films_selector));
        }

        if(theme==2){
            windowBooks.setBackground(getResources().getDrawable(R.drawable.lann_window));
            windowFilms.setBackground(getResources().getDrawable(R.drawable.lann_window));
            checkBoxBooks.setButtonDrawable(getResources().getDrawable(R.drawable.lann_books_selector));
            checkBoxFilms.setButtonDrawable(getResources().getDrawable(R.drawable.lann_films_selector));
        }
        if(theme==3){
            windowBooks.setBackground(getResources().getDrawable(R.drawable.night_window));
            windowFilms.setBackground(getResources().getDrawable(R.drawable.night_window));
            checkBoxBooks.setButtonDrawable(getResources().getDrawable(R.drawable.night_books_selector));
            checkBoxFilms.setButtonDrawable(getResources().getDrawable(R.drawable.night_films_selector));
        }

        checkBoxFilms.setTypeface(ResourcesCompat.getFont(AppForContext.getContext(), R.font.constantine));
        checkBoxFilms.setChecked(UserDataSingleton.getInstance().isIs_films());
        checkBoxFilms.setTextColor(getResources().getColor(R.color.targColorAccent));
        if (UserDataSingleton.getInstance().isIs_films()) {
            checkBoxFilms.setText(R.string.userWatchSeries);
        } else {
            checkBoxFilms.setText(R.string.userDontWatchSeries);
        }


        checkBoxBooks.setTypeface(ResourcesCompat.getFont(AppForContext.getContext(), R.font.constantine));
        checkBoxBooks.setTextColor(getResources().getColor(R.color.targColorAccent));
        checkBoxBooks.setChecked(UserDataSingleton.getInstance().isIs_books());

        if (UserDataSingleton.getInstance().isIs_books()) {
            checkBoxBooks.setText(R.string.userReadBooks);
        } else {
            checkBoxBooks.setText(R.string.userDontReadBooks);
        }


        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView == checkBoxBooks) {
                    UserDataSingleton.getInstance().setIs_books(isChecked);
                    if (isChecked) {
                        checkBoxBooks.setText(R.string.userReadBooks);
                    } else {
                        checkBoxBooks.setText(R.string.userDontReadBooks);
                    }
                    if (!isChecked) {
                        if (!UserDataSingleton.getInstance().isIs_films()) {
                            checkBoxFilms.performClick();
                        }
                    }
                }
                if (buttonView == checkBoxFilms) {
                    UserDataSingleton.getInstance().setIs_films(isChecked);
                    if (isChecked) {
                        checkBoxFilms.setText(R.string.userWatchSeries);
                    } else {
                        checkBoxFilms.setText(R.string.userDontWatchSeries);
                    }
                    if (!isChecked) {
                        if (!UserDataSingleton.getInstance().isIs_books()) {
                            checkBoxBooks.performClick();
                        }
                    }
                }
            }

        };
        checkBoxBooks.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBoxFilms.setOnCheckedChangeListener(onCheckedChangeListener);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.userPrefBackgroundBooks:
                        checkBoxBooks.performClick();
                        break;
                    case R.id.userPrefBackgroundFilms:
                        checkBoxFilms.performClick();
                        break;
                }
            }
        };
        windowBooks.setOnClickListener(onClickListener);
        windowFilms.setOnClickListener(onClickListener);
        return view;
    }
}
