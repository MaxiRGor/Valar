package harelchuk.maxim.quizwithmoxy;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class MainMenuPresenter extends MvpPresenter<MainMenuView> {

    public MainMenuPresenter() {
        Log.d("myLogs", "MainMenuPresenter const");
    }

/*
    @Override
    public static void goToGame() {
        Log.d("myLogs","SetGameActivity");
    }

    @Override
    public void goToStatistics() {
        Log.d("myLogs","Stat");
    }

    @Override
    public void goToSettings() {
        Log.d("myLogs","Set");
    }
    */
}
