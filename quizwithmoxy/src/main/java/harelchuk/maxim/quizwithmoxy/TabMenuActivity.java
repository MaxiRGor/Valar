package harelchuk.maxim.quizwithmoxy;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import harelchuk.maxim.quizwithmoxy.fragments.SettingsFragment;
import harelchuk.maxim.quizwithmoxy.fragments.StatisticsFragment;
import harelchuk.maxim.quizwithmoxy.fragments.TuneGameFragment;

public class TabMenuActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_menu);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        final ImageView imageView = findViewById(R.id.imageViewMenu);

        Picasso.get().
                load(R.drawable.fon)
                .resize(width / 2, height / 2)
                .placeholder(R.drawable.blackscreen)
                .into(imageView);


        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction
                    .add(R.id.main_container, new TuneGameFragment()).commit();
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
