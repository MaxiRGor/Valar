package harelchuk.maxim.quizwithmoxy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.squareup.picasso.Picasso;

import harelchuk.maxim.quizwithmoxy.R;

public class SetEmblemFragment extends MvpAppCompatFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.set_emblem_fragment, container, false);
        function(view);
        return view;
    }

    private void function(View view) {
        TextView starksTV = view.findViewById(R.id.emblemUserSkinStarksTV);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ImageView imageView = getActivity().
                        findViewById(R.id.imageViewMenu);
                Picasso.get().
                        load(R.drawable.background_starks)
                        .fit()
                        .placeholder(R.drawable.blackscreen)
                        .into(imageView);
            }
        };

        starksTV.setOnClickListener(onClickListener);


    }
}
