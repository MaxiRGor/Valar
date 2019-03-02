package harelchuk.maxim.quizwithmoxy.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import harelchuk.maxim.quizwithmoxy.R;
import harelchuk.maxim.quizwithmoxy.TabMenuActivity;
import harelchuk.maxim.quizwithmoxy.model.PagerAdapter;
import harelchuk.maxim.quizwithmoxy.presenter.SettingsPresenter;
import harelchuk.maxim.quizwithmoxy.view.SettingsView;

public class SettingsFragment extends MvpAppCompatFragment implements SettingsView {

    @InjectPresenter
    SettingsPresenter settingsPresenter;

    private ViewGroup mainConteinerVG;
    private View settingsView;
/*
    TabHost tabHost;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainConteinerVG = getActivity().findViewById(R.id.main_container);
        settingsView = inflater.inflate(R.layout.settings, mainConteinerVG, false);
        tabHost = settingsView.findViewById(android.R.id.tabhost);
        setUpTabs();
        return settingsView;
    }

    private void setUpTabs() {
        tabHost.setup();
        TabHost.TabSpec tabSpec;
        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator(getResources().getString(R.string.tab_game));
        tabSpec.setContent(R.id.tvTab1);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setIndicator(getResources().getString(R.string.tab_skin));
        tabSpec.setContent(R.id.tvTab2);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setIndicator(getResources().getString(R.string.tab_bank));
        tabSpec.setContent(R.id.tvTab3);
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTabByTag("tag3");
    }

    @Override
    public void show() {

    }
    */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainConteinerVG = getActivity().findViewById(R.id.main_container);
        settingsView = inflater.inflate(R.layout.settings, mainConteinerVG, false);
        //Toolbar toolbar = settingsView.findViewById(R.id.toolbar);
        //((TabMenuActivity)getActivity()).setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) settingsView.findViewById(R.id.tab_layout);
        //tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        /*
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_set_books_films));
        //tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        //tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_set_emblem));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_set_bank));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
*/
        final ViewPager viewPager = (ViewPager) settingsView.findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                ( ((TabMenuActivity)getActivity()).getSupportFragmentManager(), tabLayout.getTabCount());
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
