package harelchuk.maxim.quizwithmoxy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import harelchuk.maxim.quizwithmoxy.R;

public class SetEmblemFragment extends MvpAppCompatFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.set_emblem_fragment, container, false);
    }
}
