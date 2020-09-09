package com.frank.gadsleaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LearningRecyclerAdapter extends RecyclerView.Adapter<LearningRecyclerAdapter.ViewHolder>{

    ArrayList<LearningLeader> mLearningLeaders;
    Context mContext;

    public LearningRecyclerAdapter(Context context, ArrayList<LearningLeader> learningLeaders) {
        mContext = context;
        mLearningLeaders = learningLeaders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.learning_leader, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LearningLeader learningLeader = mLearningLeaders.get(position);
        holder.bind(learningLeader);

    }

    @Override
    public int getItemCount() {
        return mLearningLeaders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvDetails;
        ImageView badge;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            badge = itemView.findViewById(R.id.badge);
        }

        public void bind (LearningLeader leader) {
            //ImageView view = new ImageView(mContext);
            tvName.setText(leader.getName());
            badge.setImageDrawable(leader.loadImage(badge, leader.badgeUrl));
            tvDetails.setText(new StringBuilder().append(leader.getHours()).append(" learning hours, ").append(leader.getCountry()).toString());
        }
    }
}
