package harelchuk.maxim.quizwithmoxy.fragments;

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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import harelchuk.maxim.quizwithmoxy.InPlayActivity;
import harelchuk.maxim.quizwithmoxy.R;
import harelchuk.maxim.quizwithmoxy.presenter.TuneGamePresenter;
import harelchuk.maxim.quizwithmoxy.view.TuneGameView;

public class TuneGameFragment extends MvpAppCompatFragment implements TuneGameView {

    @InjectPresenter
    TuneGamePresenter gamePresenter;

    SharedPreferences sharedPreferences;

    private TextView chooseLevelTV;
    private View tuneGameMenuView;
    private View tempV;
    private ViewGroup levelListVG;
    private Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup mainContainerVG = getActivity().findViewById(R.id.main_container);
        mainContainerVG.removeAllViews();
        tuneGameMenuView = inflater.inflate(R.layout.tune_level_list_menu, mainContainerVG, false);
        levelListVG = tuneGameMenuView.findViewById(R.id.level_list_frame);
        context = getContext();
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.from_bottom_to_top);
        levelListVG.startAnimation(animation);
        return tuneGameMenuView;
    }

    @Override
    public void fillLevelList(int levels[], int[] costs, int[] reward) {

        final String ATTRIBUTE_NAME_LEVEL = "level";
        final String ATTRIBUTE_NAME_COST = "cost";
        final String ATTRIBUTE_NAME_REWARD= "reward";
        final String ATTRIBUTE_NAME_COIN= "coin";


        tempV = LayoutInflater.from(context).inflate(R.layout.tune_game_list, levelListVG, false);
        levelListVG.addView(tempV);

        ListView listView = tuneGameMenuView.findViewById(R.id.listView);

        ArrayList<Map<String, Object>> data = new ArrayList<>(levels.length);

        Map<String, Object> map;

        for (int i = 0; i < levels.length; i++) {
            map = new HashMap<>();
            map.put(ATTRIBUTE_NAME_LEVEL, levels[i]);
            map.put(ATTRIBUTE_NAME_COST, costs[i]);
            map.put(ATTRIBUTE_NAME_REWARD, reward[i]);
            if(levels[i]==1 || levels[i]==2 || levels[i]==3) {
                map.put(ATTRIBUTE_NAME_COIN, R.drawable.ic_money_warior);
            }
            if(levels[i]==4 || levels[i]==5 || levels[i]==6) {
                map.put(ATTRIBUTE_NAME_COIN, R.drawable.ic_money_deer);
            }
            if(levels[i]==7 || levels[i]==8 || levels[i]==9 || levels[i]==10) {
                map.put(ATTRIBUTE_NAME_COIN, R.drawable.ic_money_dragon);
            }
            data.add(map);
        }

        String from[] = {ATTRIBUTE_NAME_LEVEL, ATTRIBUTE_NAME_COST,ATTRIBUTE_NAME_REWARD,ATTRIBUTE_NAME_COIN};
        int to[] = {R.id.item_level_nomTV, R.id.item_level_quens_nomTV, R.id.item_level_rewardTV, R.id.item_level_coinIV};

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
