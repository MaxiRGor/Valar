package harelchuk.maxim.quizwithmoxy.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import harelchuk.maxim.quizwithmoxy.R;

public class LevelsDataAdapter extends RecyclerView.Adapter<LevelsDataAdapter.ViewHolder> {

    private LayoutInflater inflater;

    private static ClickListener clickListener;

    private Drawable[] drawablesGAC;
    private Drawable[] buttonBackgrounds;

    private int[] levels;
    private int[] rewards;
    private int[] costs;

    public LevelsDataAdapter(Context context, int[] levels, int[] rewards, int[] costs, int[] coinImagesInt) {

        this.inflater = LayoutInflater.from(context);
        this.drawablesGAC = new Drawable[levels.length];
        for(int i = 0; i<levels.length;i++){
            this.drawablesGAC[i]=context.getResources().getDrawable(coinImagesInt[i]);
        }

        int theme = UserDataSingleton.getInstance().getCurrent_theme();

        this.levels = levels;
        this.rewards = rewards;
        this.costs = costs;

        this.buttonBackgrounds = new Drawable[levels.length];

        if (theme == 0) {
            for (int i = 0; i < levels.length; i++) {
                this.buttonBackgrounds[i] = context.getResources().getDrawable(R.drawable.targ_button_selector);
            }
        }
        if (theme == 1) {
            for (int i = 0; i < levels.length; i++) {
                this.buttonBackgrounds[i] = context.getResources().getDrawable(R.drawable.stark_button_selector);
            }
        }
        if (theme == 2) {
            for (int i = 0; i < levels.length; i++) {
                this.buttonBackgrounds[i] = context.getResources().getDrawable(R.drawable.lann_button_selector);
            }
        }
        if (theme == 3) {
            for (int i = 0; i < levels.length; i++) {
                this.buttonBackgrounds[i] = context.getResources().getDrawable(R.drawable.night_button_selector);
            }
        }
    }

    @NonNull
    @Override
    public LevelsDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.tune_game_item_level, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LevelsDataAdapter.ViewHolder holder, int position) {
        String level = String.valueOf(levels[position]);
        String cost = String.valueOf(costs[position]);
        String reward = String.valueOf(rewards[position]);
        holder.rewardTV.setText(reward);
        holder.levelTV.setText(level);
        holder.costTV.setText(cost);
        holder.coinImage.setBackground(drawablesGAC[position]);
        holder.background.setBackground(buttonBackgrounds[position]);
        setAnimation(holder.coinImage);
    }

    @Override
    public int getItemCount() {
        return levels.length;
    }


    private void setAnimation(View viewToAnimate)
    {
            Animation animation = AnimationUtils.loadAnimation(AppForContext.getContext(), R.anim.from_right_to_center);
            viewToAnimate.startAnimation(animation);
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView coinImage, background;
        final TextView levelTV, costTV, rewardTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.background = itemView.findViewById(R.id.itemBackground);
            this.coinImage = itemView.findViewById(R.id.item_level_coinIV);
            this.levelTV = itemView.findViewById(R.id.item_level_nomTV);
            this.costTV = itemView.findViewById(R.id.item_level_cost_nomTV);
            this.rewardTV = itemView.findViewById(R.id.item_level_rewardTV);
        }

        @Override
        public void onClick(View v) {
            Log.d("myLogs", "here");
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        LevelsDataAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onItemClick(int position, View v);
        //void onItemLongClick(int position, View v);
    }
}
