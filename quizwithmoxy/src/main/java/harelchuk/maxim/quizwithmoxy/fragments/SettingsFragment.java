package harelchuk.maxim.quizwithmoxy.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup mainContainerVG = getActivity().findViewById(R.id.main_container);
        View settingsView = inflater.inflate(R.layout.settings, mainContainerVG, false);

        //Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.from_bottom_to_center);
        //settingsView.startAnimation(animation);

        View view1 = getLayoutInflater().inflate(R.layout.custom_tab_item_icon,null);
        view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.set_book_or_film_selector);

        View view2 = getLayoutInflater().inflate(R.layout.custom_tab_item_icon,null);
        view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.set_emblem_selector);

        View view3 = getLayoutInflater().inflate(R.layout.custom_tab_item_icon,null);
        view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.set_bank_selector);


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
