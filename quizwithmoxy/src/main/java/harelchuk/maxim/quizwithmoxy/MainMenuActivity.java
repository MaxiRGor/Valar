package harelchuk.maxim.quizwithmoxy;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class MainMenuActivity extends MvpAppCompatActivity implements MainMenuView {

    @InjectPresenter
    MainMenuPresenter mainMenuPresenter;

    private TextView gameTV;
    private TextView statisticsTV;
    private TextView settingsTV;
    private View nextView;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        // only first time
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();

        gameTV = findViewById(R.id.gameTV);
        statisticsTV = findViewById(R.id.statisticsTV);
        settingsTV = findViewById(R.id.settingsTV);

        setListeners();
    }

    private void setListeners() {

        gameTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGame();
            }
        });

        statisticsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToStatistics();
            }
        });

        settingsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSettings();
            }
        });
    }


    @Override
    public void goToGame() {
        Log.d("myLogs", "From MM to Game");
        //ViewGroup rootView = findViewById(R.id.main_menu);
        //nextView = LayoutInflater.from(this).inflate(R.layout.game_menu,rootView,false);
        //rootView.addView(nextView);
        Intent intent = new Intent(MainMenuActivity.this, GameActivity.class);
        startActivity(intent);
        //Toast.makeText(this,"GameActivity",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToStatistics() {
        Log.d("myLogs", "Stat");

        alertDialog = new AlertDialog.Builder(this)
                .setTitle("Statistics")
                .setMessage("Not Finished")
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    @Override
    public void goToSettings() {
        Log.d("myLogs", "Set");

        alertDialog = new AlertDialog.Builder(this)
                .setTitle("Settings")
                .setMessage("Not Finished")
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}
