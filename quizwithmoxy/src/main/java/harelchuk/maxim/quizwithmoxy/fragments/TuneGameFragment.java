package harelchuk.maxim.quizwithmoxy.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.Map;

import harelchuk.maxim.quizwithmoxy.InPlayActivity;
import harelchuk.maxim.quizwithmoxy.R;
import harelchuk.maxim.quizwithmoxy.model.DataAdapter;
import harelchuk.maxim.quizwithmoxy.model.UserDataSingleton;
import harelchuk.maxim.quizwithmoxy.presenter.TuneGamePresenter;
import harelchuk.maxim.quizwithmoxy.view.TuneGameView;

import static android.view.View.inflate;

public class TuneGameFragment extends MvpAppCompatFragment implements TuneGameView {

    @InjectPresenter
    TuneGamePresenter gamePresenter;

    private View tuneGameMenuView;

    private Context context;
    private long[] coinsGAC;
    private int[] level_costs;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.context = getContext();
        //ViewGroup mainContainerVG = getActivity().findViewById(R.id.main_container);
        //mainContainerVG.removeAllViews();
        tuneGameMenuView = inflater.inflate(R.layout.tune_game_empty, container, false);

        this.coinsGAC = new long[2];
        this.level_costs = new int[9];
        return tuneGameMenuView;
    }

    @Override
    public void onStart() {
        super.onStart();
        gamePresenter.showUsersMoneyAndBF();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void fillLevelList(ArrayList<Map<String, Integer>> data, int[] costs) {
        level_costs = costs;
        RecyclerView recyclerView = tuneGameMenuView.findViewById(R.id.recyclerView);
        DataAdapter adapter = new DataAdapter(getContext(), data);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new DataAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                checkIfAvailable(position);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //recyclerView.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.from_bottom_to_center));
    }

    private void checkIfAvailable(int position) {
        int level = position + 1;
        UserDataSingleton.getInstance().setChosen_level(level);
        long cost = level_costs[position];
        if (level == 4 || level == 5 || level == 6) {
            cost *= 56;
        }
        if (level == 7 || level == 8 || level == 9 || level == 10) {
            cost *= 56 * 210;
        }
        long money = coinsGAC[2] + coinsGAC[1] * 56 + coinsGAC[0] * 56 * 210;
        if (money >= cost) {
            gamePresenter.writeOff(cost);
            startGame();
        }
    }

    private void startGame() {
        Intent intent = new Intent(context, InPlayActivity.class);
        startActivity(intent);
    }

    @Override
    public void fillCoins(long[] coins_GAC) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.from_top_to_center);
        coinsGAC = coins_GAC;
        final TextView coins_GD = tuneGameMenuView.findViewById(R.id.userGDTV);
        final TextView coins_AD = tuneGameMenuView.findViewById(R.id.userADTV);
        final TextView coins_CP = tuneGameMenuView.findViewById(R.id.userCPTV);
        //ImageView booksFilms = tuneGameMenuView.findViewById(R.id.tuneBookFilmIconIV);
        ImageView moneyImage = tuneGameMenuView.findViewById(R.id.windowUserMoneyIV);
        coins_AD.startAnimation(animation);
        coins_CP.startAnimation(animation);
        coins_GD.startAnimation(animation);
        coins_GD.setText(String.valueOf(coins_GAC[0]));
        coins_AD.setText(String.valueOf(coins_GAC[1]));
        coins_CP.setText(String.valueOf(coins_GAC[2]));

        final long[] coinsInCPCAG = {coinsGAC[0] * 210 * 56 + coinsGAC[1] * 56 + coinsGAC[2],
                coinsGAC[0] * 210 + coinsGAC[1],
                coinsGAC[0]};

        final String one = String.valueOf(coinsInCPCAG[2]);

        final String two = String.valueOf(coinsInCPCAG[1]);

        final String three = String.valueOf(coinsInCPCAG[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflate(context,R.layout.alertdialog_money_describtion, null);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        Button closeDialogButton = dialogView.findViewById(R.id.alert_dialog_button);
        TextView titleTV = dialogView.findViewById(R.id.alert_dialog_text_title_TV);
        titleTV.setText(getResources().getString(R.string.money));
        TextView textTV = dialogView.findViewById(R.id.alert_dialog_text_TV);
        textTV.setText(getResources().getString(R.string.youHaveMoney));

        TextView coinsGD = dialogView.findViewById(R.id.alertUsersGD);
        coinsGD.setText(one);

        TextView coinsAD = dialogView.findViewById(R.id.alertUsersAD);
        coinsAD.setText(two);

        TextView coinsCP = dialogView.findViewById(R.id.alertUsersCP);
        coinsCP.setText(three);


        closeDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.getWindow().setDimAmount(0.8f);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;


        moneyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

}
