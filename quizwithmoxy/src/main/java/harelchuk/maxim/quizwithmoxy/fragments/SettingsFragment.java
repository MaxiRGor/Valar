package harelchuk.maxim.quizwithmoxy.fragments;

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

import harelchuk.maxim.quizwithmoxy.R;
import harelchuk.maxim.quizwithmoxy.TabMenuActivity;
import harelchuk.maxim.quizwithmoxy.model.PagerAdapter;
import harelchuk.maxim.quizwithmoxy.model.UserDataSingleton;
import harelchuk.maxim.quizwithmoxy.presenter.SettingsPresenter;
import harelchuk.maxim.quizwithmoxy.view.SettingsView;

public class SettingsFragment extends MvpAppCompatFragment implements SettingsView {

    @InjectPresenter
    SettingsPresenter settingsPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup mainContainerVG = getActivity().findViewById(R.id.main_container);
        View settingsView = inflater.inflate(R.layout.settings, mainContainerVG, false);

        //Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.from_bottom_to_center);
        //settingsView.startAnimation(animation);

        View view1 = getLayoutInflater().inflate(R.layout.custom_tab_item_icon,null);
        View view2 = getLayoutInflater().inflate(R.layout.custom_tab_item_icon,null);
        View view3 = getLayoutInflater().inflate(R.layout.custom_tab_item_icon,null);

        int theme = UserDataSingleton.getInstance().getCurrent_theme();

        if(theme==0){
            view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.targ_books_films_selector);
            view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.targ_emblem_selector);
            view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.targ_bank_selector);
        }
        if(theme==2){
            view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.lann_books_films_selector);
            view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.lann_emblem_selector);
            view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.lann_bank_selector);
        }







        TabLayout tabLayout = (TabLayout) settingsView.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view1));
        tabLayout.addTab(tabLayout.newTab().setCustomView(view2));
        tabLayout.addTab(tabLayout.newTab().setCustomView(view3));

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
