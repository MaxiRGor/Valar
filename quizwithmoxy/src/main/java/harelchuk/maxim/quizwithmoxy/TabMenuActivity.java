package harelchuk.maxim.quizwithmoxy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import harelchuk.maxim.quizwithmoxy.fragments.SettingsFragment;
import harelchuk.maxim.quizwithmoxy.fragments.StatisticsFragment;
import harelchuk.maxim.quizwithmoxy.fragments.TuneGameFragment;
import harelchuk.maxim.quizwithmoxy.model.UserDataSingleton;

public class TabMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout_with_bottom_navigation);

        final ImageView imageView = findViewById(R.id.mainBackground);
        final BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        int theme = UserDataSingleton.getInstance().getCurrent_theme();

        if (theme == 0) {
            setTheme(R.style.TargarAppTheme);
            Picasso.get()
                    .load(R.drawable.targ_background)
                    .fit()
                    .placeholder(R.drawable.blackscreen)
                    .into(imageView);

            navigation.getMenu().getItem(0).setIcon(R.drawable.targ_ic_menu_play_selector);
            navigation.getMenu().getItem(1).setIcon(R.drawable.targ_ic_menu_statistics_selector);
            navigation.getMenu().getItem(2).setIcon(R.drawable.targ_ic_menu_settings_selector);
        }

        if (theme == 1) {
            setTheme(R.style.StarkAppTheme);
            Picasso.get()
                    .load(R.drawable.stark_background)
                    .fit()
                    .placeholder(R.drawable.blackscreen)
                    .into(imageView);

            navigation.getMenu().getItem(0).setIcon(R.drawable.stark_menu_play_selector);
            navigation.getMenu().getItem(1).setIcon(R.drawable.stark_menu_statistics_selector);
            navigation.getMenu().getItem(2).setIcon(R.drawable.stark_menu_settings_selector);
        }

        if (theme == 2) {
            setTheme(R.style.LannAppTheme);
            Picasso.get()
                    .load(R.drawable.lann_background)
                    .fit()
                    .placeholder(R.drawable.blackscreen)
                    .into(imageView);

            navigation.getMenu().getItem(0).setIcon(R.drawable.lann_menu_play_selector);
            navigation.getMenu().getItem(1).setIcon(R.drawable.lann_menu_statistics_selector);
            navigation.getMenu().getItem(2).setIcon(R.drawable.lann_menu_settings_selector);
        }

        if (theme == 3) {
            setTheme(R.style.NightAppTheme);
            Picasso.get()
                    .load(R.drawable.night_background)
                    .fit()
                    .placeholder(R.drawable.blackscreen)
                    .into(imageView);

            navigation.getMenu().getItem(0).setIcon(R.drawable.night_menu_play_selector);
            navigation.getMenu().getItem(1).setIcon(R.drawable.night_menu_statistics_selector);
            navigation.getMenu().getItem(2).setIcon(R.drawable.night_menu_settings_selector);
        }

        navigation.setItemIconTintList(null);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction
                    .add(R.id.main_container, new TuneGameFragment()).commit();
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.from_bottom_to_center, R.anim.from_center_to_bottom);

            switch (item.getItemId()) {
                case R.id.navigation_play:
                    fragmentTransaction.replace(R.id.main_container, new TuneGameFragment()).commit();
                    return true;
                case R.id.navigation_settings:
                    fragmentTransaction.replace(R.id.main_container, new SettingsFragment()).commit();
                    return true;
                case R.id.navigation_statistics:
                    fragmentTransaction.replace(R.id.main_container, new StatisticsFragment()).commit();
                    return true;
            }
            return false;
        }
    };

}
