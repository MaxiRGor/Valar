package harelchuk.maxim.quizwithmoxy.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import harelchuk.maxim.quizwithmoxy.R;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Map<String, Integer>> data;
    private Drawable[] drawablesGAC;
    private static ClickListener clickListener;
    private Drawable[] buttonBackground;

    public DataAdapter(Context context, ArrayList<Map<String, Integer>> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.drawablesGAC = new Drawable[3];
        this.drawablesGAC[0] = context.getResources().getDrawable(R.drawable.ic_money_dragon);
        this.drawablesGAC[1] = context.getResources().getDrawable(R.drawable.ic_money_deer);
        this.drawablesGAC[2] = context.getResources().getDrawable(R.drawable.ic_money_warior);
        this.buttonBackground = new Drawable[data.size()];
        int theme = UserDataSingleton.getInstance().getCurrent_theme();
        if (theme == 0) {
            for(int i=0; i<data.size();i++){
                this.buttonBackground[i] = context.getResources().getDrawable(R.drawable.targ_button_selector);
            }
        }
        if (theme == 1) {
            for(int i=0; i<data.size();i++){
                this.buttonBackground[i] = context.getResources().getDrawable(R.drawable.stark_button_selector);
            }
        }
        if (theme == 2) {
            for(int i=0; i<data.size();i++){
                this.buttonBackground[i] = context.getResources().getDrawable(R.drawable.lann_button_selector);
            }
        }
        if (theme == 3) {
            for(int i=0; i<data.size();i++){
                this.buttonBackground[i] = context.getResources().getDrawable(R.drawable.night_button_selector);
            }
        }
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.tune_game_item_level, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        String level = String.valueOf(data.get(position).get("level"));
        String cost = String.valueOf(data.get(position).get("cost"));
        String reward = String.valueOf(data.get(position).get("reward"));
        int coin = data.get(position).get("coin");
        holder.rewardTV.setText(reward);
        holder.levelTV.setText(level);
        holder.costTV.setText(cost);
        holder.coinImage.setBackground(drawablesGAC[coin]);
        holder.background.setBackground(buttonBackground[position]);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView coinImage, background;
        final TextView levelTV, costTV, rewardTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.coinImage = itemView.findViewById(R.id.item_level_coinIV);
            this.levelTV = itemView.findViewById(R.id.item_level_nomTV);
            this.costTV = itemView.findViewById(R.id.item_level_cost_nomTV);
            this.rewardTV = itemView.findViewById(R.id.item_level_rewardTV);
            this.background = itemView.findViewById(R.id.itemBackground);
        }

        @Override
        public void onClick(View v) {
            Log.d("myLogs", "here");
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        DataAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onItemClick(int position, View v);
        //void onItemLongClick(int position, View v);
    }
}
