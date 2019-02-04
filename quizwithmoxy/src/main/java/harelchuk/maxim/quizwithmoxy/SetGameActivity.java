package harelchuk.maxim.quizwithmoxy;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SetGameActivity extends MvpAppCompatActivity implements SetGameView {

    @InjectPresenter
    SetGamePresenter gamePresenter;

    private TextView chooseLevelTV;
    private TextView chooseCategoryTV;
    private TextView chooseBookTV;
    private TextView chooseSeriesTV;
    private AlertDialog alertDialog;
    private ViewGroup rootGroup;
    private View tempView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.set_game_empty_frame);
        rootGroup = findViewById(R.id.frameLayout);

        if (savedInstanceState == null) {
            tempView = LayoutInflater.from(this).inflate(R.layout.set_game_menu, rootGroup, false);
            rootGroup.addView(tempView);
            setListeners();
        }
    }

    private void setListeners() {

        chooseLevelTV = findViewById(R.id.chooseLevel);
        chooseCategoryTV = findViewById(R.id.chooseCategory);
        chooseBookTV = findViewById(R.id.chooseBook);
        chooseSeriesTV = findViewById(R.id.chooseSeries);

        chooseLevelTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePresenter.getNumberOfQuestionForLevel.execute();
            }
        });

        chooseCategoryTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseCategory();
            }
        });

        chooseBookTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseBook();
            }
        });

        chooseSeriesTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseSeries();
            }
        });

    }

    @Override
    public void chooseBook() {
        alertDialog = new AlertDialog.Builder(this)
                .setTitle("Book")
                .setMessage("Not Finished")
                .setPositiveButton(android.R.string.ok, null)
                .show();

    }

    @Override
    public void chooseSeries() {
        alertDialog = new AlertDialog.Builder(this)
                .setTitle("Series")
                .setMessage("Not Finished")
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    @Override
    public void chooseLevel(int levels[], int[] numberOfQuestions) {

        final String ATTRIBUTE_NAME_LEVEL = "level";
        final String ATTRIBUTE_NAME_NUMBER = "number";

        rootGroup.removeAllViews();
        tempView = LayoutInflater.from(this).inflate(R.layout.set_game_list, rootGroup, false);
        rootGroup.addView(tempView);

        ListView listView = tempView.findViewById(R.id.listView);


        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,levels);


        ArrayList<Map<String, Object>> data = new ArrayList<>(levels.length);

        Map<String, Object> map;

        for (int i = 0; i < levels.length; i++) {
            map = new HashMap<>();
            map.put(ATTRIBUTE_NAME_LEVEL, levels[i]);
            map.put(ATTRIBUTE_NAME_NUMBER, numberOfQuestions[i]);
            data.add(map);
        }

        String from[] = {ATTRIBUTE_NAME_LEVEL, ATTRIBUTE_NAME_NUMBER};
        int to[] = {R.id.item_level_nomTV, R.id.item_level_quens_nomTV};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item_level, from, to);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("myLogs", "id= " + String.valueOf(id) + "; position= " + String.valueOf(position));
                Intent intent = new Intent(SetGameActivity.this, InPlayActivity.class);
                intent.putExtra("level", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public void chooseCategory() {
        alertDialog = new AlertDialog.Builder(this)
                .setTitle("Category")
                .setMessage("Not Finished")
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}
