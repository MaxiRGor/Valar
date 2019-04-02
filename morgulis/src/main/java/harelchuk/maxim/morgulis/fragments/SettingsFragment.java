package harelchuk.maxim.morgulis.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import harelchuk.maxim.morgulis.R;
import harelchuk.maxim.morgulis.model.SettingsPagerAdapter;
import harelchuk.maxim.morgulis.model.UserDataSingleton;
import harelchuk.maxim.morgulis.presenter.SettingsPresenter;
import harelchuk.maxim.morgulis.view.SettingsView;

public class SettingsFragment extends MvpAppCompatFragment implements SettingsView {

    @InjectPresenter
    SettingsPresenter settingsPresenter;

    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup mainContainerVG = getActivity().findViewById(R.id.main_container);
        View settingsView = inflater.inflate(R.layout.settings, mainContainerVG, false);

        @SuppressLint("InflateParams") View view1 = getLayoutInflater().inflate(R.layout.custom_tab_item_icon,null);
        @SuppressLint("InflateParams") View view2 = getLayoutInflater().inflate(R.layout.custom_tab_item_icon,null);
        @SuppressLint("InflateParams") View view3 = getLayoutInflater().inflate(R.layout.custom_tab_item_icon,null);

        int theme = UserDataSingleton.getInstance().getCurrent_theme();

        if(theme==0){
            view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.targ_books_films_selector);
            view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.targ_emblem_selector);
            view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.targ_bank_selector);
        }
        if(theme==1){
            view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.stark_books_films_selector);
            view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.stark_emblem_selector);
            view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.stark_bank_selector);
        }
        if(theme==2){
            view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.lann_books_films_selector);
            view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.lann_emblem_selector);
            view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.lann_bank_selector);
        }
        if(theme==3){
            view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.night_books_films_selector);
            view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.night_emblem_selector);
            view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.night_bank_selector);
        }

        TabLayout tabLayout = settingsView.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view1));
        tabLayout.addTab(tabLayout.newTab().setCustomView(view2));
        tabLayout.addTab(tabLayout.newTab().setCustomView(view3));

        final ViewPager viewPager =  settingsView.findViewById(R.id.pager);
        final SettingsPagerAdapter adapter = new SettingsPagerAdapter
                ( (getActivity()).getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return settingsView;
    }



    @Override
    public void show() {

    }
}
