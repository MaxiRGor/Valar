package harelchuk.maxim.quizwithmoxy.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

import harelchuk.maxim.quizwithmoxy.InPlayActivity;
import harelchuk.maxim.quizwithmoxy.R;
import harelchuk.maxim.quizwithmoxy.model.CoinValuesSingleton;
import harelchuk.maxim.quizwithmoxy.model.DataAdapter;
import harelchuk.maxim.quizwithmoxy.model.UserDataSingleton;
import harelchuk.maxim.quizwithmoxy.presenter.TuneGamePresenter;
import harelchuk.maxim.quizwithmoxy.view.TuneGameView;

import static android.view.View.inflate;

public class TuneGameFragment extends MvpAppCompatFragment implements TuneGameView {

    @InjectPresenter
    TuneGamePresenter gamePresenter;

    private View tuneGameMenuView;
    private ImageView moneyImage;
    private RecyclerView recyclerView;

    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.context = getContext();
        this.tuneGameMenuView = inflater.inflate(R.layout.tune_game_empty, container, false);
        this.recyclerView = tuneGameMenuView.findViewById(R.id.recyclerView);
        this.moneyImage = tuneGameMenuView.findViewById(R.id.tuneGameMoneyBackground);

        int theme = UserDataSingleton.getInstance().getCurrent_theme();

        if (theme == 0) {
            this.moneyImage.setBackground(getResources().getDrawable(R.drawable.targ_window));
            this.recyclerView.setBackground(getResources().getDrawable(R.drawable.targ_window));
        }
        if (theme == 1) {
            this.moneyImage.setBackground(getResources().getDrawable(R.drawable.stark_window));
            this.recyclerView.setBackground(getResources().getDrawable(R.drawable.stark_window));
        }
        if (theme == 2) {
            this.moneyImage.setBackground(getResources().getDrawable(R.drawable.lann_window));
            this.recyclerView.setBackground(getResources().getDrawable(R.drawable.lann_window));
        }
        if (theme == 3) {
            this.moneyImage.setBackground(getResources().getDrawable(R.drawable.night_window));
            this.recyclerView.setBackground(getResources().getDrawable(R.drawable.night_window));
        }
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
    public void fillLevelList(int[] levels, int[] rewards, int[] costs, int[] coinImagesInt) {
        DataAdapter adapter = new DataAdapter(getContext(), levels, rewards, costs, coinImagesInt);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new DataAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                checkIfAvailable(position);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void checkIfAvailable(int position) {
        int cost =  CoinValuesSingleton.getInstance().getCostCoins()[position];
        if (position == 3 || position == 4 || position == 5) {
            cost *= 56;
        }
        if (position == 6 || position == 7 || position == 8 || position == 9) {
            cost *= 56 * 210;
        }
        if (UserDataSingleton.getInstance().getUser_money() >= cost) {
            UserDataSingleton.getInstance().setChosen_level(position + 1);
            gamePresenter.subtractMoney(cost);
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
        final TextView coins_GD = tuneGameMenuView.findViewById(R.id.userGDTV);
        final TextView coins_AD = tuneGameMenuView.findViewById(R.id.userADTV);
        final TextView coins_CP = tuneGameMenuView.findViewById(R.id.userCPTV);

        coins_AD.startAnimation(animation);
        coins_CP.startAnimation(animation);
        coins_GD.startAnimation(animation);
        coins_GD.setText(String.valueOf(coins_GAC[0]));
        coins_AD.setText(String.valueOf(coins_GAC[1]));
        coins_CP.setText(String.valueOf(coins_GAC[2]));

        int theme = UserDataSingleton.getInstance().getCurrent_theme();

        Drawable alertDialogButtonImage1 = null;
        Drawable alertDialogWindowImage1 = null;

        if (theme == 0) {
            alertDialogButtonImage1 = getResources().getDrawable(R.drawable.targ_button_selector);
            alertDialogWindowImage1 = getResources().getDrawable(R.drawable.targ_window);
        }

        if (theme == 1) {
            alertDialogButtonImage1 = getResources().getDrawable(R.drawable.stark_button_selector);
            alertDialogWindowImage1 = getResources().getDrawable(R.drawable.stark_window);
        }

        if (theme == 2) {
            alertDialogButtonImage1 = getResources().getDrawable(R.drawable.lann_button_selector);
            alertDialogWindowImage1 = getResources().getDrawable(R.drawable.lann_window);
        }

        if (theme == 3) {
            alertDialogButtonImage1 = getResources().getDrawable(R.drawable.night_button_selector);
            alertDialogWindowImage1 = getResources().getDrawable(R.drawable.night_window);
        }

        final Drawable alertDialogButtonImage = alertDialogButtonImage1;
        final Drawable alertDialogWindowImage = alertDialogWindowImage1;


        final long[] coinsInCPCAG = {coins_GAC[0] * 210 * 56 + coins_GAC[1] * 56 + coins_GAC[2],
                coins_GAC[0] * 210 + coins_GAC[1],
                coins_GAC[0]};

        final String one = String.valueOf(coinsInCPCAG[2]);

        final String two = String.valueOf(coinsInCPCAG[1]);

        final String three = String.valueOf(coinsInCPCAG[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        View dialogView = inflate(context, R.layout.alertdialog_money_describtion, null);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();

        ImageView background = dialogView.findViewById(R.id.alertDialogBackgroundImage);
        background.setBackground(alertDialogWindowImage);

        Button closeDialogButton = dialogView.findViewById(R.id.alert_dialog_button);
        closeDialogButton.setBackground(alertDialogButtonImage);
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
