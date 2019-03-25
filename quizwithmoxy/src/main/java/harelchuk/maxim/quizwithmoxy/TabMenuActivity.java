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
import harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer;

public class TabMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_menu);

        SharedPreferencesInitializer.setSharedPreferencesMoney(getApplicationContext());
        SharedPreferencesInitializer.setSharedPreferencesUser(getApplicationContext());

        final ImageView imageView = findViewById(R.id.imageViewMenu);

        Picasso.get().
                load(R.drawable.background_targ)
                .fit()
                .placeholder(R.drawable.blackscreen)
                .into(imageView);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction
                    .add(R.id.main_container, new TuneGameFragment()).commit();
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setItemIconTintList(null);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.from_bottom_to_center,R.anim.from_center_to_bottom);

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
