package harelchuk.maxim.quizwithmoxy.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import harelchuk.maxim.quizwithmoxy.model.UserDataSingleton;
import harelchuk.maxim.quizwithmoxy.view.SetEmblemView;

@InjectViewState
public class SetEmblemPresenter extends MvpPresenter<SetEmblemView> {

    public SetEmblemPresenter() {
        fillEmblems();
    }

    private void fillEmblems() {
        getViewState().fillEmblems(UserDataSingleton.getInstance().getCurrent_theme(),
                UserDataSingleton.getInstance().isIs_skin_targar(),
                UserDataSingleton.getInstance().isIs_skin_stark(),
                UserDataSingleton.getInstance().isIs_skin_lann(),
                UserDataSingleton.getInstance().isIs_skin_night());
    }

}
