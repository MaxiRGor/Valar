package harelchuk.maxim.quizwithmoxy.fragments;

import android.content.Context;
import android.content.SharedPreferences;
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

import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.IS_BOOKS;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.IS_FILMS;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.SHARED_PREFERENCES_USER;

public class SetUsersBooksFilmsFragment extends MvpAppCompatFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final SharedPreferences sharedPreferencesUser;
        sharedPreferencesUser = getContext().getSharedPreferences(SHARED_PREFERENCES_USER, Context.MODE_PRIVATE);
        View view = inflater.inflate(R.layout.set_users_preferences_fragment, container, false);
        final CheckBox checkBoxFilms = view.findViewById(R.id.checkBoxSeries);
        checkBoxFilms.setTypeface(ResourcesCompat.getFont(getContext(), R.font.constantine));
        checkBoxFilms.setChecked(sharedPreferencesUser.getBoolean(IS_FILMS, false));
        if (sharedPreferencesUser.getBoolean(IS_FILMS, false)) {
            checkBoxFilms.setText(R.string.userWatchSeries);
        } else {
            checkBoxFilms.setText(R.string.userDontWatchSeries);
        }

        final CheckBox checkBoxBooks = view.findViewById(R.id.checkBoxBooks);
        checkBoxBooks.setTypeface(ResourcesCompat.getFont(getContext(), R.font.constantine));
        checkBoxBooks.setChecked(sharedPreferencesUser.getBoolean(IS_BOOKS, false));

        if (sharedPreferencesUser.getBoolean(IS_BOOKS, false)) {
            checkBoxBooks.setText(R.string.userReadBooks);
        } else {
            checkBoxBooks.setText(R.string.userDontReadBooks);
        }
        final ImageView windowBooks = view.findViewById(R.id.userPrefWindow1);
        final ImageView windowFilms = view.findViewById(R.id.userPrefWindow2);

        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            SharedPreferences.Editor editor = sharedPreferencesUser.edit();

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView == checkBoxBooks) {
                    editor.putBoolean(IS_BOOKS, isChecked);
                    editor.apply();
                    if (isChecked) {
                        checkBoxBooks.setText(R.string.userReadBooks);
                    } else {
                        checkBoxBooks.setText(R.string.userDontReadBooks);
                    }
                    if (!isChecked) {
                        if (!sharedPreferencesUser.getBoolean(IS_FILMS, false)) {
                            checkBoxFilms.performClick();
                        }
                    }
                }
                if (buttonView == checkBoxFilms) {
                    editor.putBoolean(IS_FILMS, isChecked);
                    editor.apply();
                    if (isChecked) {
                        checkBoxFilms.setText(R.string.userWatchSeries);
                    } else {
                        checkBoxFilms.setText(R.string.userDontWatchSeries);
                    }
                    if (!isChecked) {
                        if (!sharedPreferencesUser.getBoolean(IS_BOOKS, false)) {
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
                    case R.id.userPrefWindow1:
                        checkBoxBooks.performClick();
                        break;
                    case R.id.userPrefWindow2:
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
