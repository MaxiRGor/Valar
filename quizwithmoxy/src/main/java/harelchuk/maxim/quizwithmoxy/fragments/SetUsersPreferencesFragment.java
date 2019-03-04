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

import com.arellomobile.mvp.MvpAppCompatFragment;

import harelchuk.maxim.quizwithmoxy.R;

import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.IS_BOOKS;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.IS_FILMS;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.SHARED_PREFERENCES_USER;

public class SetUsersPreferencesFragment extends MvpAppCompatFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.tab_fragment_1, container, false);
        final SharedPreferences sharedPreferencesUser;
        sharedPreferencesUser = getContext().getSharedPreferences(SHARED_PREFERENCES_USER, Context.MODE_PRIVATE);
        View view = inflater.inflate(R.layout.set_users_preferences_fragment, container, false);
        final CheckBox checkBoxFilms = view.findViewById(R.id.checkBoxSeries);
        checkBoxFilms.setTypeface(ResourcesCompat.getFont(getContext(),R.font.constantine));
        checkBoxFilms.setChecked(sharedPreferencesUser.getBoolean(IS_FILMS,false));
        final CheckBox checkBoxBooks = view.findViewById(R.id.checkBoxBooks);
        checkBoxBooks.setTypeface(ResourcesCompat.getFont(getContext(),R.font.constantine));
        checkBoxBooks.setChecked(sharedPreferencesUser.getBoolean(IS_BOOKS,false));

        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            SharedPreferences.Editor editor = sharedPreferencesUser.edit();
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView==checkBoxBooks){
                    editor.putBoolean(IS_BOOKS,isChecked);
                    editor.commit();
                }
                if(buttonView==checkBoxFilms){
                    editor.putBoolean(IS_FILMS,isChecked);
                    editor.commit();
                }
            }

        };
        checkBoxBooks.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBoxFilms.setOnCheckedChangeListener(onCheckedChangeListener);
        return view;
    }
}
