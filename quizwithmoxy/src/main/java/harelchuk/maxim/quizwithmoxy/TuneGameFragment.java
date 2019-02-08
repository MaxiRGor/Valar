package harelchuk.maxim.quizwithmoxy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TuneGameFragment extends MvpAppCompatFragment implements TuneGameView {

    @InjectPresenter
    TuneGamePresenter gamePresenter;

    SharedPreferences sharedPreferences;

    private TextView chooseLevelTV;
    private View setGameMenuView;
    private View tempV;
    private ViewGroup levelListVG;
    private ViewGroup mainConteinerVG;
    private Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainConteinerVG = getActivity().findViewById(R.id.main_container);
        mainConteinerVG.removeAllViews();
        setGameMenuView = inflater.inflate(R.layout.tune_level_list_menu, mainConteinerVG, false);
        levelListVG = setGameMenuView.findViewById(R.id.level_list_frame);
        context = getContext();
        return setGameMenuView;
    }

    @Override
    public void chooseLevel(int levels[], int[] numberOfQuestions) {

        final String ATTRIBUTE_NAME_LEVEL = "level";
        final String ATTRIBUTE_NAME_NUMBER = "number";

        tempV = LayoutInflater.from(context).inflate(R.layout.tune_game_list, levelListVG, false);
        levelListVG.addView(tempV);

        ListView listView = setGameMenuView.findViewById(R.id.listView);

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

        SimpleAdapter simpleAdapter = new SimpleAdapter(context, data, R.layout.tune_item_level, from, to);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("myLogs", "id= " + String.valueOf(id) + "; position= " + String.valueOf(position));
                Intent intent = new Intent(context, InPlayActivity.class);
                intent.putExtra("level", position);


                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("level", position);
                editor.commit();

                //sharedPreferences = context.getPreferences(MODE_PRIVATE);
                startActivity(intent);
            }
        });
    }

}
