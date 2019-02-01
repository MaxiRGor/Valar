package harelchuk.maxim.counter2fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.MvpFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

public class CounterFragment extends MvpAppCompatFragment implements CounterView {

    @InjectPresenter (type = PresenterType.GLOBAL, tag = "blahblah")
    CounterPresenter mCounterPresenter;
    private TextView mCounterTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState){
        return inflater.inflate(R.layout.fragment_counter,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        view.setBackgroundColor(getArguments().getInt("argColor"));
        mCounterTextView = (TextView) getView().findViewById(R.id.count_text);
        view.findViewById(R.id.plus_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounterPresenter.onPlusClick();
            }
        });
    }

    @Override
    public void showCount(int count){
        mCounterTextView.setText(String.valueOf(count));
    }
}
